package core.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartElementWrapper {
    private WebElement mainElement;
    private final static By NAME = By.xpath(".//div[contains(@class, \"mall-title\")]");
    private final static By DELETE = By.xpath(".//div[contains(@class, \"mall-item_dsc\")]/a");

    public CartElementWrapper(WebElement mainElement) {
        this.mainElement = mainElement;
    }

    public String getName() {
        return mainElement.findElement(NAME).getText();
    }

    public void deleteElement() {
        mainElement.findElement(DELETE).click();
    }
}
