package com.example.farfuglinn;

import android.widget.ArrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

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
		
		to.setText(flight.destination);
		plannedArrival.setText(flight.plannedArrival);
		airline.setText(flight.airline);
		
		return convertView;
		
	}
	
}
