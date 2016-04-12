package com.cesar.nave.androidsamples.service;

import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cesar.nave.androidsamples.R;

public class ServiceActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Service Sample");
        setContentView(R.layout.service);

        final Button buttonStartService = (Button) findViewById(R.id.serviceButtonStart);
        final Button buttonStopService = (Button) findViewById(R.id.serviceButtonStop);
        final Button buttonBindService = (Button) findViewById(R.id.serviceButtonBind);

        final Intent intent = new Intent(this, CounterService.class);

        buttonStartService.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!isMyServiceRunning(CounterService.class)) startService(intent);
            }
        });

        buttonStopService.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) { stopService(intent); }
        });

        buttonBindService.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) { startActivity(new Intent(v.getContext(), BindServiceActivity.class)); }
        });
    }

    private boolean isMyServiceRunning(Class<?> serviceClass)
    {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
        {
            if (serviceClass.getName().equals(service.service.getClassName())) return true;
        }
        return false;
    }
}