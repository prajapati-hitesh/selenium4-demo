package com.qa.selenium4.demo.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JavaScriptHelper {
    private static final Logger logger = LogManager.getLogger(JavaScriptHelper.class.getName());

    public static void highlightElement(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background-color:#FFFF66; border:2px solid red;');", element);
        } catch (Exception ignored) {
        }
    }

    public static void highlightElement(WebDriver driver, By by) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background-color:#FFFF66; border:2px solid red;');", driver.findElement(by));
        } catch (Exception ignored) {
        }
    }

    public static JavascriptExecutor getJsExecutor(WebDriver driver) {
        return ((JavascriptExecutor) driver);
    }

    public static void unHighlightElement(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('style', 'background-color:#FFFF66; border:2px solid red;');", element);
        } catch (Exception ignored) {
        }
    }

    public static void unHighlightElement(WebDriver driver, By by) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('style', 'background-color:#FFFF66; border:2px solid red;');", driver.findElement(by));
        } catch (Exception ignored) {
        }
    }

    /**
     * To wait until JQuery & JavaScripts are loaded
     *
     * @param duration Timeout in seconds to wait for JQuery & JavaScript to load
     * @return <code>true</code> if loaded in timeout specified, <code>false</code> otherwise
     */
    public static boolean waitForJStoLoad(WebDriver driver, Duration duration) {
        try {
            final JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebDriverWait wait = new WebDriverWait(driver, duration);

            // wait for jQuery to load
            ExpectedCondition<Boolean> jQueryLoad = driverObj -> {
                try {
                    return ((Long) jse.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            };

            // wait for Javascript to load
            ExpectedCondition<Boolean> jsLoad = driverObj -> jse.executeScript("return document.readyState")
                    .toString().equals("complete");

            return wait.until(jQueryLoad) && wait.until(jsLoad);
        } catch (Exception ex) {
            logger.error("JS did not load in " + duration.getSeconds() + " seconds");
        }
        return false;
    }
}
