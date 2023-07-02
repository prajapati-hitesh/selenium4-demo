package com.qa.selenium4.reflection.pages;

import com.qa.appium.helper.AppiumEventHelpers;
import com.qa.appium.helper.AppiumSyncHelper;
import com.qa.appium.thread.ThreadLocalAppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class GoogleSyncPage {
    By skipSyncButton = By.id("android:id/button2");
    By backupAndSyncLabel = By.id("android:id/text1");
    By signInButton = By.id("android:id/button1");

    String backupAndSyncLabelText = "Back up & organize your contacts with Google";

    public void skipContactSync() {
        // wait for page to load
        AppiumSyncHelper.getWebDriverWait(ThreadLocalAppiumDriver.getDriver(), Duration.ofSeconds(120))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(backupAndSyncLabel));

        AppiumEventHelpers.getInstance().click(skipSyncButton);
    }
}
