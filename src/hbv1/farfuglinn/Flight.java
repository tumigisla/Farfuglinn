/*
* Tumi Snær Gíslason
* 30.10.2014
* An instance of Flight is one entry in the JSONArray data.
*/
package hbv1.farfuglinn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.*;

public class Flight {

	public String date;
	public String flightNumber;
	public String airline;
	public String to;
	public String plannedArrival;
	public String realArrival;
	public String status;
	
	
	public Flight (
			String date, 
			String flightNumber,
			String airline,
			String to,
			String plannedArrival,
			String realArrival,
			String status
	) {
		try {
			this.date = date;
			this.flightNumber = flightNumber;
			this.airline = airline;
			this.to = to;
			this.plannedArrival = plannedArrival;
			this.realArrival = realArrival;
			this.status = status;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
