package com.qa.selenium4.demo.enums;

public enum Browser {
    Chrome("Chrome", "chrome"),
    Firefox("Firefox", "firefox"),
    IE("Internet Explorer", "InternetExplorer"),
    MicrosoftEdge("Microsoft Edge", "MicrosoftEdge"),
    Chromium("Chromium", "chromium");

    final String name;
    final String capabilityName;

    Browser(String name, String capabilityName) {
        this.name = name;
        this.capabilityName = capabilityName;
    }

    public String getName() {
        return name;
    }

    public String getCapabilityName() {
        return capabilityName;
    }
}