package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    public final static String PROPERTIES = "src/main/resources/config.properties";

    public static String getPropertyValue(String key) {
        Properties prop = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES)) {
            prop.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
