package com.framework.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;

import java.io.IOException;
import org.openqa.selenium.WebDriver;


import com.PracticeFramework.qa.pages.Account;
import com.PracticeFramework.qa.pages.HomePage;
import com.PracticeFramework.qa.pages.RegisterPage;
import com.PracticeFramework.qa.utils.Utilities;
import com.framework.base.Base;

public class RegisterTest extends Base {
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() throws IOException {
		loadPropertiesFile();
		driver = initializeAndOpenURL(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccountMenu();
		homepage.clickOnRegister();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void registerWithMandatoryFields() {
		RegisterPage registerpage = new RegisterPage(driver);
		Account accountpage = new Account(driver);
		registerpage.enterFirstname(dataprop.getProperty("firstName"));
		registerpage.enterLastname(dataprop.getProperty("lastName"));
		registerpage.enterEmail("ar.ta"+Utilities.generateTimeStamp()+"@abc.com");
		registerpage.enterTelephone(dataprop.getProperty("telephone"));
		registerpage.enterPasswordandConfirmPassword(dataprop.getProperty("validPass"));
		registerpage.clickAgreePolicyButton();
		registerpage.clickContinueButton();
		String actualSuccessHeading = accountpage.accountCreationSuccessful();
		AssertJUnit.assertEquals("Your Account Has Been Created!", actualSuccessHeading);
	}
	
	@Test(priority=2)
	public void registerWithoutMandatoryFields() {
		RegisterPage registerpage = new RegisterPage(driver);
		Account accountpage = new Account(driver);
		registerpage.enterFirstname(dataprop.getProperty("firstName"));
		registerpage.enterLastname(dataprop.getProperty("lastName"));
		registerpage.enterEmail("ar.ta"+Utilities.generateTimeStamp()+"@abc.com");
		registerpage.enterTelephone(dataprop.getProperty("telephone"));
		registerpage.enterPasswordandConfirmPassword(dataprop.getProperty("validPass"));
		registerpage.newsletterOptionClick();
		registerpage.clickAgreePolicyButton();
		registerpage.clickContinueButton();
		String actualSuccessHeading = accountpage.accountCreationSuccessful();
		AssertJUnit.assertEquals("Your Account Has Been Created!", actualSuccessHeading);
	}
	
	@Test(priority=3)
	public void registerWithExistingEmail() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstname(dataprop.getProperty("firstName"));
		registerpage.enterLastname(dataprop.getProperty("lastName"));
		registerpage.enterEmail(dataprop.getProperty("alreadyregisteredemail"));
		registerpage.enterTelephone(dataprop.getProperty("telephone"));
		registerpage.enterPasswordandConfirmPassword(dataprop.getProperty("validPass"));
		registerpage.clickAgreePolicyButton();
		registerpage.clickContinueButton();
		String actualWarning = registerpage.emailAlreadyRegisteredMessageDisplay();
		AssertJUnit.assertTrue(actualWarning.contains("Warning: E-Mail Address is already registered!"));
	}
	
	@Test(priority=4)
	public void registerWithoutAnyDetails() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.clickContinueButton();
		
		
		String privacyPolicyWarning = registerpage.privacyWarningMessage();
		AssertJUnit.assertTrue( privacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy!"));
		
		String firstNameWarning = registerpage.firstNameWarningMessage();
		AssertJUnit.assertEquals(firstNameWarning, "First Name must be between 1 and 32 characters!");
		
		String lastNameWarning = registerpage.lastNameWarningMessage();
		AssertJUnit.assertEquals(lastNameWarning, "Last Name must be between 1 and 32 characters!");
		
		String emailWarning = registerpage.emailWarningMessage();
		AssertJUnit.assertEquals(emailWarning, "E-Mail Address does not appear to be valid!");
		
		String telephoneWarning = registerpage.telephoneWarningMessage();
		AssertJUnit.assertEquals(telephoneWarning, "Telephone must be between 3 and 32 characters!");
		
		String passwordWarning = registerpage.passwordWarningMessage();
		AssertJUnit.assertEquals(passwordWarning, "Password must be between 4 and 20 characters!");
		
	}

}
