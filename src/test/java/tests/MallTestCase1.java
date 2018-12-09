package tests;

import core.*;
import core.wrappers.CartElementWrapper;
import model.TestBot;
import org.junit.*;

import java.util.List;
import java.util.stream.Stream;

public class MallTestCase1 extends TestBase {
    @Test
    public void consistentAdd() throws Exception {
        String productName = new LoginMainPage(driver)
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
        long count = filter(cartElements, productName).count();
        Assert.assertEquals(1, count);
        filter(cartElements, productName).forEach(CartElementWrapper::deleteElement);

        Thread.sleep(1000);
    }
}
