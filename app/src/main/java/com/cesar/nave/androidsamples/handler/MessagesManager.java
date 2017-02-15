package com.cesar.nave.androidsamples.handler;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.cesar.nave.androidsamples.R;

class MessagesManager extends Handler
{
    static final int SUM = 1;

    static MessagesManager instance;
    private View view;

    private MessagesManager(View v)
    {
        view = v;
    }

    static MessagesManager GetInstance(View v)
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