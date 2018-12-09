package core;

import core.wrappers.CartElementWrapper;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartPage extends HelperBase {

    private static final By PORTLET = By.xpath(".//div[@class=\"portlet\"]");
    private static final By ELEMENTS = By.xpath(".//div[contains(@class, \"mall-item \")]");
    private static final By EMPTY_PORTLET = By.xpath(".//div[contains(@class, \"stub-empty __feed\")]");
    private static final By DELIVERY_ADDRESS = By.xpath(".//a[contains(@hrefattrs,\"mallAddress\")]");

    CartPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        /*new WebDriverWait(driver, TIME_OUT).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ELEMENTS));*/
        Assert.assertTrue("Не загрузились портлеты страницы",
                new WebDriverWait(driver, TIME_OUT)
                        .ignoring(StaleElementReferenceException.class)
                        .until((ExpectedCondition<Boolean>) webDriver ->
                                (isAllElementsPresent(ELEMENTS) && isElementPresent(PORTLET) )
                                || isElementPresent(EMPTY_PORTLET)
                        ));
    }

    public List<CartElementWrapper> getCartElements() {
        return Transformer.wrapCartElements(driver.findElements(ELEMENTS));
    }

    public DeliveryInfoPage clickToDeliveryAddress(){
        click(DELIVERY_ADDRESS);
        return new DeliveryInfoPage(driver);
    }
}