package com.qa.selenium4.demo.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionHelper {

    public static void sendKeys(WebDriver driver, WebElement element, CharSequence text) {
        new Actions(driver)
                .sendKeys(element, text.toString())
                .build().perform();
    }

    public static void moveToElement(WebDriver driver, By by) {
        new Actions(driver)
                .moveToElement(driver.findElement(by))
                .build()
                .perform();
    }

    public static void moveToElement(WebDriver driver, WebElement element) {
        new Actions(driver)
                .moveToElement(element)
                .build()
                .perform();
    }
}