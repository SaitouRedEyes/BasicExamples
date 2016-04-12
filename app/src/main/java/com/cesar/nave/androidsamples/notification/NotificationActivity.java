package com.cesar.nave.androidsamples.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.cesar.nave.androidsamples.R;

public class NotificationActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Notification Sample");
        setContentView(R.layout.notification);

        Button buttonSendNotification = (Button) findViewById(R.id.notificationButtonSend);

        buttonSendNotification.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) { Send(); }
        });
    }

    private void Send()
    {
        //Building the notification.
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                                                .setSmallIcon(R.drawable.notification_icon)
                                                .setContentTitle("My notification")
                                                .setContentText("Hello World!")
                                                .setAutoCancel(true);

        //Intent to be started if user activate the notification panel.
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, ExecuteNotificationsActivity.class), 0);

        //Connecting notification with intent.
        mBuilder.setContentIntent(pi);

        //Need permission on manifest.
        mBuilder.setVibrate(new long[] {100, 250, 100, 500});

        //Getting the OS notification service.
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //Sending the notification.
        mNotificationManager.notify(R.string.app_name, mBuilder.build());
    }
}