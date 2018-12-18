package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transformer {
    public static <T> List<T> wrap(List<WebElement> elements, Class<T> clazz) {
        if (elements.isEmpty())
            return Collections.emptyList();
        List<T> wrappedElements = new ArrayList<>();
        try {
            for (WebElement element : elements) {
                wrappedElements.add(clazz.getConstructor(WebElement.class).newInstance(element));
            }
        } catch (Exception e) {
            System.err.println("Transformer critical error");
            e.printStackTrace();
        }
        return wrappedElements;
    }

    public static <T> List<T> wrap(List<WebElement> elements, WebDriver driver, Class<T> clazz) {
        if (elements.isEmpty())
            return Collections.emptyList();
        List<T> wrappedElements = new ArrayList<>();
        try {
            for (WebElement element : elements) {
                wrappedElements.add(clazz.getConstructor(WebElement.class, WebDriver.class).newInstance(element, driver));
            }
        } catch (Exception e) {
            System.err.println("Transformer critical error");
            e.printStackTrace();
        }
        return wrappedElements;
    }
}
