package com.qa.selenium4.demo.pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SauceDemoLoginPage {
    public static WebElement usernameElement(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(RelativeLocator
                        .withTagName("input")
                        .above(By.id("password"))
                ));
    }

    public static WebElement passwordElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .withTagName("input")
                .below(By.id("user-name"))
                .above(By.id("login-button"))
        );
    }

    public static WebElement loginButtonElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .withTagName("input")
                .below(By.id("password"))
                .above(By.id("login_credentials"))
        );
    }

    public static void waitForPageToLoad(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofMinutes(5))
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfElementLocated(By.id("user-name")),
                        ExpectedConditions.visibilityOfElementLocated(By.id("password")),
                        ExpectedConditions.visibilityOfElementLocated(By.id("login-button"))
                ));
    }
}
