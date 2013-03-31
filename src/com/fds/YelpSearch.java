package com.fds;

import org.json.JSONArray;
import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;
import com.yelp.v2.Business;
import com.yelp.v2.YelpSearchResult;
import com.yelp.v2.YelpV2API;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;

import android.os.AsyncTask;
import android.util.Log;

public class YelpSearch extends AsyncTask<String, Void, YelpSearchResult>{

	private YelpSearchResult listPlaces;
	
	@Override
	protected YelpSearchResult doInBackground(String... params) {
		// TODO Auto-generated method stub
		String category = params[0];
		String latitude = params[1];
		String longitude = params[2];
		
		YelpSearchResult places = null;
		
		// Define your keys, tokens and secrets.  These are available from the Yelp website.  
		String CONSUMER_KEY = "3C5eXVeaojxIZaIvGtYcVA";
		String CONSUMER_SECRET = "PH-OzSS3sC-4N5tkEDzhv75Pro8";
		String TOKEN = "M5vfsNXev6H9JxFcePbqTDIsfztL2Alr";
		String TOKEN_SECRET = "ZFJUsp57PR9oXWHwzJkBQeY2iXM";
/*
		// Define your keys, tokens and secrets.  These are available from the Yelp website.  
		String CONSUMER_KEY = "PUT YOUR CONSUMER KEY HERE";
		String CONSUMER_SECRET = "PUT YOUR CONSUMER SECRET HERE";
		String TOKEN = "PUT YOUR TOKEN HERE";
		String TOKEN_SECRET = "PUT YOUR TOKEN SECRET HERE";
*/
		// Execute a signed call to the Yelp service.  
		OAuthService service = new ServiceBuilder().provider(YelpV2API.class).apiKey(CONSUMER_KEY).apiSecret(CONSUMER_SECRET).build();
		Token accessToken = new Token(TOKEN, TOKEN_SECRET);
		OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
		request.addQuerystringParameter("ll", latitude + "," + longitude);
		request.addQuerystringParameter("category", category);
		service.signRequest(accessToken, request);
		Response response = request.send();
		String rawData = response.getBody();
		
		// Sample of how to turn that text into Java objects.  
		try {
			places = new Gson().fromJson(rawData, YelpSearchResult.class);
			
			Log.d("TAG", "Your search found " + places.getTotal() + " results.");
			Log.d("TAG", "Yelp returned " + places.getBusinesses().size() + " businesses in this request.");
			//System.out.println("Your search found " + places.getTotal() + " results.");
			//System.out.println("Yelp returned " + places.getBusinesses().size() + " businesses in this request.");
			//System.out.println();
			
			for(Business biz : places.getBusinesses()) {
				Log.d("TAG", biz.getName());
				//System.out.println(biz.getName());
				for(String address : biz.getLocation().getAddress()) {
					Log.d("TAG", "  " + address);
					//System.out.println("  " + address);
				}
				Log.d("TAG", "  " + biz.getLocation().getCity());
				Log.d("TAG", biz.getUrl());

				//System.out.print("  " + biz.getLocation().getCity());
				//System.out.println(biz.getUrl());
				//System.out.println();
			}
			
			
		} catch(Exception e) {
			Log.d("error", "Error, could not parse returned data!");
			Log.d("error", rawData);
			//System.out.println("Error, could not parse returned data!");
			//System.out.println(rawData);			
		}

		return places;
	}
	
	@Override
	protected void onPostExecute(YelpSearchResult result) {
		this.listPlaces = result;
	}
	
}
