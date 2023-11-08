package utils;

import driver.DriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {
    public final static int WAIT_2_SECONDS = 2;
    public final static int WAIT_5_SECONDS = 5;

    public final static int WAIT_10_SECONDS = 10;


    public static WebElement waitElementToBeVisible(WebElement element) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_10_SECONDS))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitElementToBeClickable(WebElement element) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}
