package com.cesar.nave.androidsamples.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cesar.nave.androidsamples.R;

public class RelativeLayoutActivity extends AppCompatActivity
{
    TextView labelAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Relative Layout Sample");
        setContentView(R.layout.relative_layout);

        final Button buttonLogin = (Button) findViewById(R.id.relativeLayoutButtonLogin);
        final EditText fieldLogin = (EditText)findViewById(R.id.relativeLayoutFieldLogin);
        final EditText fieldPass = (EditText)findViewById(R.id.relativeLayoutFieldPass);
        labelAuth = (TextView) findViewById(R.id.relativeLayoutLabelAuth);

        buttonLogin.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String login = fieldLogin.getText().toString();
                String pass = fieldPass.getText().toString();

                if(login.equals("a") && pass.equals("d")) labelAuth.setVisibility(View.VISIBLE);
                else Toast.makeText(v.getContext(), "Incorrect Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        labelAuth.setVisibility(View.INVISIBLE);
    }
}