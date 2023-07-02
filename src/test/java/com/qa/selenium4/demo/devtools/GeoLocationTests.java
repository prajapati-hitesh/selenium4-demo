package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.driver.DriverFactory;
import com.qa.selenium4.demo.helper.WaitHelper;
import com.qa.selenium4.demo.pages.whatsmylocation.WhatsMyLocationPage;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.emulation.Emulation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

public class GeoLocationTests {

    @Test(priority = 0, description = "Check Current Geo-Location")
    public void geoLocationTest() {
        // Load URL
        DriverFactory.getInstance().getDriver().get("https://whatmylocation.com/");

        // Wait for 5 seconds
        WaitHelper.hardWait(5);

        WhatsMyLocationPage whatsMyLocationPage = new WhatsMyLocationPage(DriverFactory.getInstance().getDriver());

        // Move To Map
        whatsMyLocationPage.moveToMap();

        // Get Address Details
        whatsMyLocationPage
                .getAddressDetails()
                .forEach((key, value) -> {
                    System.out.println(key + " : " + value);
                });
    }

    @Test(priority = 1, description = "Mock geo-location using Chrome Dev Tools", dataProvider = "getLatLong")
    public void mockGeoLocationUsingCDPTest(String location, Number latitude, Number longitude, Number accuracy) {
        // Get Chrome Dev Tools
        DevTools chromeDevTools = DriverFactory.getInstance().getDevTools();

        // Create Session
        chromeDevTools.createSession();

        // Emulate Geo Location
        chromeDevTools.send(Emulation.setGeolocationOverride(
                Optional.of(latitude),
                Optional.of(longitude),
                Optional.of(accuracy)
        ));

        // Load URL
        DriverFactory.getInstance().getDriver().get("https://whatmylocation.com/");

        // Wait for 5 seconds
        WaitHelper.hardWait(5);

        WhatsMyLocationPage whatsMyLocationPage = new WhatsMyLocationPage(DriverFactory.getInstance().getDriver());

        // Move To Map
        whatsMyLocationPage.moveToMap();

        System.out.println("\nLocation Details For : " + location);

        // Get Address Details
        whatsMyLocationPage
                .getAddressDetails()
                .forEach((key, value) -> {
                    System.out.println(key + " : " + value);
                });
    }

    @DataProvider(name = "getLatLong")
    public Object[][] getLatLong() {
        return new Object[][]{
                {"New York, US", 40.712776, -74.005974, 100},
                {"Leeds, UK", 53.800755, -1.549077, 100},
                {"Eiffel Tower, Paris", 48.858372, 2.294481, 100}
        };
    }
}
