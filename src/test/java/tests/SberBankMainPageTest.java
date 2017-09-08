package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ChooseRegionPageModal;
import pages.SberBankMainPageAlpha;
import utils.WebDriverFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by DemidovichAS on 08.09.2017.
 */
public class SberBankMainPageTest extends Assert {

    private final static Logger logger = Logger.getLogger(SberBankMainPageTest.class);

    @DataProvider(name = "getRegionNameToInput" )
    Object[][] getRegionNameToInput() {
        Object[][] result;
        result = new Object[][]
                {{"Нижегородская область"}};

        return result;
    }


    @Test(testName = "Сбер - Главная - Выбор региона и проверка наличия ссылок на соцсести", suiteName = "Глагне", dataProvider = "getRegionNameToInput" , dataProviderClass = SberBankMainPageTest.class)
    public void MainPageTest(Object[][] parameters) throws InterruptedException {
        List<Object> regList = Arrays.asList(parameters[0]);
        for (Object regname : regList) {
            String regName = regname.toString();
            WebDriver driver = WebDriverFactory.getInstance().getDriver();
            driver.get("http://www.sberbank.ru/ru/person");
            SberBankMainPageAlpha page = PageFactory.initElements(driver, SberBankMainPageAlpha.class);
            assertTrue(page.chooseRegionElement.isDisplayed());
            logger.info("Current region : " + page.chooseRegionElement.getText());

            page.clickElement(page.chooseRegionElement);

            ChooseRegionPageModal chooseRegionPageModal = PageFactory.initElements(driver, ChooseRegionPageModal.class);
            chooseRegionPageModal.chooseRegionByInputAndSubmit(regName.toString());
        }
    }
}
