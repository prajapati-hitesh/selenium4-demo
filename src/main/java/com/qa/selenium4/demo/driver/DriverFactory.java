package com.qa.selenium4.demo.driver;

import com.qa.selenium4.demo.enums.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.Optional;

public class DriverFactory {
    private static final Logger logger = LogManager.getLogger(DriverFactory.class.getName());

    private static final ThreadLocal<DriverFactory> instance = new ThreadLocal<>();

    public static DriverFactory getInstance() {
        if (instance.get() == null) {
            instance.set(new DriverFactory());
        }
        return instance.get();
    }

    public static Object resolveOptionalObject(Optional<?> object) {
        return object.orElse(null);
    }

    public WebDriver getDriver() {
        return ThreadLocalSEDriver.getDriver();
    }

    public void initBrowser(Browser browser, Optional<?> browserOptions) {
        switch (browser) {
            case IE -> {
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                ThreadLocalSEDriver
                        .setDriver(new InternetExplorerDriver(ieOptions));
            }
            case MicrosoftEdge -> {
                WebDriverManager.edgedriver().setup();
                ThreadLocalSEDriver.setDriver(new EdgeDriver());
            }
            case Firefox -> {
                WebDriverManager.firefoxdriver().setup();
                ThreadLocalSEDriver
                        .setDriver(new FirefoxDriver());

            }
            case Chromium -> {
                WebDriverManager.chromiumdriver().setup();
                ThreadLocalSEDriver.setDriver(new ChromeDriver());
            }
            default -> {
                WebDriverManager.chromedriver().setup();

                if (browserOptions.isPresent() && browserOptions.get() instanceof ChromeOptions) {
                    ThreadLocalSEDriver.setDriver(
                            new ChromeDriver((ChromeOptions) browserOptions.get())
                    );
                } else {
                    ThreadLocalSEDriver
                            .setDriver(new ChromeDriver());
                }
            }
        }

        // Maximize browser
        ThreadLocalSEDriver.getDriver().manage().window().maximize();
    }

    public void closeBrowser() {
        try {
            if (ThreadLocalSEDriver.getDriver() != null) {
                ThreadLocalSEDriver
                        .getDriver()
                        .close();

                if (ThreadLocalSEDriver.getDriver() instanceof ChromeDriver) {
                    ThreadLocalSEDriver
                            .getDriver()
                            .quit();
                }
            }
        } catch (WebDriverException ex) {
            logger.error(ex.getMessage(), ex);
        }

        // set value to null
        ThreadLocalSEDriver
                .setDriver(null);
    }

    public DevTools getDevTools() {
        if (ThreadLocalSEDriver.getDriver() instanceof ChromeDriver) {
            return (((ChromeDriver) ThreadLocalSEDriver.getDriver()).getDevTools());
        } else {
            throw new IllegalStateException("CDP Only works with Chrome Browser. Please initiate Chrome instance before calling this method");
        }
    }
}