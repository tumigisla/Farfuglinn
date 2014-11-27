package hbv1.farfuglinn;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

public class HtmlScraperTest extends TestCase 
{
	public static ArrayList<Flight> resultsList;
	private String url = "http://www.kefairport.is/Flugaaetlun/Komur/";
	HtmlScraper htmlScraper = new HtmlScraper(url);

	@Test
	public void testHtmlScraper() { 
		assertNotNull(url);
		assertEquals(this.url,url);	
		assertTrue(resultsList==null);
	}

	@Test
	public void testDeliverResults() {
		assertTrue(resultsList == null);
		htmlScraper.getHtmlString();
		resultsList = htmlScraper.deliverResults();
		assertNotNull(resultsList);
	}
	@Test
	public void testGetHtmlString() {
		htmlScraper.getHtmlString();
	}
}
