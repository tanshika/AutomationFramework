package com.framework.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.PracticeFramework.qa.utils.Utilities;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	
	public void loadPropertiesFile() throws IOException {
		prop = new Properties();
		dataprop = new Properties();
		File propfile = new File("/Users/vipulbhardwaj/Desktop/JavaTesting/PracticeFramework1/src/main/java/com/PracticeFramework/qa/config/Config.properties");
		File datapropfile = new File("/Users/vipulbhardwaj/Desktop/JavaTesting/PracticeFramework1/src/main/java/com/PracticeFramework/qa/testdata/testdata.properties");
		FileInputStream fis = new FileInputStream(propfile);
		FileInputStream fisdata = new FileInputStream(datapropfile);
		prop.load(fis);
		dataprop.load(fisdata);
		
	}
	
	public WebDriver initializeAndOpenURL(String browserName) {
		if(browserName.equals("chrome")) { driver = new ChromeDriver();}
		else if(browserName.equals("firefox")) { driver = new FirefoxDriver();}
		else if(browserName.equals("edge")) { driver = new EdgeDriver();}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.get(prop.getProperty("url"));
		return driver;
	}

}
