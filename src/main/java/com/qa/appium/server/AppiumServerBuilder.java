package com.qa.appium.server;


import com.qa.appium.enums.AppiumServerFlag;
import com.qa.selenium4.utils.DateUtility;
import com.qa.selenium4.utils.SystemUtility;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class AppiumServerBuilder {
    private static final Logger logger = LogManager.getLogger(AppiumServerBuilder.class.getName());
    private AppiumDriverLocalService appiumDriverLocalService;
    private AppiumServiceBuilder appiumServiceBuilder;

    /***
     *
     * @param atIPAddress IP Address At Which you want to start
     *                    Appium Server
     * @return AppiumDriverLocalService
     */
    public AppiumDriverLocalService buildAppiumServerProcess(String atIPAddress, boolean chromeAutoDownload) {
        try {
            String appiumServerLogFileName = SystemUtility.getUserDirectory()
                    + SystemUtility.getFileSeparator()
                    + "appium-server-logs"
                    + SystemUtility.getFileSeparator()
                    + DateUtility.getCurrentTimeStampWithFormatAs("dd-MM-yyyy")
                    + SystemUtility.getFileSeparator()
                    + "appium-server-logs-"
                    + DateUtility.getCurrentTimeStamp()
                    + ".log";

            File appiumServerFile = new File(appiumServerLogFileName);

            if (!appiumServerFile.getParentFile().exists()) {
                appiumServerFile.getParentFile().mkdirs();
            }

            appiumServiceBuilder = new AppiumServiceBuilder();
            appiumServiceBuilder.withIPAddress(atIPAddress);
            appiumServiceBuilder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/");
            appiumServiceBuilder.usingAnyFreePort();
            appiumServiceBuilder.withLogFile(appiumServerFile);
            appiumServiceBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);

            if (chromeAutoDownload) {
                appiumServiceBuilder.withArgument(AppiumServerFlag.ALLOW_INSECURE, "chromedriver_autodownload");
            }

            logger.info("Appium Server Log File Can be found here: " + appiumServerLogFileName);

            appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumServiceBuilder);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return appiumDriverLocalService;
    }
}
