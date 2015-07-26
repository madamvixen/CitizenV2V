package com.example.malabika.test1;

import android.app.Activity;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Malabika on 7/8/2015.
 */
public class MyLocation implements LocationListener
{
    String mTag = "Citizen v2v";
    public static boolean updateDisplay = true;

    private final Context context;
    LocationManager mLocationManager;
    private Location myLocation = null;

    public MyLocation(Context context)
    {
        this.context = context;
        getMyLocation();
    }


    @Override
    public void onLocationChanged(Location location)
    {
        Log.d("Citizen V2v", "in onLocationChanged method");
        myLocation = location;

        //go back to the calling activity
        Intent newIntent = new Intent(context, MainActivity.myBroadcastReceiver.class);
        newIntent.setAction(Intent.ACTION_VIEW);
        newIntent.addCategory("update location");
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        newIntent.putExtra("Location", myLocation);

        Log.d("Citizen V2V", "UpdateDisplay" );
        context.sendBroadcast(newIntent);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public Location getMyLocation() {

        //Is GPS available?

        //Is Network available?

        Log.d(mTag, "in Get my location");
        mLocationManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(mLocationManager.NETWORK_PROVIDER, 0, 0, this);
        if(myLocation!=null)
        {
            Log.d("Citizen V2V", "my Location not null");
            return myLocation;
        }

        Log.d("Citizen V2V", "my Location null");
        if(mLocationManager!=null) {
            myLocation = mLocationManager.getLastKnownLocation(mLocationManager.NETWORK_PROVIDER);
           // Log.d("Citizen V2V", String.valueOf(myLocation.getAccuracy()));

            if(myLocation!=null) {
                myLocation.setAccuracy(90);
                Log.d("Citizen V2V ", getLatitude());
                Log.d("Citizen V2V ", getLongitude());
            }
        }

//        Toast.makeText(context,"My present location is Latitude "+ (String.valueOf(myLocation.getLatitude())+ "\n Longitude: "+ String.valueOf(myLocation.getLongitude())), Toast.LENGTH_LONG).show();

        return myLocation;
    }


    public String getLatitude()
    {
        double d =0.0;
        if(myLocation!=null)
        {
            d = myLocation.getLatitude();
        }
        return String.valueOf(d);
    }

    public String getLongitude()
    {
        double d = 0.0;
        if(myLocation!=null)
        {
            d = myLocation.getLongitude();
        }
        return String.valueOf(d);
    }
    //check if network service enabled

    //check if GPS enabled

    //check if location services enabled

    //calculate the speed using latitude and longitude
}
