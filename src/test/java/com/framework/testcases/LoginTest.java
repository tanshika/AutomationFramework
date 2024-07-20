package com.framework.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.AssertJUnit;

import java.io.IOException;
import org.openqa.selenium.WebDriver;

import com.PracticeFramework.qa.pages.Account;
import com.PracticeFramework.qa.pages.HomePage;
import com.PracticeFramework.qa.pages.LoginPage;
import com.PracticeFramework.qa.utils.Utilities;
import com.framework.base.Base;

public class LoginTest extends Base {
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() throws IOException {
		loadPropertiesFile();
		driver = initializeAndOpenURL(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccountMenu();
		homepage.clickOnLogin();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	//@Test(priority=1, dataProvider="supplyTestData") We can give method name
	@Test(priority=1, dataProvider="validlogincreds") //We can also give the name of the data provider annotation. Both ways are right
	public void verifyLoginWithValidCreds(String email, String password) {
		LoginPage loginpage = new LoginPage(driver);
		Account accountpage = new Account(driver);
		loginpage.enterEmail(email);
		loginpage.enterPassword(password);
		loginpage.clickLoginButton();
		AssertJUnit.assertTrue(accountpage.verifyAccountPageafterLogin());	
		}
	
	@DataProvider(name="validlogincreds")
	public Object[][] supplyTestData() throws IOException {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCreds() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmail("tanshikaarora78"+Utilities.generateTimeStamp()+"@gmail.com");
		loginpage.enterPassword(dataprop.getProperty("invalidPassword"));
		loginpage.clickLoginButton();
		String warningText = loginpage.getWarningText();
		AssertJUnit.assertEquals(warningText, "Warning: No match for E-Mail Address and/or Password.");
	}
	
	
	@Test(priority=3)
	public void verifyLoginWithNoEmailAndPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.clickLoginButton();
		String warningText = loginpage.getWarningText();
		AssertJUnit.assertEquals(warningText, "Warning: No match for E-Mail Address and/or Password.");
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmail(prop.getProperty("validEmail"));
		loginpage.enterPassword(dataprop.getProperty("invalidPassword"));
		loginpage.clickLoginButton();
		String warningText = loginpage.getWarningText();
		AssertJUnit.assertEquals(warningText, "Warning: No match for E-Mail Address and/or Password.");
	}
}





