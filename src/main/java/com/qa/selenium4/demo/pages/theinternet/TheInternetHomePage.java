package com.qa.selenium4.demo.pages.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

public class TheInternetHomePage {

    public static WebElement getAuthenticationFormElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .with(By.tagName("a"))
                .below(By.linkText("Forgot Password"))
                .above(By.linkText("Frames"))
        );
    }

    public static WebElement headerElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .with(By.tagName("h1"))
                .above(By.linkText("A/B Testing"))
        );
    }
}
