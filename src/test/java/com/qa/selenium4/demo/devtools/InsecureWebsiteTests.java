package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.base.BaseDriver;
import com.qa.selenium4.demo.helper.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v106.security.Security;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class InsecureWebsiteTests extends BaseDriver {
    private static final Logger logger = LogManager.getLogger(InsecureWebsiteTests.class.getName());

    @Test(priority = 0, description = "Load Insecure website normally")
    public void loadInsecureWebsiteNormallyTestOne() {
        // Load Url
        driver.get("https://expired.badssl.com/");

        // Click on Advanced button
        driver.findElement(By.id("details-button")).click();

        // Wait for Proceed Link to load
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("proceed-link")))
                .click();

        // Wait for page to load
        WebElement headerTextElement = WaitHelper.waitAndGetElement(driver, Duration.ofSeconds(30), By.id("content"));
        String headerText = headerTextElement.getText().replaceAll("\\s", "").trim();

        logger.info("Captured Header Text : " + headerText);
        Assert.assertEquals(headerText, "expired.badssl.com");
    }

    @Test(priority = 1, description = "Load Insecure website using Chrome Dev Tools")
    public void loadInsecureWebsiteUsingCDPTestOne() {

        // Get Chrome Dev Tools
        DevTools chromeDevTools = getDevTools();

        // Create Session
        chromeDevTools.createSession();

        // Enable Security
        chromeDevTools.send(Security.enable());

        // Set ignore Certificate error to true
        chromeDevTools.send(Security.setIgnoreCertificateErrors(true));

        // Load Url
        driver.get("https://expired.badssl.com/");

        // Wait for page to load
        WebElement headerTextElement = WaitHelper.waitAndGetElement(driver, Duration.ofSeconds(30), By.id("content"));
        String headerText = headerTextElement.getText().replaceAll("\\s", "").trim();

        logger.info("Captured Header Text : " + headerText);
        Assert.assertEquals(headerText, "expired.badssl.com");
    }
}