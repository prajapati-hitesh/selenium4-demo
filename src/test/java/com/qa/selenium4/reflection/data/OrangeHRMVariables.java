package com.qa.selenium4.reflection.data;

import com.qa.selenium4.reflection.models.RecordModel;

public class OrangeHRMVariables {
    private static final ThreadLocal<RecordModel> recordModel = new ThreadLocal<>();

    public static RecordModel getRecordModel() {
        return recordModel.get();
    }

    public static void setRecordModel(RecordModel model) {
        recordModel.set(model);
    }
}
