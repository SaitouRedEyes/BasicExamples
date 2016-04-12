package com.cesar.nave.androidsamples.http_client;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class HTTPService extends Service
{
    private final HTTPBinder connection = new HTTPBinder();
    private HTTPRequests requests;

    public class HTTPBinder extends Binder
    {
        public HTTPService GetService() { return HTTPService.this; }
    }

    public HTTPRequests GetRequests() { return requests; }

    @Override
    public IBinder onBind(Intent intent) { return connection; }

    @Override
    public void onCreate()
    {
        super.onCreate();
        requests = new HTTPRequests();
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