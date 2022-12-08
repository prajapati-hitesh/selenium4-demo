package com.qa.selenium4.demo.base;

import com.qa.selenium4.demo.constants.FileConstants;
import com.qa.selenium4.demo.driver.DriverFactory;
import com.qa.selenium4.demo.enums.Browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.time.Duration;
import java.util.Optional;

public class BaseDriver {
    private static final Logger logger = LogManager.getLogger(BaseDriver.class.getName());
    //public WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void initializeBrowser() {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        // WebDriverManager.chromedriver().setup(); // Commented this as this has been integrated as a part of Selenium 4.6.0

        ChromeOptions chromeOptions = new ChromeOptions();
        // Load Ad Blocker Extension
        chromeOptions.addExtensions(new File(FileConstants.CHROME_EXTENSION_DIR + "Ultimate AdBlocker_2_2_6_0.crx"));

        // Initialize Driver
        DriverFactory.getInstance().initBrowser(Browser.Chrome, Optional.of(chromeOptions));
        //driver = new ChromeDriver(chromeOptions);
        logger.info("Browser Initialized.");

        // Maximize window
        DriverFactory.getInstance().getDriver().manage().window().maximize();
        logger.info("Browser maximized.");

        // Set Implicit Wait
        DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(Duration.ofMinutes(10));
        DriverFactory.getInstance().getDriver().manage().timeouts().pageLoadTimeout(Duration.ofMinutes(10));
        DriverFactory.getInstance().getDriver().manage().timeouts().setScriptTimeout(Duration.ofMinutes(10));
        logger.info("Driver initialized with implicit wait.");
    }

    @AfterTest(alwaysRun = true)
    public void tearDownBrowser() throws InterruptedException {
        Thread.sleep(2 * 1000);
        DriverFactory.getInstance().closeBrowser();
    }
}
