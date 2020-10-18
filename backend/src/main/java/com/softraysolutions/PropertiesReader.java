package com.softraysolutions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    public static String getProperty(String key) {
        Properties prop = new Properties();
        InputStream input = null;

        try{
            input = PropertiesReader.class.getClassLoader().getResourceAsStream("application.properties");
            prop.load(input);
            if (input != null) {
                input.close();
            }
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
