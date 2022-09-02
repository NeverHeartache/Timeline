package org.expensive.time;

import org.expensive.time.jframe.FrameProxy;

import java.io.IOException;

public class TimelineApplication {

    public static void main(String[] args) throws IOException {
        FrameProxy frameProxy = new FrameProxy();
        frameProxy.initLoopShow(frameProxy);
        frameProxy.frameShow();
    }
}
