/*  Atli Sigurðsson
*   12.10.2014
*   Birtir brottfarir
*/


package com.example.farfuglinn;

import com.example.farfuglinn.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//Brottfarir
public class Brottfarir extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_brottfarir, container, false);
		
		return rootView;
	}
}
