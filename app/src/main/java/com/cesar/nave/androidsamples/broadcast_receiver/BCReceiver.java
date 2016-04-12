package com.cesar.nave.androidsamples.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony.Sms.Intents;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.cesar.nave.androidsamples.sms.MySmsManager;

public class BCReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        switch(intent.getAction())
        {
            case Intent.ACTION_BOOT_COMPLETED: Toast.makeText(context, "System On", Toast.LENGTH_LONG).show(); break;
            case "BCReceiver": Toast.makeText(context, "Broadcast received from BroadcastActivity", Toast.LENGTH_LONG).show(); break;
            case "AlarmReceiver": Toast.makeText(context, "Broadcast received from AlarmActivity", Toast.LENGTH_LONG).show(); break;
            case Intents.SMS_RECEIVED_ACTION: GetSmsMessage(context, intent); break;
        }
    }

    private void GetSmsMessage(Context c, Intent i)
    {
        SmsMessage msg = MySmsManager.GetInstance().SMSReceive(i);

        if(msg != null) Toast.makeText(c, "This message came from: " + msg.getDisplayOriginatingAddress(), Toast.LENGTH_LONG).show();
    }
}