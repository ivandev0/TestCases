package tests;

import core.pages.LoginMainPage;
import core.TestBase;
import core.pages.UserMainPage;
import model.TestBot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class MallTestCase4 extends TestBase {
    private static boolean NEED_DELETE = false;
    @Test
    public void shareCheck() throws Exception {
        String productName = new LoginMainPage(driver)
                .doLogin(new TestBot())
                .clickMallOnNavigateMenu()
                .selectRandomItem()
                .clickShareNow()
                .getName();
        NEED_DELETE = true;
        driver.navigate().to(baseUrl + "/feed");

        UserMainPage mainPage = new UserMainPage(driver);
        String actualName = mainPage.getFirstMallTitle();
        Assert.assertEquals(productName, actualName);
    }

    @Override
    @After
    public void tearDown() {
        if(NEED_DELETE) {
            driver.get(baseUrl + "/feed");
            UserMainPage mainPage = new UserMainPage(driver);
            mainPage.deleteFirstMall();
        }
        stop();
    }
}
