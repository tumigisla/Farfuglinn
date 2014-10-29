package com.example.farfuglinn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.*;

public class Flight {

	public String date;
	public String flightNumber;
	public String airline;
	public String from;
	public String plannedArrival;
	public String realArrival;
	public String status;
	public String to;
	
	
	public Flight (JSONObject object) {
		try {
			this.date = object.getString("date");
			this.flightNumber = object.getString("flightNumber");
			this.airline = object.getString("airline");
			this.from = object.getString("from");
			this.plannedArrival = object.getString("plannedArrival");
			this.realArrival = object.getString("realArrival");
			this.status = object.getString("status");
			this.to = object.getString("to");
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static ArrayList<Flight> fromJSON (JSONArray jsonArray) {
		
		ArrayList<Flight> flights = new ArrayList<Flight>();
		
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				flights.add(new Flight(jsonArray.getJSONObject(i)));
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
		}
		/*
		/// DEBUG PRINT ///
		// PRE SORT //
		for (Flight f : flights) {
			System.out.println("DEBUG PRE : \t" + f.plannedArrival);
		}
		//
		*/
		// sort by arrival time
		Collections.sort(flights, new Comparator<Flight>() {
			@Override
			public int compare (Flight f1, Flight f2) {
				return f1.plannedArrival.compareTo(f2.plannedArrival);
			}
		});
		
		/*
		/// DEBUG PRINT ///
		// POST SORT //
		for (Flight f : flights) {
			System.out.println("DEBUG POST : \t" + f.plannedArrival);
		}
		//
		*/
		
		return flights;
	}

}
