package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.WebDriverFactory;

/**
 * Created by DemidovichAS on 08.09.2017.
 */
public class BasePage extends Assert {
    protected final WebDriver driver;
    protected Actions actions;

    public BasePage() {
        this.driver = WebDriverFactory.getInstance().getDriver();
        this.actions = new Actions(driver);
    }

    public void moveToAndClick(WebElement element) {
        actions.moveToElement(element).click().build().perform();
    }

    public void clickElement(WebElement element) {
        element.click();
    }
//    @Test
//    public  void testName() throws Exception {
//        try {
//            PageFactory.initElements(driver,BasePage.class);
//            assertNull(element);
//        }catch (Exception e ){
//            quit();
//            throw  new AssertionError(e);
//        }
//    }
//    @AfterTest
//    void  quit(){
//        this.driver.quit();
//    }
}
