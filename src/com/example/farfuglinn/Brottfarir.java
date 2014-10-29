/*  Atli Sigurðsson
*   12.10.2014
*   Displays departures
*/


package com.example.farfuglinn;

import com.example.farfuglinn.R;
import com.example.farfuglinn.GetData;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.IOException;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import java.io.File;
import android.util.*;
import android.content.ContextWrapper;

import 	android.content.res.AssetManager;

import android.content.Context;


//Departures
public class Brottfarir extends Fragment {

	private static String url = "http://apis.is/flight?language=en&type=departures";
	private static final String TAG_RESULTS = "results";
	private JSONArray results = null;
	private ArrayList<Flight> resultsList;
	private ListView listView;
	private View rootView;
	private YourFlights addBrott;
	private Object temp;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_brottfarir, container, false);
		
		// not use this for now, create dummy data until this has been fixed
		//new GetResults().execute();
		
		resultsList = createDummyFlights();
		
		MyArrayAdapter adapter = new MyArrayAdapter(getActivity(), resultsList);
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

            InputStream is = getActivity().getAssets().open("jsonFiles/departures.json");
            
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
            //Log.d("Brottfarir", json);

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
						resultsList = Flight.fromJSON(results);
						/// DEBUG PRINT //
						for (Flight f : resultsList) {
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
			
				MyArrayAdapter adapter = new MyArrayAdapter(getActivity(), resultsList);
				// populate the listView
				listView = (ListView)rootView.findViewById(R.id.list);
				listView.setAdapter(adapter);
				// listener for click
				listView.setOnItemLongClickListener(onListClick);
			}
			
			
		}
		private AdapterView.OnItemLongClickListener onListClick = new AdapterView.OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
			
				temp  = listView.getItemAtPosition(position);
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
	        else if(item.getTitle().equals("Cancel")){cancel(item.getItemId());}  
	        else {return false;}  
	    return true;  
	    }
		// adds flight in yourFlights
		public void addFlight(int id){  
	        Toast.makeText(getActivity(), "You have added the flight to your flights"+temp.toString(), Toast.LENGTH_SHORT).show(); 
	        //addBrott._yourFlights = appendValue(addBrott._yourFlights, temp);
	    } 
		//nothing
		public void cancel(int id){  
			Toast.makeText(getActivity(), "you suck!", Toast.LENGTH_SHORT).show();  
    }
		/*private Object[] appendValue(Object[] obj, Object newObj) {
			 
			ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(obj));
			temp.add(newObj);
			return temp.toArray();
		 
		  }*/
		
		
}
