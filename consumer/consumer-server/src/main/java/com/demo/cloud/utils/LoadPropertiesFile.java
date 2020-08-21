package com.demo.cloud.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author feiniu
 * @create 2020-03-26 9:37
 */
public class LoadPropertiesFile {
    private static Properties properties = new Properties();
    static {
        try {
            InputStream is = LoadPropertiesFile.class.getClassLoader().getResourceAsStream("api-application.properties");
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String getPropertyFileValues(String proKey){
        return properties.getProperty(proKey);
    }
    
    public static List<String> getPropertyList(String key){
        String str = properties.getProperty(key);
        String[] split = str.split(",");
        return Arrays.asList(split);
    }

}
