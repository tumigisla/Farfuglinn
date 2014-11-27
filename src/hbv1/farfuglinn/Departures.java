/*  Atli SigurÃ°sson, Tumi Snær Gíslason, Trausti Már Svavarsson
 *   26.11.2014
 *   Displays departures
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


public class Departures extends Fragment {
	
	public static ArrayList<Flight> resultsList;
	private static String url = "http://www.kefairport.is/Flugaaetlun/Brottfarir/";
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
			//on click listeners
			listView.setOnItemLongClickListener(onListClick);
			listView.setOnItemClickListener(onListClick1);

		}

	}
	
	// Make a Flight Object for things chosen in the list.
	private Flight flight;
	
	// There's some bug with getting the position to kick in all the time ... I have to figure it out
	private AdapterView.OnItemLongClickListener onListClick = new AdapterView.OnItemLongClickListener() {
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
			
			flight = (Flight) parent.getAdapter().getItem(position);
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
		if(item.getTitle().equals("Add to Your Flights")){
	        addFlight();
	    }    
	    else {return false;}  
	    return true;  
	}	
	// adds flight in yourFlights
	private void addFlight(){  
		// check if the flight is already in your list.
		if(YourFlights.yourFlightsList.contains(flight)|| this.flight == null){
			Toast.makeText(getActivity(), "All ready in your flights", Toast.LENGTH_SHORT).show();
			return;
		}
		//else we add the flight to list
		else{
			YourFlights.yourFlightsList.add(flight);
        	Toast.makeText(getActivity(), "You have added the flight to your flights", Toast.LENGTH_SHORT).show(); 
        	Stream.saveList(null, getActivity(), YourFlights.yourFlightsList);
		}
	}
	//on click listener to show more info, unfinished.
	private AdapterView.OnItemClickListener onListClick1 = new AdapterView.OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	    { 
	    	
	    	Toast.makeText(getActivity(), "Show more info", Toast.LENGTH_SHORT).show(); 
	  
	    }	
	};	
}
