package com.qa.selenium4.demo.base;

import com.qa.selenium4.demo.constants.FileConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.time.Duration;

public class BaseDriver {
    private static final Logger logger = LogManager.getLogger(BaseDriver.class.getName());
    public WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void initializeBrowser() {
        System.setProperty("webdriver.chrome.silentOutput", "true");
       // WebDriverManager.chromedriver().setup(); // Commented this as this has been integrated as a part of Selenium 4.6.0

        ChromeOptions chromeOptions = new ChromeOptions();
        // Load Ad Blocker Extension
        chromeOptions.addExtensions(new File(FileConstants.CHROME_EXTENSION_DIR + "Ultimate AdBlocker_2_2_6_0.crx"));

        // Initialize Driver
        driver = new ChromeDriver(chromeOptions);
        logger.info("Browser Initialized.");

        // Maximize window
        driver.manage().window().maximize();
        logger.info("Browser maximized.");

        // Set Implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(10));
        driver.manage().timeouts().setScriptTimeout(Duration.ofMinutes(10));
        logger.info("Driver initialized with implicit wait.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() throws InterruptedException {

        Thread.sleep(2 * 1000);

        // Close Browser
        driver.close();
        logger.info("Browser Closed.");

        // If driver is of type ChromeDriver then close service
        if (driver instanceof ChromeDriver) {
            driver.quit();
            logger.info("ChromeDriver service closed.");
        }
    }

    /**
     * To Get the object of {@link DevTools} from {@link ChromeDriver} instance
     *
     * @return Object of {@link DevTools}
     */
    public DevTools getDevTools() {
        return ((ChromeDriver) driver).getDevTools();
    }
}
