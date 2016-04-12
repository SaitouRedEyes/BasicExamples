package com.cesar.nave.androidsamples.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class CounterService extends Service implements Runnable, Counter
{
    protected int count;
    private boolean active;
    private final LocalBinder connection = new LocalBinder();

    //This class returns to Activity the service reference.
    //With this reference is possible to get the Counter value and show to user.
    public class LocalBinder extends Binder
    {
        public Counter getService() { return CounterService.this; }
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        Log.d("SERVICE SAMPLE", "SERVICE SAMPLE onCreate()");
        active = true;
        new Thread(this).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d("SERVICE SAMPLE", "SERVICE SAMPLE onStart()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        Log.d("SERVICE SAMPLE", "SERVICE SAMPLE onDestroy()");
        active = false;
    }

    //When the service is connected to the Activity,
    //this method is called to return the reference of this service to the Activity.
    @Override
    public IBinder onBind(Intent intent) { return connection; }

    @Override
    public int Count() { return count; }

    @Override
    public void run()
    {
        while(active)
        {
            Log.d("SERVICE SAMPLE", "EXECUTING SERVICE: " + count);
            count++;

            SetInterval();
        }

        count = 0;
        Log.d("SERVICE SAMPLE", "SERVICE SAMPLE: FIM");

        stopSelf();
    }

    private void SetInterval()
    {
        try { Thread.sleep(1000); }
        catch(InterruptedException e) { e.printStackTrace(); }
    }
}