package pages.alpha;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.Constans;

import java.util.List;

/**
 * Created by DemidovichAS on 08.09.2017.
 */
public class SberBankMainPageAlpha extends BasePage {
    @FindBy(css = "body > div > div.wrap-inner > div.header > div.header-info > div.header-address > div > div > div > div > span > a")
    public WebElement chooseRegionElement;

    @FindBy(xpath = "//div[@class='footer-social_item']/a")
    public List<WebElement> socialNetworkLinks;

    @FindBy(xpath = "//div[@class='footer-social']")
    public WebElement socialNetworkFooter;

    private final String mainSelector = "body > div > div.wrap-inner > div.header > div.header-info > div.header-address > div > div > div > div > span > a";

    private JavascriptExecutor js;

    public SberBankMainPageAlpha(WebDriver driver) {
        super();
        WebDriverWait wait = new WebDriverWait(driver, Constans.webDriverWaitLong);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mainSelector)));
    }

    public List<WebElement> getSocialNetworkLinks() {
        return socialNetworkLinks;
    }

}
