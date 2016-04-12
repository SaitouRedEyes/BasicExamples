package com.cesar.nave.androidsamples.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cesar.nave.androidsamples.R;

public class LinearLayoutActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Linear Layout Sample");
        setContentView(R.layout.linear_layout);
    }
}