package hbv1.farfuglinn;

import java.io.File;
import java.util.ArrayList;

import android.app.Fragment;

import junit.framework.TestCase;

public class StreamTest extends TestCase{
	Stream stream = new Stream();
	Flight flight= new Flight("s","s","s","s","s","s","s");
	ArrayList<Flight> list1 = new ArrayList<Flight>();
	ArrayList<Flight> list2 = new ArrayList<Flight>();

	public void testSaveList() {
		fail("Not yet implemented");
	}
		/*list1.add(flight);
		Stream.saveList(getActivity(), list2, stream.fileName);
		File file = new File(stream.fileName);
		assertTrue(file.exists());
	}*/

	public void testReadFromFile() {
		fail("Not yet implemented");
	}

}
