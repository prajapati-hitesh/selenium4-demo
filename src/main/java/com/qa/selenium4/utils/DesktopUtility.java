package com.qa.selenium4.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.nio.file.NoSuchFileException;

public class DesktopUtility {
    private static final Logger logger = LogManager.getLogger(DesktopUtility.class.getName());

    public DesktopUtility() {
        // To Do
    }

    /**
     * To open a specific file in default browser
     *
     * @param pathToHTMLFile Absolute path to html file
     */
    public static void openHTMLInBrowser(String pathToHTMLFile) {
        try {
            File htmlFile = new File(pathToHTMLFile);

            if (htmlFile.exists()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } else {
                throw new NoSuchFileException("File does not exist at : " + pathToHTMLFile);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
