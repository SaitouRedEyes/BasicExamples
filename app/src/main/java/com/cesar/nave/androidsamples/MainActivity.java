package com.cesar.nave.androidsamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Log.d("ACTIVITY LIFECYCLE", "onCreate");
        setContentView(R.layout.activity_main);

        String androidContents = "Linear Layout,Relative Layout,Arkanoid,Intent,Broadcast Receiver,Notification,Alarm," +
                                 "Service,Handler,SMS,Audio,Video,Camera,Socket TCP Client,HTTP Client,UDP Client";
        String[] androidContentsSplit = androidContents.split(",");

        ArrayAdapterItem adapter = new ArrayAdapterItem(this,
                R.layout.activity_main_list_view_row_item,
                androidContentsSplit);

        ListView listViewItems = (ListView) findViewById(R.id.activityMainListView);
        listViewItems.setAdapter(adapter); //setAdapter calls the method getView from ArrayAdapterItem to inflate the layout.
        listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem());
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d("ACTIVITY LIFECYCLE", "onStart");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("ACTIVITY LIFECYCLE", "onResume");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d("ACTIVITY LIFECYCLE", "onRestart");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("ACTIVITY LIFECYCLE", "onPause");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d("ACTIVITY LIFECYCLE", "onStop");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d("ACTIVITY LIFECYCLE", "onDestroy");
    }
}