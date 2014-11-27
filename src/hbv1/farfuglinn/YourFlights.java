/*  Trausti Már Svavarsson
*   12.10.2014
*   Displays your flights
*/

package hbv1.farfuglinn;

import java.util.ArrayList;

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


//yourflights
public class YourFlights extends Fragment {
	
	//public list for yourflights.
	public static ArrayList<Flight> yourFlightsList = new ArrayList<Flight>();
	
	//flight object for listview.
	private Flight flight;
	public static Arrivals kom = new Arrivals();
	public static Departures brott = new Departures();
	
	private ListView listView;
	private View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		rootView = inflater.inflate(R.layout.fragment_brottfarir, container, false);
		
		MyArrayAdapter adapter = new MyArrayAdapter(getActivity(), yourFlightsList);
		// populate the listView
		listView = (ListView)rootView.findViewById(R.id.list);
		listView.setAdapter(adapter);
		//onclick listener
		listView.setOnItemLongClickListener(onListClick);
		listView.setOnItemClickListener(onListClick1);
		
		return rootView;
	}
	
	@SuppressWarnings("unused")
	private class GetResults extends AsyncTask<Void, Void, Void> {
			
		@Override
		protected Void doInBackground(Void... arg0) {
			//new GetData();
			return null;
		}
			
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			MyArrayAdapter adapter = new MyArrayAdapter(getActivity(), yourFlightsList);
			// populate the listView
			listView = (ListView)rootView.findViewById(R.id.list);
			listView.setAdapter(adapter);
				
		}	
	}
	// On long click listener , it's buggy, it's something wrong with arraylist.
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
		menu.add(0, v.getId(),0,"Delete flight");
		menu.add(0, v.getId(),0,"Cancel");				
	}
		
	@Override // context selected 
	public boolean onContextItemSelected(MenuItem item) {  
		if(item.getTitle().equals("Delete flight")){
			delFlight();
		}  
	    else {return false;}  
	    return true;  
	}
		
	// adds flight in yourFlights
	private void delFlight(){ 
		yourFlightsList.remove(flight);
		Stream.saveList(null, getActivity(), YourFlights.yourFlightsList);	
	} 
		
	//on click listener to show more info, unfinished.
	private AdapterView.OnItemClickListener onListClick1 = new AdapterView.OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	    { 
	    	//show more info for clicked flight	
	    	Toast.makeText(getActivity(), "Show more info", Toast.LENGTH_SHORT).show(); 
	    }	

	};	
}

