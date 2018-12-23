package com.techelevator;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginWithPageObjectTest {

	private static WebDriver webDriver;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	
	
	@BeforeClass
	public static void openWebBrowserForTesting() {
		String homeDir = System.getProperty("user.home");
		System.setProperty("webdriver.chrome.driver", homeDir + "/dev-tools/chromedriver/chromedriver_new");
		webDriver = new ChromeDriver();
	}

	@Before
	public void openHomePage() {
		webDriver.get("http://localhost:8080/capstone/homepage");
		homePage = new HomePage(webDriver);
	}

	@AfterClass
	public static void closeWebBrowser() {
		webDriver.close();
	}
	
	@Test
	public void login_test() {
		
		 					homePage.clickThisLoginLink()
							.enterUserName("Alterodd")
							.enterPassword("Testtesttesttest")
							.clickSubmit();
							
	assertEquals("Log Out", homePage.findLogoutLink());						
	assertEquals("Alterodd", homePage.findCurrentUser());
	}
}
