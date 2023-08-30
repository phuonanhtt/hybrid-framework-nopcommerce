package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.users.CustomerPageObject;
import pageObjects.users.HomePageObject;
import pageObjects.users.LoginPageObject;
import pageObjects.users.RegisterPageObject;

public class Level_15_Log extends BaseTest {
	private WebDriver driver;
	private String emailAddress = getEmailAddress();
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	
	// Thuộc side bar nên gọi các hàm tron side bar ra được
	private CustomerPageObject customerPage;
	
	@Parameters({"browser","userUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String userUrl) {		
		driver = getBrowserDriver(browserName, userUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
		
	}

	@Test
	public void User_01_Register_Validate() {	
		// Verify Register link hiển thị
		log.info("Register - Step 01: Verify register link is displayed");
		verifyTrue(homePage.isRegisterLinkDisplayed());
		
		log.info("Register - Step 02: Click to register link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Register - Step 03: Click to register button");
		registerPage.clickToRegisterButton();
		
		// verify message tại firstname textbox -> passed
		log.info("Register - Step 04: Verify error message at First name text box");
		verifyEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		
		// verify message tại lastname textbox -> failed
		log.info("Register - Step 05: Verify error message at Last name text box");
		verifyEquals(registerPage.getLastNameErrorMessage(), "Last name is required");
		
	}
	
//	@Test
	public void User_02_Login_Success() {	
		log.info("Login - Step 01: Click to homepage logo");
		homePage = registerPage.clickToHomePageLogo();
		
		log.info("Login - Step 02: Click to login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login - Step 03: Enter email, password to login");
		homePage = loginPage.loginAsUser(emailAddress, "12345678");
		
		log.info("Login - Step 04: Click to My Account link");
		customerPage = homePage.clickToMyAccountLink();
		
		log.info("Login - Step 05: Verify First name is correct");
		verifyEquals(customerPage.getFirstNameAttributeValue(), "John");
		
		log.info("Login - Step 05: Verify Last name is correct");
		verifyEquals(customerPage.getLastNameAttributeValue(), "Wick");
		
		log.info("Login - Step 05: Verify Email is correct");
		verifyEquals(customerPage.getEmailAttributeValue(), emailAddress);
	}
	
	@Test
	public void User_03_Register_Success() {	
		// Verify Register link hiển thị
		log.info("Register - Step 01: Verify register link is displayed");
		verifyFalse(homePage.isRegisterLinkDisplayed());
		
		log.info("Register - Step 02: Click to register link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Register - Step 06: Enter to First name text box");
		registerPage.enterToFirstNameTextbox("John1");
		
		log.info("Register - Step 07: Enter to Last name text box");
		registerPage.enterToLastNameTextbox("Wick1");
		
		log.info("Register - Step 08: Enter to 'Email' text box with value is " + emailAddress);
		registerPage.enterToEmailTextbox(emailAddress);
		
		log.info("Register - Step 09: Enter to Password text box");
		registerPage.enterToPasswordTextbox("12345678");
		
		log.info("Register - Step 10: Enter to Confirm password text box");
		registerPage.enterToConfirmPasswordTextbox("12345678");	
		
		
		log.info("Register - Step 11: Click to register button");
		registerPage.clickToRegisterButton();
		
		// verify success message -> failed
		log.info("Register - Step 12: Verify success message is displayed");
		verifyEquals(registerPage.getSuccessMessage(), "Your registration completed.");
	}
	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}
}
