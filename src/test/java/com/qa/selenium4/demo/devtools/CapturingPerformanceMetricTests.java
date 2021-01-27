package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.base.BaseDriver;
import com.qa.selenium4.demo.helper.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v87.performance.Performance;
import org.openqa.selenium.devtools.v87.performance.model.Metric;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

public class CapturingPerformanceMetricTests extends BaseDriver {
    private static final Logger logger = LogManager.getLogger(CapturingPerformanceMetricTests.class.getName());

    @Test(priority = 0, description = "Capture Performance Metrics Using Chrome Dev Tools")
    public void getPerformanceMetricsUsingCDPTest() {
        // Get Dev Tools
        DevTools chromeDevTools = getDevTools();

        // Create Session
        chromeDevTools.createSession();

        // Enable Performance
        chromeDevTools.send(Performance.enable(Optional.of(Performance.EnableTimeDomain.TIMETICKS)));

        // Load URL
        driver.get("https://www.amazon.in/");
        WaitHelper.hardWait(5);
        List<Metric> performanceMatrix = chromeDevTools.send(Performance.getMetrics());

        chromeDevTools.send(Performance.disable());

        performanceMatrix.forEach(matrix -> {
            logger.info(matrix.getName() + " : " + matrix.getValue());
        });
    }
}
