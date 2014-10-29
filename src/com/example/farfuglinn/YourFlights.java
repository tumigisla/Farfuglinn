/*  Atli Sigurðsson
*   12.10.2014
*   Birtir þín flug
*/

package com.example.farfuglinn;

import com.example.farfuglinn.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


//Þín flug
public class YourFlights extends Fragment {
	public Object[] _yourFlights;

	private View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_yourflights, container, false);
		
		return rootView;
	}




	
	
	// Context menu
			//@Override
		/*	public void onCreateContextMenu(ContextMenu menu, View v,
			    ContextMenuInfo menuInfo) {
				menu.setHeaderTitle("Options");
				menu.add(0, v.getId(),0,"Add to Your Flights");
				menu.add(0, v.getId(),0,"Cancel");
				
			}
			@Override  
		    public boolean onContextItemSelected(MenuItem item) {  
		        if(item.getTitle().equals("Add to Your Flights")){addFlight(item.getItemId());}  
		        else if(item.getTitle().equals("Cancel")){cancel(item.getItemId());}  
		        else {return false;}  
		    return true;  
		    }  
			public void addFlight(int id){  
			        Toast.makeText(getActivity(), "You have added the flight to your flights", Toast.LENGTH_SHORT).show(); 
			        //TODO
			    } 
			public void cancel(int id){  
		        Toast.makeText(getActivity(), "you!", Toast.LENGTH_SHORT).show();  
		    }*/
	
	
	

}



