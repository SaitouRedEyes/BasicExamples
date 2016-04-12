package com.cesar.nave.androidsamples.arkanoid;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataStorage
{
    String s = "";

    public DataStorage(){}

    public int GetHighScore(Context c)
    {
        try
        {
            InputStream inputStream = c.openFileInput("arkanoidhighscore");

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String receiveString;
            StringBuilder stringBuilder = new StringBuilder();

            while ( (receiveString = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(receiveString);
            }

            inputStream.close();
            s = stringBuilder.toString();
        }
        catch(IOException e)
        {
            Log.d("io", "IOException: " + e.getCause());
        }

        return s.length() == 0 ? 0 :  Integer.parseInt(s);
    }

    public void SetHighScore(Context c, int score)
    {
        try
        {
            FileOutputStream fos = c.openFileOutput("arkanoidhighscore", Context.MODE_PRIVATE);
            fos.write(String.valueOf(score).getBytes());
            fos.close();
        }
        catch(IOException e)
        {
            Log.d("io", "IOException: " + e.getCause());
        }
    }
}