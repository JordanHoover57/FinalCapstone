package com.techelevator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;
public class ComicBooksWithPageObjectTest {

	
	private static WebDriver webDriver;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchPage searchPage;
	private SignUpPage signUpPage;
	
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
	public void add_comics_button_test() {
		searchPage = homePage.clickLoginLink()
							.enterUserName("Alterodd")
							.enterPassword("Testtesttesttest")
							.clickSubmit()
							.clickAddComicLink();
		
		assertEquals("Search For Comics!", searchPage.findSearchForComicsButton());				
		
	}	
	
}
