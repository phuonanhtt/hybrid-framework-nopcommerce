package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.users.HomePageObject;
import pageObjects.users.RegisterPageObject;
import reportConfig.ExtentManager;

public class Level_16_Extent_V2 extends BaseTest {
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
		ExtentManager.startTest(method.getName(), "User_01_Register_Validate");
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 01: Verify register link is displayed");
		Assert.assertFalse(homePage.isRegisterLinkDisplayed());
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 02: Click to register link");
		registerPage = homePage.clickToRegisterLink();
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 03: Click to register button");
		registerPage.clickToRegisterButton();
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 04: Verify error message at First name text box");
		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 05: Verify error message at Last name text box");
		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required");
		
	}
	
	@Test
	public void User_02_Register_Success(Method method) {	
		ExtentManager.startTest(method.getName(), "User_02_Register_Success");
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 02: Click to register link");
		registerPage = homePage.clickToRegisterLink();
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 06: Enter to First name text box");
		registerPage.enterToFirstNameTextbox(firstName);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 07: Enter to Last name text box");
		registerPage.enterToLastNameTextbox(lastName);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 08: Enter to 'Email' text box with value is " + emailAddress);
		registerPage.enterToEmailTextbox(emailAddress);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 09: Enter to Password text box");
		registerPage.enterToPasswordTextbox(password);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 10: Enter to Confirm password text box");
		registerPage.enterToConfirmPasswordTextbox(password);	
		
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 11: Click to register button");
		registerPage.clickToRegisterButton();
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 12: Verify success message is displayed");
		Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
	}
	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}
}
