/*
 * Tumi Snær Gíslason
 * 26.11.2014
 * Fetches HTML source code and trims it to get
 * the desired data.
 */
package hbv1.farfuglinn;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



public class HtmlScraper {

	private int closingDataLength = 4;  // minus the > tag in the end
	private String htmlString;

	private String tableData = "td";
	private String tableDataEnd = "/td";
	private String tableEnd = "</tr>";
	private String tableRowEnd = "/tr";
	private URL url;

	public HtmlScraper (String url) {
		try {
			this.url = new URL(url);
		}
		catch (MalformedURLException e) {}
	}


	// Trims the html source code and populates
	// the array list with the desired results
	public ArrayList<Flight> deliverResults () {

		ArrayList<Flight> flightsArrayList = new ArrayList<Flight>();

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


	// Go to url and get the source code
	public void getHtmlString() {

		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = new StringBuilder();

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
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

		htmlString = part.substring(0, part.indexOf(end));

	}

}
