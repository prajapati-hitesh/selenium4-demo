package com.qa.selenium4.reflection.models;

public record FakerDataModel(Class<?> type, String methodName, ParameterModel... parameterModels) {
    public static FakerDataModel with(Class<?> type, String methodName, ParameterModel... parameterModels) {
        return new FakerDataModel(type, methodName, parameterModels);
    }
}
