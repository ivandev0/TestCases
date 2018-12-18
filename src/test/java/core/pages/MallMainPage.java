package core.pages;

import core.HelperBase;
import core.ProductLayout;
import core.Transformer;
import core.wrappers.CategoryElementWrapper;
import core.wrappers.MallElementWrapper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class MallMainPage extends HelperBase {

    private static final By PRODUCT = By.xpath(".//div[@class=\"ugrid_i\"]");
    private static final By CART = By.xpath(".//a[contains(@class, \"mall-basket\")]");

    private final List<MallElementWrapper> mallElements;

    public MallMainPage(WebDriver driver) {
        super(driver);
        mallElements = Transformer.wrap(driver.findElements(PRODUCT), driver, MallElementWrapper.class);
    }

    protected void check() {
        Assert.assertTrue("Не загрузилось достаточное для работы количество товаров",
                explicitWait(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT), 5, 500));
    }

    public ProductLayout selectRandomItem(){
        return mallElements.get(new Random().nextInt(mallElements.size() - 1)).select();
    }

    public CartPage goToCart(){
        Assert.assertTrue("Отсутствует кнопка перехода в корзину",click(CART));
        return new CartPage(driver);
    }

    public List<MallElementWrapper> getMallElements() {
        return mallElements;
    }

    public MallCategoryPage selectRandomCategory(boolean usePopUp) {
        return usePopUp ? new CategoryPromise(driver).usePopUp() : new CategoryPromise(driver).dontUsePopUp();
    }
}

class CategoryPromise{
    private WebDriver driver;

    private static final By CATEGORY = By.xpath(".//div[contains(@class,\"mall-menu-slider\")]//div[contains(@class, \"uslider_i\")]");
    private static final By CATEGORY_POP_UP = By.xpath(".//div[contains(@class,\"mall-sections_i\")]");

    CategoryPromise(WebDriver driver) {
        this.driver = driver;
    }

    MallCategoryPage usePopUp() {
        driver.findElement(CATEGORY).click();
        List<CategoryElementWrapper> categoryElements = Transformer.wrap(driver.findElements(CATEGORY_POP_UP), driver, CategoryElementWrapper.class);
        categoryElements.get(getRandom(categoryElements.size() - 1)).click();
        return new MallCategoryPage(driver);
    }

    MallCategoryPage dontUsePopUp() {
        List<CategoryElementWrapper> categoryElements = Transformer.wrap(driver.findElements(CATEGORY), driver, CategoryElementWrapper.class);
        categoryElements.remove(0); //исключаю кнопку "категории"
        CategoryElementWrapper elementWrapper = categoryElements.get(getRandom(categoryElements.size() - 1));

        Actions build = new Actions(driver);
        build.moveToElement(elementWrapper.getMainElement()).build().perform();

        elementWrapper.click();
        return new MallCategoryPage(driver);
    }

    private int getRandom(int bound){
        int index = new Random().nextInt(bound);
        if(index == 2) //исключаем "Дом и интерьер", там нет подкатегорий
            return getRandom(bound);
        return index;
    }

}
