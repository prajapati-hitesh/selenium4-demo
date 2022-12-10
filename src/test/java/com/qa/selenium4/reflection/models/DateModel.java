package com.qa.selenium4.reflection.models;

import com.qa.selenium4.demo.enums.Calendar;

import java.util.Date;

public record DateModel(int year, Calendar.Month month, int date) {
    public static DateModel with(int year, Calendar.Month month, int date) {
        return new DateModel(year, month, date);
    }

    public static DateModel toModel(Date date) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);

        return DateModel.with(
                calendar.get(java.util.Calendar.YEAR),
                Calendar.Month.getEnum(calendar.get(java.util.Calendar.MONTH) + 1),
                calendar.get(java.util.Calendar.DATE)
        );
    }

    public static String toDateString(DateModel dateModel) {
        return dateModel.date() + "/" + dateModel.month().getMonthIndex() + "/" + dateModel.year;
    }
}
