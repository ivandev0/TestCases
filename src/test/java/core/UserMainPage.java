package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserMainPage extends HelperBase {

    private static final By NAVIGATION_MENU = By.xpath(".//div[@class=\"nav-side __navigation\"]");
    private final By MALL = By.xpath(".//*[@data-l='t,mall']");

    private final By FEED_DELETE = By.xpath(".//div[contains(@class,\"feed_ac\")]");
    private final By MALL_TITLE = By.xpath(".//div[contains(@class,\"mall-title\")]");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("NavigationMenu не загрузилось",
                new WebDriverWait(driver, TIME_OUT)
                        .until((ExpectedCondition<Boolean>) webDriver -> isElementPresent(NAVIGATION_MENU)));
    }

    public MallMainPage clickMallOnNavigateMenu() {
        click(MALL);
        return new MallMainPage(driver);
    }

    public String getFirstMallTitle() {
        return getText(MALL_TITLE);
    }

    public UserMainPage deleteFirstMall(){
        click(FEED_DELETE);
        return this;
    }
}
