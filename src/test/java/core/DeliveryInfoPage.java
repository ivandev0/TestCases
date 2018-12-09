package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DeliveryInfoPage extends HelperBase {
    private static final By ADD_ADDRESS = By.xpath(".//div[contains(@class,\"portlet_h_inf\")]/a");
    private static final By ADDRESSES = By.xpath(".//div[contains(@class, \"mall-column_item\")]");

    public DeliveryInfoPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        Assert.assertTrue("Не загрузилось клавиша добавления нового адреса",
                new WebDriverWait(driver, TIME_OUT)
                        .ignoring(StaleElementReferenceException.class)
                        .until((ExpectedCondition<Boolean>) webDriver -> isElementPresent(ADD_ADDRESS) && isAllElementsPresent(ADDRESSES)));
    }

    public NewAddressLayout addNewAddress() {
        click(ADD_ADDRESS);
        return new NewAddressLayout(driver).insertNewAddress();
    }

    public List<String> getAddresses(){
        return driver.findElements(ADDRESSES)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}

