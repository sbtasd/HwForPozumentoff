package utils;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

/**
 * Created by DemidovichAS on 08.09.2017.
 */
public class WebDriverFactoryTest extends Assert {

    @org.testng.annotations.Test
    public void testGetInstance() throws Exception {
        WebDriverFactory webDriverFactory1 = WebDriverFactory.getInstance();
        WebDriverFactory webDriverFactory2 = WebDriverFactory.getInstance();
        assertTrue("Reference in heap must be equals for singleton: ", (webDriverFactory1 == webDriverFactory2));
        testGetDriver();
    }

    @org.testng.annotations.Test
    public void testGetDriver() throws Exception {
        try {

            WebDriverFactory driverFactory = WebDriverFactory.getInstance();
            WebDriver driver = driverFactory.getDriver();
            driver.get("http://www.sberbank.ru/ru/person");
//            driver = driverFactory.getDriver("ie");
            driver.quit();
        } catch (Exception e) {
            throw new AssertionError("There is something wrong with get driver method", e);
        }
    }

}