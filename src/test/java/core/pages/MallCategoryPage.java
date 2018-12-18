package core.pages;

import core.HelperBase;
import core.Transformer;
import core.wrappers.SubCategoryElementWrapper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
                explicitWait(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT), StaleElementReferenceException.class,
                        5, 500));
    }

    public List<SubCategoryElementWrapper> getSubCategoryList() {
        return Transformer.wrap(driver.findElements(SUBCATEGORY), driver, SubCategoryElementWrapper.class);
    }

    public String getCurrentCategory(){
        return driver.findElements(NAME).get(1).getText();
    }
}
