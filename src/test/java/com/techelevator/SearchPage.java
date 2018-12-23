package com.techelevator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class SearchPage {

	private WebDriver webDriver;

	public SearchPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	
	public String findSearchForComicsButton() { 
		return webDriver.findElement(By.id("comicButtonSearch")).getText();
		
	}
	
	public void openSearchPage() {
		webDriver.get("http://localhost:8080/http://localhost:8080/capstone/search");
	}
	
}
