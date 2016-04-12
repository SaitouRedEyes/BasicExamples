package com.cesar.nave.androidsamples.sms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cesar.nave.androidsamples.R;

public class SMSActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("SMS Sample");
        setContentView(R.layout.sms);

        final Button buttonSmsSend = (Button) findViewById(R.id.smsButtonSend);

        buttonSmsSend.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText fieldSmsNumber = (EditText) findViewById(R.id.smsFieldNumber);
                EditText fieldSmsMessage = (EditText) findViewById(R.id.smsFieldText);

                MySmsManager.GetInstance().SendSMS(v.getContext(), fieldSmsNumber.getText().toString(), fieldSmsMessage.getText().toString());
            }
        });
    }
}