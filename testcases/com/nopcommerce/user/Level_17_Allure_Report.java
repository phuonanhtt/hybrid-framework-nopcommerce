package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.users.HomePageObject;
import pageObjects.users.RegisterPageObject;

@Epic("Account")
@Feature("Create Account")
public class Level_17_Allure_Report extends BaseTest {
	private WebDriver driver;
	private String emailAddress = getEmailAddress();
	private String firstName, lastName, password;
	private String browserName;
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	
	
	@Parameters({"browser","userUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String userUrl) {	
		this.browserName = browserName;
		driver = getBrowserDriver(browserName, userUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		
		firstName = "John1";
		lastName = "Wick1";
		password = "123456";
	}
	
	@Description("User 01 - Validate register form")
	@Story("register")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void User_01_Register_Validate() {	
		Assert.assertFalse(homePage.isRegisterLinkDisplayed());
		
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		
		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required");
		
	}
	
	@Description("User 02 - Register success")
	@Story("register")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void User_02_Register_Success() {	
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