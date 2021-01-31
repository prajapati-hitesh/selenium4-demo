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
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

        // Get "Email Address" input element
        WebElement emailInputElement = driver.findElement(RelativeLocator
                .withTagName("input")
                .above(By.id("passwd"))
                .toRightOf(By.id("email_create"))
                .below(By.id("search_query_top"))
        );

        // Highlight element and
        JavaScriptHelper.highlightElement(driver, emailInputElement);

        // Wait for 5 sec
        WaitHelper.hardWait(5);

        // Get Bounding Rect Values
        BoundingRectHelper boundingRectHelper = new BoundingRectHelper(driver, driver.findElement(By.id("passwd")));

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
    }
}