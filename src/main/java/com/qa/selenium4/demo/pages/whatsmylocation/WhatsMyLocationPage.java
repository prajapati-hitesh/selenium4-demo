package com.qa.selenium4.demo.pages.whatsmylocation;

import com.qa.selenium4.demo.helper.ElementHelper;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class WhatsMyLocationPage {
    private WebDriver driverObj;
    @FindBy(id = "latitude")
    private WebElement latitudeLabelElement;

    @FindBy(id = "longitude")
    private WebElement longitudeLabelElement;

    @FindBy(id = "gps_lat")
    private WebElement gpsLatitudeLabelElement;

    @FindBy(id = "gps_lng")
    private WebElement gpsLongitudeLabelElement;

    @FindBy(id = "street")
    private WebElement streetLabelElement;

    @FindBy(id = "county")
    private WebElement countyLabelElement;

    @FindBy(id = "city")
    private WebElement cityLabelElement;

    @FindBy(id = "state")
    private WebElement stateLabelElement;

    @FindBy(id = "country")
    private WebElement countryLabelElement;

    @FindBy(id = "zipcode")
    private WebElement zipcodeLabelElement;

    @FindBy(id = "address")
    private WebElement addressLabelElement;

    public WhatsMyLocationPage(WebDriver driver) {
        this.driverObj = driver;
        // Initialize Page Factory
        PageFactory.initElements(driver, this);
    }

    public void moveToMap() {
        new Actions(driverObj)
                .moveToElement(driverObj.findElement(By.id("mapholder")))
                .build()
                .perform();
    }

    public Map<String, String> getAddressDetails() {
        Map<String, String> addressDetails = new LinkedHashMap<>();

        // Get Address Details
        String address = ElementHelper.getText(driverObj, addressLabelElement);
        String latitude = ElementHelper.getText(driverObj, latitudeLabelElement);
        String longitude = ElementHelper.getText(driverObj, longitudeLabelElement);
        String gpsLatitude = ElementHelper.getText(driverObj, gpsLatitudeLabelElement);
        String gpsLongitude = ElementHelper.getText(driverObj, gpsLongitudeLabelElement);
        String street = ElementHelper.getText(driverObj, streetLabelElement);
        String county = ElementHelper.getText(driverObj, countyLabelElement);
        String city = ElementHelper.getText(driverObj, cityLabelElement);
        String state = ElementHelper.getText(driverObj, stateLabelElement);
        String country = ElementHelper.getText(driverObj, countryLabelElement);
        String zipcode = ElementHelper.getText(driverObj, zipcodeLabelElement);

        // Add Details to Map
        addressDetails.putIfAbsent("Address", StringUtils.normalizeSpace(address));
        addressDetails.putIfAbsent("Latitude", StringUtils.normalizeSpace(latitude));
        addressDetails.putIfAbsent("Longitude", StringUtils.normalizeSpace(longitude));
        addressDetails.putIfAbsent("GPS Latitude", StringUtils.normalizeSpace(gpsLatitude));
        addressDetails.putIfAbsent("GPS Longitude", StringUtils.normalizeSpace(gpsLongitude));
        addressDetails.putIfAbsent("Street", StringUtils.normalizeSpace(street));
        addressDetails.putIfAbsent("County", StringUtils.normalizeSpace(county));
        addressDetails.putIfAbsent("City", StringUtils.normalizeSpace(city));
        addressDetails.putIfAbsent("State", StringUtils.normalizeSpace(state));
        addressDetails.putIfAbsent("Country", StringUtils.normalizeSpace(country));
        addressDetails.putIfAbsent("Zip Code", StringUtils.normalizeSpace(zipcode));

        // Remove all empty values
        addressDetails.values().removeIf(StringUtils::isBlank);

        return addressDetails;
    }
}
