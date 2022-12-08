package com.qa.selenium4.demo.screencapture;

import com.qa.selenium4.demo.constants.FileConstants;
import com.qa.selenium4.demo.driver.DriverFactory;
import com.qa.selenium4.demo.pages.saucedemo.SauceDemoLoginPage;
import com.qa.selenium4.utils.DateUtility;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CaptureElementScreenshotTests {

    private static final String SCREENSHOT_DIR = FileConstants.SCREENSHOT_ROOT_DIR;

    @Test(priority = 0, description = "Capture element screenshot using Selenium 4.")
    public void captureElementScreenshotTestOne() throws IOException {
        // Load URL
        DriverFactory.getInstance().getDriver().get("https://www.saucedemo.com/");

        // Wait for Page to load
        SauceDemoLoginPage.waitForPageToLoad(DriverFactory.getInstance().getDriver());

        // Get Bot Element
        WebElement sauceBotImageElement = DriverFactory.getInstance().getDriver().findElement(By.className("bot_column"));

        // Print dimension of Bot - getRect is introduced in Selenium 4.
        Rectangle botRect = sauceBotImageElement.getRect();
        System.out.println("Height x Width : [" + botRect.getHeight() + " x " + botRect.getWidth() + "]");
        System.out.println("(X, Y) : (" + botRect.getX() + ", " + botRect.getY() + ")");

        // Capture Screenshot and store it
        byte[] sauceBotImageAsBytes = sauceBotImageElement.getScreenshotAs(OutputType.BYTES);

        // Build path where to save screenshot
        String ssFilePath = SCREENSHOT_DIR + "Sauce Bot Image - " + DateUtility.getCurrentTimeStamp() + ".png";

        // Save Screenshot
        FileUtils.writeByteArrayToFile(new File(ssFilePath), sauceBotImageAsBytes);
    }
}
