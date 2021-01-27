package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.base.BaseDriver;
import com.qa.selenium4.demo.helper.ElementHelper;
import com.qa.selenium4.demo.helper.WaitHelper;
import org.apache.xmlbeans.impl.util.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v87.network.Network;
import org.openqa.selenium.devtools.v87.network.model.Headers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class BasicAuthenticationTests extends BaseDriver {

    @Test(priority = 0, description = "Basic authentication using URL")
    public void basicAuthenticationUsingUrlTest() {
        // Load URl
        driver.get("https://guest:guest@jigsaw.w3.org/HTTP/Basic/");

        // Get Message
        String loginSuccessMsg = ElementHelper.getText(driver, driver.findElement(By.xpath("//p[contains(normalize-space(),'Your browser made it!')]")));

        // Wait for 2 min
        WaitHelper.hardWait(2);

        // Assert message
        Assert.assertEquals(loginSuccessMsg, "Your browser made it!");
    }

    @Test(priority = 1, description = "Basic Authentication Using Chrome Dev Tools")
    public void basicAuthenticationUsingCDPTest() {
        // Get Dev Tools
        DevTools devTools = getDevTools();

        // Create CDP Session
        devTools.createSession();

        // Enable Network
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Send Authentication Header
        Map<String, Object> requestHeaderMap = new LinkedHashMap<>();
        String basicAuthToken = "Basic " + new String(Base64.encode(String.format("%s:%s", "guest", "guest").getBytes()));
        requestHeaderMap.putIfAbsent("Authorization", basicAuthToken);

        // Send Request header using CDP
        devTools.send(Network.setExtraHTTPHeaders(new Headers(requestHeaderMap)));

        // Load URL
        driver.get("https://guest:guest@jigsaw.w3.org/HTTP/Basic/");

        // Get Message
        String loginSuccessMsg = ElementHelper.getText(driver, driver.findElement(By.xpath("//p[contains(normalize-space(),'Your browser made it!')]")));

        // Wait for 2 min
        WaitHelper.hardWait(2);

        // Assert message
        Assert.assertEquals(loginSuccessMsg, "Your browser made it!");
    }
}