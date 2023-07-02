package com.qa.appium.session;


import com.qa.appium.capabilities.DesiredCapabilityBuilder;
import com.qa.appium.helper.ADBHelper;
import com.qa.appium.helper.AppiumSyncHelper;
import com.qa.appium.server.AppiumServerBuilder;
import com.qa.appium.thread.ThreadAppiumServer;
import com.qa.appium.thread.ThreadLocalAppiumDriver;
import com.qa.appium.thread.ThreadScriptVariables;
import com.qa.selenium4.utils.SystemUtility;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class AppiumSessionBuilder {
    private static final String USER_DIR = SystemUtility.getUserDirectory();
    private static final String FILE_SEPARATOR = SystemUtility.getFileSeparator();
    private static final Logger logger = LogManager.getLogger(AppiumSessionBuilder.class.getName());

    public void startAppiumServer(String appiumServerIPAddress, boolean chromeAutoDownload) {

        String normalizedServerIPAddress = StringUtils.normalizeSpace(appiumServerIPAddress);

        // Build Appium Service
        ThreadAppiumServer
                .setAppiumServerDriverService
                        (
                                new AppiumServerBuilder()
                                        .buildAppiumServerProcess(normalizedServerIPAddress, chromeAutoDownload)
                        );

        // Start Appium Service
        ThreadAppiumServer
                .getAppiumServerDriverService()
                .start();

        // Set appium server
        String appiumServerUrl = ThreadAppiumServer.getAppiumServerDriverService().getUrl().toString();

        String logDetails = "Appium Server Started At : " + appiumServerUrl;
        ThreadScriptVariables.setServerURI(appiumServerUrl);
        logger.info(logDetails);
    }

    public void initDevice(String deviceName, String platformName, String platformVersion,
                           String udid, String automationName, String webDriverPort,
                           String androidAppPackage,
                           String androidAppActivity) {
        try {

            String normalizedPlatformName = StringUtils.normalizeSpace(platformName).toUpperCase();

            // Set Thread Context with Device Name
            ThreadContext.put("DEVICE_NAME", StringUtils.normalizeSpace(deviceName));

            // Wait for 5 second before start
            AppiumSyncHelper.pauseScript(Duration.ofSeconds(5));

            if (normalizedPlatformName.equalsIgnoreCase(Platform.ANDROID.toString())) {

                // Setup Android Device
                androidDeviceSetup(
                        deviceName,
                        platformName,
                        platformVersion,
                        udid,
                        automationName,
                        webDriverPort,
                        androidAppPackage,
                        androidAppActivity
                );
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    private void androidDeviceSetup(String aDeviceName, String aPlatformName, String aPlatformVersion,
                                    String aUDID, String aAutomationName, String aWdaSysPort,
                                    String aAppPackage, String aAppActivity) {
        try {

            // Unlock Device If Locked
            new ADBHelper().unlockDevice(aUDID);

            String logDetails = "Device [" + aDeviceName + "] has Appium Server URL As : " + ThreadScriptVariables.getServerURI();
            logger.info(logDetails);

            DesiredCapabilityBuilder desiredCapabilityBuilder = new DesiredCapabilityBuilder();

            // Start Building Capabilities
            desiredCapabilityBuilder
                    .deviceName(aDeviceName)
                    .platformName(aPlatformName)
                    .platformVersion(aPlatformVersion)
                    .automationName(aAutomationName)
                    .setNewCommandTimeoutAs(300000);

            if (!StringUtils.isBlank(aUDID)) {
                desiredCapabilityBuilder
                        .udid(aUDID);
            }

            DesiredCapabilities aCaps = desiredCapabilityBuilder
                    .android()
                    .wdaPort(Integer.parseInt(aWdaSysPort))
                    .hideKeyboard()
                    .setAppPackageAs(aAppPackage)
                    .setAppActivityAs(aAppActivity)
                    .uiAutomator2ServerLaunchTimeout(300000)
                    .build();


            // Start Android Driver instance
            ThreadLocalAppiumDriver.setDriver(new AndroidDriver(new URL(ThreadScriptVariables.getServerURI()), aCaps));
            ThreadLocalAppiumDriver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Set Session Capability
            ThreadScriptVariables.setSessionCapability(aCaps);
        } catch (Exception ex) {
            // add exception to list
            logger.error(ex.getMessage(), ex);
        }
    }

    public void tearDownDevice() {
        try {
            // Hard wait for 5 second before closing the app
            logger.info("Waiting for 5 seconds before closing app and quitting driver.");
            Thread.sleep(5000);

            if (ThreadLocalAppiumDriver.getDriver() != null) {

                ThreadLocalAppiumDriver.getDriver().getCapabilities();
                // Close App Under Test
                logger.info("Closing app under test...!!!");

                if (ThreadLocalAppiumDriver.getDriver() instanceof IOSDriver) {
                    ThreadLocalAppiumDriver.getIOSDriver().closeApp();
                } else {
                    ThreadLocalAppiumDriver.getAndroidDriver().closeApp();
                }

                // Quit Appium Driver session
                logger.info("Deleting Appium Driver Session...!!!");

                ThreadLocalAppiumDriver.getAndroidDriver().quit();

            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void stopAppiumServer() {
        try {
            Thread.sleep(5000);

            if (ThreadAppiumServer.getAppiumServerDriverService() != null) {
                if (ThreadAppiumServer.getAppiumServerDriverService().isRunning()) {
                    // Close appium server
                    ThreadAppiumServer.getAppiumServerDriverService().stop();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
