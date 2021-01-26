package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.base.BaseDriver;
import com.qa.selenium4.demo.helper.ElementHelper;
import com.qa.selenium4.demo.pages.theinternet.DigestAuthPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DigestAuthenticationTests extends BaseDriver {

    @Test(priority = 0, description = "Digest authentication through URL")
    public void basicAuthenticationUsingURLTest() {
        // Load Url
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

        // Wait for page to load
        DigestAuthPage.waitForPageToLoad(driver);

        // Assert successful login
        WebElement messageElement = DigestAuthPage.getSuccessMessageElement(driver);

        // Get Text
        String actualMessage = ElementHelper.getText(driver, messageElement);

        Assert.assertEquals(actualMessage, "Congratulations! You must have the proper credentials.");
    }
}
