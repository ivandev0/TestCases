package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductLayout extends HelperBase {

    private static final By MALL_CARD = By.xpath(".//div[contains(@class,\"mall-card\")]");
    private static final By ADD_TO_CART = By.id("hook_Block_MallAddToBasket");
    private static final By NAME = By.xpath(".//div[contains(@class,\"mall-card_t mb-4x\")]");
    private static final By CLOSE_BUTTON = By.xpath(".//div[@class=\"modal-new_close\"]/a");
    private static final By SHARE_BUTTON = By.xpath(".//div[contains(@class,\"mall-widget_item\")]");
    private static final By SHARE_NOW = By.xpath(".//a[contains(@class,\"u-menu_li\")]");

    public ProductLayout(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Не загрузился слой выбранного товара",
                new WebDriverWait(driver, TIME_OUT)
                        .until((ExpectedCondition<Boolean>) webDriver -> isElementPresent(MALL_CARD)));
    }

    public ProductLayout addToChart() {
        click(ADD_TO_CART);
        return this;
    }

    public String getName() {
        return getText(NAME);
    }

    public void close() {
        click(CLOSE_BUTTON);
    }

    public CartPage goToChart() throws NoSuchElementException {
        if (getText(ADD_TO_CART).equals("Перейти в корзину")) {
            click(ADD_TO_CART);
        } else {
            throw new NoSuchElementException("Чтобы перейти в корзину со страницы товара, сперва надо добавить товар в корзину");
        }

        return new CartPage(driver);
    }

    public ProductLayout clickShareNow(){
        click(SHARE_BUTTON);
        click(SHARE_NOW);
        return this;
    }
}
