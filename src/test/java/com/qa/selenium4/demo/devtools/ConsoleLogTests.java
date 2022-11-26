package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.base.BaseDriver;
import com.qa.selenium4.demo.helper.JavaScriptHelper;
import com.qa.selenium4.demo.helper.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v106.log.Log;
import org.openqa.selenium.devtools.v106.log.model.LogEntry;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ConsoleLogTests extends BaseDriver {
    private static final Logger logger = LogManager.getLogger(ConsoleLogTests.class.getName());

    // https://applitools.com/blog/selenium-4-chrome-devtools/
    // https://www.swtestacademy.com/selenium-4-chrome-dev-tools-samples/
    // https://chromedevtools.github.io/devtools-protocol/tot/Log/
    // Basic Authentication         - DONE
    // Access Console logs          - DONE
    // Mocking Geolocation          - DONE
    // Load Insecure Web Site       - DONE
    // Block Specific Requests      - DONE
    // Simulate Network Speed       - DONE
    // Simulating Device Mode       - DONE
    // Simulate TimeZone            - DONE
    // Changing User Agents         - DONE
    // Capture HTTP Requests        - DONE
    // Capturing Performance Metrics- DONE

    @Test(priority = 0, description = "Get console logs using Chrome Dev Tools")
    public void amazonGetConsoleLogUsingCDPTest() {
        // Create Dev Tools
        DevTools chromeDevTools = ((ChromeDriver) driver).getDevTools();

        // Create Chrome Dev Tools Session
        chromeDevTools.createSession();

        // Enable Console Logs
        chromeDevTools.send(Log.enable());

        // Create List to hold the values of Console LogEntries
        List<LogEntry> logEntries = new ArrayList<>();

        // Add A listener to listen when an Entry is added. Add the entry to List
        chromeDevTools.addListener(Log.entryAdded(), logEntries::add);

        // Load URL
        driver.get("https://www.ebay.com/");

        // Send Log Using JS Executor
        JavaScriptHelper.getJsExecutor(driver).executeScript("console.warn('Log From Selenium Script');");

        WaitHelper.hardWait(10);

        // Print all the logs received
        logEntries.forEach(logEntry -> {
            logger.info(logEntry.getLevel() + " : " + logEntry.getText());
        });

        // Wait for 5 min
        WaitHelper.hardWait(5);
    }
}
