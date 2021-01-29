package com.qa.selenium4.demo.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class WindowHelper {
    public static Dimension getWindowSize(WebDriver driver) {
        return driver.manage().window().getSize();
    }

    public static Dimension getWebPageSize(WebDriver driver) {
        return driver.findElement(By.tagName("body")).getSize();
    }

    public static double getFullPageHeight(WebDriver driver) {
        return Double.parseDouble(JavaScriptHelper
                .getJsExecutor(driver)
                .executeScript("return document.body.scrollHeight;").toString()
        );
    }
}