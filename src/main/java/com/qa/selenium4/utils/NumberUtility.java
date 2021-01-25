package com.qa.selenium4.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtility {
    /**
     * To calculate percentage having {@link Double} values
     *
     * @param obtained Obtained value in {@link Double}
     * @param total    Total value to use to calculate percentage in {@link Double}
     * @return Percentage value in {@link Double}
     */
    public static double percentage(double obtained, double total) {
        return ((obtained / total) * 100);
    }

    /**
     * To calculate percentage having {@link Double} values
     *
     * @param obtained Obtained value out of total value
     * @param total    Total value to use to calculate percentage
     * @return Percentage value in {@link Double}
     */
    public static double percentage(int obtained, int total) {
        return ((obtained / total) * 100);
    }

    /**
     * To calculate percentage having {@link Double} values
     *
     * @param obtained Obtained value out of total value
     * @param total    Total value to use to calculate percentage
     * @return Percentage value in {@link Double}
     */
    public static double percentage(float obtained, float total) {
        return ((obtained / total) * 100);
    }

    /**
     * To calculate percentage value with number of decimal points passed as an
     * argument to the method
     *
     * @param obtained Obtained value out of total value
     * @param total    Total value to use to calculate percentage
     * @param decimals Number of decimals points to return
     * @return Percentage value in {@link Double}
     */
    public static double percentage(double obtained, double total, int decimals) {
        return round(percentage(obtained, total), decimals);
    }

    /**
     * To calculate percentage value with number of decimal points passed as an
     * argument to the method
     *
     * @param obtained Obtained value out of total value
     * @param total    Total value to use to calculate percentage
     * @param decimals Number of decimals points to return
     * @return Percentage value in {@link Double}
     */
    public static double percentage(int obtained, int total, int decimals) {
        return round(percentage(obtained, total), decimals);
    }

    /**
     * To calculate percentage value with number of decimal points passed as an
     * argument to the method
     *
     * @param obtained Obtained value out of total value
     * @param total    Total value to use to calculate percentage
     * @param decimals Number of decimals points to return
     * @return Percentage value in {@link Double}
     */
    public static double percentage(float obtained, float total, int decimals) {
        return round(percentage(obtained, total), decimals);
    }

    /**
     * To Round a double value to a specific number of decimal points.
     *
     * @param valueToRound Value to round
     * @param decimals     Number of decimal points to round with
     * @return Rounded value
     */
    public static double round(double valueToRound, int decimals) {
        return new BigDecimal(Double.toString(valueToRound))
                .setScale(decimals, RoundingMode.HALF_UP)
                .doubleValue();
    }
}