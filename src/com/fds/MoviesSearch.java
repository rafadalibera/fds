package com.fds;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MoviesSearch extends Activity{
	
	TextView title;
	TextView year;
	TextView mpaa;
	TextView synopsis;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_list);
		
		title = (TextView) findViewById (R.id.movieTitle);
		year = (TextView) findViewById (R.id.year);
		mpaa = (TextView) findViewById (R.id.mpaa_rating);
		synopsis = (TextView) findViewById (R.id.synopsis);
		
		
		
	}

}
