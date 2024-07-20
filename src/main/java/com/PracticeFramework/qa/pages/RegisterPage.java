package com.PracticeFramework.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	WebElement inputFirstname;
	
	@FindBy(id="input-lastname")
	WebElement inputLastname;
	
	@FindBy(id="input-email")
	WebElement inputEmail;
	
	@FindBy(id="input-telephone")
	WebElement inputTelephone;
	
	@FindBy(id="input-password")
	WebElement inputPassword;
	
	@FindBy(id="input-confirm")
	WebElement inputConfirmpassword;
	
	@FindBy(name="agree")
	WebElement agreePolicycheckbox;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value=1]")
	WebElement newsletterCheckbox;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	WebElement emailAlreadyRegisteredMessage;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void enterFirstname(String firstName) {
		inputFirstname.sendKeys(firstName);
	}
	
	public void enterLastname(String lastName) {
		inputLastname.sendKeys(lastName);
	}
	
	public void enterEmail(String email) {
		inputEmail.sendKeys(email);
	}
	
	public void enterTelephone(String telephone) {
		inputTelephone.sendKeys(telephone);
	}
	
	public void enterPasswordandConfirmPassword(String password) {
		inputPassword.sendKeys(password);
		inputConfirmpassword.sendKeys(password);
	}
	
	public void clickAgreePolicyButton() {
		agreePolicycheckbox.click();
	}
	
	public void clickContinueButton() {
		continueButton.click();
	}
	
	public void newsletterOptionClick() {
		newsletterCheckbox.click();
	}
	
	public String emailAlreadyRegisteredMessageDisplay() {
		return emailAlreadyRegisteredMessage.getText();
	}
	
	public String privacyWarningMessage() {
		return privacyPolicyWarning.getText();
	}
	
	public String firstNameWarningMessage() {
		return firstNameWarning.getText();
	}
	
	public String lastNameWarningMessage() {
		return lastNameWarning.getText();
	}
	
	public String emailWarningMessage() {
		return emailWarning.getText();
	}
	
	public String telephoneWarningMessage() {
		return telephoneWarning.getText();
	}
	
	public String passwordWarningMessage() {
		return passwordWarning.getText();
	}
}
