/*  Atli Sigurdsson
*   12.10.2014
*   Main klasi, tengist hinum med Fragment
*/

package com.example.farfuglinn;

import com.example.farfuglinn.R;

import info.androidhive.tabsswipe.adapter.TabsPagerAdapter;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

@SuppressLint("NewApi") public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	
	// Tab titles
	private String[] isl_tabs = { "Brottfarir", "Komur", "Min flug" };
	private String[] en_tabs = {"Departures", "Arrivals", "My flights"};
	private boolean lang = LanguagesCheck.isTrue();
	private static int counter = 1;
	private static boolean count = LanguagesCheck.count();
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
		if(lang == true && count == false){
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
		counter++;
		boolean in = true;
		LanguagesCheck.change(in);
		
	}
	
	public static int counter()
	{
		return counter;
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
