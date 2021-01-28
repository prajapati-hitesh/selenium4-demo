package com.qa.selenium4.demo.windowshandling;

import com.qa.selenium4.demo.base.BaseDriver;
import com.qa.selenium4.demo.helper.ElementHelper;
import com.qa.selenium4.demo.pages.saucedemo.SauceDemoLoginPage;
import com.qa.selenium4.demo.pages.theinternet.TheInternetHomePage;
import com.qa.selenium4.demo.pages.theinternet.TheInternetLoginPage;
import com.qa.selenium4.utils.StringUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WindowHandleTests extends BaseDriver {
    private static final Logger logger = LogManager.getLogger(WindowHandleTests.class.getName());

    @Test(priority = 0, description = "Working with Windows using Selenium 4")
    public void windowSwitchTest() {
        // Load URL in 1st window
        driver.get("https://www.saucedemo.com/");

        // Wait for Sauce Login Page to be ready
        SauceDemoLoginPage.waitForPageToLoad(driver);

        // Open 2nd Window
        driver.switchTo().newWindow(WindowType.WINDOW);

        // Load URL in 2nd Window
        driver.get("https://the-internet.herokuapp.com/");

        // Login
        theInternetLogin(driver);

        // Switch to 1st window
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        // Print window IDs
        windows.forEach(logger::info);

        // Switch to 1st window
        driver.switchTo().window(windows.get(0));

        // Login To Sauce Page
        SauceDemoLoginPage.usernameElement(driver).sendKeys("standard_user");
        SauceDemoLoginPage.passwordElement(driver).sendKeys("secret_sauce");
        SauceDemoLoginPage.loginButtonElement(driver).click();
    }

    @Test(priority = 1, description = "Working with Multiple Tabs Using Selenium 4")
    public void tabSwitchTest() {
        // Load URL in 1st window
        driver.get("https://www.saucedemo.com/");

        // Wait for Sauce Login Page to be ready
        SauceDemoLoginPage.waitForPageToLoad(driver);

        // Open 2nd Window
        driver.switchTo().newWindow(WindowType.TAB);

        // Load URL in 2nd Window
        driver.get("https://the-internet.herokuapp.com/");

        // Login
        theInternetLogin(driver);

        // Switch to 1st window
        List<String> windows = new ArrayList<>(driver.getWindowHandles());

        // Print window IDs
        windows.forEach(logger::info);

        // Switch to 1st window
        driver.switchTo().window(windows.get(0));

        // Login To Sauce Page
        SauceDemoLoginPage.usernameElement(driver).sendKeys("standard_user");
        SauceDemoLoginPage.passwordElement(driver).sendKeys("secret_sauce");
        SauceDemoLoginPage.loginButtonElement(driver).click();
    }

    private void theInternetLogin(WebDriver driverObj) {
        // Get Current base url
        if (StringUtility.getBaseUrl(driverObj.getCurrentUrl()).equalsIgnoreCase("https://the-internet.herokuapp.com/")) {
            // Get Header Element from 2nd Window
            WebElement headerElement = TheInternetHomePage.headerElement(driver);

            // Assert header text on 2nd window
            Assert.assertEquals(
                    ElementHelper.getText(driver, headerElement),
                    "Welcome to the-internet",
                    "Assertion for header text failed"
            );

            // Get Form Authentication link
            ElementHelper.click(
                    driver,
                    TheInternetHomePage.getAuthenticationFormElement(driver)
            );

            // Get Header Text
            String actualHeaderText = ElementHelper.getText(
                    driver,
                    TheInternetLoginPage.getHeaderElement(driver)
            );

            // Assert text
            Assert.assertEquals(actualHeaderText, "Login Page", "Verification failed for Login Header.");

            // Enter UserName
            ElementHelper.sendKeys(
                    driver,
                    TheInternetLoginPage.usernameElement(driver),
                    "tomsmith"
            );

            // Enter Password
            ElementHelper.sendKeys(
                    driver,
                    TheInternetLoginPage.passwordElement(driver),
                    "SuperSecretPassword!"
            );

            // Click Login Button
            ElementHelper.click(
                    driver,
                    TheInternetLoginPage.loginButtonElement(driver)
            );

            // Assert Success Message
            String actualMessageOnFlash = ElementHelper.getText(
                    driver,
                    TheInternetLoginPage.flashMessageElement(driver)
            ).split("\\n")[0];

            Assert.assertEquals(actualMessageOnFlash, "You logged into a secure area!", "Login flash message verification failed");

            // Close Window/Tab
            driverObj.close();
        } else {
            throw new IllegalArgumentException("Please navigate to 'The Internet' Website before running this function");
        }
    }
}
