package com.cesar.nave.androidsamples.audio;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cesar.nave.androidsamples.R;

public class AudioActivity extends AppCompatActivity
{
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Audio Sample");
        setContentView(R.layout.audio);

        mediaPlayer = new MediaPlayer();

        final Button buttonPlayRawFolder = (Button) findViewById(R.id.audioButtonPlayRawFolder);
        final Button buttonStopRawFolder = (Button) findViewById(R.id.audioButtonStopRawFolder);
        final Button buttonPlaySDCard = (Button) findViewById(R.id.audioButtonPlaySDCard);
        final Button buttonStopSDCard = (Button) findViewById(R.id.audioButtonStopSDCard);

        buttonPlayRawFolder.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!mediaPlayer.isPlaying())
                {
                    mediaPlayer = MediaPlayer.create(v.getContext(), R.raw.intro);
                    mediaPlayer.start();
                }
            }
        });

        buttonStopRawFolder.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) { StopAudio(); }
        });

        buttonPlaySDCard.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!mediaPlayer.isPlaying())
                {
                    try
                    {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getPath() + "/audio/intro.mp3");
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    }
                    catch (Exception e) { Toast.makeText(v.getContext(), "Problems to load audio", Toast.LENGTH_SHORT).show(); }
                }
            }
        });

        buttonStopSDCard.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) { StopAudio(); }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mediaPlayer.release();
    }

    private void StopAudio()
    {
        if(mediaPlayer.isPlaying()) mediaPlayer.stop();
    }
}