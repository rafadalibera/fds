package com.fds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import com.yelp.v2.Business;
import com.yelp.v2.YelpSearchResult;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class Locator extends ListActivity {
	
	double latitude;
	double longitude;
	
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_REVIEW = "review";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		YelpSearchResult searchResult;
		
		ArrayList<HashMap<String, String>> locationList = new ArrayList<HashMap<String, String>>();
		
		latitude = 0.0;
		longitude = 0.0;
		
		GPSTracker gps = new GPSTracker(this);
		if(gps.canGetLocation()){
			Log.d("gps", "enable");
			latitude = gps.getLatitude(); // returns latitude
			longitude = gps.getLongitude(); // returns longitude
		}
		
		Log.d("Latitude", Double.toString(latitude));
		Log.d("Longitude", Double.toString(longitude));
		
		YelpSearch search = new YelpSearch();
		search.execute("restaurants", "30.361471", "-87.164326" );
		
		try {
			searchResult = search.get();
			
			Log.d("TAG", "Your search found " + searchResult.getTotal() + " results.");
			Log.d("TAG", "Yelp returned " + searchResult.getBusinesses().size() + " businesses in this request.");
			
			for(Business biz : searchResult.getBusinesses()) {
				String id = biz.getId();
				String name = biz.getName();
				int review = biz.getReviewCount();
				String phone = biz.getPhone();
/*
				for(String address : biz.getLocation().getAddress()) {
					Log.d("TAG", "  " + address);
					//System.out.println("  " + address);
				}
*/				
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();
 
                // adding each child node to HashMap key => value
                map.put(TAG_ID, id);
                map.put(TAG_NAME, name);
                map.put(TAG_REVIEW, Integer.toString(review));
                map.put(TAG_PHONE, phone);
                
                // adding HashList to ArrayList
                locationList.add(map);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        /**
         * Updating parsed JSON data into ListView
         * */
        ListAdapter adapter = new SimpleAdapter(this, locationList,
                R.layout.list_item,
                new String[] { TAG_NAME, TAG_REVIEW, TAG_PHONE }, new int[] {
                        R.id.name, R.id.email, R.id.mobile });
 
        setListAdapter(adapter);
 
        // selecting single ListView item
        ListView lv = getListView();
/*		
		Context context = getApplicationContext();
		CharSequence text = Double.toString(latitude) + "\n" + Double.toString(longitude) ;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
*/
	}


}
