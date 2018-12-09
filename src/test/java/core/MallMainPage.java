package core;

import core.wrappers.CartElementWrapper;
import core.wrappers.CategoryElementWrapper;
import core.wrappers.MallElementWrapper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MallMainPage extends HelperBase {

    private final int THRESHOLD_TO_LOAD = 10;
    private static final By PRODUCT = By.xpath(".//div[@class=\"ugrid_i\"]");
    private static final By CART = By.xpath(".//a[contains(@class, \"mall-basket\")]");

    private final List<MallElementWrapper> mallElements;

    MallMainPage(WebDriver driver) {
        super(driver);
        mallElements = Transformer.wrapMallElements(driver.findElements(PRODUCT), driver);
    }

    protected void check() {
        Assert.assertTrue("Не загрузилось достаточное для работы количество товаров",
                new WebDriverWait(driver, TIME_OUT)
                        .until((ExpectedCondition<Boolean>) webDriver -> driver.findElements(PRODUCT).size() >= THRESHOLD_TO_LOAD));
    }

    public ProductLayout selectRandomItem(){
        return mallElements.get(new Random().nextInt(mallElements.size() - 1)).select();
    }

    public List<MallElementWrapper> selectNRandomItem(int n){
        assert n < mallElements.size();
        Collections.shuffle(mallElements);
        return mallElements.subList(0, n);
    }

    public CartPage goToCart(){
        click(CART);
        return new CartPage(driver);
    }

    public MallCategoryPage selectRandomCategory(boolean usePopUp){
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

    MallCategoryPage usePopUp(){
        driver.findElement(CATEGORY).click();
        List<CategoryElementWrapper> categoryElements = Transformer.categoryElements(driver.findElements(CATEGORY_POP_UP));
        categoryElements.get(getRandom(categoryElements.size() - 1)).click();
        return new MallCategoryPage(driver);
    }

    MallCategoryPage dontUsePopUp(){
        List<CategoryElementWrapper> categoryElements = Transformer.categoryElements(driver.findElements(CATEGORY));
        CategoryElementWrapper elementWrapper = categoryElements.get(1 + getRandom(categoryElements.size() - 2));

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
