package com.qa.appium.capabilities;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

public class AndroidCapabilities {
    private final DesiredCapabilities androidCapabilities;

    public AndroidCapabilities(DesiredCapabilities generalDesiredCapabilities) {
        this.androidCapabilities = generalDesiredCapabilities;
    }

    /**
     * Activity name for the Android activity you want to launch from your package.
     * This often needs to be preceded by a . (e.g., .MainActivity instead of MainActivity).
     * By default this capability is received from the package manifest
     * (action: android.intent.action.MAIN , category: android.intent.category.LAUNCHER)
     * <p>
     * Examples : MainActivity, .Settings
     *
     * @param appActivity
     * @return
     */
    public AndroidCapabilities setAppActivityAs(String appActivity) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, StringUtils.normalizeSpace(appActivity));
        return this;
    }

    /**
     * Java package of the Android app you want to run.
     * By default this capability is received from the package manifest
     * (@package attribute value)
     * <p>
     * Examples : com.example.android.myApp, com.android.settings
     *
     * @param appPackage
     * @return
     */
    public AndroidCapabilities setAppPackageAs(String appPackage) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, StringUtils.normalizeSpace(appPackage));
        return this;
    }

    /**
     * Activity name/names, comma separated, for the Android activity you want to wait for.
     * By default the value of this capability is the same as for appActivity.
     * You must set it to the very first focused application activity name
     * in case it is different from the one which is set as appActivity if your capability has appActivity and appPackage.
     * You can also use wildcards (*).
     * <p>
     * Examples: SplashActivity, SplashActivity,OtherActivity, *, *.SplashActivity
     *
     * @param activityNamesCommaSeparated
     * @return
     */
    public AndroidCapabilities appWaitActivity(String activityNamesCommaSeparated) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, activityNamesCommaSeparated.trim());
        return this;
    }

    /**
     * Java package of the Android app you want to wait for.
     * By default the value of this capability is the same as for appActivity
     * <br><br>
     * Example Values :
     *
     * <code>com.example.android.myApp</code>, <code>com.android.settings</code>
     *
     * <br><br>
     *
     * @param listOfPackagesCommaSeparated
     * @return
     */
    public AndroidCapabilities appWaitPackage(String listOfPackagesCommaSeparated) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_PACKAGE, listOfPackagesCommaSeparated.trim());
        return this;
    }

    /**
     * Timeout in milliseconds used to wait for the appWaitActivity to launch
     * (default 20000)
     *
     * @param appWaitDurationInMillis
     * @return
     */
    public AndroidCapabilities appWaitDuration(int appWaitDurationInMillis) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_DURATION, appWaitDurationInMillis);
        return this;
    }

    /**
     * Timeout in seconds while waiting for device to become ready
     *
     * @param timeoutInSeconds
     * @return
     */
    public AndroidCapabilities deviceReadyTimeout(int timeoutInSeconds) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.DEVICE_READY_TIMEOUT, timeoutInSeconds);
        return this;
    }

    /**
     * Timeout in milliseconds used to wait for an apk to install to the device.
     * <p>
     * Defaults to 90000
     *
     * @param timeoutInMillis
     * @return
     */
    public AndroidCapabilities appInstallTimeout(int timeoutInMillis) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, timeoutInMillis);
        return this;
    }

    /**
     * The name of the directory on the device in which the apk will be push before install.
     * <br><br>
     * Defaults to <code>/data/local/tmp</code>
     * <br><br>
     * Example : <code>/sdcard/Downloads/</code>
     *
     * @param appPathInDevice
     * @return
     */
    public AndroidCapabilities appToPushInDeviceAt(String appPathInDevice) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_PATH, appPathInDevice.trim());
        return this;
    }

    /**
     * Port used to connect to the ADB server (default 5037)
     *
     * @param adbPort
     * @return
     */
    public AndroidCapabilities adbPortToConnect(int adbPort) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.ADB_PORT, adbPort);
        return this;
    }

    /**
     * <code>systemPort</code> used to connect to appium-uiautomator2-server or appium-espresso-driver.
     * The default is <code>8200</code> in general and selects one port from <code>8200</code> to <code>8299</code>
     * for appium-uiautomator2-server, it is <code>8300</code> from <code>8300</code> to <code>8399</code> for appium-espresso-driver.
     * When you run tests in parallel, you must adjust the port to avoid conflicts.
     *
     * <br><br>
     * Read  <a href="https://github.com/appium/appium/blob/master/docs/en/advanced-concepts/parallel-tests.md#parallel-android-tests">
     * Parallel Testing Setup Guide
     * </a>
     * for more details.
     *
     * @param systemPort
     * @return
     */
    public AndroidCapabilities wdaPort(int systemPort) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
        return this;
    }


    /**
     * The absolute local path to webdriver executable
     * (if Chromium embedder provides its own webdriver,
     * it should be used instead of original chromedriver bundled with Appium)
     *
     * @param chromeDriverBinaryPath
     * @return
     */
    public AndroidCapabilities chromeDriverExecutablePath(String chromeDriverBinaryPath) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, chromeDriverBinaryPath.trim());
        return this;
    }

    /**
     * An array of arguments to be passed to the chromedriver binary when it's run by Appium.
     * By default no CLI args are added beyond what Appium uses internally
     * (such as <code>--url-base</code>, <code>--port</code>, <code>--adb-port</code>, and <code>--log-path.</code>
     * <br><br>
     * <p>
     * Example Value : <code>["--disable-gpu", "--disable-web-security"]</code>
     *
     * @param chromeArguments
     * @return
     */
    public AndroidCapabilities chromeDriverArguments(List<String> chromeArguments) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_ARGS, chromeArguments.toArray());
        return this;
    }

    /**
     * The absolute path to a directory to look for Chromedriver executables in,
     * for automatic discovery of compatible Chromedrivers.
     * Ignored if <code>chromedriverUseSystemExecutable = true</code>
     *
     * @param chromeDriverDirectory
     * @return
     */
    public AndroidCapabilities chromeDriverExecutableDirectory(String chromeDriverDirectory) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE_DIR, chromeDriverDirectory.trim());
        return this;
    }

    /**
     * The absolute path to a file which maps Chromedriver versions to the minimum Chrome that it supports.
     * Ignored if <code>chromedriverUseSystemExecutable = true</code>
     *
     * @param chromeDriverVersionMapPath
     * @return
     */
    public AndroidCapabilities chromeDriverVersionMappingFile(String chromeDriverVersionMapPath) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_CHROME_MAPPING_FILE, chromeDriverVersionMapPath.trim());
        return this;
    }

    /**
     * Numeric port to start Chromedriver on.
     * Note that use of this capability is discouraged as it will cause undefined behavior in case there are multiple webviews present.
     * By default Appium will find a free port.
     *
     * @param chromePort
     * @return
     */
    public AndroidCapabilities chromeDriverPort(int chromePort) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_PORT, chromePort);
        return this;
    }

    /**
     * Doesn't stop the process of the app under test, before starting the app using adb.
     * If the app under test is created by another anchor app, setting this false,
     * allows the process of the anchor app to be still alive, during the start of the test app using adb.
     * In other words, with dontStopAppOnReset set to true,
     * we will not include the -S flag in the adb shell am start call.
     * With this capability omitted or set to false, we include the -S flag.
     * Default false
     *
     * @param doNotStopAppOnReset Whether to stop the app on reset or not
     * @return {@link AndroidCapabilities}
     */
    public AndroidCapabilities doNotStopAppOnReset(boolean doNotStopAppOnReset) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.DONT_STOP_APP_ON_RESET, doNotStopAppOnReset);
        return this;
    }

    /**
     * To hide keyboard through execution of the script.
     * This function makes use or <code>unicodeKeyboard = true</code>
     * & <code>resetKeyboard = true</code> appium capability
     *
     * @return {@link AndroidCapabilities}
     */
    public AndroidCapabilities hideKeyboard() {
        // Enable Unicode input, default false
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);

        //Reset keyboard to its original state, after running Unicode tests with unicodeKeyboard capability.
        // Ignored if used alone. Default false
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true);
        return this;
    }

    /**
     * Allows passing chromeOptions capability for ChromeDriver.
     * For more information see
     * <a href="https://sites.google.com/a/chromium.org/chromedriver/capabilities">
     * <code>chromeOptions</code>
     * </a> <br><br>
     * <p>
     * e.g. <code>chromeOptions: {args: ['--disable-popup-blocking']}</code>
     *
     * @param chromeOptions
     * @return
     */
    public AndroidCapabilities chromeOptions(ChromeOptions chromeOptions) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.CHROME_OPTIONS, chromeOptions);
        return this;
    }

    /**
     * Kill ChromeDriver session when moving to a non-ChromeDriver webview.
     * Defaults to false
     *
     * @param reCreateSession
     * @return
     */
    public AndroidCapabilities reCreateChromeDriverSession(boolean reCreateSession) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.RECREATE_CHROME_DRIVER_SESSIONS, reCreateSession);
        return this;
    }

    /**
     * Have Appium automatically determine which permissions your app requires and grant them to the app on install.
     * Defaults to <code>false</code>.
     * If <code>noReset = true</code>, this capability doesn't work.
     *
     * @return
     */
    public AndroidCapabilities autoGrantAppPermissions() {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        return this;
    }

    /**
     * Timeout in milliseconds used to wait for adb command execution.
     * Defaults to <code>20000</code>
     *
     * @param timeOutInMillis
     * @return
     */
    public AndroidCapabilities adbExecutionTimeout(int timeOutInMillis) {
        this.androidCapabilities.setCapability(AndroidMobileCapabilityType.ADB_EXEC_TIMEOUT, timeOutInMillis);
        return this;
    }

    /**
     * Timeout in milliseconds used to wait for an uiAutomator2 server to be installed.
     * Defaults to <code>20000</code>
     *
     * @param timeoutInMillis
     * @return
     */
    public AndroidCapabilities uiAutomator2ServerLaunchTimeout(int timeoutInMillis) {
        this.androidCapabilities.setCapability("appium:uiautomator2ServerLaunchTimeout", timeoutInMillis);
        return this;
    }

    /**
     * When a find operation fails, print the current page source.
     * Defaults to <code>false</code>.
     *
     * @return
     */
    public AndroidCapabilities printPageSourceOnFindElementFailure() {
        this.androidCapabilities.setCapability("appium:printPageSourceOnFindFailure", true);
        return this;
    }

    public DesiredCapabilities build() {
        return this.androidCapabilities;
    }
}
