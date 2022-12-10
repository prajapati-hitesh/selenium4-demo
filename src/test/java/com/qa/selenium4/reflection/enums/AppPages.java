package com.qa.selenium4.reflection.enums;

import org.apache.commons.text.WordUtils;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public enum AppPages {
    ADMIN("Admin", By.linkText("Admin")),
    PIM("PIM", By.linkText("PIM")),
    LEAVE("Leave", By.linkText("Leave")),
    TIME("Time", By.linkText("Time")),
    RECRUITMENT("Recruitment", By.linkText("Recruitment")),
    MY_INFO("My Info", By.linkText("My Info")),
    PERFORMANCE("Performance", By.linkText("Performance")),
    DASHBOARD("Dashboard", By.linkText("Dashboard")),
    DIRECTORY("Directory", By.linkText("Directory")),
    MAINTENANCE("Maintenance", By.linkText("Maintenance")),
    BUZZ("Buzz", By.linkText("Buzz"));

    private static final Map<String, AppPages> enumMAP;

    static {
        Map<String, AppPages> mainNavMap = Arrays
                .stream(values())
                .collect(toMap(cg -> cg.text, e -> e));

        enumMAP = Collections.unmodifiableMap(mainNavMap);
    }

    final String text;
    final By locator;

    AppPages(String value, By locator) {
        this.text = value;
        this.locator = locator;
    }

    public static AppPages getEnum(String value) {
        return enumMAP.get(WordUtils.capitalizeFully(value.trim()));
    }

    public By getLocator() {
        return locator;
    }
}
