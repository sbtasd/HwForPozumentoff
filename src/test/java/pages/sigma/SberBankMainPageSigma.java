package pages.sigma;

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
public class SberBankMainPageSigma extends BasePage {
    @FindBy(xpath= "//div[@class='region-list']//span[@class='region-list__name']")
     WebElement chooseRegionElement;
//////span[@class='region-list_name'][1]
//    //*[@id="main"]/div/div/div/div/div/div/div[1]/div[1]/div[3]/div/div[2]/div[1]/div[3]/div/div/a/span
//    #main > div > div > div > div > div > div > div.sbrf-div-list-inner.--area.bp-area.header-container > div:nth-child(1) > div.sbrf-div-list-inner.--area.bp-area.header_all_logo_contacts > div > div:nth-child(2) > div:nth-child(1) > div.bp-widget-body > div > div > a > span
    @FindBy(xpath = "//div[@class='social__wrapper']//ul//a")
    public List<WebElement> socialNetworkLinks;

    @FindBy(xpath = "//div[@class='social__wrapper']")
    public WebElement socialNetworkFooter;

    private final String mainSelector = "//div[@class='region-list']//span[@class='region-list__name']";

    private JavascriptExecutor js;

    public SberBankMainPageSigma(WebDriver driver) {
        super();
        WebDriverWait wait = new WebDriverWait(driver, Constans.webDriverWaitLong);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mainSelector)));
    }

    public List<WebElement> getSocialNetworkLinks() {
        return socialNetworkLinks;
    }

    public WebElement getChooseRegionElement() {
        return chooseRegionElement;
    }
}
