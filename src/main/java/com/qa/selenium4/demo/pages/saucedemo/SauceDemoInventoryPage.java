package com.qa.selenium4.demo.pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

public class SauceDemoInventoryPage {
    public static WebElement productHeaderElement(WebDriver driver) {
        return driver.findElement(By.className("product_label"));
    }

    public static WebElement backPackCardElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .with(By.tagName("a"))
                .below(By.className("product_label"))
                .above(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']"))
        );
    }
}
