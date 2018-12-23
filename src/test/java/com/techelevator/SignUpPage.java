package com.techelevator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class SignUpPage {
	private WebDriver webDriver;

	public SignUpPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	
	
public String findCreateUserButton() {
	return webDriver.findElement(By.className("btn-danger")).getText();
}
	
	

}
