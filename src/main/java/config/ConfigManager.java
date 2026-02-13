package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties PROPS = new Properties();
    static {
        try (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream("application.properties")){
            if(is == null){
                throw new RuntimeException("application.properties not found in src/main/resources");
            }
            PROPS.load(is);
        } catch (Exception e){
            throw new RuntimeException("Failed to load application.properties", e);
        }
    }

    private ConfigManager(){}

    public static String get(String key){
        String val = PROPS.getProperty(key);
        if(val == null || val.trim().isEmpty()){
            throw new RuntimeException("Missing config key: " + key);
        }
        return val.trim();
    }

}
