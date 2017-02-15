package com.cesar.nave.androidsamples.tcp_client;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

class SocketSendToServer
{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private Object Connect(List<Float> params)
    {
        try
        {
            final String IP = "10.0.2.2"; //Localhost
            // final String IP = "192.168.0.5"; //internal network
            final int DOOR = 9000;

            socket = new Socket(IP, DOOR);
            out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            if(params.size() <= 0) return true;
            else return Send(params);
        }
        catch(Exception e)
        {
            return false;
        }
    }

    Object Send(List<Float> params)
    {
        if(params.size() > 0 && socket != null)
        {
            try
            {
                String sendString = "";
                for (Float param : params) sendString += param + "&";

                out.println(sendString);

                String text;
                String result = "";

                while ((text = in.readLine()) != null) result += text;

                CloseConnection();
                return result;
            }
            catch(Exception e)
            {
                Log.d("SEND CATCH", "Problems to send data to server: " + e.getMessage());
                CloseConnection();
                return null;
            }
        }
        else return Connect(params);
    }

    private void CloseConnection()
    {
        try {
            out.close();
            in.close();
            socket.close();
            out = null;
            in = null;
            socket = null;
        }
        catch(Exception e)
        {
            Log.d("SEND CATCH", "Problems to close connection: " + e.getMessage());
        }
    }
}