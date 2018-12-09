package core;

import core.wrappers.CategoryElementWrapper;
import core.wrappers.SubCategoryElementWrapper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MallCategoryPage extends HelperBase {
    private final int THRESHOLD_TO_LOAD = 10;
    private static final By PRODUCT = By.xpath(".//div[@class=\"ugrid_i\"]");
    private static final By SUBCATEGORY = By.xpath(".//a[contains(@class,\"js-subsection-link\")]");
    private static final By NAME = By.xpath(".//span[contains(@class,\"filter_crumbs\")]");

    public MallCategoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        Assert.assertTrue("Не загрузилось достаточное для работы количество товаров",
                new WebDriverWait(driver, TIME_OUT)
                        .until((ExpectedCondition<Boolean>) webDriver -> driver.findElements(PRODUCT).size() >= THRESHOLD_TO_LOAD));
    }

    public List<SubCategoryElementWrapper> getSubCategoryList(){
        return Transformer.subCategoryElements(driver.findElements(SUBCATEGORY), driver);
    }

    public String getCurrentCategory(){
        return driver.findElements(NAME).get(1).getText();
    }
}
