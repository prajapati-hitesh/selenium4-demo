package com.qa.selenium4.demo.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class WindowHelper {
    private static Dimension getWindowSize(WebDriver driver) {
        return driver.manage().window().getSize();
    }

    public static Dimension getWebPageSize(WebDriver driver) {
        return driver.findElement(By.tagName("body")).getSize();
    }
}