package org.expensive.common.frames;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component("chunIsBitch")
public class LocaleBeanName implements BeanNameAware {
    private static final Logger logger = LoggerFactory.getLogger(LocaleBeanName.class);
    @Override
    public void setBeanName(String s) {
        logger.error("validate bean life cycle of BeanNameAware: {}", s);
        // 打印日志为： validate bean life cycle of BeanNameAware: chunIsBitch
    }
}
