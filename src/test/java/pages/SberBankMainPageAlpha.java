package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by DemidovichAS on 08.09.2017.
 */
public class SberBankMainPageAlpha extends BasePage {
    @FindBy(css = "body > div > div.wrap-inner > div.header > div.header-info > div.header-address > div > div > div > div > span > a")
    public WebElement chooseRegionElement;
    private final String mainSelector = "body > div > div.wrap-inner > div.header > div.header-info > div.header-address > div > div > div > div > span > a";
    public SberBankMainPageAlpha(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Constans.webDriverWaitLong);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mainSelector)));
    }
}
