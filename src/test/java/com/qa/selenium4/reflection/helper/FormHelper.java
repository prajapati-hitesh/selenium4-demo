package com.qa.selenium4.reflection.helper;

import com.qa.selenium4.demo.driver.DriverFactory;
import com.qa.selenium4.demo.helper.ElementHelper;
import com.qa.selenium4.demo.helper.WaitHelper;
import com.qa.selenium4.reflection.enums.FieldType;
import com.qa.selenium4.reflection.enums.FormComponents;
import com.qa.selenium4.reflection.enums.FormLabels;
import com.qa.selenium4.reflection.models.FormModel;
import com.qa.selenium4.reflection.models.RecordModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class FormHelper {
    private static final Logger logger = LogManager.getLogger(FormHelper.class.getName());

    public void performActions(RecordModel recordModel) {
        // add values or perform actions based on model details
        WebDriver driver = DriverFactory.getInstance().getDriver();
        recordModel.models().forEach(fieldModel -> {
            // Cast the curr model into form models
            FormModel currFormModel = (FormModel) fieldModel;

            // if curr form model has value
            if (currFormModel.label().isPresent()) {
                // If curr form model is instance of FormLabels
                // Get Input elements and perform actions
                if (currFormModel.label().get() instanceof FormLabels currFormLabel) {

                    // Get the element
                    WebElement currFieldElement = WaitHelper.waitUntilElementIsVisible(driver, Duration.ofSeconds(90), currFormLabel.getLocator());

                    if (currFormModel.fieldType().isPresent()) {
                        if (currFormModel.fieldType().get() == FieldType.INPUT) {
                            ElementHelper.sendKeys(driver, currFieldElement, String.valueOf(currFormModel.value().orElseThrow()));
                        } else {
                            throw new IllegalArgumentException("The ["
                                    + currFormModel.fieldType().orElseThrow()
                                    + "] field type is not implemented. ["
                                    + FieldType.INPUT + ", " + FieldType.DROP_DOWN + ", " + FieldType.SELECT_2 + FieldType.DATE
                                    + "] are acceptable values.");
                        }
                    } else {
                        if (currFormLabel.getFieldType() == FieldType.INPUT) {
                            ElementHelper.sendKeys(driver, currFieldElement, String.valueOf(currFormModel.value().orElseThrow()));

                        } else {
                            throw new IllegalArgumentException("The ["
                                    + currFormLabel.getFieldType()
                                    + "] field type is not implemented. ["
                                    + FieldType.INPUT + ", " + FieldType.DROP_DOWN + ", " + FieldType.SELECT_2 + FieldType.DATE
                                    + "] are acceptable values.");
                        }
                    }


                } else if (currFormModel.label().get() instanceof FormComponents currFormComponent) {
                    // get the element and perform action
                    WebElement currComponentElement = WaitHelper.waitUntilElementIsVisible(driver, Duration.ofSeconds(90), currFormComponent.getLocator());

                    // Click element
                    ElementHelper.click(driver, currComponentElement);
                } else {
                    throw new IllegalArgumentException("Please define correct model");
                }
            } else {
                throw new IllegalArgumentException("Please define the models");
            }
        });
    }
}
