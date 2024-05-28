package co.edu.uptc.utils;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties prop = new Properties();
    private static String propFileName = "src/data/files/config.properties";

    static {
        try {
            InputStream input = new FileInputStream(propFileName);
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String IP = prop.getProperty("ip");
    public static int PORT = Integer.parseInt(prop.getProperty("port"));
    public static int racketHeight = Integer.parseInt(prop.getProperty("racketHeight"));
    public static int racketWidth = Integer.parseInt(prop.getProperty("racketWidth"));

    public static String getValue(String key) {
        return prop.getProperty(key);
    }

    private static boolean isKeyAssigned(String key) {
        String rightKey = prop.getProperty("racketRightKey");
        String leftKey = prop.getProperty("racketLeftKey");
        String exitKey = prop.getProperty("exitWhileInGameKey");

        return key.equals(rightKey) || key.equals(leftKey) || key.equals(exitKey);
    }

    public static void setRightMovementKeyProperty(String value) {
        if (!isKeyAssigned(value)) {
            prop.setProperty("racketRightKey", value);
            try {
                prop.store(new FileOutputStream(propFileName), "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setLeftMovementKeyProperty(String value) {
        if (!isKeyAssigned(value)) {
            prop.setProperty("racketLeftKey", value);
            try {
                prop.store(new FileOutputStream(propFileName), "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setExitGameButtonKeyProperty(String value) {
        if (!isKeyAssigned(value)) {
            prop.setProperty("exitWhileInGameKey", value);
            try {
                prop.store(new FileOutputStream(propFileName), "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getRightMovementKeyProperty() {
        Properties prop = new Properties();
        try (InputStream propertiesFile = new FileInputStream(propFileName)) {
            prop.load(propertiesFile);
            return KeyEvent.getKeyText(Integer.parseInt(prop.getProperty("racketRightKey")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getLeftMovementKeyProperty() {
        Properties prop = new Properties();
        try (InputStream propertiesFile = new FileInputStream(propFileName)) {
            prop.load(propertiesFile);
            return KeyEvent.getKeyText(Integer.parseInt(prop.getProperty("racketLeftKey")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getExitGameButtonKeyProperty() {
        Properties prop = new Properties();
        try (InputStream propertiesFile = new FileInputStream(propFileName)) {
            prop.load(propertiesFile);
            return KeyEvent.getKeyText(Integer.parseInt(prop.getProperty("exitWhileInGameKey")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
