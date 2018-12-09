package core;

import core.wrappers.CartElementWrapper;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.Assert.fail;

public class TestBase {
    protected WebDriver driver;
    protected final String baseUrl = "https://ok.ru";
    private StringBuffer verificationErrors = new StringBuffer();
    private final int implicitlyWait = 5;

    @Before
    public void setUp() {
        init();
    }

    @After
    public void tearDown() {
        stop();
    }

    public void reset(){
        stop();
        init();
    }

    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-single-click-autofill");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1600, 900));
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.get(baseUrl + "/");
    }

    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!verificationErrorString.equals("")) {
            fail(verificationErrorString);
        }
    }

    public Stream<CartElementWrapper> filter(List<CartElementWrapper> list, String name){
        return list
                .stream()
                .filter(cartElementWrapper -> cartElementWrapper.getName().equals(name));
    }

}
