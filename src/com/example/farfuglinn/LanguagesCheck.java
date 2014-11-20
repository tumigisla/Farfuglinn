package com.example.farfuglinn;
import java.math.*;


//Quick fix to store boolean value when refreshing main activity
public class LanguagesCheck {	

		public static boolean lang = true;
		public static boolean onClick =true;
		public static boolean counterinn = true;
		
		public static int counter = MainActivity.counter();
		
		public static boolean count()
		{
			if (!(counter % 2 == 0))
				counterinn = true;
			else 
				counterinn = false;
			
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


