package com.framework.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.PracticeFramework.qa.pages.HomePage;
import com.PracticeFramework.qa.pages.SearchPage;
import com.framework.base.Base;

public class SearchTest extends Base {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws IOException {
		loadPropertiesFile();
		driver = initializeAndOpenURL(prop.getProperty("browser"));
	}
	
	@Test(priority=1)
	public void searchWithExistingProduct() {
		HomePage homepage = new HomePage(driver);
		SearchPage searchpage = new SearchPage(driver);
		homepage.enterProductInSeachField(dataprop.getProperty("existingProduct"));
		homepage.clickSearchButton();
		boolean productDisplayoOrNot = searchpage.checkExistingProductDisplay();
		AssertJUnit.assertTrue(productDisplayoOrNot);
	}
	
	@Test(priority=2)
	public void searchWithNonExisitingProduct() {
		HomePage homepage = new HomePage(driver);
		SearchPage searchpage = new SearchPage(driver);
		homepage.enterProductInSeachField(dataprop.getProperty("nonexistingProduct"));
		homepage.clickSearchButton();
		String noProductMessage = searchpage.checkNonExistingProductMessage();
		AssertJUnit.assertEquals(noProductMessage, "There abc is no product that matches the search criteria.");
	}
	
	@Test(priority=3, dependsOnMethods= {"searchWithNonExisitingProduct"})
	//@Test(priority=3)
	public void searchWithNoProduct() {
		HomePage homepage = new HomePage(driver);
		SearchPage searchpage = new SearchPage(driver);
		homepage.clickSearchButton();
		String noProductMessage = searchpage.checkNonExistingProductMessage();
		AssertJUnit.assertEquals(noProductMessage, "There is no product that matches the search criteria.");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
