/*
 * Tumi Snær Gíslason
 * 30.10.2014
 * An instance of Flight is one entry in the ListView.
 */
package hbv1.farfuglinn;


public class Flight {

	public String airline;
	public String date;
	public String flightNumber;
	public String plannedArrival;
	public String realArrival;
	public String status;
	public String to;


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
