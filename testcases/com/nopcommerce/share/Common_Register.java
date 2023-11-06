package com.nopcommerce.share;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.users.HomePageObject;
import pageObjects.users.RegisterPageObject;

public class Common_Register extends BaseTest {
	private WebDriver driver;
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	
	public static String firstName, lastName, password, emailAddress;
	
	@Parameters("browser")
	@BeforeTest
	public void beforeClass(String browserName) {		
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		emailAddress = getEmailAddress();
		password = "1234567";
		firstName = "Taylor";
		lastName = "Swift";
		
		registerPage = homePage.clickToRegisterLink();

		registerPage.enterToFirstNameTextbox(firstName);
		registerPage.enterToLastNameTextbox(lastName);
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);	
		
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
		
		quitBrowserDriver();
	}
}
