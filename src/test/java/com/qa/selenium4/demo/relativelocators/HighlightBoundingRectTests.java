package com.qa.selenium4.demo.relativelocators;

import com.qa.selenium4.demo.base.BaseDriver;
import com.qa.selenium4.demo.helper.BoundingRectHelper;
import com.qa.selenium4.demo.helper.JavaScriptHelper;
import com.qa.selenium4.demo.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

public class HighlightBoundingRectTests extends BaseDriver {

    @Test(priority = 0, description = "Highlight Bounding Rect Areas With Respect To A Particular Element")
    public void highlightBoundingAreasOfAnElementTest() {

        // Load URL
        driver.get("https://practice.automationtesting.in/my-account/");

        // Get "Email Address" input element
        WebElement emailInputElement = driver.findElement(RelativeLocator
                .with(By.tagName("input"))
                .above(By.id("reg_password"))
                .toRightOf(By.id("username"))
                .below(By.xpath("//*[text()='Register']"))
        );

        // Highlight element and
        JavaScriptHelper.highlightElement(driver, emailInputElement);

        // Wait for 5 sec
        WaitHelper.hardWait(5);

        // Get Bounding Rect Values
        BoundingRectHelper boundingRectHelper = new BoundingRectHelper(driver, driver.findElement(By.id("reg_password")));

        // Highlight area above "Password" field
        boundingRectHelper.highlightAbove("red");
        WaitHelper.hardWait(5);

        // Refresh Page
        driver.navigate().refresh();

        // Highlight area to the left of "Password" field
        boundingRectHelper.highlightToLeftOf("yellow");
        WaitHelper.hardWait(5);

        // Refresh Page
        driver.navigate().refresh();

        // Highlight area to the Below of "Password" field
        boundingRectHelper.highlightBelow("black");
        WaitHelper.hardWait(5);

        // Refresh Page
        driver.navigate().refresh();

        // Highlight area to the Right of "Password" field
        boundingRectHelper.highlightToRightOf("green");
        WaitHelper.hardWait(5);
        // Refresh Page
        driver.navigate().refresh();

        // Highlight Area which is at most to X pixels from an element
        boundingRectHelper.highlightNear("red", 50);
        boundingRectHelper.highlightNear("black", 100);
        boundingRectHelper.highlightNear("yellow", 200);
        boundingRectHelper.highlightNear("green", 400);
    }
}