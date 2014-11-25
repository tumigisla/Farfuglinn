/*
* Tumi Snær Gíslason
* 25.10.2014
* Custom array adapter for inserting an ArrayList<Flight> to ListView
*/
package hbv1.farfuglinn;

import android.widget.ArrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.farfuglinn.R;

public class MyArrayAdapter extends ArrayAdapter<Flight> {
	
	public MyArrayAdapter (Context context, ArrayList<Flight> flights) {
		super (context, 0, flights);
	}
	
	@Override
	public View getView (int pos, View convertView, ViewGroup parent) {
		
		Flight flight = getItem(pos);
		
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
		}
		
		TextView to = (TextView)convertView.findViewById(R.id.to);
		TextView plannedArrival = (TextView)convertView.findViewById(R.id.plannedArrival);
		TextView airline = (TextView)convertView.findViewById(R.id.airline);
		
		to.setText(flight.to);
		plannedArrival.setText(flight.plannedArrival);
		airline.setText(flight.airline);
		
		return convertView;
		
	}
	
}
