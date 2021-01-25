package com.qa.selenium4.demo.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    public static WebDriverWait getWaitObject(WebDriver driver, Duration duration) {
        return new WebDriverWait(driver, duration);
    }

    public static WebElement waitAndGetElement(WebDriver driver, Duration duration, By by) {
        return WaitHelper.getWaitObject(driver, duration)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void hardWait(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (Exception ignored) {
        }
    }
}
