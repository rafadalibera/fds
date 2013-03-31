package com.fds;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {
	
    FragmentTransaction transaction;
    static ViewPager mViewPager;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ListFragment tabOneFragment = new TabMovies();
        ListFragment tabTwoFragment = new TabRestaurants();
        ListFragment tabThreeFragment = new TabNightLife();
        ListFragment tabFourFragment = new TabActiveLife();

        PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(tabOneFragment);
        mPagerAdapter.addFragment(tabTwoFragment);
        mPagerAdapter.addFragment(tabThreeFragment);
        mPagerAdapter.addFragment(tabFourFragment);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(0);
        
        mViewPager.setOnPageChangeListener(
            new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    // When swiping between pages, select the
                    // corresponding tab.
                    getActionBar().setSelectedNavigationItem(position);
                }
            });
        
        ActionBar ab = getActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab tab1 = ab.newTab().setText("Movies")
                        .setTabListener(new TabListener<TabMovies>(
                        this, "tabone", TabMovies.class));

        Tab tab2 = ab.newTab().setText("Food")
                        .setTabListener(new TabListener<TabRestaurants>(
                        this, "tabtwo", TabRestaurants.class));
                
        Tab tab3 = ab.newTab().setText("Night")
                        .setTabListener(new TabListener<TabNightLife>(
                        this, "tabtwo", TabNightLife.class));
                
        Tab tab4 = ab.newTab().setText("Day")
                        .setTabListener(new TabListener<TabActiveLife>(
                        this, "tabtwo", TabActiveLife.class));
               
        ab.addTab(tab1);
        ab.addTab(tab2);
        ab.addTab(tab3);
        ab.addTab(tab4);
        
    }
    
    public static class TabListener<T extends ListFragment> implements ActionBar.TabListener {
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;

        /** Constructor used each time a new tab is created.
          * @param activity  The host Activity, used to instantiate the fragment
          * @param tag  The identifier tag for the fragment
          * @param clz  The fragment's Class, used to instantiate the fragment
          */
        public TabListener(Activity activity, String tag, Class<T> clz) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
        }

        /* The following are each of the ActionBar.TabListener callbacks */

        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            // Check if the fragment is already initialized
            if (mFragment == null) {
                // If not, instantiate and add it to the activity
                mFragment = Fragment.instantiate(mActivity, mClass.getName());
                ft.add(android.R.id.content, mFragment, mTag);
            } else {
                // If it exists, simply attach it in order to show it
                ft.attach(mFragment);
            }
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                // Detach the fragment, because another one is being attached
                ft.detach(mFragment);
            }
        }

        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            // User selected the already selected tab. Usually do nothing.
        }

                public void onTabReselected(Tab arg0,
                                android.app.FragmentTransaction arg1)
                {
                        // TODO Auto-generated method stub
                        
                }

                public void onTabSelected(Tab arg0, android.app.FragmentTransaction arg1)
                {
                        // TODO Auto-generated method stub
                        mViewPager.setCurrentItem(arg0.getPosition());
                }

                public void onTabUnselected(Tab arg0,
                                android.app.FragmentTransaction arg1)
                {
                        // TODO Auto-generated method stub
                        
                }
    }
    
    public class PagerAdapter extends FragmentPagerAdapter {

        private final ArrayList<ListFragment> mFragments = new ArrayList<ListFragment>();

        public PagerAdapter(FragmentManager manager) {
            super(manager);
        }

        public void addFragment(ListFragment tabOneFragment) {
            mFragments.add(tabOneFragment);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public ListFragment getItem(int position) {
            return mFragments.get(position);
        }
    }
    
    
}
/*
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
*/