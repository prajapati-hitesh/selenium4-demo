package com.qa.selenium4.demo.windowshandling;

import com.qa.selenium4.demo.driver.DriverFactory;
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

public class WindowHandleTests {
    private static final Logger logger = LogManager.getLogger(WindowHandleTests.class.getName());

    @Test(priority = 0, description = "Working with Windows using Selenium 4")
    public void windowSwitchTest() {
        // Load URL in 1st window
        DriverFactory.getInstance().getDriver().get("https://www.saucedemo.com/");

        // Wait for Sauce Login Page to be ready
        SauceDemoLoginPage.waitForPageToLoad(DriverFactory.getInstance().getDriver());

        // Open 2nd Window
        DriverFactory.getInstance().getDriver().switchTo().newWindow(WindowType.WINDOW);

        // Load URL in 2nd Window
        DriverFactory.getInstance().getDriver().get("https://the-internet.herokuapp.com/");

        // Login
        theInternetLogin(DriverFactory.getInstance().getDriver());

        // Switch to 1st window
        List<String> windows = new ArrayList<>(DriverFactory.getInstance().getDriver().getWindowHandles());
        // Print window IDs
        windows.forEach(logger::info);

        // Switch to 1st window
        DriverFactory.getInstance().getDriver().switchTo().window(windows.get(0));

        // Login To Sauce Page
        SauceDemoLoginPage.usernameElement(DriverFactory.getInstance().getDriver()).sendKeys("standard_user");
        SauceDemoLoginPage.passwordElement(DriverFactory.getInstance().getDriver()).sendKeys("secret_sauce");
        SauceDemoLoginPage.loginButtonElement(DriverFactory.getInstance().getDriver()).click();
    }

    @Test(priority = 1, description = "Working with Multiple Tabs Using Selenium 4")
    public void tabSwitchTest() {
        // Load URL in 1st window
        DriverFactory.getInstance().getDriver().get("https://www.saucedemo.com/");

        // Wait for Sauce Login Page to be ready
        SauceDemoLoginPage.waitForPageToLoad(DriverFactory.getInstance().getDriver());

        // Open 2nd Window
        DriverFactory.getInstance().getDriver().switchTo().newWindow(WindowType.TAB);

        // Load URL in 2nd Window
        DriverFactory.getInstance().getDriver().get("https://the-internet.herokuapp.com/");

        // Login
        theInternetLogin(DriverFactory.getInstance().getDriver());

        // Switch to 1st window
        List<String> windows = new ArrayList<>(DriverFactory.getInstance().getDriver().getWindowHandles());

        // Print window IDs
        windows.forEach(logger::info);

        // Switch to 1st window
        DriverFactory.getInstance().getDriver().switchTo().window(windows.get(0));

        // Login To Sauce Page
        SauceDemoLoginPage.usernameElement(DriverFactory.getInstance().getDriver()).sendKeys("standard_user");
        SauceDemoLoginPage.passwordElement(DriverFactory.getInstance().getDriver()).sendKeys("secret_sauce");
        SauceDemoLoginPage.loginButtonElement(DriverFactory.getInstance().getDriver()).click();
    }

    private void theInternetLogin(WebDriver driverObj) {
        // Get Current base url
        if (StringUtility.getBaseUrl(driverObj.getCurrentUrl()).equalsIgnoreCase("https://the-internet.herokuapp.com/")) {
            // Get Header Element from 2nd Window
            WebElement headerElement = TheInternetHomePage.headerElement(DriverFactory.getInstance().getDriver());

            // Assert header text on 2nd window
            Assert.assertEquals(
                    ElementHelper.getText(DriverFactory.getInstance().getDriver(), headerElement),
                    "Welcome to the-internet",
                    "Assertion for header text failed"
            );

            // Get Form Authentication link
            ElementHelper.click(
                    DriverFactory.getInstance().getDriver(),
                    TheInternetHomePage.getAuthenticationFormElement(DriverFactory.getInstance().getDriver())
            );

            // Get Header Text
            String actualHeaderText = ElementHelper.getText(
                    DriverFactory.getInstance().getDriver(),
                    TheInternetLoginPage.getHeaderElement(DriverFactory.getInstance().getDriver())
            );

            // Assert text
            Assert.assertEquals(actualHeaderText, "Login Page", "Verification failed for Login Header.");

            // Enter UserName
            ElementHelper.sendKeys(
                    DriverFactory.getInstance().getDriver(),
                    TheInternetLoginPage.usernameElement(DriverFactory.getInstance().getDriver()),
                    "tomsmith"
            );

            // Enter Password
            ElementHelper.sendKeys(
                    DriverFactory.getInstance().getDriver(),
                    TheInternetLoginPage.passwordElement(DriverFactory.getInstance().getDriver()),
                    "SuperSecretPassword!"
            );

            // Click Login Button
            ElementHelper.click(
                    DriverFactory.getInstance().getDriver(),
                    TheInternetLoginPage.loginButtonElement(DriverFactory.getInstance().getDriver())
            );

            // Assert Success Message
            String actualMessageOnFlash = ElementHelper.getText(
                    DriverFactory.getInstance().getDriver(),
                    TheInternetLoginPage.flashMessageElement(DriverFactory.getInstance().getDriver())
            ).split("\\n")[0];

            Assert.assertEquals(actualMessageOnFlash, "You logged into a secure area!", "Login flash message verification failed");

            // Close Window/Tab
            driverObj.close();
        } else {
            throw new IllegalArgumentException("Please navigate to 'The Internet' Website before running this function");
        }
    }
}
