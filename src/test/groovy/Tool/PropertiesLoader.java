package Tool;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties load(String path) {
        Properties properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream(path)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
