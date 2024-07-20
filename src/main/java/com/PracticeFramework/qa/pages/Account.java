package com.PracticeFramework.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Account {
	WebDriver driver;
	
	@FindBy(linkText="Edit your account information")
	WebElement loginConfirmation;
	
	@FindBy(xpath="//div[@id='content']/h1")
	WebElement registerConfirmation;
	
	public Account (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyAccountPageafterLogin() {
		boolean displayStatus = loginConfirmation.isDisplayed();	
		return displayStatus;
	}
	
	public String accountCreationSuccessful() {
		return registerConfirmation.getText();
	}
	

}
