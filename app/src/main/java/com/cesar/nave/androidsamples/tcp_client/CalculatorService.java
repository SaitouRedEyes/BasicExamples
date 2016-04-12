package com.cesar.nave.androidsamples.tcp_client;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class CalculatorService extends Service
{
    private final CalculatorBinder connection = new CalculatorBinder();
    private SocketRequests requests;

    public class CalculatorBinder extends Binder
    {
        public CalculatorService GetService() { return CalculatorService.this; }
    }

    public SocketRequests GetRequests() { return requests; }

    @Override
    public IBinder onBind(Intent intent) { return connection; }

    @Override
    public void onCreate()
    {
        super.onCreate();
        requests = new SocketRequests();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        requests = null;
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
    }
}