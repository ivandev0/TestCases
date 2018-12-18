package core.wrappers;

import core.WaitBase;
import core.pages.MallCategoryPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SubCategoryElementWrapper extends WaitBase {
    private WebElement mainElement;
    private WebDriver driver;

    public SubCategoryElementWrapper(WebElement mainElement, WebDriver driver) {
        this.mainElement = mainElement;
        this.driver = driver;
    }

    public String getName(){
        Assert.assertTrue("Элемент " + mainElement + " не загрузился",
                explicitWait(driver, ExpectedConditions.visibilityOf(mainElement), 5, 500));
        return mainElement.getText();
    }

    public MallCategoryPage click(){
        Assert.assertTrue("Элемент " + mainElement + " не загрузился",
                explicitWait(driver, ExpectedConditions.visibilityOf(mainElement), 5, 500));
        mainElement.click();
        return new MallCategoryPage(driver);
    }
}
