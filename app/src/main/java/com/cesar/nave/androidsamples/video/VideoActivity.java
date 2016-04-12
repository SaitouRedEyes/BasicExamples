package com.cesar.nave.androidsamples.video;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.cesar.nave.androidsamples.R;

public class VideoActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Video Sample");
        setContentView(R.layout.video);

        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/video/adeus_pikachu.3gp"));
        videoView.requestFocus();
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                Toast.makeText(getApplicationContext(), "Video Complete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}