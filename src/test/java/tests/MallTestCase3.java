package tests;

import core.*;
import core.pages.CartPage;
import core.pages.LoginMainPage;
import core.wrappers.CartElementWrapper;
import core.wrappers.MallElementWrapper;
import model.TestBot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class MallTestCase3 extends TestBase {
    private static String SECOND_NAME;
    @Test
    public void separateDelete() throws Exception {
        List<MallElementWrapper> mallElements = new LoginMainPage(driver)
                .doLogin(new TestBot())
                .clickMallOnNavigateMenu()
                .getMallElements();
        List<MallElementWrapper> products = selectNRandomItem(mallElements, 2);
        ProductLayout first = products.get(0).select().addToChart();
        String firstName = first.getName();
        first.close();

        ProductLayout second = products.get(1).select().addToChart();
        SECOND_NAME = first.getName();

        CartPage cartPage = second
                .goToChart();
        List<CartElementWrapper> cartElements = cartPage.getCartElements();

        //проверяю что первый на месте
        List<CartElementWrapper> elementWithFirstName = filter(cartElements, firstName);
        long count = elementWithFirstName.size();
        Assert.assertEquals(1, count);
        //проверяю что второй на месте
        count = filter(cartElements, SECOND_NAME).size();
        Assert.assertEquals(1, count);
        //удаляю первый
        elementWithFirstName.forEach(CartElementWrapper::deleteElement);

        //для удаления нужно время, чтобы страница перестроилась
        driver.navigate().refresh();
        cartElements = new CartPage(driver).getCartElements();
        //проверяю что второй на месте
        count = filter(cartElements, SECOND_NAME).size();
        Assert.assertEquals(1, count);
    }

    @Override
    @After
    public void tearDown() {
        if(SECOND_NAME != null) {
            driver.get(baseUrl + "/mall/shopcart");
            List<CartElementWrapper> cartElements = new CartPage(driver).getCartElements();
            List<CartElementWrapper> elementWithGivenName = filter(cartElements, SECOND_NAME);
            elementWithGivenName.forEach(CartElementWrapper::deleteElement);
        }
        stop();
    }

    private List<MallElementWrapper> selectNRandomItem(List<MallElementWrapper> mallElements, int n) {
        Assert.assertTrue("Количество запрашиваемых случайных элементов не может первышать количества элементов в списке",
                n < mallElements.size());
        Collections.shuffle(mallElements);
        return mallElements.subList(0, n);
    }
}
