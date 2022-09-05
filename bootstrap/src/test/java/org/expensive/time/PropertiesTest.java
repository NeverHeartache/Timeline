package org.expensive.time;

import org.expensive.common.utils.GlobalPropertiesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"applicationContext.xml"})
public class PropertiesTest {

    @Autowired
    private GlobalPropertiesUtil propertiesUtil;

    @Test
    public void getProperties() {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        GlobalPropertiesUtil propertiesUtil = context.getBean("globalProperties", GlobalPropertiesUtil.class);
        String value = propertiesUtil.getProperty("application.name");
        System.out.println(value);
    }

}
