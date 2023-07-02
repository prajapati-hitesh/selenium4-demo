package com.qa.selenium4.reflection.steps;

import com.qa.selenium4.demo.helper.LoggerHelper;
import com.qa.selenium4.reflection.data.OrangeHRMVariables;
import com.qa.selenium4.reflection.enums.FieldType;
import com.qa.selenium4.reflection.enums.FormComponents;
import com.qa.selenium4.reflection.enums.ScreenLabels;
import com.qa.selenium4.reflection.helper.ScreenFormHelper;
import com.qa.selenium4.reflection.models.DateModel;
import com.qa.selenium4.reflection.models.FormModel;
import com.qa.selenium4.reflection.models.RecordModel;
import com.qa.selenium4.reflection.pages.AddContactPage;
import com.qa.selenium4.reflection.pages.GoogleSyncPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class GoogleContactSteps {
    private static final Logger logger = LogManager.getLogger(GoogleContactSteps.class.getName());
    Scenario scenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("^User is on Google Contacts app$")
    public void userIsOnGoogleContactsApp() {
        new GoogleSyncPage().skipContactSync();
    }

    @When("^User navigates to Add Contact screen$")
    public void userNavigatesToAddContactScreen() {
        AddContactPage.addContact();
    }

    @And("^For the following fields, user add contact with mentioned values$")
    public void forTheFollowingFieldsUserAddContactWithValues(DataTable contactDataTable) {
        List<Map<String, String>> fieldValueMap = contactDataTable.asMaps(String.class, String.class);

        List<FormModel> formModelList = new ArrayList<>();
        fieldValueMap.forEach(map -> {
            FormModel currFieldModel = generateDataModelForRow(map);

            // Add generated Model to list
            formModelList.add(currFieldModel);
        });

        OrangeHRMVariables.setRecordModel(null);
        OrangeHRMVariables.setRecordModel(RecordModel.with(formModelList.toArray(new FormModel[0])));
        OrangeHRMVariables.getRecordModel().models().forEach(System.out::println);
        // Add Record
        new ScreenFormHelper().performActions(OrangeHRMVariables.getRecordModel());
    }

    @Then("^The contact should be saved successfully$")
    public void theContactShouldBeSavedSuccessfully() {

    }

    private FormModel generateDataModelForRow(Map<String, String> dataMap) {
        FormModel formModel = null;
        // Get Column Name, Field Type & Value from map
        String fieldName = dataMap.get("FieldName").trim();
        String fieldType = dataMap.get("FieldType").trim();
        String value = dataMap.get("Value / Action").trim();

        // if the value of current column is "Random" then set random value
        ScreenLabels screenLabelEnum = ScreenLabels.getEnum(fieldName);

        boolean isCurrRowInFormLabels = ScreenLabels.hasField(fieldName);
        boolean isCurrRowInFormComponents = FormComponents.hasComponent(fieldName);

        // if curr row is in Form Labels then generate random
        if (isCurrRowInFormLabels) {
            if (value.trim().equalsIgnoreCase("RANDOM")) {
                // get field type
                FieldType fieldTypeEnum = FieldType.getEnum(fieldType);
                // Object to store random value
                Object randomValue;

                if (fieldTypeEnum == FieldType.DATE) {
                    // generate Date Model from generated value
                    randomValue = DateModel.toModel((Date) screenLabelEnum.generateRandom());
                } else if (fieldTypeEnum == FieldType.SELECT_2 || fieldTypeEnum == FieldType.DROP_DOWN) {
                    randomValue = new Random();
                } else {
                    randomValue = screenLabelEnum.generateRandom();
                }

                if (screenLabelEnum != null) {
                    LoggerHelper.infoLog(
                            logger,
                            scenario,
                            "Entering random value for [" + screenLabelEnum.getLabel() + " --> " + randomValue + "]"
                    );
                } else {
                    LoggerHelper.errorLog(
                            logger,
                            scenario,
                            "Enum value for column [" + fieldName + "] is not defined at [FormLabels] enum repository."
                    );
                }

                formModel = FormModel.with(
                        screenLabelEnum,
                        FieldType.getEnum(fieldType),
                        randomValue
                );
            } else {
                formModel = FormModel.with(
                        screenLabelEnum,
                        FieldType.getEnum(fieldType),
                        value.trim()
                );
            }
        } else if (isCurrRowInFormComponents) {
            FormComponents formComponentEnum = FormComponents.getEnum(fieldName);
            formModel = FormModel.with(
                    formComponentEnum,
                    FieldType.getEnum(fieldType),
                    formComponentEnum.getActionType()
            );
        } else {
            throw new IllegalArgumentException("Please add the field [" + fieldName + "] into either FormLabels or FormComponents enum based on their category");

        }
        return formModel;
    }
}
