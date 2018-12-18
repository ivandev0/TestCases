package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class NewAddressLayout extends HelperBase {
    private static final By LAYOUT = By.xpath(".//div[contains(@class, \"modal-new_cnt\")]");

    private static final By NAME = By.id("field_userName");
    private static final By COUNTRY = By.id("field_addressEdit_CountryId");
    private static final By TOWN = By.id("field_addressEdit_SearchInput");
    private static final By STREET = By.id("field_adrFL");
    private static final By APARTMENT = By.id("field_adrSL");
    private static final By INDEX = By.id("field_zipcode");
    private static final By PHONE = By.id("field_userPhone");
    private static final By SAVE = By.id("hook_FormButton_button_save");

    private String name, country, town, street, apartment, index, phone, save;

    public NewAddressLayout(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        Assert.assertTrue("Не загрузился основной слой ввода данных",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(LAYOUT), 5, 500));
        Assert.assertTrue("Не загрузилолсь поле ввода имени",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(NAME), 5, 500));
        Assert.assertTrue("Не загрузилолсь поле ввода страны",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(COUNTRY), 5, 500));
        Assert.assertTrue("Не загрузилолсь поле ввода города",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(TOWN), 5, 500));
        Assert.assertTrue("Не загрузилолсь поле ввода улицы",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(STREET), 5, 500));
        Assert.assertTrue("Не загрузилолсь поле ввода номера квартиры",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(APARTMENT), 5, 500));
        Assert.assertTrue("Не загрузилолсь поле ввода индекса",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(INDEX), 5, 500));
        Assert.assertTrue("Не загрузилолсь поле ввода номера телефона",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(PHONE), 5, 500));
    }

    public NewAddressLayout insertNewAddress() {
        name = randomString(3, 6) + " " + randomString(1, 2);
        country = "Россия";
        town = randomString(15, 20);
        street = randomString(5, 10);
        apartment = randomInt(3);
        index = randomInt(6);
        phone = "+7" + randomInt(10);

        Assert.assertTrue("Отсутствует поле ввода имени",type(NAME, name));
        Assert.assertTrue("Отсутствует поле ввода города",type(TOWN, town));
        Assert.assertTrue("Отсутствует поле ввода улицы",type(STREET, street));
        Assert.assertTrue("Отсутствует поле ввода номера квартиры",type(APARTMENT, apartment));
        Assert.assertTrue("Отсутствует поле ввода индекса",type(INDEX, index));
        Assert.assertTrue("Отсутствует поле ввода телефона",type(PHONE, phone));

        Assert.assertTrue("Отсутствует кнопка сохранения нового адреса доставки",click(SAVE));

        return this;
    }

    private String randomString(int min, int max) {
        int leftLimit = 'a';
        int rightLimit = 'z';
        int targetStringLength = min + new Random().nextInt(max - min);
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    private String randomInt(int length) {
        int leftLimit = '0';
        int rightLimit = '9';
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    @Override
    public String toString() {
        return name + "\n" +
                "+7(" + phone.substring(2, 5) + ")" +
                phone.substring(5, 8) + "-" + phone.substring(8, 10) + "-" + phone.substring(10, 12) + "\n" +
                country + ", " + town + ", " + street + ", " + apartment + ", " + index;
    }
}
