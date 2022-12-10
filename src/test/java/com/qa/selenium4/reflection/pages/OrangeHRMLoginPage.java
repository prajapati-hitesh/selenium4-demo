package com.qa.selenium4.reflection.pages;

import com.qa.selenium4.demo.driver.DriverFactory;
import com.qa.selenium4.demo.helper.ElementHelper;
import com.qa.selenium4.demo.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class OrangeHRMLoginPage {
    By loginLogo = By.xpath("//*[text()='Login']");
    By loginButton = By.xpath("//button[normalize-space()='Login']");

    public WebElement getLoginLogoElement() {
        return WaitHelper.waitUntilElementIsVisible(
                DriverFactory.getInstance().getDriver(),
                Duration.ofSeconds(90),
                loginLogo
        );
    }

    public void login() {
        ElementHelper.click(DriverFactory.getInstance().getDriver(), loginButton);
    }
}
