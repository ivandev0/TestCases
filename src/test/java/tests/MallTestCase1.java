package tests;

import core.*;
import core.pages.CartPage;
import core.pages.LoginMainPage;
import core.wrappers.CartElementWrapper;
import model.TestBot;
import org.junit.*;

import java.util.List;

public class MallTestCase1 extends TestBase {
    private static String PRODUCT_NAME;
    @Test
    public void consistentAdd() throws Exception {
        PRODUCT_NAME = new LoginMainPage(driver)
                .doLogin(new TestBot())
                .clickMallOnNavigateMenu()
                .selectRandomItem()
                .addToChart()
                .getName();
        reset();
        List<CartElementWrapper> cartElements = new LoginMainPage(driver)
                .doLogin(new TestBot())
                .clickMallOnNavigateMenu()
                .goToCart()
                .getCartElements();
        List<CartElementWrapper> elementWithGivenName = filter(cartElements, PRODUCT_NAME);
        long count = elementWithGivenName.size();
        Assert.assertEquals(1, count);
    }

    @Override
    @After
    public void tearDown() {
        if(PRODUCT_NAME != null) {
            driver.get(baseUrl + "/mall/shopcart");
            List<CartElementWrapper> cartElements = new CartPage(driver).getCartElements();
            List<CartElementWrapper> elementWithGivenName = filter(cartElements, PRODUCT_NAME);
            elementWithGivenName.forEach(CartElementWrapper::deleteElement);
        }
        stop();
    }
}
