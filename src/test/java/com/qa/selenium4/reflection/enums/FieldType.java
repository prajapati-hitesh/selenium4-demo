package com.qa.selenium4.reflection.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public enum FieldType {
    INPUT("Input", ActionType.SEND_KEYS),
    DROP_DOWN("Dropdown", ActionType.SELECT),
    BUTTON("Button", ActionType.CLICK),
    CHECKBOX("Checkbox", ActionType.CLICK),
    DATE("Date", ActionType.DATE_PICKER),
    TIME_PICKER("Time Picker", ActionType.TIME_PICKER),
    SELECT_2("Select 2", ActionType.SELECT);

    private static final Map<String, FieldType> enumMAP;

    static {
        Map<String, FieldType> mainNavMap = Arrays
                .stream(values())
                .collect(toMap(cg -> cg.innerText, e -> e));

        enumMAP = Collections.unmodifiableMap(mainNavMap);
    }

    private final String innerText;
    private final ActionType actionType;


    FieldType(String text, ActionType actionType) {
        this.innerText = text;
        this.actionType = actionType;
    }

    public static FieldType getEnum(String text) {
        return enumMAP.get(text);
    }

    public String getText() {
        return innerText;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
