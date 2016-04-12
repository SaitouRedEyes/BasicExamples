package com.cesar.nave.androidsamples.tcp_client;

import java.util.ArrayList;
import java.util.List;

public class SocketRequests implements Runnable
{
    private List<Float> params;
    private SocketSendToServer sendToServer;
    private Object serverResponse = null;

    public enum Services
    {
        CONNECT, SUM
    }

    public SocketRequests()
    {
        params = new ArrayList<>();
        sendToServer = new SocketSendToServer();
    }

    public Object ServerResponse() { return serverResponse; }
    public void NullServerResponse() { serverResponse = null; }

    public void Connect()
    {
        params.clear();
        StartThread("Connect Thread");
    }

    public void Sum(String n1, String n2)
    {
        params.clear();
        params.add(Float.parseFloat(n1));
        params.add(Float.parseFloat(n2));

        StartThread("SumThread");
    }

    private void StartThread(String name)
    {
        Thread thread = new Thread(this);
        thread.setName(name);
        thread.start();
    }

    @Override
    public void run() { serverResponse = sendToServer.Send(params); }
}