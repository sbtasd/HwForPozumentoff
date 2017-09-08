package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by DemidovichAS on 07.09.2017.
 */
public class WebDriverFactory {
    Properties properties;
    private final static Logger logger = Logger.getLogger(WebDriverFactory.class);
    private static WebDriverFactory webDriverFactory;
    private static WebDriver currentWebDriver;
    private String defaultDriver;
    private final static String defaultDriverPropertyKey = "wedriver.defaultDriver";

    private WebDriverFactory() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/WebDriverFactory.properties"));
            for (Object propKey : properties.keySet()) {
                String key = propKey.toString();
                String val = properties.getProperty(key);
                logger.info("Set system properties : " + key + ":" + val);
                System.setProperty(key, val);
            }
            if (!properties.containsKey(defaultDriverPropertyKey) | properties.getProperty(defaultDriverPropertyKey) == null) {
                properties.setProperty(defaultDriverPropertyKey, "chrome");
                System.setProperty(defaultDriverPropertyKey, "chrome");

            }
        } catch (IOException e) {
            logger.error("Could not read WebDriverFactory.properties " + e.getLocalizedMessage());
        }
    }

    public static WebDriverFactory getInstance() {
        if (webDriverFactory == null) {
            webDriverFactory = new WebDriverFactory();
        }
        return webDriverFactory;
    }

    public void initDriver(String browserName) {
        DesiredCapabilities capabilities;
        if (browserName == null) {

        }
        switch (browserName) {
            case "ie":
                capabilities = DesiredCapabilities.internetExplorer();
                currentWebDriver = new InternetExplorerDriver(capabilities);
                break;
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                currentWebDriver = new ChromeDriver(DesiredCapabilities.chrome());
                break;
            default:
                logger.error("Browser " + browserName + " not supported app will use to use default driver: " + properties.getProperty(defaultDriverPropertyKey));
                break;
        }
    }

    public WebDriver getDriver() {
        if (currentWebDriver == null) {
            initDriver(properties.getProperty(defaultDriverPropertyKey));
        }
        return currentWebDriver;
    }

    public WebDriver getDriver(String driverName) {
        if (currentWebDriver == null) {
            initDriver(driverName);
        } else {
            currentWebDriver.quit();
            initDriver(driverName);
        }
        return currentWebDriver;
    }
}


