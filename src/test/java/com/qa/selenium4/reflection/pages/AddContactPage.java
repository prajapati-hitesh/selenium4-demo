package com.qa.selenium4.reflection.pages;

import com.qa.appium.helper.AppiumEventHelpers;
import io.appium.java_client.AppiumBy;

public class AddContactPage {

    public static void addContact() {
        AppiumEventHelpers.getInstance().click(AppiumBy.id("com.google.android.contacts:id/floating_action_button"));
    }
}
