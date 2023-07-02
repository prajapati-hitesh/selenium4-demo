package com.qa.selenium4.reflection.enums;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public enum FormComponents {
    LOGIN("Login", By.xpath("//button[normalize-space()='Login']"), FieldType.BUTTON, ActionType.CLICK),
    SAVE_EMPLOYEE("Employee Details Save", By.xpath("//div[contains(@class,'orangehrm-horizontal-padding')]//button[normalize-space()='Save']"), FieldType.BUTTON, ActionType.CLICK),
    SAVE_CONTACT("Contact Details Save", AppiumBy.id("com.google.android.contacts:id/toolbar_button"), FieldType.BUTTON, ActionType.CLICK);
    private static final Map<String, FormComponents> enumMAP;

    static {
        Map<String, FormComponents> mainNavMap = Arrays
                .stream(values())
                .collect(toMap(cg -> cg.label, e -> e));

        enumMAP = Collections.unmodifiableMap(mainNavMap);
    }

    private final String label;
    private final By locator;
    private final FieldType fieldType;
    private final ActionType actionType;

    FormComponents(String label, By locator, FieldType fieldType, ActionType actionType) {
        this.label = label;
        this.locator = locator;
        this.fieldType = fieldType;
        this.actionType = actionType;
    }

    public static boolean hasComponent(String componentName) {
        return enumMAP.containsKey(componentName);
    }

    public static FormComponents getEnum(String labelName) {
        return enumMAP.get(labelName);
    }

    public String getLabel() {
        return label;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public By getLocator() {
        return locator;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
