package pages.sigma;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.Constans;

import java.util.List;


/**
 * Created by sbt-demidovich-as on 08.09.2017.
 */
public class ChooseRegionPageModalSigma extends BasePage {
    @FindBy(className = "regions-town-link")
    public List<WebElement> listRegions;
    @FindBy(xpath = "//div[@class='region-list__modal-content']//input")
    public WebElement inputRegionName;

    private final static Logger logger = Logger.getLogger(ChooseRegionPageModalSigma.class);
    private final static String mainSelector = "//div[@class='region-list__modal-content']//input" ;

    public ChooseRegionPageModalSigma() {
        super();
        WebDriverWait wait = new WebDriverWait(this.driver, Constans.webDriverWaitLong);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mainSelector)));
        logger.info("Init page");
    }

    public void chooseRegionByInputAndSubmit(String regionName) {
        WebDriverWait wait = new WebDriverWait(driver,Constans.webDriverWaitLong) ;
        wait.until(ExpectedConditions.visibilityOf(inputRegionName));
        moveToAndClick(inputRegionName);
        inputRegionName.sendKeys(regionName);
        WebElement resultDropDown = driver.findElement(By.xpath("//div[@class='region-list__modal-content']//*[text()='" + regionName + "']"));
        resultDropDown.click();
        }
}
