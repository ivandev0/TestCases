package tests;

import core.*;
import core.pages.DeliveryInfoPage;
import core.pages.LoginMainPage;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;

public class MallTestCase5 extends TestBase {
    @Test
    public void addNewDeliveryAddress() throws Exception {
        NewAddressLayout newAddress = new LoginMainPage(driver)
                .doLogin(new TestBot())
                .clickMallOnNavigateMenu()
                .goToCart()
                .clickToDeliveryAddress()
                .addNewAddress();

        //System.out.println(newAddress.toString());
        driver.navigate().refresh();
        long count = new DeliveryInfoPage(driver)
                .getAddresses()
                .stream()
                .filter(s -> s.equals(newAddress.toString()))
                .count();

        Assert.assertEquals(1, count);

        Thread.sleep(3000);
    }
}
