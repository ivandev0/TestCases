package core.wrappers;

import core.WaitBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartElementWrapper extends WaitBase {
    private WebElement mainElement;
    private WebDriver driver;

    private final static By NAME = By.xpath(".//div[contains(@class, \"mall-title\")]");
    private final static By DELETE = By.xpath(".//div[contains(@class, \"mall-item_dsc\")]/a");

    public CartElementWrapper(WebElement mainElement, WebDriver driver) {
        this.mainElement = mainElement;
        this.driver = driver;
    }

    public String getName() {
        Assert.assertTrue("Элемент " + mainElement + " не загрузился",
                explicitWait(driver, ExpectedConditions.visibilityOf(mainElement), 5, 500));
        return mainElement.findElement(NAME).getText();
    }

    public void deleteElement() {
        Assert.assertTrue("Элемент " + mainElement + " не загрузился",
                explicitWait(driver, ExpectedConditions.visibilityOf(mainElement), 5, 500));
        mainElement.findElement(DELETE).click();
    }
}
