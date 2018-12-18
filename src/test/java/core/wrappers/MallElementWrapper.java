package core.wrappers;

import core.ProductLayout;
import core.WaitBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MallElementWrapper extends WaitBase {
    private WebElement mainElement;
    private WebDriver driver;

    public MallElementWrapper(WebElement mainElement, WebDriver driver) {
        this.mainElement = mainElement;
        this.driver = driver;
    }

    public ProductLayout select() {
        Assert.assertTrue("Элемент " + mainElement + " не загрузился",
                explicitWait(driver, ExpectedConditions.visibilityOf(mainElement), 5, 500));

        mainElement.click();
        return new ProductLayout(driver);
    }

}
