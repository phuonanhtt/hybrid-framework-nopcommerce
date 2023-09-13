package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.users.HomePageObject;
import pageObjects.users.RegisterPageObject;

public class Level_16_Extent_V4 extends BaseTest {
	private WebDriver driver;
	private String emailAddress = getEmailAddress();
	private String firstName, lastName, password;
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	
	
	@Parameters({"browser","userUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String userUrl) {		
		driver = getBrowserDriver(browserName, userUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		
		firstName = "John1";
		lastName = "Wick1";
		password = "123456";
	}

	@Test
	public void User_01_Register_Validate(Method method) {	
		
		Assert.assertFalse(homePage.isRegisterLinkDisplayed());
		
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		
		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required");
		
	}
	
	@Test
	public void User_02_Register_Success(Method method) {	
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.enterToFirstNameTextbox(firstName);
		
		registerPage.enterToLastNameTextbox(lastName);
		
		registerPage.enterToEmailTextbox(emailAddress);
		
		registerPage.enterToPasswordTextbox(password);
		
		registerPage.enterToConfirmPasswordTextbox(password);	
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
	}
	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}
}

