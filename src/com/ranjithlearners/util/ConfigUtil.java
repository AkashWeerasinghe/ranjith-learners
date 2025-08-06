package com.ranjithlearners.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("com/ranjithlearners/properties/config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties file not found in classpath");
            }
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
