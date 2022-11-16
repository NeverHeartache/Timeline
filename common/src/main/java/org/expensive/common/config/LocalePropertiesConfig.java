package org.expensive.common.config;

import org.expensive.common.pojo.GlobalPropertiesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/config/global.properties")
public class LocalePropertiesConfig {
    @Autowired
    private Environment env;

    @Bean
    public GlobalPropertiesEntity getPropertiesConfig() {
        GlobalPropertiesEntity properties = new GlobalPropertiesEntity();
        properties.setApplicationName(env.getProperty("application.name", "Timeline"));
        properties.setVersion(env.getProperty("application.version", "1.0.1"));
        properties.setDescription(env.getProperty("application.description", ""));
        properties.setFontSize(env.getProperty("application.font.size", "40"));
        properties.setFontFamliy(env.getProperty("application.font.family", "SansSerif"));
        return properties;
    }
}
