package org.expensive.common.frames;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class LocaleApplicationAware implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(LocaleApplicationAware.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.error("LocaleApplicationAware's getApplicationName is: ", applicationContext.getApplicationName());
        logger.error("LocaleApplicationAware's getDisplayName is: ", applicationContext.getDisplayName());
        logger.error("LocaleApplicationAware's getEnvironment().getActiveProfiles is: ", applicationContext.getEnvironment().getActiveProfiles());
    }
}
