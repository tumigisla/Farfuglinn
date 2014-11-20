package com.example.farfuglinn;
import java.math.*;

import android.util.Log;


//Quick fix to store boolean value when refreshing main activity
public class LanguagesCheck {	

		public static boolean lang = true;
		public static boolean onClick =true;
		public static boolean counterinn = true;
		public static int cout = 0;
		
		
		
		
		public static int atli(int cout)
		{
			return cout;
		}
		
		

		public static int counter = cout;
		
		public static boolean count()
		{
			if (counter % 2 == 0)
				counterinn = false;
			else 
				counterinn = true;
			
			return counterinn;
		}
		//lang = true tha island
		public static boolean change(boolean landid)
		{
			if (landid = false )
					onClick = true;
			else onClick = false;
			
			return onClick;
			
			
		}
		
		
		public static boolean isTrue(){
			if (onClick == false)
					lang = false;
			else lang = true;
			
		return lang;
		}
		
	}


