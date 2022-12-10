package com.qa.selenium4.demo.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementHelper {
    private static final Logger logger = LogManager.getLogger(ElementHelper.class.getName());

    public static void click(WebDriver driver, WebElement element) {
        ActionHelper.moveToElement(driver, element);
        // Highlight element
        JavaScriptHelper.highlightElement(driver, element);

        // Click
        element.click();

        WaitHelper.hardWaitInMillis(400);

        // Unhighlight Element
        JavaScriptHelper.unHighlightElement(driver, element);
    }

    public static void click(WebDriver driver, By by) {
        ActionHelper.moveToElement(driver, by);
        // Highlight element
        WebElement element = driver.findElement(by);
        JavaScriptHelper.highlightElement(driver, element);

        // Click
        element.click();

        WaitHelper.hardWaitInMillis(400);

        // Unhighlight Element
        JavaScriptHelper.unHighlightElement(driver, element);
    }

    public static void sendKeys(WebDriver driver, WebElement element, CharSequence charSequence) {

        ActionHelper.moveToElement(driver, element);
        // Highlight element
        JavaScriptHelper.highlightElement(driver, element);

        // Click
        //  element.clear();
        new Actions(driver)
                .click(element)
                .keyDown(Keys.LEFT_CONTROL)
                .keyDown("A")
                .keyDown(Keys.BACK_SPACE)
                .keyUp(Keys.BACK_SPACE)
                .keyUp("A")
                .keyUp(Keys.LEFT_CONTROL)
                .sendKeys(element, charSequence)
                .build().perform();

        WaitHelper.hardWaitInMillis(400);

        // Unhighlight Element
        JavaScriptHelper.unHighlightElement(driver, element);
    }

    public static void sendKeysWithJs(WebDriver driver, By by, CharSequence sequence) {
        ActionHelper.moveToElement(driver, by);
        // Highlight element
        JavaScriptHelper.highlightElement(driver, by);


        JavaScriptHelper.getJsExecutor(driver)
                .executeScript("arguments[0].value='" + sequence.toString() + "'", driver.findElement(by));

        WaitHelper.hardWaitInMillis(400);

        // Unhighlight Element
        JavaScriptHelper.unHighlightElement(driver, by);

    }

    public static void selectElementByVisibleText(WebDriver driver, WebElement element, String charSequence) {
        ActionHelper.moveToElement(driver, element);
        // Highlight element
        JavaScriptHelper.highlightElement(driver, element);

        Select selectElement = new Select(element);
        selectElement.selectByVisibleText(charSequence);

        WaitHelper.hardWaitInMillis(400);

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

        // Move To Element
        ActionHelper.moveToElement(driver, element);

        // Highlight Element
        JavaScriptHelper.highlightElement(driver, element);

        /// Get Text
        innerText = element.getText();

        WaitHelper.hardWaitInMillis(400);

        // Highlight Element
        JavaScriptHelper.unHighlightElement(driver, element);

        return innerText;
    }
}
