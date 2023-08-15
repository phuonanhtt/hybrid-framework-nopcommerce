package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_06_Page_Generator_02 extends BaseTest {
	private WebDriver driver;
	private String emailAddress = getEmailAddress();
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerPageObject customerPage;
	
	@Parameters({"browser","userUrl","adminUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String userUrl, String adminUrl) {		
		// User
		driver = getBrowserDriver(browserName, userUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
		
//		//Admin
//		driver = getBrowserDriver(browserName, adminUrl);
//		adminPage = PageGeneratorManager.getAdminLoginPage(driver);
	}

	@Test
	public void Register_01_Empty_Data() {
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		homePage = registerPage.clickToHomePageLogo();
		
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.enterToFirstNameTextbox("John");
		registerPage.enterToLastNameTextbox("Wick");
		registerPage.enterToEmailTextbox("abc@123@gmail.com");
		registerPage.enterToPasswordTextbox("12345678");
		registerPage.enterToConfirmPasswordTextbox("12345678");
		
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");
	}

	@Test
	public void Register_03_Invalid_Password() {
		homePage = registerPage.clickToHomePageLogo();
		
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.enterToFirstNameTextbox("John");
		registerPage.enterToLastNameTextbox("Wick");
		registerPage.enterToEmailTextbox(getEmailAddress());
		registerPage.enterToPasswordTextbox("123");
		registerPage.enterToConfirmPasswordTextbox("123");		
		
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_04_Incorect_Confirm_Password() {
		homePage = registerPage.clickToHomePageLogo();
		
		registerPage = homePage.clickToRegisterLink();

		registerPage.enterToFirstNameTextbox("John");
		registerPage.enterToLastNameTextbox("Wick");
		registerPage.enterToEmailTextbox(getEmailAddress());
		registerPage.enterToPasswordTextbox("12345678");
		registerPage.enterToConfirmPasswordTextbox("12378945");	

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "The password and confirmation password do not match.");
	}

	@Test
	public void Register_05_Success() {
		homePage = registerPage.clickToHomePageLogo();
		
		registerPage = homePage.clickToRegisterLink();

		registerPage.enterToFirstNameTextbox("John");
		registerPage.enterToLastNameTextbox("Wick");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("12345678");
		registerPage.enterToConfirmPasswordTextbox("12345678");	
		
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
	}

	@Test
	public void Register_06_Verify_Login() {
		homePage = registerPage.clickToHomePageLogo();
		
		loginPage = homePage.clickToLoginLink();
		
		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextbox("12345678");
		homePage = loginPage.clickToLoginButton();
		
		customerPage = homePage.clickToMyAccountLink();
		
		Assert.assertEquals(customerPage.getFirstNameAttributeValue(), "John");
		Assert.assertEquals(customerPage.getLastNameAttributeValue(), "Wick");
		Assert.assertEquals(customerPage.getEmailAttributeValue(), emailAddress);
	}
	

	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}
}
