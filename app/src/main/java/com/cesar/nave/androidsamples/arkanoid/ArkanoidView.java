package com.cesar.nave.androidsamples.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ArkanoidView extends View implements Runnable
{
    public static int screenW, screenH;
    public static boolean isDead, isPaused, isUpdating;

    private Handler handler;
    private Context c;

    private Paint white;

    private Ball ball;
    private Player player;
    private BlockManager bm;
    private Score score;
    private int highScore;
    private DataStorage dt;

    public ArkanoidView(Context context)
    {
        super(context);
        Start(context);
    }

    public ArkanoidView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        Start(context);
    }

    private void Start(Context context)
    {
        c = context;

        setBackgroundColor(Color.BLACK);

        screenW = c.getResources().getDisplayMetrics().widthPixels;
        screenH = c.getResources().getDisplayMetrics().heightPixels;

        isDead = isPaused = false;
        isUpdating = true;

        white = new Paint();
        white.setARGB(255, 255, 255, 255);

        ball = new Ball();
        player = Player.getInstance();
        bm = BlockManager.getInstance();
        score = new Score();

        dt = new DataStorage();
        highScore = dt.GetHighScore(context);

        handler = new Handler();
        handler.post(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(!isDead && !isPaused) player.preUpdate(event);
        else if(isDead) RestartGame();
        else if(isPaused) isPaused = false;

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if(!isDead && !isPaused)
        {
            ball.draw(canvas);
            player.draw(canvas);
            score.draw(canvas);

            for(Block b : bm.blocks) b.draw(canvas);

            canvas.drawText("High score: " + String.valueOf(highScore), screenW * 0.65f, screenH * 0.03f, white);
        }
        else canvas.drawText("Touch to restart", screenW * 0.2f, screenH * 0.5f, white);
    }

    private void Update()
    {
        if(!isDead && !isPaused)
        {
            ball.update();
            player.update();
        }
        else if(isDead) GameOver();
    }

    private void GameOver()
    {
        if(Score.score > highScore)
        {
            dt.SetHighScore(c, Score.score);
            highScore = Score.score;
        }
    }

    private void RestartGame()
    {
        ball = new Ball();
        score = new Score();
        bm.SetupBlocks();
        isDead = false;
    }

    @Override
    public void run()
    {
        if(isUpdating)
        {
            //Add a Runnable in the main thread message queue to be executed after a delayed time (ms).
            handler.postDelayed(this, 30);

            Update();
            invalidate();
        }
        else bm.SetupBlocks();
    }
}