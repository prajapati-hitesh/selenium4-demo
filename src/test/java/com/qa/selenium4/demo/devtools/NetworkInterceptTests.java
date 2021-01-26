package com.qa.selenium4.demo.devtools;

import com.google.common.collect.ImmutableList;
import com.qa.selenium4.demo.base.BaseDriver;
import com.qa.selenium4.demo.helper.WaitHelper;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v84.network.Network;
import org.testng.annotations.Test;

import java.util.Optional;

public class NetworkInterceptTests extends BaseDriver {

    @Test(priority = 0, description = "Block Images Using Chrome Dev Tools")
    public void blockImagesUsingCDPTest() {
        // Get Dev Tools
        DevTools chromeDevTools = getDevTools();

        // Create Session
        chromeDevTools.createSession();

        // Enable network
        chromeDevTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Block Specific file types by intercepting network
        chromeDevTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*.png", "*.jpeg", "*.svg", "*.gif")));

        // Load URl
        driver.get("https://www.amazon.in/");

        // Wait for 10 seconds
        WaitHelper.hardWait(10);
    }

    @Test(priority = 1, description = "Block Css using Chrome Dev Tools")
    public void blockCSSUsingCDPTest() {
        // Get Dev Tools
        DevTools chromeDevTools = getDevTools();

        // Create Session
        chromeDevTools.createSession();

        // Enable network
        chromeDevTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Block Specific file types by intercepting network
        chromeDevTools.send(Network.setBlockedURLs(ImmutableList.of("*.css", "*.scss")));

        // Load URl
        driver.get("https://www.amazon.in/");

        // Wait for 10 seconds
        WaitHelper.hardWait(10);
    }

    @Test(priority = 2, description = "Block JS Using Chrome Dev Tools")
    public void blockJSUsingCDPTest() {
        // Get Dev Tools
        DevTools chromeDevTools = getDevTools();

        // Create Session
        chromeDevTools.createSession();

        // Enable network
        chromeDevTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Block Specific file types by intercepting network
        chromeDevTools.send(Network.setBlockedURLs(ImmutableList.of("*.js")));

        // Load URl
        driver.get("https://www.amazon.in/");

        // Wait for 10 seconds
        WaitHelper.hardWait(10);
    }

    @Test(priority = 3, description = "Block Images & Css Using Chrome Dev Tools")
    public void blockImagesAndCSSUsingCDPTest() {
        // Get Dev Tools
        DevTools chromeDevTools = getDevTools();

        // Create Session
        chromeDevTools.createSession();

        // Enable network
        chromeDevTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Block Specific file types by intercepting network
        chromeDevTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*.png", "*.jpeg", "*.svg", "*.css", "*.scss", "*.gif")));

        // Load URl
        driver.get("https://www.amazon.in/");

        // Wait for 10 seconds
        WaitHelper.hardWait(10);
    }

    @Test(priority = 4, description = "Block Images, Css & Js Using Chrome Dev Tools")
    public void blockImagesCssAndJSUsingCDPTest() {
        // Get Dev Tools
        DevTools chromeDevTools = getDevTools();

        // Create Session
        chromeDevTools.createSession();

        // Enable network
        chromeDevTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Block Specific file types by intercepting network
        chromeDevTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*.png", "*.jpeg", "*.svg", "*.css", "*.scss", "*.js", "*.gif")));

        // Load URl
        driver.get("https://www.amazon.in/");

        // Wait for 10 seconds
        WaitHelper.hardWait(10);
    }
}