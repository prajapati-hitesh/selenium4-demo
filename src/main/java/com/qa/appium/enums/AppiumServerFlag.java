package com.qa.appium.enums;

import io.appium.java_client.service.local.flags.ServerArgument;

public enum AppiumServerFlag implements ServerArgument {
    ALLOW_INSECURE("--allow-insecure");
    private final String arg;

    private AppiumServerFlag(String arg) {
        this.arg = arg;
    }

    public String getArgument() {
        return this.arg;
    }
}
