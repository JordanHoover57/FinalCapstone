package com.techelevator;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {
	
	private WebDriver webDriver;

	public HomePage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	
	public String findLoginLink() {
		return webDriver.findElement(By.id("LogIn")).getText();
		
	}
	
	public String findLogoutLink() {
		return webDriver.findElement(By.id("logoutLink")).getText();
	}
	
	public LoginPage clickLoginLink() {
		WebElement linkClick = webDriver.findElement(By.id("LogIn"));
		linkClick.click();
		return new LoginPage(webDriver);
	}
	public LoginPage clickThisLoginLink() {
		WebElement thisLinkClick = webDriver.findElement(By.id("LogIn"));
		thisLinkClick.click();
		return new LoginPage(webDriver);
	}
	
	
	
	public String findCurrentUser() {
		return webDriver.findElement(By.id("currentUser")).getText();
	}


	public SearchPage clickAddComicLink() {
		WebElement addComicButton = webDriver.findElement(By.id("addComic"));
		addComicButton.click();
		return new SearchPage(webDriver);
	}
	
	
	public SignUpPage clickSignUpButton() {
		WebElement signUpButton = webDriver.findElement(By.id("SignUp"));
		signUpButton.click();
		return new SignUpPage(webDriver);
	}
	
	
	
	
}

