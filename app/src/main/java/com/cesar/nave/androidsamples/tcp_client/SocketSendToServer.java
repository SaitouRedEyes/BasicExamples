package com.cesar.nave.androidsamples.tcp_client;

import android.util.Log;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

public class SocketSendToServer
{
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    private Boolean Connect()
    {
        try
        {
            final String IP = "10.0.2.2"; //Localhost
            // final String IP = "192.168.0.5"; //internal network
            final int DOOR = 7777;

            socket = new Socket(IP, DOOR);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            return true;
        }
        catch(Exception e) { return false; }
    }

    public Object Send(List<Float> params)
    {
        if(params.size() > 0 && Connect())
        {
            try
            {
                for (Float param : params) out.writeFloat(param);
                out.flush();

                Float result = in.readFloat();

                out.close();
                in.close();
                socket.close();

                return result;
            }
            catch(Exception e)
            {
                Log.d("SEND CATCH", "Problems to send data to server: " + e.getMessage());
                return null;
            }
        }
        else return Connect();
    }
}