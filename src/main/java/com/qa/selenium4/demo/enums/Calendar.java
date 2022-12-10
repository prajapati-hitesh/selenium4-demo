package com.qa.selenium4.demo.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public enum Calendar {
    MONTH,
    YEAR,
    DAY,
    WEEK;

    public enum Quarter {
        Q1(Month.JANUARY, Month.FEBRUARY, Month.MARCH),
        Q2(Month.APRIL, Month.MAY, Month.JUNE),
        Q3(Month.JULY, Month.AUGUST, Month.SEPTEMBER),
        Q4(Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);

        private final List<Month> listOfMonths;

        Quarter(Month... months) {
            this.listOfMonths = Arrays.stream(months).toList();
        }

        public List<Month> getMonths() {
            return listOfMonths;
        }
    }

    public enum Month {
        JANUARY(1, 31),
        FEBRUARY(2, 28),
        MARCH(3, 31),
        APRIL(4, 30),
        MAY(5, 31),
        JUNE(6, 30),
        JULY(7, 31),
        AUGUST(8, 31),
        SEPTEMBER(9, 30),
        OCTOBER(10, 31),
        NOVEMBER(11, 30),
        DECEMBER(12, 31);


        private static final Logger logger = LogManager.getLogger(Calendar.class.getName());
        private static final Map<Integer, Month> enumMAP;

        static {
            Map<Integer, Month> mainNavMap = Arrays
                    .stream(values())
                    .collect(toMap(cg -> cg.monthIndex, e -> e));

            enumMAP = Collections.unmodifiableMap(mainNavMap);
        }

        private final int monthIndex;
        private final int numberOfDays;

        Month(int index, int daysInAMonth) {
            this.monthIndex = index;
            this.numberOfDays = daysInAMonth;
        }

        public static Month getEnum(int monthIndex) {
            return enumMAP.get(monthIndex);
        }

        public int getMonthIndex() {
            return monthIndex;
        }

        /**
         * To get number of days in a month falls in a specific year
         *
         * @param year Year for which to count the days
         * @return Number of days for a selected month
         */
        public int getNumberOfDays(int year) {
            if (getMonthIndex() == 2) {
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return numberOfDays;
                }
            } else {
                return numberOfDays;
            }
        }

        /**
         * To check whether a particular year is a leap year or not
         *
         * @param year year to check for
         * @return <code>true</code> if the year is leap year, <code>false</code> otherwise
         */
        private boolean isLeapYear(int year) {
            boolean leap = false;

            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    // year is divisible by 400, hence the year is a leap year
                    if (year % 400 == 0)
                        leap = true;
                } else
                    leap = true;
            }
            if (leap)
                logger.info(year + " is a leap year.");
            else
                logger.info(year + " is not a leap year.");
            return leap;
        }
    }

    public enum Week {
        SUNDAY("Sunday"),
        MONDAY("Monday"),
        TUESDAY("Tuesday"),
        WEDNESDAY("Wednesday"),
        THURSDAY("Thursday"),
        FRIDAY("Friday"),
        SATURDAY("Saturday");

        private static final Map<String, Week> enumMAP;

        static {
            Map<String, Week> mainNavMap = Arrays
                    .stream(values())
                    .collect(toMap(cg -> cg.text, e -> e));

            enumMAP = Collections.unmodifiableMap(mainNavMap);
        }

        private final String text;

        Week(String innerText) {
            this.text = innerText;
        }

        public static Week getEnum(String linkText) {
            return enumMAP.get(linkText);
        }

        public String getName() {
            return text;
        }
    }
}
