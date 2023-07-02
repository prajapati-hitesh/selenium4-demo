package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.driver.DriverFactory;
import com.qa.selenium4.demo.helper.ElementHelper;
import com.qa.selenium4.demo.helper.WaitHelper;
import org.bouncycastle.util.encoders.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.network.Network;
import org.openqa.selenium.devtools.v114.network.model.Headers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class BasicAuthenticationTests {

    @Test(priority = 0, description = "Basic authentication using URL")
    public void basicAuthenticationUsingUrlTest() {
        // Load URl
        DriverFactory.getInstance().getDriver().get("https://guest:guest@jigsaw.w3.org/HTTP/Basic/");

        WebElement successLabelElement = DriverFactory.getInstance().getDriver()
                .findElement(By.xpath("//p[contains(normalize-space(),'Your browser made it!')]"));

        // Get Message
        String loginSuccessMsg = ElementHelper
                .getText(
                        DriverFactory.getInstance().getDriver(),
                        successLabelElement
                );

        // Wait for 2 min
        WaitHelper.hardWait(2);

        // Assert message
        Assert.assertEquals(loginSuccessMsg, "Your browser made it!");
    }

    @Test(priority = 1, description = "Basic Authentication Using Chrome Dev Tools")
    public void basicAuthenticationUsingCDPTest() {
        // Get Dev Tools
        DevTools devTools = DriverFactory.getInstance().getDevTools();

        // Create CDP Session
        devTools.createSession();

        // Enable Network
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Send Authentication Header
        Map<String, Object> requestHeaderMap = new LinkedHashMap<>();

        // Create Basic Authentication String to pass it as Header With Request
        String basicAuthToken = "Basic " +
                new String(Base64.encode(String.format("%s:%s", "guest", "guest").getBytes()));

        // Set value of Authorization to basic auth token
        requestHeaderMap.putIfAbsent("Authorization", basicAuthToken);

        // Send Request header using CDP
        devTools.send(Network.setExtraHTTPHeaders(new Headers(requestHeaderMap)));

        // Load URL
        DriverFactory.getInstance().getDriver().get("https://jigsaw.w3.org/HTTP/Basic/");

        WebElement successLabelElement = DriverFactory.getInstance().getDriver()
                .findElement(By.xpath("//p[contains(normalize-space(),'Your browser made it!')]"));

        // Get Message
        String loginSuccessMsg = ElementHelper.getText(DriverFactory.getInstance().getDriver(), successLabelElement);

        // Wait for 2 min
        WaitHelper.hardWait(2);

        // Assert message
        Assert.assertEquals(loginSuccessMsg, "Your browser made it!");
    }
}