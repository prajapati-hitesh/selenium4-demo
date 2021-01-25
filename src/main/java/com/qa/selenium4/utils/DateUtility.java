package com.qa.selenium4.utils;

import com.qa.selenium4.enums.Calendar;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateUtility {

    public static String getCurrentTimeStampWithFormatAs(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public static String getCurrentTimeStamp() {
        return new SimpleDateFormat("dd-MM-yyyy - HH.mm.ss").format(new Date());
    }

    public static String getDateFormatted(Date date, String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(date);
    }


    /**
     * To convert Date time of string to {@link Date} object
     *
     * @param dateAsString   Date time string to be converted to date Object
     * @param dateTimeFormat Date Time format to use while conversion
     * @return {@link Date}, null otherwise
     */
    public static Date toDate(String dateAsString, String dateTimeFormat) {
        try {
            return new SimpleDateFormat(dateTimeFormat).parse(dateAsString);
        } catch (ParseException pEx) {
            return null;
        }
    }

    /**
     * To get date as a String in a particular date format.
     *
     * @param date       {@link Date} to convert into <code>String</code>
     * @param dateFormat Date format in which to convert {@link Date}
     * @return Date as a String format
     */
    public static String toString(Date date, String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(date);
    }

    /**
     * To get date before X Days, Months or Years as a <code>String</code>
     * with date format passed as an argument to this function
     *
     * @param dateFormat Format in which to get the date
     * @param calendar   {@link Calendar} Months, Years or Days
     * @param interval   value in <code>Integer</code> to get date
     * @return Date as a <code>String</code>
     */
    public static String getDateBefore(String dateFormat, Calendar calendar, int interval) {
        String calculatedDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        if (calendar == Calendar.DAY) {
            calculatedDate = simpleDateFormat.format(new DateTime().minusDays(interval).toDate());
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = simpleDateFormat.format(new DateTime().minusMonths(interval).toDate());
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = simpleDateFormat.format(new DateTime().minusYears(interval).toDate());
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }

    /**
     * To get date before X Days, Months or Years as a <code>Date</code>
     *
     * @param calendar {@link Calendar} Months, Years or Days
     * @param interval value in <code>Integer</code> to get date
     * @return Date before X month, Year or Days as {@link Date} value
     */
    public static Date getDateBefore(Calendar calendar, int interval) {
        Date calculatedDate;

        if (calendar == Calendar.DAY) {
            calculatedDate = new DateTime().minusDays(interval).toDate();
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = new DateTime().minusMonths(interval).toDate();
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = new DateTime().minusYears(interval).toDate();
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }

    /**
     * To get date After X Days, Months or Years as a <code>Date</code>
     *
     * @param calendar {@link Calendar} Months, Years or Days
     * @param interval value in <code>Integer</code> to get date
     * @return Date before X month, Year or Days as {@link Date} value
     */
    public static Date getDateAfter(Calendar calendar, int interval) {
        Date calculatedDate;

        if (calendar == Calendar.DAY) {
            calculatedDate = new DateTime().plusDays(interval).toDate();
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = new DateTime().plusMonths(interval).toDate();
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = new DateTime().plusYears(interval).toDate();
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }

    /**
     * To get date After X Days, Months or Years as a <code>String</code>
     * with date format passed as an argument to this function
     *
     * @param dateFormat Format in which to get the date
     * @param calendar   {@link DateFor} Months, Years or Days
     * @param interval   value in <code>Integer</code> to get date
     * @return Date as a <code>String</code>
     */
    public static String getDateAfter(String dateFormat, Calendar calendar, int interval) {
        String calculatedDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        if (calendar == Calendar.DAY) {
            calculatedDate = simpleDateFormat.format(new DateTime().plusDays(interval).toDate());
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = simpleDateFormat.format(new DateTime().plusMonths(interval).toDate());
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = simpleDateFormat.format(new DateTime().plusYears(interval).toDate());
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }

    /**
     * To get date after X Day(s), Month(s) or Year(s) using a specific date
     * as a base or start date.
     *
     * @param date       {@link Date} to use as base
     * @param dateFormat Date format in which to return the calculated date
     * @param calendar   {@link Calendar} Days, Months, Years
     * @param interval   value in <code>Integer</code> to get date
     * @return Date as a <code>String</code>
     */
    public static String getDateAfter(Date date, String dateFormat, Calendar calendar, int interval) {
        String calculatedDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        if (calendar == Calendar.DAY) {
            calculatedDate = simpleDateFormat.format(new DateTime(date).plusDays(interval).toDate());
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = simpleDateFormat.format(new DateTime(date).plusMonths(interval).toDate());
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = simpleDateFormat.format(new DateTime(date).plusYears(interval).toDate());
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }

    /**
     * To get date Before X Day(s), Month(s) or Year(s) using a specific date
     * as a base or start date.
     *
     * @param date       {@link Date} to use as base
     * @param dateFormat Date format in which to return the calculated date
     * @param calendar   {@link Calendar} Days, Months, Years
     * @param interval   value in <code>Integer</code> to get date
     * @return Date as a <code>String</code>
     */
    public static String getDateBefore(Date date, String dateFormat, Calendar calendar, int interval) {
        String calculatedDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        if (calendar == Calendar.DAY) {
            calculatedDate = simpleDateFormat.format(new DateTime(date).minusDays(interval).toDate());
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = simpleDateFormat.format(new DateTime(date).minusMonths(interval).toDate());
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = simpleDateFormat.format(new DateTime(date).minusYears(interval).toDate());
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }

    /**
     * To get date after X Day(s), Month(s) or Year(s) using a specific date
     * as a base or start date.
     *
     * @param date     {@link Date} to use as base
     * @param calendar {@link Calendar} Days, Months, Years
     * @param interval value in <code>Integer</code> to get date
     * @return Date before X month, Year or Days as {@link Date} value
     */
    public static Date getDateAfter(Date date, Calendar calendar, int interval) {
        Date calculatedDate;

        if (calendar == Calendar.DAY) {
            calculatedDate = new DateTime(date).plusDays(interval).toDate();
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = new DateTime(date).plusMonths(interval).toDate();
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = new DateTime(date).plusYears(interval).toDate();
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }

    /**
     * To get date Before X Day(s), Month(s) or Year(s) using a specific date
     * as a base or start date.
     *
     * @param date     {@link Date} to use as base
     * @param calendar {@link Calendar} Days, Months, Years
     * @param interval value in <code>Integer</code> to get date
     * @return Date before X month, Year or Days as {@link Date} value
     */
    public static Date getDateBefore(Date date, Calendar calendar, int interval) {
        Date calculatedDate;

        if (calendar == Calendar.DAY) {
            calculatedDate = new DateTime(date).minusDays(interval).toDate();
        } else if (calendar == Calendar.MONTH) {
            calculatedDate = new DateTime(date).minusMonths(interval).toDate();
        } else if (calendar == Calendar.YEAR) {
            calculatedDate = new DateTime(date).minusYears(interval).toDate();
        } else {
            throw new IllegalArgumentException("Date for parameter value is incorrect.");
        }
        return calculatedDate;
    }


    public static String getCurrentMonth() {
        LocalDate localDate = LocalDate.now();

        /* Methods that can be used
            localDate.getYear() --> This will return year in int. i.e. 2019,2018 etc
            localDate.getDayOfMonth() --> This will return current date. i.e. 1,2,3,4....31
            localDate.getDayOfWeek() --> This will return day of week. i.e. Monday, Tuesday
            localDate.getDayOfYear() --> This will return day of year for current date. i.e. 311th day for date 7th November 2019
            localDate.getMonth().name() --> This will return the current month as string. i.e. JANUARY, FEBRUARY .... DECEMBER
         */

        return localDate.getMonth().name();
    }

    public static int getCurrentDate() {
        LocalDate localDate = LocalDate.now();

        /* Methods that can be used
            localDate.getYear() --> This will return year in int. i.e. 2019,2018 etc
            localDate.getDayOfMonth() --> This will return current date. i.e. 1,2,3,4....31
            localDate.getDayOfWeek() --> This will return day of week. i.e. Monday, Tuesday
            localDate.getDayOfYear() --> This will return day of year for current date. i.e. 311th day for date 7th November 2019
            localDate.getMonth().name() --> This will return the current month as string. i.e. JANUARY, FEBRUARY .... DECEMBER
         */

        return localDate.getDayOfMonth();
    }

    public static int getCurrentYear() {
        LocalDate localDate = LocalDate.now();

        /* Methods that can be used
            localDate.getYear() --> This will return year in int. i.e. 2019,2018 etc
            localDate.getDayOfMonth() --> This will return current date. i.e. 1,2,3,4....31
            localDate.getDayOfWeek() --> This will return day of week. i.e. Monday, Tuesday
            localDate.getDayOfYear() --> This will return day of year for current date. i.e. 311th day for date 7th November 2019
            localDate.getMonth().name() --> This will return the current month as string. i.e. JANUARY, FEBRUARY .... DECEMBER
         */

        return localDate.getYear();
    }

    public static int getCurrentMonthAsNumber() {
        LocalDate localDate = LocalDate.now();

        /* Methods that can be used
            localDate.getYear() --> This will return year in int. i.e. 2019,2018 etc
            localDate.getDayOfMonth() --> This will return current date. i.e. 1,2,3,4....31
            localDate.getDayOfWeek() --> This will return day of week. i.e. Monday, Tuesday
            localDate.getDayOfYear() --> This will return day of year for current date. i.e. 311th day for date 7th November 2019
            localDate.getMonth().name() --> This will return the current month as string. i.e. JANUARY, FEBRUARY .... DECEMBER
         */

        return localDate.getMonthValue();
    }

    public enum DateFor {
        MONTH,
        DAYS,
        YEARS
    }
}
