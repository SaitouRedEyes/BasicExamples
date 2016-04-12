package com.cesar.nave.androidsamples.intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cesar.nave.androidsamples.R;

public class IntentActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Intents Sample");
        setContentView(R.layout.intent);

        Button buttonFacebook = (Button) findViewById(R.id.intentButtonFacebook);
        Button buttonCall = (Button) findViewById(R.id.intentButtonCall);
        Button buttonGeoLocalization = (Button) findViewById(R.id.intentButtonGeoLocalization);
        Button buttonGeoDestination = (Button) findViewById(R.id.intentButtonGeoDestination);
        Button buttonContacts = (Button) findViewById(R.id.intentButtonContacts);
        Button buttonWebAudio = (Button) findViewById(R.id.intentButtonWebAudio);
        Button buttonHelloWorld = (Button) findViewById(R.id.intentButtonHelloWorld);

        buttonFacebook.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com")));
            }
        });

        buttonCall.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try { startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:988931303"))); }
                catch(SecurityException ex) { Toast.makeText(v.getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show(); }
            }
        });

        buttonGeoLocalization.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Rua+Uruguai,Rio+de+Janeiro"));

                //Need have the maps app to test on emulator.
                if(i.resolveActivity(getPackageManager()) != null) startActivity(i);
                else Toast.makeText(v.getContext(), "You don't have maps installed", Toast.LENGTH_SHORT).show();
            }
        });

        buttonGeoDestination.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String source = "-22.911667,+-43.230278";
                String destination = "-25.443195,+-49.280977";
                String url = "https://www.google.com.br/maps/dir/"+source+"/"+destination+"/";

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });

        buttonContacts.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Intent.ACTION_PICK, Uri.parse("content://com.android.contacts/contacts/")));
            }
        });

        buttonWebAudio.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tonycuffe.com/mp3/tailtoddle_lo.mp3"));
                i.setType("audio/a*");

                if(i.resolveActivity(getPackageManager()) != null) startActivity(i);
                else Toast.makeText(v.getContext(), "You don't have any music player app installed", Toast.LENGTH_SHORT).show();
            }
        });

        buttonHelloWorld.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Opening activity from another project.
                Intent i = new Intent("TEST_ACTION");
                i.addCategory("TEST_CATEGORY");
                i.putExtra(Intent.EXTRA_TEXT, "Intent-Filter Sample Message Received");

                if(i.resolveActivity(getPackageManager()) != null) startActivity(i);
                else Toast.makeText(v.getContext(), "You don't have this app installed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}