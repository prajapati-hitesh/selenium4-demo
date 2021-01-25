package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.base.BaseDriver;
import com.qa.selenium4.demo.constants.FileConstants;
import com.qa.selenium4.utils.DateUtility;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v87.network.Network;
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
        driver.get("https://www.phptravels.net/");

        // Thread Sleep
        Thread.sleep(5000);
    }

    @DataProvider(name = "userAgents")
    public Object[] getUserAgents() {
        // Get Example of User Agents from this link -> https://developers.whatismybrowser.com/useragents/explore/
        // Detect User Agent At -> https://gs.statcounter.com/detect
        return new Object[][]{
                // Hitesh PC UA
                {"Current Machine UA", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36 RuxitSynthetic/1.0 v8389930799 t38550 ath9b965f92 altpub cvcv=2"},
                {"Chrome On Windows", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36"},
                {"Chrome On Linux", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36"},
                {"Microsoft Edge on Windows", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134"},
                {"Chrome on Android", "Mozilla/5.0 (Linux; Android 10.0; RedMi Note 5 Build/RB3N5C; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/68.0.3440.91 Mobile Safari/537.36"},
                {"Chrome on Android", "Mozilla/5.0 (Linux; Android 7.1.2; AFTMM Build/NS6265; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/70.0.3538.110 Mobile Safari/537.36"},
                {"Firefox on Linux", "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:24.0) Gecko/20100101 Firefox/24.0"},
                {"Safari on Mac OSX", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1.2 Safari/605.1.15"},
                {"Safari on iOS", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.4 Mobile/15E148 Safari/604.1"},
                {"Facebook app on Apple Iphone 7 Plus", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 [FBAN/FBIOS;FBDV/iPhone9,4;FBMD/iPhone;FBSN/iOS;FBSV/12.3.1;FBSS/3;FBID/phone;FBLC/en_US;FBOP/5;FBCR/AT&T]"},
                {"Chrome on Nexus 6P", "Mozilla/5.0 (Linux; Android 6.0.1; Nexus 6P Build/MMB29P) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.83 Mobile Safari/537.36"},
                {"Chrome on IphoneXS", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/69.0.3497.105 Mobile/15E148 Safari/605.1"}
        };
    }
}
