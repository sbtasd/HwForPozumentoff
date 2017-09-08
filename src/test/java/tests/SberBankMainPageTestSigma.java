package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.alpha.SberBankMainPageAlpha;
import pages.sigma.ChooseRegionPageModalSigma;
import pages.sigma.SberBankMainPageSigma;
import utils.WebDriverFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DemidovichAS on 08.09.2017.
 */
public class SberBankMainPageTestSigma extends Assert {

    private final static Logger logger = Logger.getLogger(SberBankMainPageTestSigma.class);

    @Test(testName = "Сбер - Главная - Выбор региона и проверка наличия ссылок на соцсести из сигмы", suiteName = "Глагне из сигмы")
    public void MainPageTestSigma() throws InterruptedException {

//        1.       Перейти на главную страницу  http://www.sberbank.ru/ru/person
//        2.       Кликнуть на ссылку с текущим вашим регионом/городом
        final String regionName = "Нижегородская область"; //TODO: вынести в параметры
        WebDriver driver = WebDriverFactory.getInstance().getDriver();
        driver.get("http://www.sberbank.ru/ru/person");
        SberBankMainPageSigma page = PageFactory.initElements(driver, SberBankMainPageSigma.class);
        assertTrue(page.getChooseRegionElement().isDisplayed());
        logger.info("Current region : " + page.getChooseRegionElement().getText());
        page.clickElement(page.getChooseRegionElement());

//        3.       В появившемся div “Выбор региона”, в поле «Введите название региона» введите «Нижегородская область» и выполните поиск
//        4.       Вернитесь на главную страницу и проверьте , что регион изменился на  «Нижегородская область»
        ChooseRegionPageModalSigma chooseRegionPageModal = PageFactory.initElements(driver, ChooseRegionPageModalSigma.class);
        chooseRegionPageModal.chooseRegionByInputAndSubmit(regionName);
        String actualRegionName = page.getChooseRegionElement().getText();
        logger.info("Actual regionName: " + regionName);
        String partRegionName = regionName.substring(0, 4);
        boolean checkRegionChangedResult = actualRegionName.contains(partRegionName);
        logger.info("Check that region changed: " + checkRegionChangedResult);
        assertTrue(checkRegionChangedResult, "Регион должен быть изменен на новое значение и содержать в названии:" + partRegionName);
//        5.       При помощи  JavaScriptExecutor проскрольте страницу до элемента DOM footer

        JavascriptExecutor js = page.getJs();
        js.executeScript("window.scrollTo(0," + page.socialNetworkFooter.getLocation().getY() + ")");
//        6.       Проверьте, что  footer содержит ссылки на социальные группы

        List<String> liksInPageFooter = new ArrayList<>();
        for (WebElement e :
                page.getSocialNetworkLinks()) {
            String url = e.getAttribute("href");
            logger.info("found link in footer: " + url);
            liksInPageFooter.add(url);
        }

        List<String> links = new ArrayList<>(Arrays.asList(
                "https://www.facebook.com/bankdruzey",
                "http://twitter.com/sberbank/",
                "http://www.youtube.com/sberbank",
                "http://instagram.com/sberbank",
                "http://vk.com/sberbank",
                "https://ok.ru/sberbank"));
        for (String link : links) {
            logger.info("check for footer contains: " + link);
            assertTrue(liksInPageFooter.contains(link) , "Страница в списке ссылок на гуппы в соцсетях должна содержать " + link);
        }

        driver.quit();
    }
}
