/*  Atli Sigurdsson
*   12.10.2014
*   Main klasi, tengist hinum med Fragment
*/

package com.example.farfuglinn;

import com.example.farfuglinn.UpdateTask;
import com.example.farfuglinn.R;

import info.androidhive.tabsswipe.adapter.TabsPagerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

@SuppressLint("NewApi") public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private Menu mymenu; 
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	
	// Tab titles
	private String[] isl_tabs = { "Brottfarir", "Komur", "Min flug" };
	private String[] en_tabs = {"Departures", "Arrivals", "My flights"};
	private static int counter = LanguagesCheck.increment();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		

		//Adding tabs
		if(counter % 2 == 0){
			for (String tab_name : isl_tabs) {
				actionBar.addTab(actionBar.newTab().setText(tab_name)
						.setTabListener(this));
			}
		}
		else {
			for (String tab_name : en_tabs) {
				actionBar.addTab(actionBar.newTab().setText(tab_name)
						.setTabListener(this));
			}
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	//Buttonclick method to switch between languages
	public void buttonClickMethodLang(MenuItem item)
	{
		counter = LanguagesCheck.increment();
		Intent i = getIntent();
		finish();
		startActivity(i);
		
	}
	

	
	
	//Fyrir refresh
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.main, menu);
		// We should save our menu so we can use it to reset our updater.
        mymenu = menu;
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch(item.getItemId()) {
	    case R.id.action_refresh:
	    	
	    	 // Do animation start
	        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        ImageView iv = (ImageView)inflater.inflate(R.layout.iv_refresh, null);
	        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate_refresh);
	        rotation.setRepeatCount(Animation.INFINITE);
	        iv.startAnimation(rotation);
	        item.setActionView(iv);
	        
	        new UpdateTask(this).execute();
	        
	       
	   //     new GetResults(this).execute();
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	 
	 
	@TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi") public void resetUpdating()
	{
	    // Get our refresh item from the menu
	    MenuItem m = mymenu.findItem(R.id.action_refresh);
	    if(m.getActionView()!=null)
	    {
	        // Remove the animation.
	        m.getActionView().clearAnimation();
	        m.setActionView(null);
	    }
	}
	

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

}
