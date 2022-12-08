package com.qa.selenium4.demo.tradeoff;

import com.qa.selenium4.demo.constants.FileConstants;
import com.qa.selenium4.demo.driver.DriverFactory;
import com.qa.selenium4.demo.helper.ElementHelper;
import com.qa.selenium4.demo.pages.seleniumeasy.RegisterPage;
import com.qa.selenium4.json.JSONArray;
import com.qa.selenium4.json.JSONObject;
import com.qa.selenium4.json.JSONParser;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Selenium4PerformanceTest {
    private static final Logger logger = LogManager.getLogger(Selenium4PerformanceTest.class.getName());

    @Test(priority = 0, dataProvider = "getPersonDetails")
    private void executionPerformanceTest(String firstName, String lastName, String email,
                                          String phone, String address, String city,
                                          String state, String zipCode, String website) {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        driver.get("https://www.seleniumeasy.com/test/input-form-demo.html");

        StopWatch stopWatch = new StopWatch();
        // Start Watch
        stopWatch.start();
        WebElement firstNameElement = RegisterPage.getFirstNameElement(driver);
        WebElement lastNameElement = RegisterPage.getLastNameElement(driver);
        WebElement emailElement = RegisterPage.getEmailElement(driver);
        WebElement phoneElement = RegisterPage.getPhoneElement(driver);
        WebElement addressElement = RegisterPage.getAddressElement(driver);
        WebElement cityElement = RegisterPage.getCityElement(driver);
        WebElement stateElement = RegisterPage.getStateElement(driver);
        WebElement zipcodeElement = RegisterPage.getZipCodeElement(driver);
        WebElement websiteElement = RegisterPage.getWebsiteElement(driver);

        // Perform Actions
        ElementHelper.sendKeys(driver, firstNameElement, firstName);
        ElementHelper.sendKeys(driver, lastNameElement, lastName);
        ElementHelper.sendKeys(driver, emailElement, email);
        ElementHelper.sendKeys(driver, phoneElement, phone);
        ElementHelper.sendKeys(driver, addressElement, address);
        ElementHelper.sendKeys(driver, cityElement, city);
        ElementHelper.selectElementByVisibleText(driver, stateElement, state);
        ElementHelper.sendKeys(driver, zipcodeElement, zipCode);
        ElementHelper.sendKeys(driver, websiteElement, website);

        // End Stopwatch
        stopWatch.stop();
        logger.info("Time Taken To Perform Action : " + stopWatch.toString());
    }

    @DataProvider(name = "getPersonDetails")
    public Object[][] getPersonDetailsFromJSON() {

        JSONObject personDetailsJsonObject = new JSONParser().parse(FileConstants.TEST_DATA_DIR + "PersonDetails.json");

        // Get JsonArray of person Details
        JSONArray personDetailsJArray = personDetailsJsonObject.getJSONArray("PersonDetails");

        // Get Rows & Columns
        int rows = personDetailsJArray.length();
        int cols = personDetailsJArray.getJSONObject(0).length();

        Object[][] testDataObject = new Object[rows][cols];

        final int[] rowCounter = {0};
        personDetailsJArray.forEach(object -> {
            JSONObject personJsonObject = (JSONObject) object;
            int colCounter = 0;
            testDataObject[rowCounter[0]][colCounter++] = personJsonObject.get("first_name");
            testDataObject[rowCounter[0]][colCounter++] = personJsonObject.get("last_name");
            testDataObject[rowCounter[0]][colCounter++] = personJsonObject.get("email");
            testDataObject[rowCounter[0]][colCounter++] = personJsonObject.get("phone");
            testDataObject[rowCounter[0]][colCounter++] = personJsonObject.get("address");
            testDataObject[rowCounter[0]][colCounter++] = personJsonObject.get("city");
            testDataObject[rowCounter[0]][colCounter++] = personJsonObject.get("state");
            testDataObject[rowCounter[0]][colCounter++] = personJsonObject.get("zipcode");
            testDataObject[rowCounter[0]][colCounter] = personJsonObject.get("website");
            rowCounter[0]++;
        });
        return testDataObject;
    }
}
