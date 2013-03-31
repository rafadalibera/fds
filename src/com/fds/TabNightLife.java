package com.fds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import com.yelp.v2.Business;
import com.yelp.v2.YelpSearchResult;

import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TabNightLife extends ListFragment {
	
	double latitude;
	double longitude;
	
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_REVIEW = "review";
	private static final String TAG_URL = "url";
	
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	String url = ((TextView) v.findViewById(R.id.url)).getText().toString();
    	//Log.d("URL", url);
    	
		Intent i = new Intent(getActivity(), DisplayMovie.class);
		i.putExtra("url", url);
		startActivity(i); 
    }

    @Override
       public void onActivityCreated(Bundle savedInstanceState) {
       super.onActivityCreated(savedInstanceState);
       
       YelpSearchResult searchResult;
		
		ArrayList<HashMap<String, String>> nightList = new ArrayList<HashMap<String, String>>();
		
		latitude = 0.0;
		longitude = 0.0;
		
		//Gets the position of the user
		GPSTracker gps = new GPSTracker(getActivity());
		if(gps.canGetLocation()){
			Log.d("gps", "enable");
			latitude = gps.getLatitude(); // returns latitude
			longitude = gps.getLongitude(); // returns longitude
		}
		
		//Make a search on Yelp
		YelpSearch search = new YelpSearch();
		search.execute("nightlife", "40.741653", "-74.175030" );
		
		try {
			searchResult = search.get();
			
			Log.d("TAG", "Your search found " + searchResult.getTotal() + " results.");
			Log.d("TAG", "Yelp returned " + searchResult.getBusinesses().size() + " businesses in this request.");
			
			for(Business biz : searchResult.getBusinesses()) {
				String id = biz.getId();
				String name = biz.getName();
				int review = biz.getReviewCount();
				String phone = biz.getPhone();
				String url = biz.getUrl();
				
               // creating new HashMap
               HashMap<String, String> map = new HashMap<String, String>();

               // adding each child node to HashMap key => value
               map.put(TAG_ID, id);
               map.put(TAG_NAME, name);
               map.put(TAG_REVIEW, Integer.toString(review));
               map.put(TAG_PHONE, phone);
               map.put(TAG_URL, url);
               
               // adding HashList to ArrayList
               nightList.add(map);
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
       ListAdapter adapter = new SimpleAdapter(getActivity(), nightList,
               R.layout.list_item,
               new String[] { TAG_NAME, TAG_REVIEW, TAG_PHONE, TAG_URL }, new int[] {
                       R.id.name, R.id.email, R.id.mobile, R.id.url });

       setListAdapter(adapter);

       // selecting single ListView item
       ListView lv = getListView();
		
	}
   
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                   Bundle savedInstanceState)
   {
           View view = inflater.inflate(R.layout.tab_list, container, false);
           
           return view;
   }
   
}
