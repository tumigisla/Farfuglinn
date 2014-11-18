/*
 * Tumi Snær Gíslason
 * November 4th 2014
 * Creates and updates an sqlite db containing flights and departures.
 * Parses JSONObject into the db.
 */

package com.example.farfuglinn;

import com.example.farfuglinn.*;

import android.database.sqlite.*;
import android.database.Cursor;

import android.content.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;



public class SQLiteManager extends SQLiteOpenHelper{

	private static final String DB_NAME = "Flights";
	private static final int DB_VERSION = 1;
	
	private static final String TAG_RESULTS = "results";
	
	// Database attributes
	private static final String ATTR_ID = "ID";
	private static final String ATTR_DATE = "date";
	private static final String ATTR_FLIGHTNUMBER = "flightNumber";
	private static final String ATTR_AIRLINE = "airline";
	private static final String ATTR_FROM = "fromPlace";
	private static final String ATTR_PLANNEDARRIVAL = "plannedArrival";
	private static final String ATTR_REALARRIVAL = "realArrival";
	private static final String ATTR_STATUS = "status";
	private static final String ATTR_TO = "toPlace";
	
	private static HashMap<String, String> dropTablesMap = new HashMap<>();
	private static final String DROP_TABLES = "" +
			"DROP TABLE IF EXISTS Departures;" +
			"DROP TABLE IF EXISTS Arrivals;"
		;	
	
	
	
	public SQLiteManager(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(this.createTable("Departures"));
		db.execSQL(this.createTable("Arrivals"));
	}
	
	
	
	
	
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		// Tear down and build database again
		db.execSQL(DROP_TABLES);
		
		this.onCreate(db);
	}

	
	// Input: String jsonStr containing the JSON data
	//			table is Departures/Arrivals
	// Post: jsonStr -> JSONObject -> JSONArray -> this database 
	public void jsonToDb(String jsonStr, String table) {
		
		dropTablesMap.put("Departures", "DROP TABLE IF EXISTS Departures;");
		dropTablesMap.put("Arrivals", "DROP TABLE IF EXISTS Arrivals;");
		
		// Get reference to this (readable) database
		SQLiteDatabase db = this.getReadableDatabase();
		
		db.execSQL(dropTablesMap.get(table));
		db.execSQL(this.createTable(table));

		db.close();
		
		
		try {
			JSONObject JSONObject = new JSONObject(jsonStr);
			JSONArray jsonArray = JSONObject.getJSONArray(TAG_RESULTS);
			
			// Insert all the JSON strings into the database
			// Open up database connection for each insertion to database because
			// we might be inserting to both Departures and Arrivals at the
			// same time, from separate threads.
			for (int i = 0; i < jsonArray.length(); i++) {
				
				JSONObject object = jsonArray.getJSONObject(i);
				
				ContentValues values = new ContentValues();
				values.put(ATTR_ID, i);
				values.put(ATTR_DATE, object.getString(ATTR_DATE));
				values.put(ATTR_FLIGHTNUMBER, object.getString(ATTR_FLIGHTNUMBER));
				values.put(ATTR_AIRLINE, object.getString(ATTR_AIRLINE));
				values.put(ATTR_FROM, object.getString("from"));
				values.put(ATTR_PLANNEDARRIVAL, object.getString(ATTR_PLANNEDARRIVAL));
				values.put(ATTR_REALARRIVAL, object.getString(ATTR_REALARRIVAL));
				values.put(ATTR_STATUS, object.getString(ATTR_STATUS));
				values.put(ATTR_TO, object.getString("to"));
				
				db = this.getReadableDatabase();
				db.insert(table, null, values); // null is nullColumnHack
				db.close();
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/*
	 * Input: String table -> "Departures"/"Arrivals"
	 * Output: String with table declaration for Departures/Arrivals
	 */
	private String createTable(String table) {
		
		String dbStr = null;
		
		// Get current Unix epoch time and convert to String.
				// long value in case of Y2038 problem, you never know...
				long unixTimeStamp = System.currentTimeMillis() / 1000;
				String unixTimeStampStr = Objects.toString(unixTimeStamp);
				
				// Create table departures
				String CREATE_TABLE_DEPARTURES = "" +
					"CREATE TABLE Departures (" +
						"ID int" +
						"date varchar(7)," +
						"flightNumber varchar(7)," +
						"airline varchar(20)," +
						"fromPlace varchar(20)," +
						"plannedArrival varchar(5)," +
						"realArrival varchar(5)," +
						"status varchar(10)," +
						"toPlace varchar(20)," +
						"timeStamp INTEGER DEFAULT " + unixTimeStampStr + "," +
						"PRIMARY KEY(flightNumber)" + // will be unique for each day at least
					");";
				
				// Create table arrivals
				String CREATE_TABLE_ARRIVALS = "" +
					"CREATE TABLE Arrivals (" +
						"ID int" +
						"date varchar(7)," +
						"flightNumber varchar(7)," +
						"airline varchar(20)," +
						"fromPlace varchar(20)," +
						"plannedArrival varchar(5)," +
						"realArrival varchar(5)," +
						"status varchar(10)," +
						"toPlace varchar(20)," +
						"timeStamp INTEGER DEFAULT " + unixTimeStampStr + "," +
						"PRIMARY KEY(flightNumber)" + // will be unique for each day at least
					");";
		
		
		
		
		if (table.equals("Departures")) {
			dbStr = CREATE_TABLE_DEPARTURES;
		}
		else if (table.equals("Arrivals")) {
			dbStr = CREATE_TABLE_ARRIVALS;
		}
		
		return dbStr;
	}
	
	
	
	// table is Departures/Arrivals
	// Extract all values from table to an ArrayList of Flights
	public ArrayList<Flight> extractValues (String table) {
		
		ArrayList<Flight> flightsList = new ArrayList<Flight>();
		
		int i = 0;
		
		String query = "SELECT * FROM " + table + " WHERE ID = " + Integer.toString(i) + ";";
		
		// Get reference to this (readable) database
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		
		Flight flight = null;
		while (cursor.moveToNext()) {
			
			String[] tmp = new String[8];
			for (int j = 1; j <= tmp.length; j++) {
				tmp[j] = cursor.getString(j);
			}
			 
			//flight = new Flight(tmp);    commented out while fixing
			flightsList.add(flight);
		}
		return flightsList;
		
	}
	
	
}
