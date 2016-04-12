package com.cesar.nave.androidsamples.http_client;

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

public class LoginActivity extends AppCompatActivity implements ServiceConnection, Runnable
{
    private HTTPService myService;
    private HTTPRequests.Services currService;
    private final ServiceConnection connection = this;
    private Intent i;
    private Handler handler;
    private Button buttonLogin;
    private TextView labelResult;
    private int pingTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("HTTP Client Sample");
        setContentView(R.layout.login);

        i = new Intent(this, HTTPService.class);

        pingTime = 0;

        handler = new Handler();
        startService(i);

        labelResult = (TextView) findViewById(R.id.httpLabelAuth);
        labelResult.setVisibility(View.INVISIBLE);

        buttonLogin = (Button) findViewById(R.id.httpButtonLogin);
        buttonLogin.setEnabled(false);

        buttonLogin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fieldLogin = (EditText) findViewById(R.id.httpFieldLogin);
                EditText fieldPass = (EditText) findViewById(R.id.httpFieldPass);

                if (!fieldLogin.getText().toString().equals("") && !fieldPass.getText().toString().equals(""))
                    Login(fieldLogin.getText().toString(), fieldPass.getText().toString());
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

    private void Login(String login, String pass)
    {
        myService.GetRequests().Login(login, pass);
        currService = HTTPRequests.Services.LOGIN;
        pingTime = 0;
        handler.post(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service)
    {
        HTTPService.HTTPBinder binder = (HTTPService.HTTPBinder) service;
        myService = binder.GetService();
        myService.GetRequests().Connect();
        currService = HTTPRequests.Services.CONNECT;
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
            if(currService == HTTPRequests.Services.CONNECT) ConnectionResponse(myService.GetRequests().ServerResponse());
            else if (currService == HTTPRequests.Services.LOGIN) LoginResponse(myService.GetRequests().ServerResponse());
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
            buttonLogin.setEnabled(true);
        }
        else
        {
            unbindService(connection);
            stopService(i);

            Toast.makeText(this, "Problems to connect with server", Toast.LENGTH_SHORT).show();
        }
    }

    private void LoginResponse(Object serverResponse)
    {
        labelResult.setText(!serverResponse.toString().equals("") ? serverResponse.toString() : "User not found!!");
        labelResult.setVisibility(View.VISIBLE);
    }
}