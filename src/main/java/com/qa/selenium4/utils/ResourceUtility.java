package com.qa.selenium4.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;

public class ResourceUtility {
    private static Logger logger = LogManager.getLogger(ResourceUtility.class.getName());

    public ResourceUtility() {
        // To Do
    }

    /**
     * To get Absolute File Path of a resource
     *
     * @param resourceFileName Name of Resource File
     * @return Absolute file path
     */
    public File getResourceAsFile(String resourceFileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        // Get Resource as URL
        URL resource = classLoader.getResource(resourceFileName);

        if (resource != null) {
            return new File(resource.getFile());
        } else {
            throw new IllegalArgumentException("Resource file [" + resourceFileName + "] Not found in classpath");
        }
    }

    /**
     * To get Absolute File Path of a resource
     *
     * @param resourceFileName Name of Resource File
     * @return Absolute file path
     */
    public String getResourceFilePath(String resourceFileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        // Get Resource as URL
        URL resource = classLoader.getResource(resourceFileName);

        if (resource != null) {
            return new File(resource.getFile()).getAbsolutePath();
        } else {
            throw new IllegalArgumentException("Resource file [" + resourceFileName + "] Not found in classpath");
        }
    }
}
