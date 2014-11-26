/*
 * Ellert Finnbogi Eiríksson
 * 26.11.2014
 * Stores a global variable for keeping track of which language
 * is being used (English/Icelandic).
 */
package hbv1.farfuglinn;

//Quick fix to store int value when refreshing main activity on buttonclick to change language
public class LanguagesCheck {


	public static int cout = 1;

	public static int increment()
	{
		cout++;
		return cout;
	}

}


