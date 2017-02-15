package com.cesar.nave.androidsamples.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cesar.nave.androidsamples.R;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity
{
    private AlarmManager alarmManager;
    private PendingIntent alarmReceiverIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Alarms Sample");
        setContentView(R.layout.alarm);

        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmReceiverIntent = PendingIntent.getBroadcast(this, 0, new Intent("AlarmReceiver"), 0);

        //cancel alarm for tests.
        //alarmManager.cancel(alarmReceiverIntent);

        final Button buttonElapsedRealTimeRepeat = (Button) findViewById(R.id.alarmButtonElapsedRTRepeat);
        final Button buttonElapsedRealTime = (Button) findViewById(R.id.alarmButtonElapsedRT);
        final Button buttonRTCExact = (Button) findViewById(R.id.alarmButtonRTCExact);
        final Button buttonRTCInexact = (Button) findViewById(R.id.alarmButtonRTCInexact);

        buttonElapsedRealTimeRepeat.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) { SetElapsedRealTimeRepeatAlarm(); }
        });

        buttonElapsedRealTime.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) { SetElapsedRealTimeAlarm(); }
        });

        buttonRTCExact.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) { SetRTCExactAlarm(); }
        });

        buttonRTCInexact.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) { SetRTCInexactAlarm(); }
        });
    }

    private void SetElapsedRealTimeRepeatAlarm()
    {
        //Wake up the device to fire the alarm in 30 minutes, and every 30 minutes after that:
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                AlarmManager.INTERVAL_HALF_HOUR,
                AlarmManager.INTERVAL_HALF_HOUR,
                alarmReceiverIntent);

        //Toast.makeText(this, "Alarm set to 30 min", Toast.LENGTH_SHORT).show();
    }

    private void SetElapsedRealTimeAlarm()
    {
        //Wake up the device to fire a one-time (non-repeating) alarm in 5 seconds:
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + (5 * 1000),
                alarmReceiverIntent);

        //Toast.makeText(this, "Alarm set to 5 seconds: " + currTime, Toast.LENGTH_SHORT).show();
    }

    private void SetRTCExactAlarm()
    {
        //Wake up the device to fire the alarm at precisely 8:30 a.m., and every 60 seconds thereafter:
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 30);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), 1000 * 60,
                alarmReceiverIntent);

        //Toast.makeText(this, "Alarm set to a specific hour of day and repeat each 5 seconds", Toast.LENGTH_SHORT).show();
    }

    private void SetRTCInexactAlarm()
    {
        //Wake up the device to fire the alarm at approximately 3:00 p.m., and repeat once a day at the same time:
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 16);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                alarmReceiverIntent);

        //Toast.makeText(this, "Alarm set to 15:00 every day", Toast.LENGTH_SHORT).show();
    }
}