// Aðfenginn klasi úr pakkasafni androidhive

package info.androidhive.tabsswipe.adapter;

import com.example.farfuglinn.Brottfarir;
import com.example.farfuglinn.Komur;
import com.example.farfuglinn.YourFlights;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	
	// Just make the activity once, probably makes it easier
	// for changing some attribute of an instance of the
	// YourFlights class.
	public Brottfarir brottfarir = new Brottfarir();;
	public Komur komur = new Komur();
	public YourFlights yourFlights = new YourFlights();

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			return brottfarir;
		case 1:
			return komur;
		case 2:
			return yourFlights;
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
