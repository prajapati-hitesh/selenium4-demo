package com.qa.selenium4.demo.helper;

import com.qa.selenium4.utils.DateUtility;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class LoggerHelper {
    private static final String TIME_STAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static void infoLog(Logger logger, Scenario scenario, String message) {
        String dateStamp = "[" +
                DateUtility.getCurrentTimeStampWithFormatAs(TIME_STAMP_FORMAT) +
                "] ";
        logger.info(message);
        scenario.log(dateStamp + message);
    }

    public static void infoLog(Logger logger, Scenario scenario, String... messages) {
        String dateStamp = "[" +
                DateUtility.getCurrentTimeStampWithFormatAs(TIME_STAMP_FORMAT) +
                "] ";
        Arrays.stream(messages).forEach(message -> {
            logger.info(message);
            scenario.log(dateStamp + message);
        });
    }

    public static void warnLog(Logger logger, Scenario scenario, String message) {
        String dateStamp = "[" +
                DateUtility.getCurrentTimeStampWithFormatAs(TIME_STAMP_FORMAT) +
                "] ";
        logger.warn(message);
        scenario.log(dateStamp + message);
    }

    public static void errorLog(Logger logger, Scenario scenario, String message) {
        String dateStamp = "[" +
                DateUtility.getCurrentTimeStampWithFormatAs(TIME_STAMP_FORMAT) +
                "] ";
        logger.error(message);
        scenario.log(dateStamp + message);
    }

    public static void errorLog(Logger logger, Scenario scenario, Exception exception) {
        String dateStamp = "[" +
                DateUtility.getCurrentTimeStampWithFormatAs(TIME_STAMP_FORMAT) +
                "] ";
        logger.error(exception.getMessage(), exception);
        scenario.log(dateStamp + exception.getMessage());
    }

    public static void attach(Scenario scenario, String filePath, String mimeType, String caption) {
        String dateStamp = "[" +
                DateUtility.getCurrentTimeStampWithFormatAs(TIME_STAMP_FORMAT) +
                "] ";
        try {
            scenario.attach(FileUtils.readFileToByteArray(new File(filePath)), mimeType, caption);
        } catch (IOException e) {
            scenario.log(dateStamp + e.getMessage());
        }
    }

    public static void attach(Scenario scenario, byte[] fileByteArray, String mimeType, String caption) {
        scenario.attach(fileByteArray, mimeType, caption);
    }
}
