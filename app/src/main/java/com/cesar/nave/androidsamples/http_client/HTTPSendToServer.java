package com.cesar.nave.androidsamples.http_client;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class HTTPSendToServer
{
    URL url;
    HttpURLConnection conn; //Use HttpsURLConnection for secure.

    public boolean Connect()
    {
        //final String URL_STRING = "http://10.0.2.2:8080/calculator_server/requests.php"; //Localhost
        final String URL_STRING = "http://10.0.2.2/calculator_server/requests.php"; //Localhost
        //final String URL_STRING = "http://192.168.0.5:8080/calculator_server/requests.php"; //Localhost

        try
        {
            url = new URL(URL_STRING);
            conn = (HttpURLConnection) url.openConnection();

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                Log.d("HTTP CLIENT SAMPLE", "Connection with server OK!!");
                return true;
            }
            else return false;
        }
        catch(IOException e)
        {
            Log.d("HTTP CLIENT SAMPLE", "Problems to connect with server!! " + e.getMessage());
            return false;
        }
        finally { if(conn != null) conn.disconnect(); }
    }

    public Object DoPost(Map<Object, Object> map)
    {
        if(map.size() > 0) return Post(GetQueryString(map));
        else return Connect();
    }

    private Object Post(String params)
    {
        try
        {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();

            byte[] bytes = params.getBytes("UTF8");
            OutputStream out = new BufferedOutputStream(conn.getOutputStream());
            out.write(bytes);
            out.flush();
            out.close();

            InputStream in = new BufferedInputStream(conn.getInputStream());

            return ReadString(in);
        }
        catch(IOException e)
        {
            Log.d("HTTP CLIENT SAMPLE", "Problems to send data to server!! " + e.getMessage());
            return false;
        }
        finally { conn.disconnect(); }
    }

    private String ReadString(InputStream in)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try
        {
            byte[] buffer = new byte[1024];
            int len;

            while((len = in.read(buffer)) > 0) baos.write(buffer, 0, len);

            baos.close();
            in.close();

            return new String(baos.toByteArray());
        }
        catch(IOException e) { Log.d("HTTP CLIENT SAMPLE", "Problems to translate the server answer in string!!"); }

        return "";
    }

    private String GetQueryString(Map<Object, Object> params)
    {
        if(params == null || params.size() == 0) return null;

        String urlParams = null;
        Iterator<Object> i = params.keySet().iterator();
        String key, value;

        while(i.hasNext())
        {
            key = (String) i.next();
            value = params.get(key).toString();
            urlParams = urlParams == null ? "" : urlParams + "&";
            urlParams += key + "=" + value;
        }

        return urlParams;
    }
}