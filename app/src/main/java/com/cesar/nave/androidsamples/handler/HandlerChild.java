package com.cesar.nave.androidsamples.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class HandlerChild extends HandlerActivity implements Runnable
{
    private float n1, n2;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void Sum(float n1, float n2)
    {
        this.n1 = n1;
        this.n2 = n2;

        handler = MessagesManager.GetInstance(getWindow().getDecorView().getRootView());

        new Thread(this).start();
    }

    @Override
    public void run()
    {
        //android.view.ViewRoot$CalledFromWrongThreadException:
        //only the original thread that created a view hierarchy can touch its views.
        //TextView fieldResult = (TextView) findViewById(R.id.handlerLabelSum);
        //fieldResult.setText(String.valueOf("SUM: " + (n1 + n2)));

        //Solution is use Handler to send a message
        //to the main thread message queue tp update the view
        Bundle b = new Bundle();
        b.putString("SUM", String.valueOf(n1 + n2));

        Message msg = new Message();
        msg.what = MessagesManager.SUM;
        msg.setData(b);

        handler.sendMessage(msg);

        //message with delay
        //handler.sendMessageDelayed(msg, 3000);
    }
}