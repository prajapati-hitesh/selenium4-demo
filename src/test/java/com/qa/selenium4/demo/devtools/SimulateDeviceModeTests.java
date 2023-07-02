package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.driver.DriverFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.emulation.Emulation;
import org.openqa.selenium.devtools.v114.emulation.model.ScreenOrientation;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This Test class contains use of CDP for Simulating Device Mode using
 * In-Build options available as well as using executeCdpCommand().
 * <p>
 * Please refer to the following URL :
 * https://chromedevtools.github.io/devtools-protocol/tot/Emulation/
 */
public class SimulateDeviceModeTests {

    @Test(priority = 0, description = "Enable simulation of a device using CDP's send method")
    public void phpTravelsSimulatingDeviceTestOne() {

        // Get Dev Tools
        DevTools devTools = DriverFactory.getInstance().getDevTools();

        // Create Session
        devTools.createSession();

        // Set Simulating device details
        devTools.send(Emulation.setDeviceMetricsOverride(
                320,
                556,
                0.5,
                true,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of(new ScreenOrientation(ScreenOrientation.Type.LANDSCAPEPRIMARY, 90)),
                Optional.empty(),
                Optional.empty())
        );

        // load url
        DriverFactory.getInstance().getDriver().get("https://www.amazon.in/");
    }

    @Test(priority = 1, description = "Enable simulation of a device using Emulation.setDeviceMetricsOverride")
    public void phpTravelsSimulatingDeviceTestTwo() {

        // Get Dev Tools
        DevTools devTools = DriverFactory.getInstance().getDevTools();

        // Create Session
        devTools.createSession();

        // Create a map to hold Device Metrics
        Map deviceMetrics = new HashMap() {{
            put("width", 320);
            put("height", 556);
            put("mobile", false);
            put("deviceScaleFactor", 1.25);
        }};

        ((ChromeDriver) DriverFactory.getInstance().getDriver()).executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        // load url
        DriverFactory.getInstance().getDriver().get("https://www.amazon.in/");
    }
}
