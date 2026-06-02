package com.intelehealth.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TestListener implements ITestListener {
	TestUtils utils = new TestUtils();
	
	public void onTestFailure(ITestResult result) {

	    // Step 1: Log the throwable always
	    if (result.getThrowable() != null) {
	        StringWriter sw = new StringWriter();
	        PrintWriter pw = new PrintWriter(sw);
	        result.getThrowable().printStackTrace(pw);
	        utils.log().error(sw.toString());
	    }

	    // Step 2: Log failure to ExtentReport always
	    ExtentReport.getTest().fail(result.getThrowable());

	    // Step 3: Get driver directly from ThreadLocal — 
	    // avoids triggering BaseTest constructor which calls PageFactory
	    AppiumDriver activeDriver = BaseTest.driver.get();

	    // Step 4: Null check
	    if (activeDriver == null) {
	        utils.log().warn("Driver is null — skipping screenshot");
	        return;
	    }

	    // Step 5: Session alive check
	    try {
	        activeDriver.getPageSource(); // safer than getCurrentUrl() for native apps
	    } catch (Exception sessionDead) {
	        utils.log().warn("Driver session is dead — skipping screenshot: "
	            + sessionDead.getMessage());
	        return;
	    }

	    // Step 6: Safe to take screenshot
	    try {
	        File file = activeDriver.getScreenshotAs(OutputType.FILE);
	        byte[] encoded = Base64.encodeBase64(
	            FileUtils.readFileToByteArray(file));

	        Map<String, String> params = result.getTestContext()
	            .getCurrentXmlTest().getAllParameters();

	        String imagePath = "Screenshots" + File.separator
	            + params.get("platformName") + "_" + params.get("deviceName")
	            + File.separator + BaseTest.dateTime.get() + File.separator
	            + result.getTestClass().getRealClass().getSimpleName()
	            + File.separator + result.getName() + ".png";

	        String completeImagePath = System.getProperty("user.dir")
	            + File.separator + imagePath;

	        // Ensure directory exists before copying
	        new File(completeImagePath).getParentFile().mkdirs();

	        FileUtils.copyFile(file, new File(imagePath));

	        ExtentReport.getTest().fail("Test Failed",
	            MediaEntityBuilder.createScreenCaptureFromBase64String(
	                new String(encoded, StandardCharsets.US_ASCII)).build());

	        utils.log().info("Screenshot captured: " + completeImagePath);

	    } catch (Exception screenshotEx) {
	        utils.log().warn("Screenshot capture failed: " 
	            + screenshotEx.getMessage());
	        ExtentReport.getTest().fail("Screenshot unavailable: "
	            + screenshotEx.getMessage());
	    }
	}
	@Override
	public void onTestStart(ITestResult result) {
  
		 BaseTest base = new BaseTest();
		 
	        ExtentReport.startTest(result.getName(), result.getMethod().getDescription())
	                .assignCategory(base.getPlatform() + "_" + base.getDeviceName())
	                .assignAuthor("Shweta Naik");
	    	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReport.getTest().log(Status.PASS, "Test Passed");
		
	}


	
	@Override
	public void onTestSkipped(ITestResult result) {
	    Throwable skipCause = result.getThrowable();
	    String skipMessage = (skipCause != null) ? skipCause.getMessage() : "No specific reason provided";
	    ExtentReport.getTest().log(Status.SKIP, "Test Skipped: " + skipMessage);

	    try {
	        BaseTest base = new BaseTest();
	        if (base.getDriver() != null) {
	            File file = base.getDriver().getScreenshotAs(OutputType.FILE);
	            Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();
	            String dirPath = "Screenshots" + File.separator + params.get("platformName")
	                             + "_" + params.get("deviceName") + File.separator + base.getDateTime()
	                             + File.separator + result.getTestClass().getRealClass().getSimpleName()
	                             + File.separator + result.getName();
	            String imagePath = dirPath + ".png";
	            String completeImagePath = System.getProperty("user.dir") + File.separator + imagePath;

	            // Ensure directory path exists
	            File screenshotDirectory = new File(dirPath);
	            if (!screenshotDirectory.exists()) {
	                screenshotDirectory.mkdirs();
	            }
	            
	            FileUtils.copyFile(file, new File(completeImagePath));

	            byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
	            String base64Image = new String(encoded, StandardCharsets.US_ASCII);
	            ExtentReport.getTest().log(Status.SKIP, "Screenshot on skip:",
	                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
	        }
	    } catch (Exception e) {
	        // Log the exception or handle it, but do not rethrow, as this is just logging additional information
	        System.err.println("Failed to capture or store screenshot on skip: " + e.getMessage());
	    }
	}




	


	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentReport.getReporter().flush();		
	}

}