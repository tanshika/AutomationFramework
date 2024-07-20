package com.PracticeFramework.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReporter {
	
	public static ExtentReports generateReports() throws IOException {
		ExtentReports extentReport = new ExtentReports();
		File report = new File("/Users/vipulbhardwaj/Desktop/JavaTesting/PracticeFramework1/test-output/ExtentReports/Ereport.html");
		ExtentSparkReporter extentSpark = new ExtentSparkReporter(report);
		extentSpark.config().setTheme(Theme.DARK);
		extentSpark.config().setReportName("Automation Result Report");
		extentSpark.config().setDocumentTitle("Automation Report");
		extentSpark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		extentReport.attachReporter(extentSpark);
		
		Properties configProp = new Properties();
		File configfile = new File("/Users/vipulbhardwaj/Desktop/JavaTesting/PracticeFramework1/src/main/java/com/PracticeFramework/qa/config/Config.properties");
		FileInputStream fisConfigprop = new FileInputStream(configfile);
		configProp.load(fisConfigprop);
		
		extentReport.setSystemInfo("Application link", configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browser"));
		
		return extentReport;
	}

}
