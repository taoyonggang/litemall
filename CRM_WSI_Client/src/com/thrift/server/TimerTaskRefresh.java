package com.thrift.server;

import java.util.Timer;

public class TimerTaskRefresh extends java.util.TimerTask{
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TimerTaskRefresh.class.getName());
    @Override
    public void run() {
        AyServiceRun.init();
        logger.info("Token has be refresh.");
    }
}