package com.example.farfuglinn;

import com.example.farfuglinn.Flight;

import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import 	java.io.IOException;
import java.net.MalformedURLException;

import java.util.ArrayList;



public class HtmlScraper {

	private URL url;
	private String htmlString;
	
	// constants, for trimming html
	private String tableEnd = "</tr>";
	private String tableHead = "<th>";
	private String tableData = "td";
	private String tableDataEnd = "/td";
	private String tableRowEnd = "/tr";
	private int closingDataLength = 4;  // minus the > tag in the end
	
	public HtmlScraper (String url) {
		try {
			this.url = new URL(url);
		}
		catch (MalformedURLException e) {}
	}
	
	
	// Go to url and get the source code
	public void getHtmlString() {
		
		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = new StringBuilder();
		
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(this.url.openStream(), "UTF-8"));
			for (String line; (line = bufferedReader.readLine()) != null;) {
				stringBuilder.append(line.trim());
			}
		}
		catch (Exception e) {}
		finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				}
				catch (IOException logOrIgnore) 
				{}
			}
		}
		
		String start = "<tr>";
		String end = "</tbody>";
		String part = stringBuilder.substring(stringBuilder.indexOf(start) + start.length());
		
		this.htmlString = part.substring(0, part.indexOf(end));
			
	}
	
	
	// Trims the html source code and populates
	// the array list with the desired results
	public ArrayList<Flight> deliverResults () {
		
		ArrayList<Flight> flightsArrayList = new ArrayList<Flight>();
		
		String headerTable = "";
		int htmlLength = this.htmlString.length();
		
		// Cut out the table header
		for (int i = 0; i < htmlLength; i++) {
			if (headerTable != null && headerTable.length() >= 5 && headerTable.substring(headerTable.length() - 5).equals(tableEnd)) {
				break;
			}
			else {
				headerTable = headerTable + htmlString.charAt(i);
						
			}
		}
		
		// The rest of the html, w/o the table header
		htmlString = htmlString.substring(headerTable.length());
				
		// Strings for Flight object to put in ArrayList
		String[] flightData = new String[7];
		int flightDataIndex = 0;
				
				
		htmlLength = htmlString.length();
		String tmpStr = "";
		int beginStrIndex = 0;
				
		for (int i = 0; i < htmlLength; i++) {
					
			if (htmlString.charAt(i) == '>' &&
				tmpStr.length() > 3) {	// check for closing tags
						
				if (tmpStr.substring(tmpStr.length() - 3).equals(tableDataEnd) ) {
					// add flight item to array
					flightData[flightDataIndex++] = 
							tmpStr.substring(beginStrIndex, tmpStr.length() - closingDataLength );
				}
						
				else if (tmpStr.substring(tmpStr.length() - 2).equals(tableData) ) {
					beginStrIndex = i + 1; // set starting index for flight item
					// i + 1 for starting right after the closing > tag
				}
						
				else if (tmpStr.substring(tmpStr.length() - 3).equals(tableRowEnd) ) {
					flightsArrayList.add(new Flight( // end of table row
							flightData[0],
							flightData[1],
							flightData[2],
							flightData[3],
							flightData[4],
							flightData[5],
							flightData[6]
					));
				flightDataIndex = 0; // initialize index for populating the next array
				}
			}
			// do this in every iteration
			tmpStr = tmpStr + Character.toString(htmlString.charAt(i) );
		}
		
		return flightsArrayList;
	}
	
	
	/*
	public static void main(String[] args) throws Exception{
		
		URL url = new URL("http://www.kefairport.is/Flugaaetlun/Brottfarir/");
		
		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = new StringBuilder();
		
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			for (String line; (line = bufferedReader.readLine()) != null;) {
				stringBuilder.append(line.trim());
			}
		} 
		finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				}
				catch (IOException logOrIgnore) 
				{}
			}
		}
		
		String start = "<tr>";
		String end = "</tbody>";
		String part = stringBuilder.substring(stringBuilder.indexOf(start) + start.length());
		String htmlString = part.substring(0, part.indexOf(end));
		//System.out.println(htmlString);
		
		ArrayList<Flight> flightsArrayList = new ArrayList<Flight>();
		
		String tableEnd = "</tr>";
		String tableHead = "<th>";
		String tableData = "td";
		String tableDataEnd = "/td";
		String tableRowEnd = "/tr";
		int closingDataLength = 4;  // minus the > tag in the end
		
		String headerTable = "";
		int htmlLength = htmlString.length();
		
		// Cut out the table header
		for (int i = 0; i < htmlLength; i++) {
			if (headerTable != null && headerTable.length() >= 5 && headerTable.substring(headerTable.length() - 5).equals(tableEnd)) {
				break;
			}
			else {
				headerTable = headerTable + htmlString.charAt(i);
				
			}
		}
		
		// The rest of the html, w/o the table header
		htmlString = htmlString.substring(headerTable.length());
		
		// Strings for Flight object to put in ArrayList
		String[] flightData = new String[7];
		int flightDataIndex = 0;
		
		
		htmlLength = htmlString.length();
		String tmpStr = "";
		int beginStrIndex = 0;
		
		for (int i = 0; i < htmlLength; i++) {
			
			if (htmlString.charAt(i) == '>' &&
				tmpStr.length() > 3) {	// check for closing tags
				
				
				if (tmpStr.substring(tmpStr.length() - 3).equals(tableDataEnd) ) {
					// add flight item to array
					System.out.println(flightDataIndex);
					flightData[flightDataIndex++] = 
							tmpStr.substring(beginStrIndex, tmpStr.length() - closingDataLength );
				}
				
				
				else if (tmpStr.substring(tmpStr.length() - 2).equals(tableData) ) {
					beginStrIndex = i + 1; // set starting index for flight item
					// i + 1 for starting right after the closing > tag
				}
				
				
				else if (tmpStr.substring(tmpStr.length() - 3).equals(tableRowEnd) ) {
					flightsArrayList.add(new Flight( // end of table row
							flightData[0],
							flightData[1],
							flightData[2],
							flightData[3],
							flightData[4],
							flightData[5],
							flightData[6]
					));
					flightDataIndex = 0; // initialize index for populating the next array
				}
				
				
			}
			tmpStr = tmpStr + Character.toString(htmlString.charAt(i) );
		}
		
		
		for (Flight f : flightsArrayList) {
			System.out.println(
					f.date + "\t" + 
					f.flightNumber + "\t" + 
					f.airline + "\t" +
					f.to + "\t" + 
					f.plannedArrival + "\t" + 
					f.realArrival + "\t" +
					f.status
					);
		}
	}
	*/
	
	
}
