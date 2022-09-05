package org.expensive.time;

import org.expensive.time.jframe.FrameProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class TimelineApplication {

    private static FrameProxy frameProxy;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        frameProxy = context.getBean("frameProxy", FrameProxy.class);
        frameProxy.initLoopShow(frameProxy);
        frameProxy.frameShow();
    }
}
