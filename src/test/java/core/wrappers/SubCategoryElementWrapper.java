package core.wrappers;

import core.MallCategoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SubCategoryElementWrapper {
    private WebElement mainElement;
    private WebDriver driver;

    public SubCategoryElementWrapper(WebElement mainElement, WebDriver driver) {
        this.mainElement = mainElement;
        this.driver = driver;
    }

    public String getName(){
        return mainElement.getText();
    }

    public MallCategoryPage click(){
        mainElement.click();
        return new MallCategoryPage(driver);
    }
}
