package com.cesar.nave.androidsamples.tcp_client;

import java.util.ArrayList;
import java.util.List;

class SocketRequests implements Runnable
{
    private List<Float> params;
    private SocketSendToServer sendToServer;
    private Object serverResponse = null;

    enum Services
    {
        CONNECT, SUM
    }

    SocketRequests()
    {
        params = new ArrayList<>();
        sendToServer = new SocketSendToServer();
    }

    Object ServerResponse() { return serverResponse; }
    void NullServerResponse() { serverResponse = null; }

    void Connect()
    {
        params.clear();
        StartThread("Connect Thread");
    }

    void Sum(String n1, String n2)
    {
        params.clear();
        params.add((float)Services.SUM.ordinal());
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