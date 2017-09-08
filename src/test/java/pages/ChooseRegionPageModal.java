package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;



/**
 * Created by sbt-demidovich-as on 08.09.2017.
 */
public class ChooseRegionPageModal extends BasePage {
    @FindBy(className = "regions-town-link")
    private List<WebElement> listRegions;
    @FindBy(xpath = "//div[@class=\"popup-content\"]//input")
    private WebElement inputRegionName;

    private final static Logger logger = Logger.getLogger(ChooseRegionPageModal.class);
    private final static String inputSelector = "//div[@class=\"popup-content\"]//input";
    private final static String mainSelector = inputSelector;

    ChooseRegionPageModal() {
        super();
        WebDriverWait wait = new WebDriverWait(this.driver, Constans.webDriverWaitLong);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mainSelector)));
        logger.info("Init page");
    }

    public void chooseRegionByInputAndSubmit(String regionName) {
        moveToAndClick(inputRegionName);
        actions.sendKeys(regionName);
        actions.sendKeys("{ENTER}");
    }
}
