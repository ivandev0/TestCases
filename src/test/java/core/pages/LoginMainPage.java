package core.pages;

import core.HelperBase;
import model.TestBot;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginMainPage extends HelperBase {

    //поля статичны потому что check вызывается до инициализации полей класса
    private static final By EMAIL = By.xpath("//*[@id='field_email']");
    private static final By PASSWORD = By.xpath("//*[@id='field_password']");
    private static final By ENTER_BUTTON = By.xpath(".//*[@value='Войти']");

    public LoginMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Поле логина не загрузились",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(EMAIL), 5, 500));

        Assert.assertTrue("Поле пароля не загрузились",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(PASSWORD), 5, 500));
    }

    public UserMainPage doLogin(TestBot testBot) {
        Assert.assertTrue("Отсутствует поле ввода логина",type(EMAIL, testBot.getLogin()));
        Assert.assertTrue("Отсутствует поле ввода пароля",type(PASSWORD, testBot.getPassword()));
        Assert.assertTrue("Отсутствует кнопка логина",click(ENTER_BUTTON));
        return new UserMainPage(driver);
    }
}
