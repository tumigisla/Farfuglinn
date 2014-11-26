/*
 * Atli Sigurðsson
 * 26.11.2014
 * Runs for 4 seconds and then displays an "up-to-date" message.
 * Was meant to be used for the refresh activity.
 */
package hbv1.farfuglinn;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;



public class NOT_BEGIN_USED_UpdateTask extends AsyncTask<Void, Void, Void>{

	private Context mCon;



	public NOT_BEGIN_USED_UpdateTask(Context con)
	{
		mCon = con;
	}



	@Override
	protected Void doInBackground(Void... nope) {
		try {
			// Set a time to simulate a long update process.
			Thread.sleep(4000);

			return null;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	protected void onPostExecute(Void nope) {
		// Give some feedback on the UI.
		Toast.makeText(mCon, "Flights are up to date!",
				Toast.LENGTH_LONG).show();

		// Change the menu back
		((MainActivity) mCon).resetUpdating();
	}
}

