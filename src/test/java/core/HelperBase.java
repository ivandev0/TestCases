package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;
import java.util.List;

public abstract class HelperBase {
    WebDriver driver;
    private boolean acceptNextAlert = true;
    final int TIME_OUT = 10;

    HelperBase(WebDriver driver) {
        this.driver = driver;
        check();
    }

    protected abstract void check();

    void type(By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
        driver.findElement(locator).click();
    }

    void click(By locator) {
        Actions build = new Actions(driver);
        build.moveToElement(driver.findElement(locator)).build().perform();
        driver.findElement(locator).click();
    }

    String getText(By locator){
        return driver.findElement(locator).getText();
    }

    boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    boolean isAllElementsPresent(By by) {
        List<WebElement> elements = driver.findElements(by);
        Iterator var3 = elements.iterator();

        WebElement element;
        do {
            if (!var3.hasNext()) {
                return elements.size() > 0;
            }

            element = (WebElement)var3.next();
        } while(element.isDisplayed());

        return false;
    }

    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
