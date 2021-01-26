package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.base.BaseDriver;
import com.qa.selenium4.demo.helper.JavaScriptHelper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v87.emulation.Emulation;
import org.openqa.selenium.devtools.v87.log.Log;
import org.openqa.selenium.devtools.v87.log.model.LogEntry;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CDPConsoleTests extends BaseDriver {

    // https://applitools.com/blog/selenium-4-chrome-devtools/
    // https://www.swtestacademy.com/selenium-4-chrome-dev-tools-samples/
    // https://chromedevtools.github.io/devtools-protocol/tot/Log/
    // Simulate Network Speed - Done
    // Simulating Device Mode - Done
    // Changing User Agents - Done
    // Load Insecure Web Site - Done
    // Mocking Geolocation - Done
    // Basic Authentication - Done
    // Block Specific Requests
    // Access Console logs
    // Capture HTTP Requests
    // Capturing Performance Metrics

    @Test(priority = 0)
    public void phpTravelsConsoleTest() {
        // Create Dev Tools
        DevTools chromeDevTools = ((ChromeDriver) driver).getDevTools();

        chromeDevTools.createSession();
        chromeDevTools.send(Log.enable());

        List<LogEntry> logEntries = new ArrayList<>();

        chromeDevTools.addListener(Log.entryAdded(),
                logEntry -> {
                    logEntries.add(logEntry);
                });

        chromeDevTools.send(Emulation.setDeviceMetricsOverride(
                800,
                500,
                50,
                true,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty())
        );

        driver.get("https://www.phptravels.net/home");
        JavaScriptHelper.getJsExecutor(driver).executeScript("console.warn('Log From Selenium Script');");

        logEntries.forEach(logEntry -> {
            System.out.println(logEntry.getLevel() + " : " + logEntry.getText());
        });
    }
}
