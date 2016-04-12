package com.cesar.nave.androidsamples.arkanoid;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Player
{
    private static Player instance;

    private Paint blue;
    private float x, y, width, height, speedX;
    private boolean isMoving, isMovingLeft;

    private Player()
    {
        blue = new Paint();
        blue.setARGB(200, 0, 0, 255);

        width = ArkanoidView.screenW * 0.2f;
        height = ArkanoidView.screenH * 0.03f;
        x = (ArkanoidView.screenW / 2) - (width / 2);
        y = ArkanoidView.screenH * 0.8f;

        speedX = 7f;
        isMoving = isMovingLeft = false;
    }

    public static Player getInstance()
    {
        if(instance == null) instance = new Player();

        return instance;
    }

    public float GetX() { return x; }
    public float GetY() { return y; }
    public float GetW() { return width; }
    public float GetH() { return height; }

    public void draw(Canvas canvas)
    {
        canvas.drawRect(x, y, x + width, y + height, blue);
    }

    public void update()
    {
        if(isMoving)
        {
            if(isMovingLeft) x  -= speedX;
            else x += speedX;
        }

        CollisionWithScreen();
    }

    public void preUpdate(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN ||
                event.getAction() == MotionEvent.ACTION_MOVE)
        {
            isMoving = true;
            isMovingLeft = x > event.getRawX(); // x = true || false
        }
        else if(event.getAction() == MotionEvent.ACTION_UP) isMoving = false;
    }

    private void CollisionWithScreen()
    {
        if(x < 0) x += speedX;
        else if(x + width > ArkanoidView.screenW) x -= speedX;
    }
}