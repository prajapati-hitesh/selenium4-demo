package com.qa.selenium4.reflection.runners;

import com.qa.appium.session.AppiumSessionBuilder;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/json-report/cucumber.json",
        },
        monochrome = true,
        features = {"src/test/resources/features/"},
        glue = {
                "com.qa.selenium4.reflection.runners",
                "com.qa.selenium4.reflection.steps"
        },
        tags = "@GoogleContact",
        dryRun = false
)
public class GoogleContactRunner extends AbstractTestNGCucumberTests {

    private static final Logger logger = LogManager.getLogger(GoogleContactRunner.class.getName());
    private final AppiumSessionBuilder appiumSessionBuilder = new AppiumSessionBuilder();

    @BeforeSuite(alwaysRun = true)
    public void initializeSuite() {

        String serverIp = "127.0.0.1";
        // start session
        appiumSessionBuilder.startAppiumServer(serverIp, false);
    }

    @BeforeTest(alwaysRun = true)
    public void initializeRunEnvironment() {


        // initialize device
        this.appiumSessionBuilder.initDevice(
                "Mi A1",
                "Android",
                "12.0",
                "338b5cfc0404",
                "UiAutomator2",
                "8202",
                "com.google.android.contacts",
                "com.android.contacts.activities.PeopleActivity"
        );
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        this.appiumSessionBuilder.tearDownDevice();
    }

    @AfterSuite(alwaysRun = true)
    public void cleanUpSuite() {
        this.appiumSessionBuilder.stopAppiumServer();
    }
}

