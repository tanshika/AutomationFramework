package com.PracticeFramework.qa.listeners;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.Desktop;
import java.io.File;

import com.PracticeFramework.qa.utils.ExtendReporter;
import com.PracticeFramework.qa.utils.Utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListeners implements ITestListener {
	ExtentReports er;
	ExtentTest extenttest;
	
	@Override
	public void onStart(ITestContext context) {
		try {
			 er = ExtendReporter.generateReports();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extenttest = er.createTest(testName);
		extenttest.log(Status.INFO,testName+" started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extenttest.log(Status.PASS,testName+" test passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();	
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, testName);
		extenttest.addScreenCaptureFromPath("../../" + destinationScreenshotPath);
		extenttest.log(Status.INFO,result.getThrowable());
		extenttest.log(Status.FAIL,result.getName()+" got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extenttest.log(Status.INFO,result.getThrowable());
		extenttest.log(Status.SKIP, result.getName()+" got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		er.flush();
		File file = new File("/Users/vipulbhardwaj/Desktop/JavaTesting/PracticeFramework1/test-output/ExtentReports/Ereport.html");
		try {
			Desktop.getDesktop().browse(file.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
