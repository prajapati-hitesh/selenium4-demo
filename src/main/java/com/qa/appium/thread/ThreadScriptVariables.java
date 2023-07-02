package com.qa.appium.thread;

import org.openqa.selenium.remote.DesiredCapabilities;

public class ThreadScriptVariables {
    private static String serverURI;
    private static ThreadLocal<DesiredCapabilities> sessionCapabilityMap = new ThreadLocal<>();
    private static ThreadLocal<String> appiumServerUrl = new ThreadLocal<>();

    public static String getServerURI() {
        return serverURI;
    }

    public static void setServerURI(String serverUrl) {
        serverURI = serverUrl;
    }

    public static DesiredCapabilities getSessionCapability() {
        return sessionCapabilityMap.get();
    }

    public static void setSessionCapability(DesiredCapabilities sessionCapability) {
        sessionCapabilityMap.set(sessionCapability);
    }

    public static String getAppiumServerUrl() {
        return appiumServerUrl.get();
    }

    public static void setAppiumServerUrl(String url) {
        appiumServerUrl.set(url);
    }
}
