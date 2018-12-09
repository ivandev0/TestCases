package core;

import core.wrappers.CartElementWrapper;
import core.wrappers.CategoryElementWrapper;
import core.wrappers.MallElementWrapper;
import core.wrappers.SubCategoryElementWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Transformer {
    static List<CartElementWrapper> wrapCartElements(List<WebElement> elements) {
        if (elements.isEmpty())
            return Collections.emptyList();
        List<CartElementWrapper> wrappedElements = new ArrayList<>();
        for (WebElement element : elements) {
            wrappedElements.add(new CartElementWrapper(element));
        }
        return wrappedElements;
    }

    static List<MallElementWrapper> wrapMallElements(List<WebElement> elements, WebDriver driver) {
        if (elements.isEmpty())
            return Collections.emptyList();
        List<MallElementWrapper> wrappedElements = new ArrayList<>();
        for (WebElement element : elements) {
            wrappedElements.add(new MallElementWrapper(element, driver));
        }
        return wrappedElements;
    }

    static List<CategoryElementWrapper> categoryElements(List<WebElement> elements) {
        if (elements.isEmpty())
            return Collections.emptyList();
        List<CategoryElementWrapper> wrappedElements = new ArrayList<>();
        for (WebElement element : elements) {
            wrappedElements.add(new CategoryElementWrapper(element));
        }
        return wrappedElements;
    }

    static List<SubCategoryElementWrapper> subCategoryElements(List<WebElement> elements, WebDriver driver) {
        if (elements.isEmpty())
            return Collections.emptyList();
        List<SubCategoryElementWrapper> wrappedElements = new ArrayList<>();
        for (WebElement element : elements) {
            wrappedElements.add(new SubCategoryElementWrapper(element, driver));
        }
        return wrappedElements;
    }
}
