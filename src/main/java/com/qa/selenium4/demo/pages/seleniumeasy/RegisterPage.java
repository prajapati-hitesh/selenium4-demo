package com.qa.selenium4.demo.pages.seleniumeasy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

public class RegisterPage {
    public static WebElement getFirstNameElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .with(By.tagName("input"))
                .above(By.name("last_name"))
        );
    }

    public static WebElement getLastNameElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .with(By.tagName("input"))
                .below(By.name("first_name"))
                .above(By.name("email"))
        );
    }

    public static WebElement getEmailElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .with(By.tagName("input"))
                .below(By.name("last_name"))
                .above(By.name("phone"))
        );
    }

    public static WebElement getPhoneElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .with(By.tagName("input"))
                .below(By.name("email"))
                .above(By.name("address"))
        );
    }

    public static WebElement getAddressElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .with(By.tagName("input"))
                .below(By.name("phone"))
                .above(By.name("city"))
        );
    }

    public static WebElement getCityElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .with(By.tagName("input"))
                .below(By.name("address"))
                .above(By.name("state"))
        );
    }

    public static WebElement getStateElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .with(By.tagName("select"))
                .below(By.name("city"))
                .above(By.name("zip"))
        );
    }

    public static WebElement getZipCodeElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .with(By.tagName("input"))
                .below(By.name("state"))
                .above(By.name("website"))
        );
    }

    public static WebElement getWebsiteElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .with(By.tagName("input"))
                .below(By.name("zip"))
                .above(By.name("hosting"))
        );
    }
}
