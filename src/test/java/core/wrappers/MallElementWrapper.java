package core.wrappers;

import core.ProductLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MallElementWrapper {
    private WebElement mainElement;
    private WebDriver driver;

    public MallElementWrapper(WebElement mainElement, WebDriver driver) {
        this.mainElement = mainElement;
        this.driver = driver;
    }

    public ProductLayout select() {
        while (!mainElement.isDisplayed());
        mainElement.click();
        return new ProductLayout(driver);
    }

}
