package com.qa.selenium4.demo.devtools;

import com.qa.selenium4.demo.driver.DriverFactory;
import com.qa.selenium4.demo.helper.WaitHelper;
import com.qa.selenium4.json.JSONArray;
import com.qa.selenium4.json.JSONObject;
import com.qa.selenium4.utils.DateUtility;
import com.qa.selenium4.utils.SystemUtility;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v106.network.Network;
import org.openqa.selenium.devtools.v106.network.model.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CaptureHTTPRequestTests {

    private static final Logger logger = LogManager.getLogger(CaptureHTTPRequestTests.class.getName());

    @Test(priority = 0, description = "Capture HTTP Requests using Chrome Dev Tools")
    public void captureHTTPRequestsUsingCDPTestOne() throws IOException {
        // Create Dev Tools Object
        DevTools devTools = DriverFactory.getInstance().getDevTools();

        // Create Session
        devTools.createSession();

        // Enable Network
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        JSONArray capturedRequestJsonArray = new JSONArray();
        JSONArray responseReceivedJsonArray = new JSONArray();

        List<RequestWillBeSent> sentNetworkLogs = new ArrayList<>();
        List<ResponseReceived> receivedNetworkLogs = new ArrayList<>();

        // Listen to network for Requests being sent
        devTools.addListener(Network.requestWillBeSent(), requestWillBeSent -> {
            // Add To list
            sentNetworkLogs.add(requestWillBeSent);

            JSONObject jsonObjectOfEachRequest = new JSONObject();

            // Get Request Id, Document URL, Request Object, Redirect Response & Type of Resource
            RequestId requestId = requestWillBeSent.getRequestId();
            String docUrl = requestWillBeSent.getDocumentURL();
            Request request = requestWillBeSent.getRequest();
            ResourceType resourceType = requestWillBeSent.getType().get();

            jsonObjectOfEachRequest.put("RequestId", requestId.toString());
            jsonObjectOfEachRequest.put("DocUrl", docUrl);
            jsonObjectOfEachRequest.put("ResourceType", resourceType.toJson());

            // Create new JSONObject to hold request Object
            JSONObject requestJsonObject = new JSONObject();

            requestJsonObject.put("Method", request.getMethod());
            requestJsonObject.put("Url", request.getUrl());
            requestJsonObject.put("PostData", request.getHasPostData().equals(Optional.of(true)) ? request.getPostData() : JSONObject.NULL);

            JSONObject headerJsonObject = new JSONObject();
            request.getHeaders().toJson().forEach((key, value) -> {
                headerJsonObject.put(key, value);
            });

            // Attach Header to request object
            requestJsonObject.put("Headers", headerJsonObject);

            // Attach Request Object to main
            jsonObjectOfEachRequest.put("Request", requestJsonObject);

            // Add to main array
            capturedRequestJsonArray.put(jsonObjectOfEachRequest);
        });

        // Listen to Network for Responses being received
        devTools.addListener(Network.responseReceived(), responseReceived -> {
            // Add To list
            receivedNetworkLogs.add(responseReceived);
            JSONObject responseReceivedJsonObject = new JSONObject();

            // Get Request ID, Resource Type, Response
            RequestId requestId = responseReceived.getRequestId();
            ResourceType resourceType = responseReceived.getType();
            Response response = responseReceived.getResponse();

            responseReceivedJsonObject.put("RequestId", requestId.toString());
            responseReceivedJsonObject.put("ResourceType", resourceType);

            JSONObject responseJsonObject = new JSONObject();

            responseJsonObject.put("Url", response.getUrl());
            responseJsonObject.put("StatusCode", response.getStatus());
            responseJsonObject.put("StatusText", !StringUtils.isBlank(response.getStatusText()) ? response.getStatusText() : JSONObject.NULL);
            responseJsonObject.put("MimeType", response.getMimeType());
            responseJsonObject.put("RemoteIP", response.getRemoteIPAddress().isPresent() && response.getRemotePort().isPresent() ? response.getRemoteIPAddress().get() + ":" + response.getRemotePort().get() : JSONObject.NULL);
            responseJsonObject.put("FromDiskCache?", response.getFromDiskCache().isPresent() ? response.getFromDiskCache().get() : JSONObject.NULL);
            responseJsonObject.put("Timing", response.getTiming().isPresent() ? response.getTiming().get().getRequestTime() : JSONObject.NULL);
            responseJsonObject.put("ResponseTime", response.getResponseTime().isPresent() ? response.getResponseTime().get().toString() : JSONObject.NULL);
            responseJsonObject.put("Protocol", response.getProtocol().isPresent() ? response.getProtocol().get() : JSONObject.NULL);

            JSONObject responseHeadersJsonObject = new JSONObject();

            response.getHeaders().toJson().forEach((key, value) -> {
                responseHeadersJsonObject.put(key, value);
            });

            // Add To Response Json Object
            responseJsonObject.put("Headers", responseHeadersJsonObject);

            // Add Response Object to main object
            responseReceivedJsonObject.put("Response", responseJsonObject);

            // Add to main Array
            responseReceivedJsonArray.put(responseReceivedJsonObject);
        });

        // Load URL
        DriverFactory.getInstance().getDriver().get("https://www.amazon.in/");

        WaitHelper.waitUntilElementIsVisible(
                DriverFactory.getInstance().getDriver(),
                Duration.ofMinutes(1),
                By.id("twotabsearchtextbox")
        ).sendKeys("Apple Iphone 12 Pro");

        // Sleep for 5
        WaitHelper.hardWait(3);

        System.out.println(capturedRequestJsonArray);
        String USER_DIR = SystemUtility.getUserDirectory();
        String FILE_SEPARATOR = SystemUtility.getFileSeparator();
        String requestPath = USER_DIR + FILE_SEPARATOR + "http-capture" + FILE_SEPARATOR + "request-" + DateUtility.getCurrentTimeStampWithFormatAs("ddMMyyyy-HH.mm.ss.SSS") + ".json";
        String responsePath = USER_DIR + FILE_SEPARATOR + "http-capture" + FILE_SEPARATOR + "response-" + DateUtility.getCurrentTimeStampWithFormatAs("ddMMyyyy-HH.mm.ss.SSS") + ".json";
        FileUtils.writeStringToFile(new File(requestPath), String.valueOf(capturedRequestJsonArray), "UTF-8");
        FileUtils.writeStringToFile(new File(responsePath), String.valueOf(responseReceivedJsonArray), "UTF-8");
        System.out.println(responseReceivedJsonArray);
    }
}
