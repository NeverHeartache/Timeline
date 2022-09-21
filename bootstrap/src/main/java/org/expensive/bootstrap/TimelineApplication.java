package org.expensive.bootstrap;

import org.expensive.layout.jframe.FrameProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TimelineApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        FrameProxy frameProxy = context.getBean("frameProxy", FrameProxy.class);
        frameProxy.initLoopShow(frameProxy);
        frameProxy.frameShow();
    }
}
