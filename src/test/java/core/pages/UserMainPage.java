package core.pages;

import core.HelperBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserMainPage extends HelperBase {

    private static final By NAVIGATION_MENU = By.xpath(".//div[@class=\"nav-side __navigation\"]");
    private static final By MALL = By.xpath(".//*[@data-l='t,mall']");

    private static final By FEED = By.xpath(".//div[contains(@class,\"feed-w\")]");
    private static final By FEED_DELETE = By.xpath(".//div[contains(@class,\"feed_ac\")]");
    private static final By MALL_TITLE = By.xpath(".//div[contains(@class,\"mall-title\")]");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("NavigationMenu не загрузилось",
                explicitWait(ExpectedConditions.visibilityOfAllElementsLocatedBy(NAVIGATION_MENU), 5, 500));

        Assert.assertTrue("Новости на стене не загрузилось",
                explicitWait(ExpectedConditions.visibilityOfAllElementsLocatedBy(FEED), 5, 500));
    }

    public MallMainPage clickMallOnNavigateMenu() {
        Assert.assertTrue("Отсутствует кнопка перехода в раздел товары",click(MALL));
        return new MallMainPage(driver);
    }

    public String getFirstMallTitle() {
        String result = getText(MALL_TITLE);
        Assert.assertNotNull("Отсутствует название товара на стене", result);
        return result;
    }

    public UserMainPage deleteFirstMall(){
        Assert.assertTrue("Отсутствует кнопка удаление записи со стены",click(FEED_DELETE));
        return this;
    }
}
