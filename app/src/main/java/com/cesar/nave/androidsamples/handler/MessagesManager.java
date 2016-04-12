package com.cesar.nave.androidsamples.handler;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.cesar.nave.androidsamples.R;

public class MessagesManager extends Handler
{
    public static final int SUM = 1;

    private static MessagesManager instance;
    private View view;

    private MessagesManager(View v)
    {
        view = v;
    }

    public static MessagesManager GetInstance(View v)
    {
        if(instance == null) instance = new MessagesManager(v);

        return instance;
    }

    @Override
    public void handleMessage(Message msg)
    {
        super.handleMessage(msg);

        switch(msg.what)
        {
            case SUM: TextView labelSum = (TextView) view.findViewById(R.id.handlerLabelSum);
                               labelSum.setText(msg.getData().getString("SUM")); break;
        }
    }
}