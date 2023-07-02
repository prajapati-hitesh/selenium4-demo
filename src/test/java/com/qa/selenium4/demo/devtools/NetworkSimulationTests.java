package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.driver.DriverFactory;
import com.qa.selenium4.demo.helper.JavaScriptHelper;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.network.Network;
import org.openqa.selenium.devtools.v114.network.model.ConnectionType;
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
public class NetworkSimulationTests {

    @Test(priority = 0, dataProvider = "amazonIndiaNetworkSimulation")
    public void amazonIndiaNetworkSimulation(boolean disableNetwork, int latency, int download, int upload) {
        DevTools chromeDevTools = DriverFactory.getInstance().getDevTools();

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
            DriverFactory.getInstance().getDriver().get("https://www.amazon.in/");
        } catch (WebDriverException ignored) {
        }

        // If network is not disabled.
        if (!disableNetwork) {
            // Wait for JS to load
            JavaScriptHelper.waitForJStoLoad(DriverFactory.getInstance().getDriver(), Duration.ofMinutes(5));

            // Wait for page to be ready
            new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofMinutes(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-logo")));
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

    @DataProvider(name = "amazonIndiaNetworkSimulation")
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
