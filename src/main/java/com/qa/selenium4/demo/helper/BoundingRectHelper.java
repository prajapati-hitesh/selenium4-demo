package com.qa.selenium4.demo.helper;

import com.google.common.base.Splitter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;


public class BoundingRectHelper {
    Map<String, String> boundingRectTransformedEntryMap;
    private WebDriver driverObj;
    private WebElement elementObj;

    public BoundingRectHelper(WebDriver driver, WebElement element) {
        this.driverObj = driver;
        this.elementObj = element;

        // Get Bounding Client Rect
        Object boundingRectTransformedEntryMap = getJSExecutor()
                .executeScript(
                        "return arguments[0].getBoundingClientRect();",
                        elementObj
                );

        // Replace All WhiteSpace, {, } with no space
        String boundingRectString = boundingRectTransformedEntryMap.toString().replaceAll("[\\s{}]+", "");
        this.boundingRectTransformedEntryMap = Splitter.on(",").withKeyValueSeparator("=").split(boundingRectString);
    }

    private JavascriptExecutor getJSExecutor() {
        return ((JavascriptExecutor) this.driverObj);
    }

    public double getBottomRightY() {
        return Double.parseDouble(boundingRectTransformedEntryMap.get("bottom"));
    }

    public double getBottomRightX() {
        return Double.parseDouble(boundingRectTransformedEntryMap.get("right"));
    }

    public double getTopX() {
        return Double.parseDouble(boundingRectTransformedEntryMap.get("x"));
    }

    public double getTopY() {
        return Double.parseDouble(boundingRectTransformedEntryMap.get("y"));
    }

    public double getWidth() {
        return Double.parseDouble(boundingRectTransformedEntryMap.get("width"));
    }

    public double getHeight() {
        return Double.parseDouble(boundingRectTransformedEntryMap.get("height"));
    }

    public void highlightAbove(String hexCode) {
        drawRectangle(
                0,
                0,
                WindowHelper.getWebPageSize(driverObj).getWidth(),
                getTopY(),
                hexCode
        );
    }

    public void highlightToLeftOf(String hexCode) {
        drawRectangle(
                0,
                0,
                getTopX(),
                WindowHelper.getFullPageHeight(driverObj),
                hexCode
        );
    }

    public void highlightBelow(String hexCode) {
        drawRectangle(
                0,
                getBottomRightY(),
                WindowHelper.getWebPageSize(driverObj).getWidth(),
                WindowHelper.getFullPageHeight(driverObj),
                hexCode
        );
    }

    public void highlightToRightOf(String hexCode) {
        drawRectangle(
                getBottomRightX(),
                0,
                WindowHelper.getWindowSize(driverObj).getWidth() - getBottomRightX(),
                WindowHelper.getFullPageHeight(driverObj),
                hexCode
        );
    }

    private void drawRectangle(double xCord, double yCord, double width, double height, String hexColor) {
        String drawCanvasRectJs =
                "var x = " + xCord + ";\n" +
                        "var y = " + yCord + ";\n" +
                        "var width = " + width + ";\n" +
                        "var height = " + height + ";\n" +
                        "\n" +
                        "var canvas = document.createElement('canvas'); //Create a canvas element\n" +
                        "//Set canvas width/height\n" +
                        "canvas.style.width='100%';\n" +
                        "canvas.style.height='100%';\n" +
                        "\n" +
                        "//Set canvas drawing area width/height\n" +
                        "canvas.width = window.innerWidth;\n" +
                        "canvas.height = window.innerHeight;\n" +
                        "\n" +
                        "//Position canvas\n" +
                        "canvas.style.position='absolute';\n" +
                        "canvas.style.left=0;\n" +
                        "canvas.style.top=0;\n" +
                        "canvas.style.zIndex=100000;\n" +
                        "canvas.style.pointerEvents='none'; //Make sure you can click 'through' the canvas\n" +
                        "document.body.appendChild(canvas); //Append canvas to body element\n" +
                        "var context = canvas.getContext('2d');\n" +
                        "\n" +
                        "//Draw rectangle\n" +
                        "context.beginPath();\n" +
                        "context.lineWidth = '5';\n" +
                        "context.strokeStyle = '" + hexColor + "';\n" +
                        "context.rect(x, y, width, height);\n" +
                        "context.stroke();\n";
        // Execute Script
        getJSExecutor().executeScript(drawCanvasRectJs);
    }
}