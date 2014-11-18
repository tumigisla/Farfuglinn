/*  Atli SigurÃ°sson, Tumi Snær Gíslason, Trausti Már Svavarsson
*   05.11.2014
*   Displays departures
*/


package com.example.farfuglinn;

import com.example.farfuglinn.R;
import com.example.farfuglinn.HtmlScraper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.util.Log;
import android.app.ProgressDialog;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


//Departures
public class Brottfarir extends Fragment {

	private static String url = "http://www.kefairport.is/Flugaaetlun/Brottfarir/";
	public static ArrayList<Flight> resultsList;
	private ListView listView;
	private View rootView;
	
	// gerum fligh object fyrir það sem er valið í lista og pos breytur sem heldur utan um position í listview.
	private Flight flight;
	private Integer pos=null;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_brottfarir, container, false);
		new GetResults().execute();
		return rootView;
	}
	
	
		private class GetResults extends AsyncTask<Void, Void, Void> {
			
			ProgressDialog progressDialog;
			
			@Override
			protected void onPreExecute() {
				progressDialog = ProgressDialog.show(
					getActivity(), "Farfuglinn", "Loading flights...", true
				);
			}
			
			@Override
			protected Void doInBackground(Void... arg0) {
				
				HtmlScraper htmlScraper = new HtmlScraper(url);
				htmlScraper.getHtmlString();
				resultsList = htmlScraper.deliverResults();

				return null;
			}
			
			
			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				
				progressDialog.dismiss();
			
				MyArrayAdapter adapter = new MyArrayAdapter(getActivity(), resultsList);
				// populate the listView
				listView = (ListView)rootView.findViewById(R.id.list);
				listView.setAdapter(adapter);
				
			}
				
		}
		
		
		
		
		// það er eitthvað bug með að fá position til að kicka alltaf inn.... þarf að finna eh útúr því.
		private AdapterView.OnItemLongClickListener onListClick = new AdapterView.OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
				
				pos=position;
				
				registerForContextMenu(listView);
				
				return false;
			}
		};
		// Context menu
		@Override
		public void onCreateContextMenu(ContextMenu menu, View v,
				ContextMenuInfo menuInfo) {
				menu.setHeaderTitle("Options");
				menu.add(0, v.getId(),0,"Add to Your Flights");
				menu.add(0, v.getId(),0,"Cancel");
					
				}
		
		@Override // context selected 
	    public boolean onContextItemSelected(MenuItem item) {  
	        if(item.getTitle().equals("Add to Your Flights")){addFlight(item.getItemId());}    
	        else {return false;}  
	    return true;  
	    }
		// adds flight in yourFlights
		public void addFlight(int id){ 
			flight = (Flight) listView.getItemAtPosition(pos);
			if(YourFlights.yourFlightsList.contains(flight)){
				Toast.makeText(getActivity(), "All ready in your flights", Toast.LENGTH_SHORT).show();
				return;
			}
			
			else{
				YourFlights.yourFlightsList.add(flight);
	        	Toast.makeText(getActivity(), "You have added the flight to your flights"+YourFlights.yourFlightsList.toString(), Toast.LENGTH_SHORT).show(); 
	        	//stream_out
			}
	    } 
}
