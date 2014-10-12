package info.androidhive.tabsswipe.adapter;

import com.example.farfuglinn.Brottfarir;
import com.example.farfuglinn.Komur;
import com.example.farfuglinn.YourFlights;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new Brottfarir();
		case 1:
			// Games fragment activity
			return new Komur();
		case 2:
			// Movies fragment activity
			return new YourFlights();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
