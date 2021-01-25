package com.qa.selenium4.demo.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementHelper {
    private static final Logger logger = LogManager.getLogger(ElementHelper.class.getName());

    public static void click(WebDriver driver, WebElement element) {
        // Highlight element
        JavaScriptHelper.highlightElement(driver, element);

        // Click
        element.click();

        // Unhighlight Element
        JavaScriptHelper.unHighlightElement(driver, element);
    }

    public static void sendKeys(WebDriver driver, WebElement element, CharSequence charSequence) {
        // Highlight element
        JavaScriptHelper.highlightElement(driver, element);

        // Click
        element.sendKeys(charSequence);

        // Unhighlight Element
        JavaScriptHelper.unHighlightElement(driver, element);
    }

    public static void selectElementByVisibleText(WebDriver driver, WebElement element, String charSequence) {
        // Highlight element
        JavaScriptHelper.highlightElement(driver, element);

        Select selectElement = new Select(element);
        selectElement.selectByVisibleText(charSequence);

        // Unhighlight Element
        JavaScriptHelper.unHighlightElement(driver, element);
    }

    public static WebElement findElement(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement findElement(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static String getText(WebDriver driver, WebElement element) {
        String innerText;

        // Highlight Element
        JavaScriptHelper.highlightElement(driver, element);

        /// Get Text
        innerText = element.getText();

        // Highlight Element
        JavaScriptHelper.unHighlightElement(driver, element);

        return innerText;
    }
}
