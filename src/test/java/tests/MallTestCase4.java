package tests;

import core.LoginMainPage;
import core.TestBase;
import core.UserMainPage;
import core.wrappers.CartElementWrapper;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MallTestCase4 extends TestBase {
    @Test
    public void shareCheck() throws Exception {
        String productName = new LoginMainPage(driver)
                .doLogin(new TestBot())
                .clickMallOnNavigateMenu()
                .selectRandomItem()
                .clickShareNow()
                .getName();
        driver.navigate().to(baseUrl + "/feed");

        UserMainPage mainPage = new UserMainPage(driver);
        String actualName = mainPage.getFirstMallTitle();
        Assert.assertEquals(productName, actualName);
        mainPage.deleteFirstMall();

        Thread.sleep(3000);
    }
}
