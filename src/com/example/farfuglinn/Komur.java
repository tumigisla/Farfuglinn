/*  Atli Sigurðsson
*   12.10.2014
*   Birtir komur
*/

package com.example.farfuglinn;

import com.example.farfuglinn.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//Komur
public class Komur extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_komur, container, false);
		
		return rootView;
	}
}
