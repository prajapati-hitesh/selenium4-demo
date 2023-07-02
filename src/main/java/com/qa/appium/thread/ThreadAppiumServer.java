package com.qa.appium.thread;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class ThreadAppiumServer {
    private static ThreadLocal<AppiumDriverLocalService> appiumDriverService = new ThreadLocal<>();

    public static AppiumDriverLocalService getAppiumServerDriverService() {
        return appiumDriverService.get();
    }

    public static void setAppiumServerDriverService(AppiumDriverLocalService appiumService) {
        appiumDriverService.set(appiumService);
    }
}
