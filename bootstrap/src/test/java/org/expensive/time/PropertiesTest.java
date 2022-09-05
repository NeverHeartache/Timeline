package org.expensive.time;

import org.expensive.common.utils.GlobalPropertiesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"/applicationContext.xml"})
public class PropertiesTest {

    @Autowired
    private GlobalPropertiesUtil propertiesUtil;

    @Test
    public void getProperties() throws IOException {
        propertiesUtil.loadResource("config/global.properties");
        String appName = propertiesUtil.getProperty("application.name");
        String appVersion = propertiesUtil.getProperty("application.version");
        String appDesc = propertiesUtil.getProperty("application.description");
        System.out.println(appName);
        System.out.println(appVersion);
        System.out.println(appDesc);
    }

}
/**
 * 结果如下：
 * 时间轴
 * 1.0.1
 * 工作时间轴，要工作也要身体伸展
 */
