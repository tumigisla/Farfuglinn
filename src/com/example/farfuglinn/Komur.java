/*  Atli Sigurðsson
*   12.10.2014
*   Displays arrivals
*/


package com.example.farfuglinn;

import com.example.farfuglinn.R;
import com.example.farfuglinn.GetData;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import android.widget.ListView;



//Departures
public class Komur extends Fragment {

	private static String url = "http://apis.is/flight?language=en&type=arrivals";
	private static final String TAG_RESULTS = "results";
	private JSONArray results = null;
	private ArrayList<Flight> resultsList;
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
							System.out.println("DEBUG "+f.destination);
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
			}
			
			
		}
}
