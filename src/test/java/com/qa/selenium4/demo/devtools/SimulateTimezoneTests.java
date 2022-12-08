package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.driver.DriverFactory;
import com.qa.selenium4.demo.helper.ElementHelper;
import com.qa.selenium4.demo.helper.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v106.emulation.Emulation;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SimulateTimezoneTests {
    private static final Logger logger = LogManager.getLogger(SimulateTimezoneTests.class.getName());

    @Test(priority = 0, description = "Get Current Time Zone")
    public void getCurrentTimeZoneTest() {
        // Load URL
        DriverFactory.getInstance().getDriver().get("https://webbrowsertools.com/timezone/");

        // Get Current TimeZone Element
        WebElement timeZoneElement = WaitHelper.waitAndGetElement(DriverFactory.getInstance().getDriver(), Duration.ofMinutes(1), By.id("timeZone"));

        // Get Timezone
        String currTimeZone = ElementHelper.getText(DriverFactory.getInstance().getDriver(), timeZoneElement);

        logger.info("Current Time Zone : " + currTimeZone);

        // Assert Timezone
        Assert.assertEquals(currTimeZone, "Asia/Calcutta");

        // Wait for 5 sec
        WaitHelper.hardWait(5);
    }

    @Test(priority = 1, description = "Simulate TimeZone using Chrome Dev Tools")
    public void simulateTimeZoneUsingCDPTest() {
        // Get Dev Tools
        DevTools chromeDevTools = DriverFactory.getInstance().getDevTools();

        // Create Session
        chromeDevTools.createSession();

        // Simulate Time Zone using TimeZone Id -> This Switch is still Experimental
        // Get TimeZone Ids Below
        // https://docs.oracle.com/middleware/12212/wcs/tag-ref/MISC/TimeZones.html
        chromeDevTools.send(Emulation.setTimezoneOverride("Europe/London"));

        // Load URL
        DriverFactory.getInstance().getDriver().get("https://webbrowsertools.com/timezone/");

        // Get Current TimeZone Element
        WebElement timeZoneElement = WaitHelper.waitAndGetElement(DriverFactory.getInstance().getDriver(), Duration.ofMinutes(1), By.id("timeZone"));

        // Get Timezone
        String currTimeZone = ElementHelper.getText(DriverFactory.getInstance().getDriver(), timeZoneElement);
        logger.info("Current Time Zone : " + currTimeZone);

        // Assert Timezone
        Assert.assertEquals(currTimeZone, "Europe/London");

        // Wait for 5 sec
        WaitHelper.hardWait(5);
    }
}
