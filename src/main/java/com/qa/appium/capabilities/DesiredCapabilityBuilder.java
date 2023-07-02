package com.qa.appium.capabilities;

import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilityBuilder {

    DesiredCapabilities generalDesiredCapabilities;

    public DesiredCapabilityBuilder() {
        // To Do
        this.generalDesiredCapabilities = new DesiredCapabilities();
    }

    /**
     * Which automation engine to use
     * <br><br>
     * <p>
     * e.g. <code>Appium</code> (default), or <code>UiAutomator2, Espresso</code>, or <code>UiAutomator1</code> for Android,
     * or <code>XCUITest</code> or <code>Instruments</code> for iOS,
     * or <code>YouiEngine</code> for application built with You.i Engine <br>
     *
     * @param automationName
     * @return
     */
    public DesiredCapabilityBuilder automationName(String automationName) {
        this.generalDesiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, StringUtils.normalizeSpace(automationName));
        return this;
    }

    /**
     * Which mobile OS platform to use
     * <br><br>
     * e.g. <code>iOS, Android, or FirefoxOS</code>
     *
     * @param platformName
     * @return
     */
    public DesiredCapabilityBuilder platformName(String platformName) {
        this.generalDesiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, StringUtils.normalizeSpace(platformName));
        return this;
    }

    /**
     * Mobile OS version
     * e.g. <code> 7.1, 4.4, 7.0.1</code>
     *
     * @param platformVersion
     * @return
     */
    public DesiredCapabilityBuilder platformVersion(String platformVersion) {
        this.generalDesiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, StringUtils.normalizeSpace(platformVersion));
        return this;
    }

    /**
     * The kind of mobile device or emulator to use <br><br>
     * <p>
     * e.g. <code>iPhone Simulator, iPad Simulator, iPhone Retina 4-inch, Android Emulator, Galaxy S4,</code> etc....
     * On iOS, this should be one of the valid devices returned by instruments with <code>instruments -s devices</code>.
     * On Android this capability is currently ignored, though it remains required.
     *
     * @param deviceName
     * @return
     */
    public DesiredCapabilityBuilder deviceName(String deviceName) {
        this.generalDesiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, StringUtils.normalizeSpace(deviceName));
        return this;
    }

    /**
     * The absolute local path or remote http URL to a .ipa file (IOS),
     * .app folder (IOS Simulator), .apk file (Android) or .apks file (Android App Bundle),
     * or a .zip file containing one of these. Appium will attempt to install this app binary on the appropriate device first.
     * Note that this capability is not required for Android if you specify appPackage and appActivity capabilities (see below).
     * UiAutomator2 and XCUITest allow to start the session without app or appPackage. Incompatible with browserName.
     * See <a href="http://appium.io/docs/en/writing-running-appium/android/android-appbundle/index.html">here</a> about .apks file.
     * <br><br>
     * e.g. <code>/abs/path/to/my.apk or http://myapp.com/app.ipa</code>
     *
     * @param appPath
     * @return
     */
    public DesiredCapabilityBuilder appPathAs(String appPath) {
        this.generalDesiredCapabilities.setCapability(MobileCapabilityType.APP, appPath.trim());
        return this;
    }

    /**
     * Name of mobile web browser to automate.
     * Should be an empty string if automating an app instead.
     * <br><br>
     * e.g. Safari' for iOS and 'Chrome', 'Chromium', or 'Browser' for Android
     *
     * @param browserName
     * @return
     */
    public DesiredCapabilityBuilder browserAs(String browserName) {
        this.generalDesiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, StringUtils.normalizeSpace(browserName));
        return this;
    }

    /**
     * How long (in seconds) Appium will wait for a new command
     * from the client before assuming the client quit and ending the session. Defaults to 60
     *
     * @param commandTimeout
     * @return
     */
    public DesiredCapabilityBuilder setNewCommandTimeoutAs(int commandTimeout) {
        this.generalDesiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, commandTimeout);
        return this;
    }

    /**
     * Unique device identifier of the connected physical device. <br><br>
     * <p>
     * e.g. <code>1ae203187fc012g</code>
     *
     * @param udid
     * @return
     */
    public DesiredCapabilityBuilder udid(String udid) {
        this.generalDesiredCapabilities.setCapability(MobileCapabilityType.UDID, StringUtils.normalizeSpace(udid));
        return this;
    }

    /**
     * Don't reset app state before this session.
     * See <a href="http://appium.io/docs/en/writing-running-appium/other/reset-strategies/index.html">
     * here
     * </a>
     * for more details
     *
     * @param noReset
     * @return
     */
    public DesiredCapabilityBuilder noReset(boolean noReset) {
        this.generalDesiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, noReset);
        return this;
    }

    /**
     * <b>(Sim/Emu-only)</b><br>
     * start in a certain orientation <br><br>
     * <p>
     * e.g. <b>LANDSCAPE</b> or <b>PORTRAIT</b>
     *
     * @param deviceOrientation {@link DeviceOrientation}
     * @return
     */
    public DesiredCapabilityBuilder setEmulatorOrientation(DeviceOrientation deviceOrientation) {
        this.generalDesiredCapabilities.setCapability(MobileCapabilityType.ORIENTATION, deviceOrientation.toString());
        return this;
    }

    /**
     * Perform a complete reset.
     * See <a href="http://appium.io/docs/en/writing-running-appium/other/reset-strategies/index.html">
     * here
     * </a>
     * for more details
     *
     * @param fullReset
     * @return
     */
    public DesiredCapabilityBuilder fullReset(boolean fullReset) {
        this.generalDesiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, fullReset);
        return this;
    }

    /**
     * To get {@link DesiredCapabilities} build using {@link DesiredCapabilityBuilder}
     *
     * @return {@link DesiredCapabilities}
     */
    public DesiredCapabilities build() {
        return generalDesiredCapabilities;
    }

    /**
     * To get android specific {@link DesiredCapabilities}
     *
     * @return {@link AndroidCapabilities}
     */
    public AndroidCapabilities android() {
        return new AndroidCapabilities(generalDesiredCapabilities);
    }

    /**
     * To get iOS specific {@link DesiredCapabilities}
     *
     * @return {@link IOSCapabilities}
     */
    public IOSCapabilities iOS() {
        return new IOSCapabilities(generalDesiredCapabilities);
    }


    public enum DeviceOrientation {
        LANDSCAPE,
        PORTRAIT
    }
}
