package com.cesar.nave.androidsamples.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ExecuteNotificationsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Execute Notifications Sample");

        Toast.makeText(this, "Notification OK. Do something now!!", Toast.LENGTH_LONG).show();
    }
}