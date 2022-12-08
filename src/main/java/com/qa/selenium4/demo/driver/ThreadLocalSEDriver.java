package com.qa.selenium4.demo.driver;

import org.openqa.selenium.WebDriver;

class ThreadLocalSEDriver {
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * To get Thread Safe object of WebDriver
     *
     * @return <code>WebDriver</code> <b>Thread safe object of WebDriver</b>
     */
    protected static WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * To set Thread Safe object of WebDriver
     *
     * @param driverInstance <code>WebDriver</code> <b>Object of WebDriver instance</b>
     */
    protected static void setDriver(WebDriver driverInstance) {
        tlDriver.set(driverInstance);
    }

}