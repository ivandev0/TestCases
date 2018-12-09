package tests;

import core.LoginMainPage;
import core.MallCategoryPage;
import core.TestBase;
import core.wrappers.SubCategoryElementWrapper;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MallTestCase2 extends TestBase {
    @Test
    public void selectCategory() throws Exception {
        List<SubCategoryElementWrapper> subCategoryElements = new LoginMainPage(driver)
                .doLogin(new TestBot())
                .clickMallOnNavigateMenu()
                .selectRandomCategory(true)
                .getSubCategoryList();

        int index = new Random().nextInt(subCategoryElements.size() - 1);
        SubCategoryElementWrapper subCategoryElement = subCategoryElements.get(index);

        String expectedName = subCategoryElement.getName();
        MallCategoryPage subCategoryPage = subCategoryElement.click();

        Thread.sleep(100); //переход с одной категории на другую
        String actualName = subCategoryPage.getCurrentCategory();
        Assert.assertEquals(expectedName, actualName);

        Thread.sleep(2000);
    }
}
