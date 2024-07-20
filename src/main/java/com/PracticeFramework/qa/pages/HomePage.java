package com.PracticeFramework.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement homePageMenu;
	
	@FindBy(linkText="Login")
	WebElement loginOption;
	
	@FindBy(linkText="Register")
	WebElement registerOption;
	
	@FindBy(name="search")
	WebElement searchField;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	WebElement searchButton;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnMyAccountMenu() {
		homePageMenu.click();
	}
	
	public void clickOnLogin() {
		loginOption.click();
	}
	
	public void clickOnRegister() {
		registerOption.click();
	}
	
	public void enterProductInSeachField(String productName) {
		searchField.sendKeys(productName);
	}
	
	public void clickSearchButton() {
		searchButton.click();
	}

}
