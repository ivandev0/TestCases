package tests;

import core.*;
import core.wrappers.CartElementWrapper;
import core.wrappers.MallElementWrapper;
import core.wrappers.SubCategoryElementWrapper;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class MallTestCase3 extends TestBase {
    @Test
    public void separateDelete() throws Exception {
        List<MallElementWrapper> products = new LoginMainPage(driver)
                .doLogin(new TestBot())
                .clickMallOnNavigateMenu()
                .selectNRandomItem(2);
        ProductLayout first = products.get(0).select().addToChart();
        String firstName = first.getName();
        first.close();

        ProductLayout second = products.get(1).select().addToChart();
        String secondName = first.getName();

        CartPage cartPage = second
                .goToChart();
        List<CartElementWrapper> cartElements = cartPage.getCartElements();

        //проверяю что первый на месте
        long count = filter(cartElements, firstName).count();
        Assert.assertEquals(1, count);
        //проверяю что второй на месте
        count = filter(cartElements, secondName).count();
        Assert.assertEquals(1, count);
        //удаляю первый
        filter(cartElements, firstName).forEach(CartElementWrapper::deleteElement);

        //для удаления нужно время, чтобы страница перестроилась
        driver.navigate().refresh();
        cartElements = new CartPage(driver).getCartElements();
        //cartElements = cartPage.getCartElements();
        //проверяю что второй на месте
        count = filter(cartElements, secondName).count();
        Assert.assertEquals(1, count);

        Thread.sleep(1000);
    }
}
