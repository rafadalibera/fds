package com.fds;

import java.util.List;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.app.Fragment;

public class MyPagerAdapter extends FragmentPagerAdapter{
	
	private final List<android.support.v4.app.Fragment> fragments;

	/**
	* @param fm
	* @param fragments
	*/
	public MyPagerAdapter(FragmentManager fm, List<android.support.v4.app.Fragment> fragments) {
	super(fm);
	this.fragments = fragments;
	}

	/*
	* (non-Javadoc)
	*
	* @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
	*/
	@Override
	public android.support.v4.app.Fragment getItem(int position) {
	return this.fragments.get(position);
	}

	/*
	* (non-Javadoc)
	*
	* @see android.support.v4.view.PagerAdapter#getCount()
	*/
	@Override
	public int getCount() {
	return this.fragments.size();
	}


}

