package com.thrift.server;

import java.util.Timer;

public class TimerTaskRefresh extends java.util.TimerTask{

    @Override
    public void run() {
        AyServiceRun.init();
        System.out.println("Token has be refresh.");
    }
}