package com.qa.selenium4.reflection.enums;

import com.github.javafaker.Faker;
import com.github.javafaker.FunnyName;
import com.github.javafaker.Name;
import com.qa.selenium4.reflection.models.FakerDataModel;
import com.qa.selenium4.reflection.models.ParameterModel;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.openqa.selenium.By;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public enum FormLabels {
    USERNAME("Username", By.name("username"), FieldType.INPUT, FakerDataModel.with(Name.class, "username")),
    PASSWORD("Password", By.name("password"), FieldType.INPUT, null),
    FIRST_NAME("First Name", By.name("firstName"), FieldType.INPUT, FakerDataModel.with(Name.class, "firstName")),

    MIDDLE_NAME("Middle Name", By.name("middleName"), FieldType.INPUT, FakerDataModel.with(Name.class, "nameWithMiddle")),
    LAST_NAME("Last Name", By.name("lastName"), FieldType.INPUT, FakerDataModel.with(Name.class, "lastName")),

    NICK_NAME("Nickname", By.xpath("//div[child::label[text()='Nickname']]//following-sibling::div//input[contains(@class,'oxd-input')]"), FieldType.INPUT, FakerDataModel.with(FunnyName.class, "name")),
    LICENCE_NUMBER("Driver's License Number", By.xpath("//div[child::label[text()=\"Driver's License Number\"]]//following-sibling::div//input[contains(@class,'oxd-input')]"), FieldType.INPUT, FakerDataModel.with(Faker.class, "regexify", ParameterModel.with(String.class, "IND-LIC-[1-9]{2}-[1-9]{6}"))),
    EMPLOYEE_ID("Employee Id", By.xpath("//div[child::label[text()='Employee Id']]//following-sibling::div//input[contains(@class,'oxd-input')]"), FieldType.INPUT, FakerDataModel.with(Faker.class, "regexify", ParameterModel.with(String.class, "EID[1-9]{6}")));

    private static final Map<String, FormLabels> enumMAP;
    private static final EasyRandom randomGenerator;

    static {
        Map<String, FormLabels> mainNavMap = Arrays
                .stream(values())
                .collect(toMap(cg -> cg.label, e -> e));

        enumMAP = Collections.unmodifiableMap(mainNavMap);

        // Create Easy Random Parameters
        EasyRandomParameters easyRandomParameters = new EasyRandomParameters()
                .scanClasspathForConcreteTypes(true)
                .bypassSetters(true)
                .ignoreRandomizationErrors(true);
        randomGenerator = new EasyRandom(easyRandomParameters);
    }

    private final String label;
    private final By locator;
    private final FieldType fieldType;
    private final FakerDataModel fakerDataModel;

    FormLabels(String labelText, By byLocator, FieldType type, FakerDataModel fakerDataModel) {
        this.label = labelText;
        this.locator = byLocator;
        this.fieldType = type;
        this.fakerDataModel = fakerDataModel;
    }

    public static FormLabels getEnum(String labelName) {
        return enumMAP.get(labelName);
    }

    public static boolean hasField(String labelName) {
        return enumMAP.containsKey(labelName);
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

    public FakerDataModel getFakerDataModel() {
        return fakerDataModel;
    }

    public Object generateRandom(ParameterModel... parameterModels) {
        if (getFakerDataModel() != null) {
            Object randomValue = null;
            try {
                Object classObject = randomGenerator.nextObject(getFakerDataModel().type());
                if (parameterModels.length > 0) {
                    randomValue = generate(classObject, parameterModels);
                } else {
                    if (getFakerDataModel().parameterModels().length > 0) {
                        randomValue = generate(classObject, getFakerDataModel().parameterModels());
                    } else {
                        randomValue = classObject.getClass().getMethod(getFakerDataModel().methodName()).invoke(classObject);
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            return randomValue;
        } else {
            throw new NullPointerException("Cannot invoke generateRandom() because the FakerDataModel provided for enum is not defined.");
        }
    }

    private Object generate(Object classObject, ParameterModel... parameterModels) {
        List<? extends Class<?>> parameterTypes = Arrays.stream(parameterModels).map(ParameterModel::type).toList();
        Object[] parameterValues = Arrays.stream(parameterModels).map(e -> e.value() != null ? e.value() : e.values()).toArray();
        try {
            return classObject.getClass()
                    .getDeclaredMethod(getFakerDataModel().methodName(), parameterTypes.toArray(new Class[0]))
                    .invoke(classObject, parameterValues);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
