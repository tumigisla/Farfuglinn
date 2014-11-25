package hbv1.farfuglinn;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;



public class UpdateTask extends AsyncTask<Void, Void, Void>{

    private Context mCon;
     

    
    public UpdateTask(Context con)
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
        Toast.makeText(mCon, "Flights are up tp date!", 
                Toast.LENGTH_LONG).show();
         
        // Change the menu back
        ((MainActivity) mCon).resetUpdating();
    }
}

