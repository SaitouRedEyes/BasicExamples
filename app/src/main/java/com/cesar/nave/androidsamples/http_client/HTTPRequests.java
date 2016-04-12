package com.cesar.nave.androidsamples.http_client;

import java.util.HashMap;
import java.util.Map;

public class HTTPRequests implements Runnable
{
    private Map<Object, Object> params;
    private HTTPSendToServer sendToServer;
    private Object serverResponse = null;

    public enum Services
    {
        CONNECT, LOADFILE, SUM, SUB, MULT, DIV, REGISTRY, LOGIN
    }

    public HTTPRequests()
    {
        params = new HashMap<>();
        sendToServer = new HTTPSendToServer();
    }

    public Object ServerResponse() { return serverResponse; }
    public void NullServerResponse() { serverResponse = null; }

    public void Connect()
    {
        params.clear();
        StartThread("Connect Thread");
    }

    public void Login(String login, String pass)
    {
        params.clear();
        params.put("sID", Services.LOGIN.ordinal());
        params.put("login", login);
        params.put("pass", pass);

        StartThread("LoginThread");
    }

    private void StartThread(String name)
    {
        Thread thread = new Thread(this);
        thread.setName(name);
        thread.start();
    }

    @Override
    public void run() { serverResponse = sendToServer.DoPost(params); }
}