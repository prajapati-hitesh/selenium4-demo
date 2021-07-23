package com.qa.selenium4.demo.relativelocators;

import com.qa.selenium4.demo.base.BaseDriver;
import com.qa.selenium4.demo.helper.ElementHelper;
import com.qa.selenium4.demo.helper.JavaScriptHelper;
import com.qa.selenium4.demo.helper.WaitHelper;
import com.qa.selenium4.demo.pages.saucedemo.SauceDemoInventoryPage;
import com.qa.selenium4.demo.pages.saucedemo.SauceDemoLoginPage;
import com.qa.selenium4.demo.pages.seleniumeasy.RegisterPage;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RelativeLocatorTests extends BaseDriver {
    private static final Logger logger = LogManager.getLogger(RelativeLocatorTests.class.getName());

    @Test(priority = 0, description = "Locate elements using RelativeLocator strategy for Selenium Easy Page")
    private void registerTestOnSeleniumEasyPage() {
        driver.get("https://www.seleniumeasy.com/test/input-form-demo.html");

        StopWatch stopWatch = new StopWatch();
        // Start Watch
        stopWatch.start();
        WebElement firstNameElement = RegisterPage.getFirstNameElement(driver);
        WebElement lastNameElement = RegisterPage.getLastNameElement(driver);
        WebElement emailElement = RegisterPage.getEmailElement(driver);
        WebElement phoneElement = RegisterPage.getPhoneElement(driver);
        WebElement addressElement = RegisterPage.getAddressElement(driver);
        WebElement cityElement = RegisterPage.getCityElement(driver);
        WebElement stateElement = RegisterPage.getStateElement(driver);
        WebElement zipcodeElement = RegisterPage.getZipCodeElement(driver);
        WebElement websiteElement = RegisterPage.getWebsiteElement(driver);

        // Perform Actions
        ElementHelper.sendKeys(driver, firstNameElement, "Hitesh");
        ElementHelper.sendKeys(driver, lastNameElement, "Prajapati");
        ElementHelper.sendKeys(driver, emailElement, "hiteshprajapati1992@gmail.com");
        ElementHelper.sendKeys(driver, phoneElement, "(+91) 9427004603");
        ElementHelper.sendKeys(driver, addressElement, "A-102, XYZ");
        ElementHelper.sendKeys(driver, cityElement, "Vadodara");
        ElementHelper.selectElementByVisibleText(driver, stateElement, "Indiana");
        ElementHelper.sendKeys(driver, zipcodeElement, "390012");
        ElementHelper.sendKeys(driver, websiteElement, "https://www.linkedin.com/in/hiteshprajapati007/");

        // End Stopwatch
        stopWatch.stop();
        logger.info("Time Taken To Perform Action : " + stopWatch.toString());
    }

    @Test(priority = 1, description = "Locate elements using RelativeLocator strategy for Sauce Demo page")
    public void sauceDemoTest() {
        driver.get("https://www.saucedemo.com/index.html");

        ElementHelper.sendKeys(driver, SauceDemoLoginPage.usernameElement(driver), "standard_user");
        ElementHelper.sendKeys(driver, SauceDemoLoginPage.passwordElement(driver), "secret_sauce");
        ElementHelper.click(driver, SauceDemoLoginPage.loginButtonElement(driver));

        // Assert Product text
        Assert.assertEquals(ElementHelper.getText(driver, SauceDemoInventoryPage.productHeaderElement(driver)), "Products");

        WebElement cardElement = SauceDemoInventoryPage.backPackCardElement(driver);

        JavaScriptHelper.highlightElement(driver, cardElement);
    }

    @Test(priority = 2, description = "Locate elements using RelativeLocator strategy. Bad Example")
    public void relativeLocatorLoginPageBadExampleTest() {
        // Load URL
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

        // Get "Email Address" input element
        WebElement emailInputElement = driver.findElement(RelativeLocator
                .with(By.tagName("input"))
                .above(By.id("passwd"))
                .toRightOf(By.id("email_create"))
        );

        // Highlight element and
        JavaScriptHelper.highlightElement(driver, emailInputElement);

        // Wait for 5 sec
        WaitHelper.hardWait(5);

        // Enter Email Address
        ElementHelper.sendKeys(driver, emailInputElement, "hiteshprajapati1992@gmail.com");
    }

    @Test(priority = 3, description = "Locate elements using RelativeLocator strategy. Good Example")
    public void relativeLocatorLoginPageGoodExampleTest() {
        // Load URL
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

        // Get "Email Address" input element
        WebElement emailInputElement = driver.findElement(RelativeLocator
                .with(By.tagName("input"))
                .above(By.id("passwd"))
                .toRightOf(By.id("email_create"))
                .below(By.id("search_query_top"))
        );

        // Highlight element and
        JavaScriptHelper.highlightElement(driver, emailInputElement);

        // Wait for 5 sec
        WaitHelper.hardWait(5);

        // Enter Email Address
        ElementHelper.sendKeys(driver, emailInputElement, "hiteshprajapati1992@gmail.com");
    }
}
