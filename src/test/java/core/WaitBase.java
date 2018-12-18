package core;

import com.google.common.base.Preconditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitBase {
    protected final int TIME_OUT = 10;

    /**
     * Ожидание
     */
    protected boolean explicitWait(WebDriver driver, final ExpectedCondition<?> condition, Class<? extends Throwable> ignore,
                                   long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        Preconditions.checkNotNull(condition, "Condition must be not null");
        Preconditions.checkArgument(TimeUnit.MINUTES.toSeconds(3) > maxCheckTimeInSeconds, "Max check time in seconds should be less than 3 minutes");
        checkConditionTimeouts(maxCheckTimeInSeconds, millisecondsBetweenChecks);
        try {
            // сбрасываем ожидания в 0
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            // создаем эксплицитное ожидание
            WebDriverWait explicitWait = new WebDriverWait(driver, maxCheckTimeInSeconds, millisecondsBetweenChecks);
            // проверяем его
            if(ignore != null) {
                explicitWait.ignoring(ignore).until(condition);
            } else {
                explicitWait.until(condition);
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            // при любом результате восстанавливаем значение имплицитного ожидания по умолчанию
            if (driver != null) {
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            } else {
                throw new IllegalArgumentException("Driver shouldnt be null");
            }
        }
    }

    protected boolean explicitWait(WebDriver driver, final ExpectedCondition<?> condition, long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        return explicitWait(driver, condition, null, maxCheckTimeInSeconds, millisecondsBetweenChecks);
    }

    /**
     * Проверяет таймаут провекри условия и интервал между проверками: таймаут
     * должен быть больше нуля, интервал проверки должен быть больше нуля
     * интервал между проверками умноженный на 1000 должен быть меньше таймаута
     * проверки
     *
     * @param maxCheckTimeInSeconds     максимальное время проверки в секундах
     * @param millisecondsBetweenChecks интервал между проверками в милисекундах
     */
    protected void checkConditionTimeouts(long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        Preconditions.checkState(maxCheckTimeInSeconds > 0, "maximum check time in seconds must be not 0");
        Preconditions.checkState(millisecondsBetweenChecks > 0, "milliseconds count between checks must be not 0");
        Preconditions.checkState(millisecondsBetweenChecks < (maxCheckTimeInSeconds * 1000),
                "Millis between checks must be less than max seconds to wait");
    }
}
