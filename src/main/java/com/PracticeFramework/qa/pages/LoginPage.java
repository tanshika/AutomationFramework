package com.PracticeFramework.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	@FindBy(id="input-email")
	WebElement emailInput;
	
	@FindBy(id="input-password")
	WebElement passwordInput;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginButton;
	
	
	@FindBy(xpath="//div[contains(@class,'alert alert-danger alert-dismissible')]")
	WebElement warningMessage; 
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void enterEmail(String emailText) {
		emailInput.sendKeys(emailText);
	}
	
	public void enterPassword(String passwordText) {
		passwordInput.sendKeys(passwordText);
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}
	
	public String getWarningText() {
		return warningMessage.getText();
	}

}
