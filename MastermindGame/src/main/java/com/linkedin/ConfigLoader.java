package com.linkedin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties = new Properties();

    static { // open data as a stream and stores it in the memory
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw  new RuntimeException("config.properties not found in the resources folder");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties", e);
        }
    }

    public static String getApiKey(){
        return properties.getProperty("api.key");
    }
}
