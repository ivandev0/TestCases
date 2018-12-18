package core.wrappers;

import core.WaitBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CategoryElementWrapper extends WaitBase {
    private WebElement mainElement;
    private WebDriver driver;

    private static final By NAME = By.xpath(".//span[contains(@class,\"category-menu_label\")]");

    public CategoryElementWrapper(WebElement mainElement, WebDriver driver) {
        this.mainElement = mainElement;
        this.driver = driver;
    }

    public String getName(){
        Assert.assertTrue("Элемент " + mainElement + " не загрузился",
                explicitWait(driver, ExpectedConditions.visibilityOf(mainElement), 5, 500));

        return mainElement.findElement(NAME).getText();
    }

    public WebElement getMainElement(){
        return mainElement;
    }

    public void click(){
        Assert.assertTrue("Элемент " + mainElement + " не загрузился",
                explicitWait(driver, ExpectedConditions.visibilityOf(mainElement), 5, 500));

        mainElement.click();
    }
}
