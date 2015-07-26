package com.example.malabika.test1;

import android.app.AlertDialog;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.content.*;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    String mTag = "CITIZEN V@V";
    //Button btnShowLocation;
    MyLocation mLocation;

    static TextView latTextView;
    static TextView longTextView;
    static TextView speedTextView;

    static String myLong;
    static String myLat;
    static double mySpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(mTag, "this app is called citizen v2v");
        super.onCreate(savedInstanceState);
        Log.d(mTag, "inside on create method");
        //add transition or delay
        setContentView(R.layout.activity_main);

        // btnShowLocation = (Button) findViewById(R.id.startButton);
        latTextView = (TextView) findViewById(R.id.input_gps_location_lat_text);
        longTextView = (TextView) findViewById(R.id.input_gps_location_long_text);
        speedTextView = (TextView) findViewById(R.id.text_speed);


        latTextView.setText("NA");
        longTextView.setText("NA");



    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "Application started! Getting the parameters! ", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //display the present location of the device
        mLocation = new MyLocation(MainActivity.this);

        if(mLocation!=null) {
            latTextView.setText(mLocation.getLatitude());
            longTextView.setText(mLocation.getLongitude());
        }

    }


    private void showWelcomeAlert(Context context) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setMessage("GETTING YOUR GPS LOCATION!!");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public static class myBroadcastReceiver extends BroadcastReceiver {
        Bundle extras;
        Location mLoc;

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("INTENT", "RECEIVED");
            extras = intent.getExtras();
            mLoc = (Location) extras.get("Location");

            if(mLoc!=null) {
                myLong = String.valueOf(mLoc.getLongitude());
                myLat = String.valueOf(mLoc.getLatitude());
            }

//            if (mLocation.hasSpeed()) {
//                mySpeed = mLocation.getSpeed();
//            }
            Log.d("Citizen V2v  ", "Latitude : " + myLat);
            Log.d("Citizen V2V ", "Longitude: " + myLong);
//            Log.d("Citizen V2V", " Speed : " + mySpeed);
            latTextView.setText(myLat);
            longTextView.setText(myLong);
            Toast.makeText(context, " INTENT RECEIVED: Longitude: " + myLong + "\n Latitude: " + myLat, Toast.LENGTH_LONG).show();

        }

    }


}
