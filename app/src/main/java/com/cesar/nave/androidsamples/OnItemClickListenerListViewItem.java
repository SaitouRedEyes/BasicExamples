package com.cesar.nave.androidsamples;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.cesar.nave.androidsamples.alarm.AlarmActivity;
import com.cesar.nave.androidsamples.arkanoid.StartScreen;
import com.cesar.nave.androidsamples.audio.AudioActivity;
import com.cesar.nave.androidsamples.broadcast_receiver.BroadcastActivity;
import com.cesar.nave.androidsamples.camera.CameraActivity;
import com.cesar.nave.androidsamples.handler.HandlerChild;
import com.cesar.nave.androidsamples.http_client.LoginActivity;
import com.cesar.nave.androidsamples.intent.IntentActivity;
import com.cesar.nave.androidsamples.layout.RelativeLayoutActivity;
import com.cesar.nave.androidsamples.layout.LinearLayoutActivity;
import com.cesar.nave.androidsamples.notification.NotificationActivity;
import com.cesar.nave.androidsamples.service.ServiceActivity;
import com.cesar.nave.androidsamples.sms.SMSActivity;
import com.cesar.nave.androidsamples.tcp_client.CalculatorActivity;
import com.cesar.nave.androidsamples.video.VideoActivity;

class OnItemClickListenerListViewItem implements OnItemClickListener
{
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        Context c = view.getContext();

        TextView textViewItem = ((TextView) view.findViewById(R.id.activityMainTextViewItem));

        switch(textViewItem.getText().toString())
        {
            case "Linear Layout": ChangeActivity(c, LinearLayoutActivity.class); break;
            case "Relative Layout": ChangeActivity(c, RelativeLayoutActivity.class); break;
            case "Arkanoid": ChangeActivity(c, StartScreen.class); break;
            case "Intent": ChangeActivity(c, IntentActivity.class); break;
            case "Broadcast Receiver": ChangeActivity(c, BroadcastActivity.class); break;
            case "Notification": ChangeActivity(c, NotificationActivity.class); break;
            case "Alarm": ChangeActivity(c, AlarmActivity.class); break;
            case "Service": ChangeActivity(c, ServiceActivity.class); break;
            case "Handler": ChangeActivity(c, HandlerChild.class); break;
            case "SMS": ChangeActivity(c, SMSActivity.class); break;
            case "Audio": ChangeActivity(c, AudioActivity.class); break;
            case "Video": ChangeActivity(c, VideoActivity.class); break;
            case "Camera": ChangeActivity(c, CameraActivity.class); break;
            case "Socket TCP Client": ChangeActivity(c, CalculatorActivity.class); break;
            case "HTTP Client": ChangeActivity(c, LoginActivity.class); break;
            default:
                Toast.makeText(c, "Go to Fócking Hell", Toast.LENGTH_LONG).show(); break;
        }
    }

    private void ChangeActivity(Context c, Class activity)
    {
        c.startActivity(new Intent(c, activity));
    }
}