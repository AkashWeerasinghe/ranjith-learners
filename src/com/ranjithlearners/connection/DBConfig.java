package com.ranjithlearners.connection;

import java.io.InputStream;
import java.util.Properties;

public class DBConfig {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("com/ranjithlearners/properties/db.properties")) {
            if (input != null) {
                props.load(input);
            } else {
                throw new RuntimeException("⚠️ db.properties not found in classpath!");
            }
        } catch (Exception e) {
            throw new RuntimeException("⚠️ Failed to load DB config: " + e.getMessage(), e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}