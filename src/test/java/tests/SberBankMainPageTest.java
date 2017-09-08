package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.alpha.ChooseRegionPageModal;
import pages.alpha.SberBankMainPageAlpha;
import utils.WebDriverFactory;

/**
 * Created by DemidovichAS on 08.09.2017.
 */
public class SberBankMainPageTest extends Assert {

    private final static Logger logger = Logger.getLogger(SberBankMainPageTest.class);


    @Test(testName = "Сбер - Главная - Выбор региона и проверка наличия ссылок на соцсести", suiteName = "Глагне")
    public void MainPageTest() throws InterruptedException {

        final String regionName = "Нижегородская область"; //TODO: вынести в параметры
        WebDriver driver = WebDriverFactory.getInstance().getDriver();
        driver.get("http://www.sberbank.ru/ru/person");
        SberBankMainPageAlpha page = PageFactory.initElements(driver, SberBankMainPageAlpha.class);
        assertTrue(page.chooseRegionElement.isDisplayed());
        logger.info("Current region : " + page.chooseRegionElement.getText());
        page.clickElement(page.chooseRegionElement);

        ChooseRegionPageModal chooseRegionPageModal = PageFactory.initElements(driver, ChooseRegionPageModal.class);
        chooseRegionPageModal.chooseRegionByInputAndSubmit(regionName);
        String actualRegionName = page.chooseRegionElement.getText();
        logger.info("Actual regionName: " + regionName);
        String partRegionName = regionName.substring(0, 4);
        boolean checkRegionChangedResult = actualRegionName.contains(partRegionName);
        logger.info("Check that region changed: " + checkRegionChangedResult);
        assertTrue(checkRegionChangedResult, "Регион должен быть изменен на новое значение и содержать в названии:" + partRegionName);

        JavascriptExecutor js = page.getJs();
        js.executeScript("window.scrollTo(0,"+page.socialNetworkFooter.getLocation().getY()+")");

        for (WebElement e :
                page.getSocialNetworkLinks()) {
            logger.info(e.getAttribute("href"));
        }
        driver.quit();
    }
}
