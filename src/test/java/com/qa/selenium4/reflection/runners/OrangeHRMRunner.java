package com.qa.selenium4.reflection.runners;

import com.qa.selenium4.demo.driver.DriverFactory;
import com.qa.selenium4.demo.enums.Browser;
import com.qa.selenium4.demo.helper.WaitHelper;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.Optional;

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
        tags = "@OrangeHRMTest",
        dryRun = false
)
public class OrangeHRMRunner extends AbstractTestNGCucumberTests {

    private static final Logger logger = LogManager.getLogger(OrangeHRMRunner.class.getName());

    @BeforeSuite(alwaysRun = true)
    public void initializeSuite() {

    }

    @BeforeTest(alwaysRun = true)
    public void initializeRunEnvironment() {

        DriverFactory.getInstance().initBrowser(
                Browser.Chrome,
                Optional.empty()
        );
        // Set Browser Url
        DriverFactory.getInstance().setUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        logger.info("Application has been loaded......!!!");
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        WaitHelper.hardWait(5);
        // Tear Down Driver Instances
        DriverFactory
                .getInstance()
                .closeBrowser();
    }

    @AfterSuite(alwaysRun = true)
    public void cleanUpSuite() {
    }
}
