package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.Iterator;
import java.util.List;

public abstract class HelperBase extends WaitBase{
    protected WebDriver driver;
    private boolean acceptNextAlert = true;

    protected HelperBase(WebDriver driver) {
        this.driver = driver;
        check();
    }

    protected abstract void check();

    protected boolean type(By locator, String text) {
        if(isElementPresent(locator)) {
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
            driver.findElement(locator).click();
            return true;
        }
        return false;
    }

    protected boolean click(By locator) {
        if(isElementPresent(locator)) {
            driver.findElement(locator).click();
            return true;
        }
        return false;
    }

    public void moveToElement(WebElement webElement){
        new Actions(driver).moveToElement(webElement).build().perform();
    }

    protected String getText(By locator){
        if(isElementPresent(locator)) {
            return driver.findElement(locator).getText();
        }
        return null;
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isAllElementsPresent(By by) {
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

    /**
     * Ожидание
     */
    protected boolean explicitWait(final ExpectedCondition<?> condition, long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        return super.explicitWait(driver, condition, maxCheckTimeInSeconds, millisecondsBetweenChecks);
    }

    protected boolean explicitWait(final ExpectedCondition<?> condition, Class<? extends Throwable> ignore,
                                   long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        return super.explicitWait(driver, condition, ignore, maxCheckTimeInSeconds, millisecondsBetweenChecks);
    }
}
