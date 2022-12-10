package com.qa.selenium4.reflection.models;

public record ParameterModel(Class<?> type, Object value, Object... values) {
    public static ParameterModel with(Class<?> type, Object value) {
        return new ParameterModel(type, value);
    }

    public static ParameterModel with(Class<?> type, Object... values) {
        return new ParameterModel(type, null, values);
    }
}
