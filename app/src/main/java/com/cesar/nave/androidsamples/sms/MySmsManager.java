package com.cesar.nave.androidsamples.sms;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySmsManager
{
    private static MySmsManager instance;

    private MySmsManager()
    {}

    public static MySmsManager GetInstance()
    {
        if(instance != null) return instance;

        instance = new MySmsManager();

        return instance;
    }

    void SendSMS(Context context, String number, String text)
    {
        try
        {
            //Getting the smsManager from the system.
            SmsManager smsManager = SmsManager.getDefault();
            //Creating a pendingIntent to identify the call.
            PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
            //Send the message
            smsManager.sendTextMessage(number, null, text, pIntent, null);
        }
        catch(Exception e) { Toast.makeText(context, "Problem to send SMSActivity", Toast.LENGTH_SHORT).show(); }
    }

    public SmsMessage SMSReceive(Intent i)
    {
        SmsMessage[] messages;

        if (Build.VERSION.SDK_INT >= 19)
        {
            messages = Telephony.Sms.Intents.getMessagesFromIntent(i);

            if(messages != null) return messages[0];
        }
        else
        {
            Object pdus[] = (Object[]) i.getSerializableExtra("pdus");
            SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdus[0]);

            if(msg != null) return msg;
        }

        return null;
    }
}