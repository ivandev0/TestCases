package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

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
        long count = new DeliveryInfoPage(driver)
                .getAddresses()
                .stream()
                .filter(s -> s.equals(newAddress.toString()))
                .count();

        Assert.assertEquals(1, count);

        Thread.sleep(3000);
    }
}
