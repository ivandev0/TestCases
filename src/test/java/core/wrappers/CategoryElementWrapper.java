package core.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CategoryElementWrapper {
    private WebElement mainElement;

    private static final By NAME = By.xpath(".//span[contains(@class,\"category-menu_label\")]");

    public CategoryElementWrapper(WebElement mainElement) {
        this.mainElement = mainElement;
    }

    public String getName(){
        return mainElement.findElement(NAME).getText();
    }

    public WebElement getMainElement(){
        return mainElement;
    }

    public void click(){
        mainElement.click();
    }
}
