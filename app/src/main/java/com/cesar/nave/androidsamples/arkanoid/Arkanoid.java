package com.cesar.nave.androidsamples.arkanoid;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Arkanoid extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Custom View Sample");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(new ArkanoidView(this));
        GetIntentMessage();
    }

    private void GetIntentMessage()
    {
        Intent i = getIntent();

        if(i != null)
        {
            Bundle params = i.getExtras();

            if(params != null) Toast.makeText(this, params.getString(Intent.EXTRA_TEXT), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        ArkanoidView.isPaused = true;
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        ArkanoidView.isPaused = true;
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        ArkanoidView.isUpdating = false;
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
}