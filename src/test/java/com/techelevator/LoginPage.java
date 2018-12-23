package com.techelevator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver webDriver;

	public LoginPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	
	public LoginPage enterUserName(String userName) { 
		WebElement userNameField = webDriver.findElement(By.id("userName"));
		userNameField.sendKeys(userName);
		return this;
		
	}
	
	public LoginPage enterPassword(String password) { 
		WebElement passwordField = webDriver.findElement(By.id("password"));
		passwordField.sendKeys(password);
		return this;
	}
	public HomePage clickSubmit() {
		WebElement submitButton = webDriver.findElement(By.id("LoginButton"));
		submitButton.click();
		return new HomePage(webDriver);
		
	}
	public SearchPage clickAddComicLink() {
		WebElement addComicButton = webDriver.findElement(By.id("addComic"));
		addComicButton.click();
		return new SearchPage(webDriver);
	}
	
}
