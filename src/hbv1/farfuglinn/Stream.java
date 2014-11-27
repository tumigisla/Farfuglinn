/*
 * Trausti Már Svavarsson
 * 26.11.2014
 * Saves the selected flightslist to a file and
 * loads flightslist from that file.
 */

package hbv1.farfuglinn;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import android.content.Context;

public class Stream{
	public static String fileName= "yourlist.ser";
	
	//save yourflightlist into empty file
	public static void saveList(Flight flight ,Context context, ArrayList<Flight> list){
		System.out.println("hallo");
		try{
			context.deleteFile(fileName);
			FileOutputStream fileOutputStream = context.openFileOutput(fileName,Context.MODE_PRIVATE);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(list);
			objectOutputStream.close();
			fileOutputStream.close();
			
		}
		catch(IOException e){
			e.printStackTrace();
			
		}
	}
	//load from file into yourflightlist
	@SuppressWarnings("unchecked")
	public static ArrayList<Flight> readFromFile(Context context) {
			ArrayList<Flight> Stream=null;
		try{
			
			FileInputStream fileInputStream = context.openFileInput(fileName);
	        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	        Stream = (ArrayList<Flight>) objectInputStream.readObject();
	        objectInputStream.close();
	        fileInputStream.close();
	        //public yourflightslist gets the data.
	        YourFlights.yourFlightsList=Stream;
	        
		}
		catch(IOException e) {
	       e.printStackTrace();
	        
	    }
		catch (ClassNotFoundException e) {
	        e.printStackTrace();
	      
	    }
		return Stream;
	    
	}
}