package com.qa.selenium4.demo.windowshandling;

import com.qa.selenium4.demo.base.BaseDriver;
import com.qa.selenium4.demo.helper.ElementHelper;
import com.qa.selenium4.demo.pages.theinternet.TheInternetHomePage;
import com.qa.selenium4.demo.pages.theinternet.TheInternetLoginPage;
import com.qa.selenium4.utils.StringUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WindowHandleTests extends BaseDriver {
    private static final Logger logger = LogManager.getLogger(WindowHandleTests.class.getName());

    @Test(priority = 0)
    public void windowSwitchTest() {
        // Load URL in 1st window
        driver.get("https://phptravels.net/");

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

        // Click on Flights link
        ElementHelper.click(driver, driver.findElement(By.linkText("FLIGHTS")));
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
