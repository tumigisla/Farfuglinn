/*  Atli SigurÃ°sson, Tumi Snær Gíslason, Trausti Már Svavarsson
*   12.10.2014
*   Birtir Ã¾Ã­n flug
*/

package com.example.farfuglinn;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.farfuglinn.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


//ÃžÃ­n flug
public class YourFlights extends Fragment {
	//public static ArrayList<Flight> yourFlightsList;
	private static String url = "http://apis.is/flight?language=en&type=departures";
	private static final String TAG_RESULTS = "results";
	private JSONArray results = null;
	//public listi fyrir yourflights.
	public static ArrayList<Flight> yourFlightsList = new ArrayList<Flight>();
	private int pos;
	
	
	private ListView listView;
	private View rootView;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// need to change this later. find out some other method.
		delOldFlights(yourFlightsList);
		
		rootView = inflater.inflate(R.layout.fragment_brottfarir, container, false);
		
		// not use this for now, create dummy data until this has been fixed
		//new GetResults().execute();
		
		//yourFlightsList = createDummyFlights();
		
		MyArrayAdapter adapter = new MyArrayAdapter(getActivity(), yourFlightsList);
		// populate the listView
		listView = (ListView)rootView.findViewById(R.id.list);
		listView.setAdapter(adapter);
		// listener for click
		listView.setOnItemLongClickListener(onListClick);
		
		return rootView;
	}
	
	// creating dummy flights while the GetData is being fixed
	public ArrayList<Flight> createDummyFlights() {
        String json = null;
        ArrayList<Flight> dummyFlights;
        try {

            InputStream is = getActivity().getAssets().open("jsonFiles/yourflights.json");
            
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
            //Log.d("YourFlights", json);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        
        try {
        	JSONObject object = new JSONObject(json);
			results = object.getJSONArray(TAG_RESULTS);
			dummyFlights = Flight.fromJSON(results);
        }
        catch (JSONException e) {
        	dummyFlights = null;
        	e.printStackTrace();
        }
        
        
        return dummyFlights;

    }
	
	
		private class GetResults extends AsyncTask<Void, Void, Void> {
			
			@Override
			protected Void doInBackground(Void... arg0) {
				
				GetData getData = new GetData();

				String jsonStr = getData.getDataFromService(url, GetData.GET);
				
				if (jsonStr != null) {
					try {
						JSONObject object = new JSONObject(jsonStr);
						results = object.getJSONArray(TAG_RESULTS);
						yourFlightsList = Flight.fromJSON(results);
						/// DEBUG PRINT //
						for (Flight f : yourFlightsList) {
							System.out.println("DEBUG "+f.to);
							System.out.println("DEBUG "+f.plannedArrival);
							System.out.println("DEBUG "+f.airline);
						}
						//////
					}
					catch (JSONException e) {
						e.printStackTrace();
					}
				}
				
				else {
					Log.e("GetData", "Data could not be retrieved from the given URL");
				}

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
				menu.add(0, v.getId(),0,"Delete flight");
				menu.add(0, v.getId(),0,"Cancel");
					
				}
		
		@Override // context selected 
	    public boolean onContextItemSelected(MenuItem item) {  
	        if(item.getTitle().equals("Delete flight")){delFlight(item.getItemId());}  
	        else {return false;}  
	    return true;  
	    }
		// adds flight in yourFlights
		public void delFlight(int id){ 
			yourFlightsList.remove(pos);
			
	    } 
		
		public void delOldFlights(ArrayList<Flight> list){
			for(Flight f: list){
				if(Brottfarir.resultsList.contains(f) || Komur.resultsList.contains(f)){	
					return;
				}
				else {list.remove(f);}
			}
		}
	
	
	

}
