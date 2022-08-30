package org.expensive.time;

import org.expensive.time.jframe.FrameProxy;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TimelineApplication {
    private final Long delay = 1000L * 60 * 10L;//
    private final Long period = 1000L * 60 * 50L;//x minutes 之后继续执行
    private FrameProxy frameProxy;
    private Timer timer = null;
    private TimerTask timerTask = null;
    public TimelineApplication() throws IOException {
        frameProxy = new FrameProxy();
        timer = new Timer("Timeline");
    }

    public static void main(String[] args) throws IOException {
        TimelineApplication app = new TimelineApplication();
        app.loopShow();
    }

    public void loopShow(){
        timerTask = new TimerTask() {
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
        timer.scheduleAtFixedRate(timerTask, delay, period);
    }
}
