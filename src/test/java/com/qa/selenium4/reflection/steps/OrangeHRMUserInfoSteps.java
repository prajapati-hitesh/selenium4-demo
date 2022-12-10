package com.qa.selenium4.reflection.steps;

import com.qa.selenium4.demo.driver.DriverFactory;
import com.qa.selenium4.demo.helper.ElementHelper;
import com.qa.selenium4.demo.helper.LoggerHelper;
import com.qa.selenium4.demo.helper.WaitHelper;
import com.qa.selenium4.reflection.data.OrangeHRMVariables;
import com.qa.selenium4.reflection.enums.AppPages;
import com.qa.selenium4.reflection.enums.FieldType;
import com.qa.selenium4.reflection.enums.FormComponents;
import com.qa.selenium4.reflection.enums.FormLabels;
import com.qa.selenium4.reflection.helper.FormHelper;
import com.qa.selenium4.reflection.models.DateModel;
import com.qa.selenium4.reflection.models.FormModel;
import com.qa.selenium4.reflection.models.RecordModel;
import com.qa.selenium4.reflection.pages.OrangeHRMLoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class OrangeHRMUserInfoSteps {
    private static final Logger logger = LogManager.getLogger(OrangeHRMUserInfoSteps.class.getName());
    Scenario scenario;
    OrangeHRMLoginPage loginPage;
    private WebDriver driver;

    @Before
    public void setPages(Scenario scenario) {
        this.scenario = scenario;
        this.loginPage = new OrangeHRMLoginPage();
        this.driver = DriverFactory.getInstance().getDriver();
    }

    @Given("^I am on Login Page of Orange HRM application$")
    public void iAmOnLoginPageOfOrangeHRMApplication() {
        // Code that helps login
        WebElement loginLogo = loginPage.getLoginLogoElement();
        assertThat(ElementHelper.getText(DriverFactory.getInstance().getDriver(), loginLogo))
                .isEqualTo("Login");
    }


    @When("^I enter login credentials using following$")
    public void iEnterLoginCredentialsUsingFollowing(DataTable fieldModelDataTable) {
        List<Map<String, String>> fieldValueMap = fieldModelDataTable.asMaps(String.class, String.class);

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
        new FormHelper().performActions(OrangeHRMVariables.getRecordModel());
    }

    @Then("^I should be able to login to system$")
    public void iShouldBeAbleToLoginToSystem() {
        WebElement element = WaitHelper.waitUntilElementIsVisible(driver, Duration.ofSeconds(90), By.className("oxd-topbar-header-breadcrumb"));
        assertThat(ElementHelper.getText(driver, element))
                .isEqualTo("Dashboard");
    }

    @Given("^User is on (.*) page$")
    public void userIsOnASpecificPage(String pageName) {
        String currUrl = driver.getCurrentUrl();

        assertThat(currUrl)
                .contains(pageName.trim().toLowerCase());
    }

    @When("^I navigate to (.*)$")
    public void iNavigateToASpecificPage(String pageName) {
        ElementHelper.click(driver, AppPages.getEnum(pageName.trim()).getLocator());

        WebElement element = WaitHelper.waitUntilElementIsVisible(driver, Duration.ofSeconds(90), By.linkText("Personal Details"));
        assertThat(ElementHelper.getText(driver, element))
                .isEqualTo("Personal Details");
    }


    @And("^For the following field, I (?:update|add) a user details with mentioned values$")
    public void forTheFieldsIAddOrUpdateUserDetails(DataTable detailsToAddUpdateDataTable) {
        List<Map<String, String>> fieldValueMap = detailsToAddUpdateDataTable.asMaps(String.class, String.class);

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
        new FormHelper().performActions(OrangeHRMVariables.getRecordModel());
    }

    @Then("^User details should be (?:added|updated) successfully$")
    public void thenTheUserShouldBeUpdatedSuccessfully() {
        WebElement toastMessage = WaitHelper.waitUntilElementIsVisible(
                driver,
                Duration.ofSeconds(90),
                By.xpath("//*[@id='oxd-toaster_1']//p[contains(@class,'oxd-text--toast-message')]")
        );

        assertThat(ElementHelper.getText(driver, toastMessage))
                .isEqualTo("Successfully Updated");
    }

    @ParameterType("UPDATED|CREATED")
    public AppPages filterOnParameter(String filter) {
        return AppPages.getEnum(filter);
    }

    private FormModel generateDataModelForRow(Map<String, String> dataMap) {
        FormModel formModel = null;
        // Get Column Name, Field Type & Value from map
        String fieldName = dataMap.get("FieldName").trim();
        String fieldType = dataMap.get("FieldType").trim();
        String value = dataMap.get("Value / Action").trim();

        // if the value of current column is "Random" then set random value
        FormLabels formLabelEnum = FormLabels.getEnum(fieldName);

        boolean isCurrRowInFormLabels = FormLabels.hasField(fieldName);
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
                    randomValue = DateModel.toModel((Date) formLabelEnum.generateRandom());
                } else if (fieldTypeEnum == FieldType.SELECT_2 || fieldTypeEnum == FieldType.DROP_DOWN) {
                    randomValue = new Random();
                } else {
                    randomValue = formLabelEnum.generateRandom();
                }

                if (formLabelEnum != null) {
                    LoggerHelper.infoLog(
                            logger,
                            scenario,
                            "Entering random value for [" + formLabelEnum.getLabel() + " --> " + randomValue + "]"
                    );
                } else {
                    LoggerHelper.errorLog(
                            logger,
                            scenario,
                            "Enum value for column [" + fieldName + "] is not defined at [FormLabels] enum repository."
                    );
                }

                formModel = FormModel.with(
                        formLabelEnum,
                        FieldType.getEnum(fieldType),
                        randomValue
                );
            } else {
                formModel = FormModel.with(
                        formLabelEnum,
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
