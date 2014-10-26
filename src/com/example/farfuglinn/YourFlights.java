/*  Atli Sigurðsson
*   12.10.2014
*   Birtir þín flug
*/

package com.example.farfuglinn;

import com.example.farfuglinn.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


//Þín flug
public class YourFlights extends Fragment {

	private View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_yourflights, container, false);
		
		return rootView;
	}

}
