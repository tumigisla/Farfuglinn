/*  Atli SigurÃ°sson, Tumi Snær Gíslason, Trausti Már Svavarsson
 *   12.10.2014
 *   Displays arrivals
 */


package hbv1.farfuglinn;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import hbv1.farfuglinn.R;


public class Arrivals extends Fragment {
	
	public static ArrayList<Flight> resultsList;
	private static String url = "http://www.kefairport.is/Flugaaetlun/Komur/";
	private ListView listView;
	private View rootView;
	
	
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
			listView.setOnItemLongClickListener(onListClick);
			//listView.setOnItemClickListener(onListClick1);

		}


		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(
					getActivity(), "Farfuglinn", "Loading flights...", true
					);
		}

	}

	// Make a Flight Object for things chosen in the list, and a pos variable that keeps
	// track of the position in ListView.
	private Flight flight;

	// There's some bug with getting the position to kick in all the time ... I have to figure it out
	private AdapterView.OnItemLongClickListener onListClick = new AdapterView.OnItemLongClickListener() {
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
			
			flight = (Flight) parent.getAdapter().getItem(position);
			//flight = (Flight) parent.getItemAtPosition(position);
			//System.out.println("debug onclick"+flight.to);
			registerForContextMenu(listView);
			return false;		
		}
	};
	
	
	// create Context menu
			@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("Options");
		menu.add(0, v.getId(),0,"Add to Your Flights");
		menu.add(0, v.getId(),0,"Cancel");
	}
			
	@Override  // context selected
	public boolean onContextItemSelected(MenuItem item) {  
		 if(item.getTitle().equals("Add to Your Flights"))
		 { 
			 addFlight();
		 }  
		 else {
			 return false;
		 }  
		 return true;  
	}
	
	//adds flight in YourFlightslist
	private void addFlight(){  
		
		// check if the flight is already in your list.
		if(YourFlights.yourFlightsList.contains(this.flight)|| flight == null){
			Toast.makeText(getActivity(), "All ready in your flights", Toast.LENGTH_SHORT).show();
			return;
		}
		//else we add the flight to list
		else{
			YourFlights.yourFlightsList.add(this.flight);
        	Toast.makeText(getActivity(), "You have added the flight to your flights"+YourFlights.yourFlightsList.toString(), Toast.LENGTH_SHORT).show(); 
        	Stream.saveList(null, getActivity(), YourFlights.yourFlightsList);
		}
	} 
}
