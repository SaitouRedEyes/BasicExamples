package com.cesar.nave.androidsamples.arkanoid;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball
{
    private Paint red;
    private float x, y, radius, speedX, speedY;
    private boolean couldCollide;
    private Player player;
    private BlockManager bm;

    public Ball()
    {
        red = new Paint();
        red.setARGB(255, 255, 0, 0);

        x = ArkanoidView.screenW / 2;
        y = ArkanoidView.screenH / 2;
        radius = ArkanoidView.screenW * 0.04f;
        speedX = 6;
        speedY = -6f;
        couldCollide = true;

        player = Player.getInstance();
        bm = BlockManager.getInstance();
    }

    public void draw(Canvas canvas)
    {
        canvas.drawCircle(x, y, radius, red);
    }

    public void update()
    {
        x += speedX;
        y += speedY;

        CollisionWithScreen();
        CollisionWithBlocks();
        CollisionWithPlayer();
    }

    private void ChangeBallState(boolean width)
    {
        if(couldCollide)
        {
            if(width) speedX *= -1;
            else speedY *= -1;

            couldCollide = false;
        }
    }

    private void CollisionWithScreen()
    {
        if(x + radius > ArkanoidView.screenW || x - radius < 0) ChangeBallState(true);
        else if(y - radius < 0) ChangeBallState(false);
        else if(y + radius > ArkanoidView.screenH) ArkanoidView.isDead = true;
        else couldCollide = true;
    }

    private void CollisionWithBlocks()
    {
        for(Block b : bm.blocks)
        {
            if (!b.GetRemoved() &&
                    x - radius < b.GetX() + b.GetW() && x + radius > b.GetX() &&
                    y - radius < b.GetY() + b.GetH() && y + radius > b.GetY())
            {
                speedY *= -1;
                b.SetRemoved(true);
                //bm.SetNumberOfBlocksRemoved();
                Score.score += 10;
                break;
            }
        }
    }

    private void CollisionWithPlayer()
    {
        if (x - radius < player.GetX() + player.GetW() && x + radius > player.GetX() &&
                y - radius < player.GetY() + player.GetH() && y + radius > player.GetY())
        {
            speedY *= -1;
            couldCollide = false;
        }
    }
}