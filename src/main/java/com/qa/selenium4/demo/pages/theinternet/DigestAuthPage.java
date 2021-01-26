package com.qa.selenium4.demo.pages.theinternet;

import com.qa.selenium4.demo.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.time.Duration;

public class DigestAuthPage {

    public static void waitForPageToLoad(WebDriver driver) {
        WaitHelper.waitUntilElementHasText(
                driver,
                Duration.ofMinutes(1),
                By.tagName("h3"),
                "Basic Auth"
        );
    }

    public static WebElement getSuccessMessageElement(WebDriver driver) {
        return driver.findElement(RelativeLocator
                .withTagName("p")
                .below(By.tagName("h3"))
                .above(By.id("page-footer"))
        );
    }
}
