package com.qa.appium.helper;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppiumSyncHelper {

    public static void pauseScript(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (Exception ignored) {
        }
    }

    public static void pauseScriptInMillis(long timeInMilliSeconds) {
        try {
            Thread.sleep(timeInMilliSeconds);
        } catch (Exception ignored) {
        }
    }

    public static WebDriverWait getWebDriverWait(AppiumDriver driver, Duration duration) {
        return new WebDriverWait(driver, duration);
    }

    public static WebElement waitUntilElementIsVisible(AppiumDriver driver, Duration duration, By by) {
        return new WebDriverWait(driver, duration)
                .until(ExpectedConditions.visibilityOfElementLocated((by)));
    }
}
