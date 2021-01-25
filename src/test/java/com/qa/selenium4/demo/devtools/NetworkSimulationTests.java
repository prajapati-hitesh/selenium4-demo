package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.base.BaseDriver;
import com.qa.selenium4.demo.helper.JavaScriptHelper;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v87.network.Network;
import org.openqa.selenium.devtools.v87.network.model.ConnectionType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class contains tests related to simulation
 * of network related switches for Chrome.
 * <p>
 * Please use below url for detailed list of Network simulation options
 * https://chromedevtools.github.io/devtools-protocol/tot/Network/
 */
public class NetworkSimulationTests extends BaseDriver {

    @Test(priority = 0, dataProvider = "getNetworkSpeeds")
    public void phpTravelsNetworkSimulationOne(boolean disableNetwork, int latency, int download, int upload) {
        DevTools chromeDevTools = ((ChromeDriver) driver).getDevTools();

        // Create session
        chromeDevTools.createSession();

        // Enables network tracking, network events will now be delivered to the client.
        chromeDevTools.send(Network.enable(Optional.of(1000000), Optional.empty(), Optional.empty()));

        // Emulate Network Conditions
        chromeDevTools.send(Network.emulateNetworkConditions(
                disableNetwork,
                latency,
                download,
                upload,
                Optional.of(ConnectionType.WIFI)
        ));

        List<String> networkLogs = new ArrayList<>();

        chromeDevTools.addListener(Network.loadingFailed(), loadingFailed -> {
            networkLogs.add(loadingFailed.getErrorText());
        });

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            // Load URL
            driver.get("https://www.phptravels.net/");
        } catch (WebDriverException ignored) {
        }

        // If network is not disabled.
        if (!disableNetwork) {
            // Wait for JS to load
            JavaScriptHelper.waitForJStoLoad(driver, Duration.ofMinutes(5));

            // Wait for page to be ready
            new WebDriverWait(driver, Duration.ofMinutes(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdownCurrency")));
        } else {
            // Check if all the captured logs matches expected result
            networkLogs.forEach(networkLog -> {
                Assert.assertEquals(networkLog, "net::ERR_INTERNET_DISCONNECTED");
            });
        }

        // Stop the watch
        stopWatch.stop();
        System.out.println("Time taken to load website : " + stopWatch);
    }

    @DataProvider(name = "getNetworkSpeeds")
    public Object[][] getNetworkSpeeds() {
        // Speed should be in bytes/sec
        // 1 Mbps   = 1,25,000  bytes/sec ~ 128 KBPS
        // 2 Mbps   = 2,50,000  bytes/sec ~ 256 KBPS
        // 4 Mbps   = 5,00,000  bytes/sec ~ 512 KBPS
        // 8 Mbps   = 10,00,000 bytes/sec ~ 1 MBPS
        // 16 Mbps  = 20,00,000 bytes/sec ~ 2 MBPS
        return new Object[][]
                {
                        {false, 5, 125000, 125000},
                        {false, 5, 250000, 250000},
                        {false, 5, 500000, 500000},
                        {false, 5, 1000000, 10000},
                        {false, 5, 2000000, 2000000},
                        {true, 5, 500000, 500000}
                };
    }
}
