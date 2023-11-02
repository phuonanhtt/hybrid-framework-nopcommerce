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

public class Level_19_Pattern_Object extends BaseTest {
	private WebDriver driver;
	private String emailAddress = getEmailAddress();
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerPageObject customerPage;
	
	@Parameters({"browser","userUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String userUrl) {		
		driver = getBrowserDriver(browserName, userUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
		
	}

	@Test
	public void Register_01_Empty_Data() {
		homePage.clickToHeaderLinkByName("Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		
		registerPage.clickToButtonByText("Register");
		
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("FirstName"), "First name is required.");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("LastName"), "Last name is required.");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("Email"), "Email is required.");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("Password"), "Password is required.");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("ConfirmPassword"), "Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		homePage = registerPage.clickToHomePageLogo();
		
		homePage.clickToHeaderLinkByName("Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		
		registerPage.enterToTextboxByID("FirstName", "John");
		registerPage.enterToTextboxByID("LastName", "Wick");
		registerPage.enterToTextboxByID("Email", "abc@123@gmail.com");
		registerPage.enterToTextboxByID("Password", "12345678");
		registerPage.enterToTextboxByID("ConfirmPassword", "12345678");
		
		registerPage.clickToButtonByText("Register");

		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("Email"), "Wrong email");
	}

	@Test
	public void Register_03_Invalid_Password() {
		homePage = registerPage.clickToHomePageLogo();
		
		homePage.clickToHeaderLinkByName("Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		
		registerPage.enterToTextboxByID("FirstName", "John");
		registerPage.enterToTextboxByID("LastName", "Wick");
		registerPage.enterToTextboxByID("Email", getEmailAddress());
		registerPage.enterToTextboxByID("Password", "123");
		registerPage.enterToTextboxByID("ConfirmPassword", "123");	
		
		registerPage.clickToButtonByText("Register");

		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("Password"),  "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_04_Incorect_Confirm_Password() {
		homePage = registerPage.clickToHomePageLogo();
		
		homePage.clickToHeaderLinkByName("Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		registerPage.enterToTextboxByID("FirstName", "John");
		registerPage.enterToTextboxByID("LastName", "Wick");
		registerPage.enterToTextboxByID("Email", getEmailAddress());
		registerPage.enterToTextboxByID("Password", "12345678");
		registerPage.enterToTextboxByID("ConfirmPassword", "12345675");

		registerPage.clickToButtonByText("Register");

		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("ConfirmPassword"), "The password and confirmation password do not match.");
	}

	@Test
	public void Register_05_Success() {
		homePage = registerPage.clickToHomePageLogo();
		
		homePage.clickToHeaderLinkByName("Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		registerPage.enterToTextboxByID("FirstName", "John");
		registerPage.enterToTextboxByID("LastName", "Wick");
		registerPage.enterToTextboxByID("Email", emailAddress);
		registerPage.enterToTextboxByID("Password", "12345678");
		registerPage.enterToTextboxByID("ConfirmPassword", "12345678");
		
		registerPage.clickToButtonByText("Register");

		Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
	}

	@Test
	public void Register_06_Verify_Login() {
		homePage = registerPage.clickToHomePageLogo();
		
		homePage.clickToHeaderLinkByName("Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		loginPage.enterToTextboxByID("Email", emailAddress);
		loginPage.enterToTextboxByID("Password", "12345678");
		
		loginPage.clickToButtonByText("Log in");
		homePage = PageGeneratorManager.getHomePage(driver);
		
		homePage.clickToHeaderLinkByName("My account");
		customerPage = PageGeneratorManager.getCustomerPage(driver);
		
		Assert.assertEquals(customerPage.getTextboxAttributeByID("FirstName"), "John");
		Assert.assertEquals(customerPage.getTextboxAttributeByID("LastName"), "Wick");
		Assert.assertEquals(customerPage.getTextboxAttributeByID("Email"), emailAddress);
		
		
	}
	

	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}
}
