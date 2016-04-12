package com.cesar.nave.androidsamples.arkanoid;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Score
{
    public static int score;
    private Paint white;

    public Score()
    {
        score = 0;

        white = new Paint();
        white.setARGB(255, 255, 255, 255);
    }

    public void draw(Canvas canvas)
    {
        canvas.drawText(String.valueOf(score), ArkanoidView.screenW * 0.02f, ArkanoidView.screenH * 0.03f, white);
    }
}