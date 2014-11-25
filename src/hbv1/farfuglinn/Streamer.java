//�essi klasi ver�ur l�klegast ekki nota�ur, f�kk �etta ekki almennilega til a� virka. 
//�tla k�kja SQL d�ti� hj� Tuma.

package hbv1.farfuglinn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamConstants;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import android.os.Environment;

public class Streamer extends OutputStream implements ObjectOutput, ObjectStreamConstants, Serializable {
	// save-ar Flight array � skr�.
	public static void out(ArrayList<Flight> array) {	
		try
		{
			File sdCard = Environment.getExternalStorageDirectory();
			File dir = new File (sdCard.getAbsolutePath() + "/assets/objectfiles");
			dir.mkdirs();
			File file1 = new File(dir, "yourflights.ser");
			
			FileOutputStream file = new FileOutputStream(file1);
			ObjectOutputStream outf = new ObjectOutputStream(file);
			
			
			for (Flight f : array){   
				outf.writeObject(f+"/n");
				outf.close();
				//System.out.println("Done");
				}
		}
		catch(Exception ex){
				ex.printStackTrace();
		}	
	}
	
	// les �r skr� � flight array.
	public static void in(ArrayList<Flight> array){
		try{
			
			FileInputStream file = new FileInputStream("yourflights.ser");
			ObjectInputStream inf = new ObjectInputStream(file);
			
			array.add((Flight) inf.readObject());
			
			inf.close();}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}


	@Override
	public void writeBoolean(boolean val) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeByte(int val) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeBytes(String str) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeChar(int val) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeChars(String str) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeDouble(double val) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeFloat(float val) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeInt(int val) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeLong(long val) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeShort(int val) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeUTF(String str) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeObject(Object obj) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void write(int oneByte) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
