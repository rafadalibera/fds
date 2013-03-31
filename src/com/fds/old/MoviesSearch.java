package com.fds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MoviesSearch extends ListActivity{
	
	TextView title;
	TextView year;
	TextView mpaa;
	TextView synopsis;
	
    // url to make request
    private static String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=2kmkxmvk58re97tw6w9a3qef";
    //private static String url = "http://api.androidhive.info/contacts/";
    
    	
    // JSON Node names
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_YEAR = "year";
    private static final String TAG_MPAA = "mpaa_rating";
    private static final String TAG_CRITICS = "critics_consensus";
    private static final String TAG_RUNTIME = "runtime";
    private static final String TAG_POSTERS = "posters";
    private static final String TAG_POSTERS_THUMBNAIL = "thumbnail";
    private static final String TAG_POSTERS_PROFILE = "profile";
    private static final String TAG_POSTERS_DETAILED = "detailed";
    private static final String TAG_POSTERS_ORIGINAL = "original";
    private static final String TAG_SYNOPSIS = "synopsis";
    private static final String TAG_RATINGS = "ratings";
    private static final String TAG_RATINGS_SCORE = "critics_score";
    
 
    // contacts JSONArray
    JSONArray movies = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        // Hashmap for ListView
        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
 /*
        // Creating JSON Parser instance
        JSONParser jParser = new JSONParser();
 
        // getting JSON string from URL
        JSONObject json = jParser.getJSONFromUrl(url);
 */

        JSONSearch search = new JSONSearch();
        search.execute(url, TAG_MOVIES);
        
        Log.d("TAG", "passed here");
        
        try {
			movies = search.get();
			Log.d("TAG", Integer.toString(movies.length()));
			
			for(int i = 0; i < movies.length(); i++){
                JSONObject c = movies.getJSONObject(i);
 
                // Storing each json item in variable
                String id = c.getString(TAG_ID);
                String title = c.getString(TAG_TITLE);
                String year = c.getString(TAG_YEAR);
                String mpaa = c.getString(TAG_MPAA);
                //String critics = c.getString(TAG_CRITICS);
                String runtime = c.getString(TAG_RUNTIME);
                String synopsis = c.getString(TAG_SYNOPSIS);
 
                // Phone number is agin JSON Object
                JSONObject posters = c.getJSONObject(TAG_POSTERS);
                String thumbnail = posters.getString(TAG_POSTERS_THUMBNAIL);
                String profile = posters.getString(TAG_POSTERS_PROFILE);
                
                JSONObject rating = c.getJSONObject(TAG_RATINGS);
                String score = rating.getString(TAG_RATINGS_SCORE);
 
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();
 
                // adding each child node to HashMap key => value
                map.put(TAG_ID, id);
                map.put(TAG_TITLE, title);
                map.put(TAG_RATINGS_SCORE, score+"/100");
                map.put(TAG_RUNTIME, runtime+"min");
 
                // adding HashList to ArrayList
                contactList.add(map);
            }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
            // Getting Array of Contacts
            //contacts = json.getJSONArray(TAG_CONTACTS);
 
            // looping through All Contacts
            

 
        /**
         * Updating parsed JSON data into ListView
         * */
        ListAdapter adapter = new SimpleAdapter(this, contactList,
                R.layout.list_item,
                new String[] { TAG_TITLE, TAG_RATINGS_SCORE, TAG_RUNTIME }, new int[] {
                        R.id.name, R.id.email, R.id.mobile });
 
        setListAdapter(adapter);
 
        // selecting single ListView item
        ListView lv = getListView();
        /*
        // Launching new screen on Selecting Single ListItem
        lv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
                String cost = ((TextView) view.findViewById(R.id.email)).getText().toString();
                String description = ((TextView) view.findViewById(R.id.mobile)).getText().toString();
 
                // Starting new intent
                Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
                in.putExtra(TAG_NAME, name);
                in.putExtra(TAG_EMAIL, cost);
                in.putExtra(TAG_PHONE_MOBILE, description);
                startActivity(in);
            }
        });
        */
    }
}
//---------------------------------------------------------------
/*
class JSONSearch extends AsyncTask<String, Void, JSONArray> {
	
	private JSONArray returnList;

	@Override
	protected JSONArray doInBackground(String... params) {
		String url = params[0];
		String tag = params[1];
		
	    // contacts JSONArray
	    JSONArray listJSON = null;
		
		// Creating JSON Parser instance
        JSONParser jParser = new JSONParser();
 
        // getting JSON string from URL
        JSONObject json = jParser.getJSONFromUrl(url);
        
        try {
			listJSON = json.getJSONArray(tag);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listJSON;
	}
	
	@Override
	protected void onPostExecute(JSONArray result) {
		this.returnList = result;
	}

}
*/