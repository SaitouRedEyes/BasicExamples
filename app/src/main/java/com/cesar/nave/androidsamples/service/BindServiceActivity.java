package com.cesar.nave.androidsamples.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cesar.nave.androidsamples.R;

public class BindServiceActivity extends AppCompatActivity implements ServiceConnection
{
    private Counter counter;
    final ServiceConnection connection = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Bind Service Sample");
        setContentView(R.layout.bind_service);

        final Button buttonStartBindService = (Button) findViewById(R.id.serviceButtonStartBind);
        final Button buttonStopBindService = (Button) findViewById(R.id.serviceButtonStopBind);
        final Button buttonGetCounter = (Button) findViewById(R.id.serviceButtonGetCounter);

        buttonStartBindService.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("BIND SERVICE SAMPLE", "START BIND SERVICE");

                bindService(new Intent(BindServiceActivity.this, CounterService.class), connection, Context.BIND_AUTO_CREATE);
                Toast.makeText(v.getContext(), "Bind service", Toast.LENGTH_SHORT).show();
            }
        });

        buttonStopBindService.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                UnbindConnection();
                Toast.makeText(v.getContext(), "Unbind service", Toast.LENGTH_SHORT).show();
            }
        });

        buttonGetCounter.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(counter != null)
                {
                    int count = counter.Count();

                    Toast.makeText(BindServiceActivity.this, "Counter: " + count, Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(BindServiceActivity.this, "The service is not connected to Activity", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        UnbindConnection();
    }

    private void UnbindConnection()
    {
        if(counter != null)
        {
            Log.d("BIND SERVICE SAMPLE", "STOP BIND SERVICE");
            counter = null;
            unbindService(connection);
        } else Log.d("BIND SERVICE SAMPLE", "THE SERVICE ISN'T CONNECTED");
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service)
    {
        //Here we get the reference of the service through IBinder.
        Log.d("BIND SERVICE SAMPLE", "SERVICE CONNECTED");

        CounterService.LocalBinder binder = (CounterService.LocalBinder) service;
        counter = binder.getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name)
    {
        Log.d("BIND SERVICE SAMPLE", "SERVICE DISCONNECTED");
        counter = null;
    }
}