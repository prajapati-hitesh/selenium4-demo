package com.qa.selenium4.demo.pages.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TheInternetLoginPage {
    public static WebElement getHeaderElement(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(90))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(RelativeLocator
                                .withTagName("h2")
                                .above(By.className("subheader"))
                        )
                );
    }

    public static WebElement usernameElement(WebDriver driver) {
        return driver.findElement(RelativeLocator.withTagName("input").above(By.id("password")));
    }

    public static WebElement passwordElement(WebDriver driver) {
        return driver.findElement(RelativeLocator.withTagName("input").below(By.id("username")).above(By.className("radius")));
    }

    public static WebElement loginButtonElement(WebDriver driver) {
        return driver.findElement(By.className("radius"));
    }

    public static WebElement flashMessageElement(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
    }

    public static WebElement logOutButtonElement(WebDriver driver) {
        return driver.findElement(RelativeLocator.withTagName("a").below(By.className("subheader")));
    }
}
