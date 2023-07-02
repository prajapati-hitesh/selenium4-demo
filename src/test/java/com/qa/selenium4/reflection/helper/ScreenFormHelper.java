package com.qa.selenium4.reflection.helper;

import com.qa.appium.helper.AppiumEventHelpers;
import com.qa.appium.helper.AppiumSyncHelper;
import com.qa.appium.thread.ThreadLocalAppiumDriver;
import com.qa.selenium4.demo.helper.WaitHelper;
import com.qa.selenium4.reflection.enums.FieldType;
import com.qa.selenium4.reflection.enums.FormComponents;
import com.qa.selenium4.reflection.enums.ScreenLabels;
import com.qa.selenium4.reflection.models.FormModel;
import com.qa.selenium4.reflection.models.RecordModel;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class ScreenFormHelper {

    public void performActions(RecordModel recordModel) {
        // add values or perform actions based on model details
        AppiumDriver driver = ThreadLocalAppiumDriver.getDriver();
        recordModel.models().forEach(fieldModel -> {
            // Cast the curr model into form models
            FormModel currFormModel = (FormModel) fieldModel;

            // if curr form model has value
            if (currFormModel.label().isPresent()) {
                // If curr form model is instance of FormLabels
                // Get Input elements and perform actions
                if (currFormModel.label().get() instanceof ScreenLabels currScreenLabel) {

                    // Get the element
                    WebElement currFieldElement = AppiumSyncHelper.waitUntilElementIsVisible(driver, Duration.ofSeconds(90), currScreenLabel.getLocator());

                    if (currFormModel.fieldType().isPresent()) {
                        if (currFormModel.fieldType().get() == FieldType.INPUT) {
                            AppiumEventHelpers.getInstance().sendKeys(currFieldElement, String.valueOf(currFormModel.value().orElseThrow()));
                        } else {
                            throw new IllegalArgumentException("The ["
                                    + currFormModel.fieldType().orElseThrow()
                                    + "] field type is not implemented. ["
                                    + FieldType.INPUT + ", " + FieldType.DROP_DOWN + ", " + FieldType.SELECT_2 + FieldType.DATE
                                    + "] are acceptable values.");
                        }
                    } else {
                        if (currScreenLabel.getFieldType() == FieldType.INPUT) {
                            AppiumEventHelpers.getInstance().sendKeys(currFieldElement, String.valueOf(currFormModel.value().orElseThrow()));

                        } else {
                            throw new IllegalArgumentException("The ["
                                    + currScreenLabel.getFieldType()
                                    + "] field type is not implemented. ["
                                    + FieldType.INPUT + ", " + FieldType.DROP_DOWN + ", " + FieldType.SELECT_2 + FieldType.DATE
                                    + "] are acceptable values.");
                        }
                    }


                } else if (currFormModel.label().get() instanceof FormComponents currFormComponent) {
                    // get the element and perform action
                    WebElement currComponentElement = WaitHelper.waitUntilElementIsVisible(driver, Duration.ofSeconds(90), currFormComponent.getLocator());

                    // Click element
                    AppiumEventHelpers.getInstance().click(currComponentElement);
                } else {
                    throw new IllegalArgumentException("Please define correct model");
                }
            } else {
                throw new IllegalArgumentException("Please define the models");
            }
        });
    }
}
