package org.expensive.time;

import org.expensive.time.jframe.FrameProxy;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TimelineApplication {
    private final FrameProxy frameProxy;
    private final Timer timer;

    public TimelineApplication() throws IOException {
        frameProxy = new FrameProxy();
        timer = new Timer("Timeline");
    }

    public static void main(String[] args) throws IOException {
        TimelineApplication app = new TimelineApplication();
        app.loopShow();
    }

    public void loopShow(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                frameProxy.frameShow();
                java.awt.Toolkit.getDefaultToolkit().beep();
                try {
                    Thread.sleep(1000L * 5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frameProxy.frameHide();
            }
        };
        //
        Long delay = 1000L * 60 * 10L;
        //x minutes 之后继续执行
        Long period = 1000L * 60 * 50L;
        timer.scheduleAtFixedRate(timerTask, delay, period);
    }
}
