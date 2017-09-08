package tests;

import org.testng.annotations.AfterSuite;
import utils.WebDriverFactory;

/**
 * Created by DemidovichAS on 08.09.2017.
 */
public class Finalizer {
    @AfterSuite(alwaysRun = true)
    public static void finalizeTests() {
        WebDriverFactory.getInstance().getDriver().quit();
    }
}
