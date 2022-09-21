package org.expensive.common.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

@Component
public class GlobalPropertiesUtil {

    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * 默认的Properties路径是：config/global.properties
     * @param resourcePath 路径
     */
    public Properties loadResource(String resourcePath) throws IOException {
        URL propertiesUrl = getClass().getClassLoader().getResource(resourcePath);
        properties.load(propertiesUrl.openStream());
        return properties;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
