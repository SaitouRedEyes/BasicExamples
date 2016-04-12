package com.cesar.nave.androidsamples.broadcast_receiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cesar.nave.androidsamples.R;

public class BroadcastActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Broadcast Samples");
        setContentView(R.layout.broadcast);

        Button buttonSendBroadcast = (Button) findViewById(R.id.broadcastButtonSend);

        buttonSendBroadcast.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Send a broadcast to OS.
                sendBroadcast(new Intent("BCReceiver"));
            }
        });
    }
}