package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.driver.DriverFactory;
import com.qa.selenium4.demo.helper.ElementHelper;
import com.qa.selenium4.demo.pages.theinternet.DigestAuthPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DigestAuthenticationTests {

    @Test(priority = 0, description = "Digest authentication through URL")
    public void basicAuthenticationUsingURLTest() {
        // Load Url
        DriverFactory.getInstance().getDriver().get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

        // Wait for page to load
        DigestAuthPage.waitForPageToLoad(DriverFactory.getInstance().getDriver());

        // Assert successful login
        WebElement messageElement = DigestAuthPage.getSuccessMessageElement(DriverFactory.getInstance().getDriver());

        // Get Text
        String actualMessage = ElementHelper.getText(DriverFactory.getInstance().getDriver(), messageElement);

        Assert.assertEquals(actualMessage, "Congratulations! You must have the proper credentials.");
    }
}
