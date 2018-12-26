package network.essentials.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility functions
 */
public class NetworkUtility {

    private static Properties prop = new Properties();
    private static InputStream input = null;

    public static String getProperty(String property) {
        try {
            input = new FileInputStream("resources/config.properties");
            prop.load(input);

        } catch (FileNotFoundException e) {
            System.out.print("File not found exception occured while trying to find config.properties");
            System.out.print(e.getStackTrace());
        } catch (IOException e) {
            System.out.print("IOexception occured while trying to find config.properties");
            System.out.print(e.getStackTrace());
        }
        return prop.getProperty(property);
    }
}
