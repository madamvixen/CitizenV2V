package com.example.malabika.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Malabika on 6/17/2015.
 */
public class LaunchActivity extends Activity
{
    static boolean enhancedMode = false;
    static boolean basicMode = false;
    static boolean rememberChoice = false;
    Button btnstart;
    //LinearLayout background;
    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        Log.d("Start", "im in oncreate of launchscr");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        btnstart = (Button)findViewById(R.id.startButton);

        // add function

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(btnstart.getVisibility()==View.INVISIBLE){
            btnstart.setVisibility(View.VISIBLE);
        }
    }
    public void onLaunchMainActivity(View view)
    {
        // add functionality for start communication with server and update the GPS location
        Intent startApplicationIntent = new Intent(this, MainActivity.class);

        startActivity(startApplicationIntent);
        Log.d("END", "of Launch Screen ");

    }


    @Override
    protected  void onStop()
    {
        super.onStop();
    }
}
