package com.fds;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button movies;
	Button restaurants;
	Button night;
	Button active;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		movies = (Button) findViewById (R.id.buttonMovies);
		restaurants = (Button) findViewById (R.id.buttonRestaurants);
		night = (Button) findViewById (R.id.buttonNightLife);
		active = (Button) findViewById (R.id.buttonActiveLife);
		
		movies.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), MoviesSearch.class);
				startActivity(intent);
			}
		});
		
		restaurants.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), Restaurants.class);
				startActivity(intent);
			}
		});
		
		night.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), NightLife.class);
				startActivity(intent);
			}
		});
		
		active.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), ActiveLife.class);
				startActivity(intent);
			}
		});
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
