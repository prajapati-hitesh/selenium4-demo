package com.qa.appium.helper;


import com.qa.appium.thread.ThreadLocalAppiumDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppiumEventHelpers {
    private static final ThreadLocal<AppiumEventHelpers> instance = new ThreadLocal<>();

    public AppiumEventHelpers() {
        // To Do
    }

    public static AppiumEventHelpers getInstance() {
        if (instance.get() == null) {
            instance.set(new AppiumEventHelpers());
        }
        return instance.get();
    }

    public void click(WebElement element) {
        AppiumSyncHelper.getWebDriverWait(ThreadLocalAppiumDriver.getDriver(), Duration.ofSeconds(60))
                .until(ExpectedConditions.visibilityOf(element)).click();
    }

    public void click(By element) {
        AppiumSyncHelper.getWebDriverWait(ThreadLocalAppiumDriver.getDriver(), Duration.ofSeconds(60))
                .until(ExpectedConditions.visibilityOfElementLocated(element)).click();
    }

    public void waitAndClickWhenEnabled(WebElement element) {
        AppiumSyncHelper.getWebDriverWait(ThreadLocalAppiumDriver.getDriver(), Duration.ofSeconds(60))
                .until(ExpectedConditions.attributeToBe(element, "enabled", "true"));
        element.click();
    }

    public void performKeyBoardEvent(KeyBoardEvent keyBoardEvent) {

        switch (keyBoardEvent) {
            case ENTER -> ThreadLocalAppiumDriver.getAndroidDriver().pressKey(new KeyEvent(AndroidKey.ENTER));
            case LEFT_SHIFT -> ThreadLocalAppiumDriver.getAndroidDriver().pressKey(new KeyEvent(AndroidKey.SHIFT_LEFT));
            case RIGHT_SHIFT ->
                    ThreadLocalAppiumDriver.getAndroidDriver().pressKey(new KeyEvent(AndroidKey.SHIFT_RIGHT));
            case LEFT_CTRL -> ThreadLocalAppiumDriver.getAndroidDriver().pressKey(new KeyEvent(AndroidKey.CTRL_LEFT));
            case RIGHT_CTRL -> ThreadLocalAppiumDriver.getAndroidDriver().pressKey(new KeyEvent(AndroidKey.CTRL_RIGHT));
        }

    }

    public void clear(WebElement element) {
        WebDriverWait wait = AppiumSyncHelper.getWebDriverWait(ThreadLocalAppiumDriver.getDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(element)).clear();

    }

    public void sendKeys(WebElement element, String textToEnter) {
        WebDriverWait wait = AppiumSyncHelper.getWebDriverWait(ThreadLocalAppiumDriver.getDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(element)).clear();
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(textToEnter);
    }

    public void sendKeys(By element, String textToEnter) {
        WebDriverWait wait = AppiumSyncHelper.getWebDriverWait(ThreadLocalAppiumDriver.getDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(textToEnter);
    }

    public String getText(WebElement element) {
        return AppiumSyncHelper.getWebDriverWait(ThreadLocalAppiumDriver.getDriver(), Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public String getText(By element) {
        return AppiumSyncHelper.getWebDriverWait(ThreadLocalAppiumDriver.getDriver(), Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
    }

    public boolean isSessionActive() {
        try {
            String sessionID = ThreadLocalAppiumDriver.getDriver().getSessionId().toString();
            if (sessionID.length() > 0) {
                return true;
            }
        } catch (WebDriverException ex) {
            return false;
        }
        return false;
    }

    public enum KeyBoardEvent {
        SEARCH,
        ENTER,
        LEFT_SHIFT,
        RIGHT_SHIFT,
        LEFT_CTRL,
        RIGHT_CTRL
    }
}