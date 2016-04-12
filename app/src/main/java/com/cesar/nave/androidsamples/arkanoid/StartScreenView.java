package com.cesar.nave.androidsamples.arkanoid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.cesar.nave.androidsamples.R;

public class StartScreenView extends View
{
    private Bitmap background;
    private Paint white;
    private Context context;

    public StartScreenView(Context context)
    {
        super(context);
        Start(context);
    }

    public StartScreenView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        Start(context);
    }

    private void Start(Context context)
    {
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.arkanoid_title_screen);

        white = new Paint();
        white.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawBitmap(background, 0, 0, white);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            Intent i = new Intent(context, Arkanoid.class);

            Bundle params = new Bundle();
            params.putString(Intent.EXTRA_TEXT, "HALLO!!");

            i.putExtras(params);

            context.startActivity(i);
        }
        return true;
    }
}