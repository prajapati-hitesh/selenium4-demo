package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.driver.DriverFactory;
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

public class InsecureWebsiteTests {
    private static final Logger logger = LogManager.getLogger(InsecureWebsiteTests.class.getName());

    @Test(priority = 0, description = "Load Insecure website normally")
    public void loadInsecureWebsiteNormallyTestOne() {
        // Load Url
        DriverFactory.getInstance().getDriver().get("https://expired.badssl.com/");

        // Click on Advanced button
        DriverFactory.getInstance().getDriver().findElement(By.id("details-button")).click();

        // Wait for Proceed Link to load
        new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("proceed-link")))
                .click();

        // Wait for page to load
        WebElement headerTextElement = WaitHelper.waitAndGetElement(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(30), By.id("content"));
        String headerText = headerTextElement.getText().replaceAll("\\s", "").trim();

        logger.info("Captured Header Text : " + headerText);
        Assert.assertEquals(headerText, "expired.badssl.com");
    }

    @Test(priority = 1, description = "Load Insecure website using Chrome Dev Tools")
    public void loadInsecureWebsiteUsingCDPTestOne() {

        // Get Chrome Dev Tools
        DevTools chromeDevTools = DriverFactory.getInstance().getDevTools();

        // Create Session
        chromeDevTools.createSession();

        // Enable Security
        chromeDevTools.send(Security.enable());

        // Set ignore Certificate error to true
        chromeDevTools.send(Security.setIgnoreCertificateErrors(true));

        // Load Url
        DriverFactory.getInstance().getDriver().get("https://expired.badssl.com/");

        // Wait for page to load
        WebElement headerTextElement = WaitHelper.waitAndGetElement(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(30), By.id("content"));
        String headerText = headerTextElement.getText().replaceAll("\\s", "").trim();

        logger.info("Captured Header Text : " + headerText);
        Assert.assertEquals(headerText, "expired.badssl.com");
    }
}