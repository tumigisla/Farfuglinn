/*  Ellert Finnbogi Eiríksson
*   12.10.2014
*   Tekur inn url frá vefþjónustu og skilar streng með json
*/


package hbv1.farfuglinn;

import java.io.BufferedReader;
//import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;


public class getData_ellert {
	
	public static String getFlightInfo(String url) 
	{

	DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
	//String url = "http://apis.is/flight?language=en&type=departures";
	
	// Depends on your web service"
	HttpPost httppost = new HttpPost(url);
	httppost.setHeader("Content-type", "application/json");
	

	
	InputStream inputStream = null;
	String result = null;
	
	
	try {
	    HttpResponse response = httpclient.execute(httppost);           
	    HttpEntity entity = response.getEntity();

	    inputStream = entity.getContent();
	    // json is UTF-8 by default
	    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    while ((line = reader.readLine()) != null)
	    {
	        sb.append(line + "\n");
	    }
	    result = sb.toString();
	} catch (Exception e) { 
	    // Oops
	}
	finally {
	    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
	}

	
	return result;
	}
}


