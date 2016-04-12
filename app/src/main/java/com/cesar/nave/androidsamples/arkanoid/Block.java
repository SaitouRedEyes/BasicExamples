package com.cesar.nave.androidsamples.arkanoid;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Block
{
    private Paint green;
    private float x, y, width, height;
    private boolean removed;

    public Block(int i, int j, int columns)
    {
        green = new Paint();
        green.setARGB(235, 0, 255, 0);

        width = (ArkanoidView.screenW / columns) - ((ArkanoidView.screenW * 0.02f) + 2 * columns) / columns;
        height = ArkanoidView.screenH * 0.05f;
        this.x = (ArkanoidView.screenW * 0.02f) + j * width + (j * 2);
        this.y = (ArkanoidView.screenH * 0.05f) + i * height + (i * 2);

        removed = false;
    }

    public float GetX() { return x; }
    public float GetY() { return y; }
    public float GetW() { return width; }
    public float GetH() { return height; }
    public boolean GetRemoved() { return removed; }
    public void SetRemoved(boolean value) { removed = value; }

    public void draw(Canvas canvas)
    {
        if(!removed) canvas.drawRect(x, y, x + width, y + height, green);
    }
}