package com.cesar.nave.androidsamples.tcp_client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cesar.nave.androidsamples.R;

public class CalculatorActivity extends AppCompatActivity implements ServiceConnection, Runnable
{
    private CalculatorService myService;
    private SocketRequests.Services currService;
    private final ServiceConnection connection = this;
    private Intent i;
    private Handler handler;
    private Button buttonSum;
    private TextView labelResult;
    private int pingTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("TCP Client Sample");
        setContentView(R.layout.handler);

        i = new Intent(this, CalculatorService.class);

        pingTime = 0;

        handler = new Handler();
        startService(i);

        labelResult = (TextView) findViewById(R.id.handlerLabelSum);
        labelResult.setVisibility(View.INVISIBLE);

        buttonSum = (Button) findViewById(R.id.handlerButtonSum);
        buttonSum.setEnabled(false);

        buttonSum.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fieldN1 = (EditText) findViewById(R.id.handlerFieldN1);
                EditText fieldN2 = (EditText) findViewById(R.id.handlerFieldN2);

                if (!fieldN1.getText().toString().equals("") && !fieldN2.getText().toString().equals(""))
                    Sum(fieldN1.getText().toString(), fieldN2.getText().toString());
                else
                    Toast.makeText(v.getContext(), "Missing Data!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        bindService(i, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        handler.removeCallbacks(this);

        if(myService.GetRequests() != null)
        {
            unbindService(connection);
            stopService(i);
        }
    }

    private void Sum(String n1, String n2)
    {
        myService.GetRequests().Sum(n1, n2);
        currService = SocketRequests.Services.SUM;
        pingTime = 0;
        handler.post(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service)
    {
        CalculatorService.CalculatorBinder binder = (CalculatorService.CalculatorBinder) service;
        myService = binder.GetService();
        myService.GetRequests().Connect();
        currService = SocketRequests.Services.CONNECT;
        pingTime = 0;
        handler.post(this);
    }

    @Override
    public void onServiceDisconnected(ComponentName name)
    {
        myService = null;
        handler.removeCallbacks(this);
    }

    @Override
    public void run()
    {
        if(myService.GetRequests().ServerResponse() != null)
        {
            if(currService == SocketRequests.Services.CONNECT) ConnectionResponse(myService.GetRequests().ServerResponse());
            else if (currService == SocketRequests.Services.SUM) SumResponse(myService.GetRequests().ServerResponse());
        }
        else
        {
            if(pingTime < 5)
            {
                pingTime++;
                handler.postDelayed(this, 3000);
                Toast.makeText(this, "Waiting server response - Attempt: " + pingTime, Toast.LENGTH_SHORT).show();
            }
            else
            {
                pingTime = 0;
                Toast.makeText(this, "Server doesn't response. Try again later.", Toast.LENGTH_SHORT).show();
                finish(); //backing to previous Activity
            }
        }

        myService.GetRequests().NullServerResponse();
    }

    private void ConnectionResponse(Object serverResponse)
    {
        if((Boolean) serverResponse)
        {
            Toast.makeText(this, "Server connected!!", Toast.LENGTH_SHORT).show();
            buttonSum.setEnabled(true);
        }
        else
        {
            unbindService(connection);
            stopService(i);

            Toast.makeText(this, "Problems to connect with server", Toast.LENGTH_SHORT).show();
        }
    }

    private void SumResponse(Object serverResponse)
    {
        if(serverResponse != null)
        {
            labelResult.setText(serverResponse.toString());
            labelResult.setVisibility(View.VISIBLE);
        }
        else
        {
            Toast.makeText(this, "Problems to make the Sum operation. Try again later.", Toast.LENGTH_SHORT).show();
            unbindService(connection);
            stopService(i);
            finish();
        }
    }
}