/*  Atli SigurÃ°sson, Tumi Snær Gíslason, Trausti Már Svavarsson
 *   12.10.2014
 *   Birtir Ã¾Ã­n flug
 */

package hbv1.farfuglinn;

import java.util.ArrayList;

import org.json.JSONArray;

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

import hbv1.farfuglinn.R;


//ÃžÃ­n flug
public class YourFlights extends Fragment {
	private static final String TAG_RESULTS = "results";
	//public static ArrayList<Flight> yourFlightsList;
	private static String url = "http://apis.is/flight?language=en&type=departures";
	//public list for yourflights.
	public static ArrayList<Flight> yourFlightsList = new ArrayList<Flight>();
	private ListView listView;
	
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
	
	
	
	// There's some bug with getting the position to kick in all the time ... I have to figure it out
	private AdapterView.OnItemLongClickListener onListClick = new AdapterView.OnItemLongClickListener() {
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){

			pos=position;

			registerForContextMenu(listView);

			return false;
		}
	};


	private int pos;
	private JSONArray results = null;


	private View rootView;

	// creating dummy flights while the GetData is being fixed
	/*
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
	 */

	// adds flight in yourFlights
	public void delFlight(int id){
		yourFlightsList.remove(pos);

	}
	public void delOldFlights(ArrayList<Flight> list){
		for(Flight f: list){
			if(Departures.resultsList.contains(f) || Arrivals.resultsList.contains(f))
				return;
			else {list.remove(f);}
		}
	}

	@Override // context selected
	public boolean onContextItemSelected(MenuItem item) {
		if(item.getTitle().equals("Delete flight")){delFlight(item.getItemId());} else
			return false;
		return true;
	}
	// Context menu
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("Options");
		menu.add(0, v.getId(),0,"Delete flight");
		menu.add(0, v.getId(),0,"Cancel");

	}


}
