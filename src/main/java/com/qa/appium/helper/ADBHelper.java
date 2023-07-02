package com.qa.appium.helper;


import com.qa.appium.utils.CommandPrompt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ADBHelper {
    private static final Logger logger = LogManager.getLogger(ADBHelper.class.getName());

    public ADBHelper() {
        // to do
    }

    /**
     * To Unlock the connected device using Device UDID
     *
     * @param deviceUDID UDID of Android Device
     */
    public void unlockDevice(String deviceUDID) {
        try {
            // To check if the screen is awake or not
            String mAwake = "adb -s " + deviceUDID + " shell dumpsys window | find \"mAwake\"";

            // To check if the screen is lock or not
            String mScreenLock = "adb -s " + deviceUDID + " shell dumpsys window | find \"mShowingLockscreen\"";

            // To click on Menu button
            String clickMenuBtn = "adb -s " + deviceUDID + " shell input keyevent 82";

            // Wake up device
            String wakeUpDevice = "adb -s " + deviceUDID + " shell input keyevent 224";
            String AND = " & ";

            String adbCommand = mAwake + " & " + mScreenLock;

            CommandPrompt cmd = new CommandPrompt();

            // run the command
            String adbResult = cmd.runCommand(adbCommand);

            boolean isDeviceAwake = adbResult.contains("mAwake=true");
            boolean isDeviceLocked = adbResult.contains("mShowingLockscreen=true");

            if (isDeviceAwake && isDeviceLocked) {
                // if isDeviceAwake = true & isDeviceLocked = true
                cmd.runCommand(clickMenuBtn);
            } else if (!isDeviceAwake && isDeviceLocked) {
                // if isDeviceAwake = false & isDeviceLocked = true
                cmd.runCommand(wakeUpDevice + AND + clickMenuBtn);
            } else {
                // wake up device
                cmd.runCommand(wakeUpDevice);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * To reboot device having specific UDID
     *
     * @param deviceUDID UDID of an android device
     */
    public void rebootDevice(String deviceUDID) {
        try {
            // Initialize object of command prompt
            CommandPrompt cmd = new CommandPrompt();

            // Wait for 10 seconds before rebooting device
            logger.info("Waiting for 20 seconds before rebooting : " + deviceUDID);

            // Wait for 20 seconds before calling adb reboot
            Thread.sleep(20000);

            String rebootDevice = "adb -s " + deviceUDID + " reboot";

            String cmdOutput = cmd.runCommand(rebootDevice);

            logger.info("Response Returned By Adb Reboot : " + cmdOutput);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
