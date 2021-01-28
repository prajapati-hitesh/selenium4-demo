package com.qa.selenium4.demo.constants;

import com.qa.selenium4.utils.SystemUtility;

public class FileConstants {
    public static final String USER_DIR = SystemUtility.getUserDirectory();
    public static final String FILE_SEPARATOR = SystemUtility.getFileSeparator();
    public static final String SCREENSHOT_ROOT_DIR = USER_DIR + FILE_SEPARATOR + "screenshot" + FILE_SEPARATOR;
    public static final String CHROME_EXTENSION_DIR = USER_DIR + FILE_SEPARATOR + "chrome-extension" + FILE_SEPARATOR;
    public static final String TEST_DATA_DIR = USER_DIR + FILE_SEPARATOR + "test-data" + FILE_SEPARATOR;
}
