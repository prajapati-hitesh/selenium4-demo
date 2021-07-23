package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.base.BaseDriver;
import com.qa.selenium4.demo.constants.FileConstants;
import com.qa.selenium4.utils.DateUtility;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v91.network.Network;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * When any user sends a request to access a web application,
 * the web browser sends a HTTP Header called the user agent.
 * The user agent string contains information about the user’s web browser name, operating system, device type, and other information
 * <p>
 * https://www.whoishostingthis.com/tools/user-agent/
 * <p>
 * Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36
 * (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36
 * <p>
 * Breaking the example down, we get the following information:
 * <p>
 * The user agent application - is Mozilla version 5.0, or a piece of software compatible with it.
 * The operating system - is OS X version 10.2.2 (and is running on a Mac).
 * The client - is Chrome version 51.0.2704.84.
 * The client - is based on Safari version 537.36.
 * The engine - responsible for displaying content on this device is AppleWebKit version 537.36 (and KHTML, an open-source layout engine, is present too).
 */
public class UserAgentSimulationTests extends BaseDriver {

    @Test(priority = 0, description = "To Change the User Agent of browser", dataProvider = "userAgents")
    public void phpTravelsUserAgentSimulationTestOne(String uAOf, String userAgentString) throws InterruptedException, IOException {

        // Get Dev Tools Object
        DevTools chromeDevTools = getDevTools();

        // Create a Dev Tool Session
        chromeDevTools.createSession();

        // Enable Network simulation
        chromeDevTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Simulate User Agent
        chromeDevTools.send(Network.setUserAgentOverride(
                userAgentString,
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        ));

        // Load url
        driver.get("https://gs.statcounter.com/detect");

        Thread.sleep(5000);

        // Get Screenshot
        byte[] imageAsBytes = driver.findElement(By.className("section")).getScreenshotAs(OutputType.BYTES);

        // Build File Path to store screenshot at
        String uaSSDirPath = FileConstants.SCREENSHOT_ROOT_DIR + FileConstants.FILE_SEPARATOR + "user-agents";

        // Build full file path to store UA
        String ssFileName = uaSSDirPath + FileConstants.FILE_SEPARATOR + uAOf + " - " + DateUtility.getCurrentTimeStamp() + ".png";

        // Save Screenshot
        FileUtils.writeByteArrayToFile(new File(ssFileName), imageAsBytes);

        // Load Php Travels
        driver.get("https://www.amazon.in/");

        // wait for page to load
        Thread.sleep(5000);
    }

    @DataProvider(name = "userAgents")
    public Object[] getUserAgents() {
        // Get Example of User Agents from below link
        // 01 - https://developers.whatismybrowser.com/useragents/explore/
        // 02 - https://deviceatlas.com/blog/list-of-user-agent-strings
        // Detect User Agent At -> https://gs.statcounter.com/detect
        return new Object[][]{
                // Mobile Devices
                {"Apple iPhone XR (Safari)", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0 Mobile/15E148 Safari/604.1"},
                {"Apple iPhone XS Max (Firefox)", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) FxiOS/13.2b11866 Mobile/16A366 Safari/605.1.15"},
                {"Nexus 6P (Chrome)", "Mozilla/5.0 (Linux; Android 6.0.1; Nexus 6P Build/MMB29P) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.83 Mobile Safari/537.36"},
                {"IphoneXS (Chrome)", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/69.0.3497.105 Mobile/15E148 Safari/605.1"},
                {"Redmi Note 5 (Chrome)", "Mozilla/5.0 (Linux; Android 10.0; RedMi Note 5 Build/RB3N5C; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/68.0.3440.91 Mobile Safari/537.36"},

                // Desktop User Agents
                {"Windows 10 (Chrome)", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36"},
                {"Windows 7 (Chrome)", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.36"},
                {"Linux (Chrome)", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36"},
                {"Windows 10 (Edge)", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246"},
                {"Ubuntu (Firefox)", "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:24.0) Gecko/20100101 Firefox/24.0"},
                {"Mac OSX (Safari)", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/601.3.9 (KHTML, like Gecko) Version/9.0.2 Safari/601.3.9"},

        };
    }
}
