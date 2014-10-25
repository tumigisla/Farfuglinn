package com.example.farfuglinn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Flight {

	public String destination;
	public String plannedArrival;
	public String airline;
	
	public Flight (JSONObject object) {
		try {
			this.destination = object.getString("to");
			this.plannedArrival = object.getString("plannedArrival");
			this.airline = object.getString("airline");
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
		
		return flights;
	}

}
