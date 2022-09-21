package org.expensive.bootstrap;

import org.expensive.common.utils.SpringContextUtil;
import org.expensive.layout.jframe.FrameProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TimelineApplication {

    @Autowired
    private static SpringContextUtil springContextUtil;

    public static void main(String[] args) {

        springContextUtil.setApplicationContext(new ClassPathXmlApplicationContext("applicationContext.xml"));
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        FrameProxy frameProxy = springContextUtil.getBean("frameProxy", FrameProxy.class);
        frameProxy.initLoopShow(frameProxy);
        frameProxy.frameShow();
    }
}
