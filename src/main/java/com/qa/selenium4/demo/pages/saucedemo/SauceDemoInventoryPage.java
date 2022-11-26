package com.qa.selenium4.demo.pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

public class SauceDemoInventoryPage {
    public static WebElement productHeaderElement(WebDriver driver) {
        return driver.findElement(By.className("title"));
    }

    public static WebElement backPackCardElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .with(By.xpath("//div[@class='inventory_item']"))
                .below(By.className("title"))
                .above(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']"))
        );
    }
}
