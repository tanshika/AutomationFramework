package com.PracticeFramework.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;
	
	@FindBy(linkText="HP LP3065")
	WebElement existingProductMessage;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	WebElement nonexistingProductMessage;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Action
	
	public String checkNonExistingProductMessage() {
		return nonexistingProductMessage.getText();
	}
	
	public boolean checkExistingProductDisplay() {
		return existingProductMessage.isDisplayed();
	}

}
