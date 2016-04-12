package com.cesar.nave.androidsamples.handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cesar.nave.androidsamples.R;

public abstract class HandlerActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Handler Sample");
        setContentView(R.layout.handler);

        final Button buttonSum = (Button) findViewById(R.id.handlerButtonSum);

        buttonSum.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText fieldN1 = (EditText) findViewById(R.id.handlerFieldN1);
                EditText fieldN2 = (EditText) findViewById(R.id.handlerFieldN2);

                if(!fieldN1.getText().toString().equals("") && !fieldN2.getText().toString().equals(""))
                {
                    Sum(Float.parseFloat(fieldN1.getText().toString()), Float.parseFloat(fieldN2.getText().toString()));
                }
                else Toast.makeText(v.getContext(), "Empty field", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected abstract void Sum(float n1, float n2);
}