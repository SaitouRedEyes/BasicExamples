package com.cesar.nave.androidsamples.camera;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cesar.nave.androidsamples.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CameraActivity extends AppCompatActivity
{
    private final int MEDIA_TYPE_IMAGE = 1;
    private final int MEDIA_TYPE_VIDEO = 2;
    final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Camera Sample");
        setContentView(R.layout.camera);

        Button buttonTakePicture = (Button) findViewById(R.id.cameraButtonTakePicture);
        Button buttonVideoCapture = (Button) findViewById(R.id.cameraButtonVideoCapture);

        buttonTakePicture.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) { CaptureMedia(MediaStore.ACTION_IMAGE_CAPTURE, MEDIA_TYPE_IMAGE, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE); }
        });

        buttonVideoCapture.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) { CaptureMedia(MediaStore.ACTION_VIDEO_CAPTURE, MEDIA_TYPE_VIDEO, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE); }
        });
    }

    private void CaptureMedia(String captureType, int mediaType, int mediaRequestCode)
    {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(captureType);

        Uri fileUri = getOutputMediaFileUri(mediaType); // create a file to save the image

        if(fileUri != null)
        {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

            if(mediaType == MEDIA_TYPE_VIDEO) intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

            // start the image/video capture Intent
            startActivityForResult(intent, mediaRequestCode);
        }
        else Toast.makeText(this, "Problems to take a picture", Toast.LENGTH_SHORT).show();
    }

    /** Create a file Uri for saving an image or video */
    private Uri getOutputMediaFileUri(int type)
    {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private File getOutputMediaFile(int type)
    {
        // To be safe, you should check that the SDCard is mounted
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyCameraApp");

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists())
            {
                if (!mediaStorageDir.mkdirs())
                {
                    Toast.makeText(this, "Problems to create directory", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }

            // Create a media file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
            File mediaFile;

            if (type == MEDIA_TYPE_IMAGE)      mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
            else if (type == MEDIA_TYPE_VIDEO) mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
            else return null;

            return mediaFile;
        }
        else return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE || requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE)
        {
            if      (resultCode == RESULT_OK) Toast.makeText(this, "Media saved", Toast.LENGTH_LONG).show();
            else if (resultCode == RESULT_CANCELED) Toast.makeText(this, "User cancelled the media capture", Toast.LENGTH_LONG).show();
            else    Toast.makeText(this, "Media capture failed, advise user", Toast.LENGTH_LONG).show();
        }
    }
}