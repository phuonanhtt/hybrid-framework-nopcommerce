package com.nopcommerce.user;

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

public class Level_20_Close_Browser extends BaseTest {
	private WebDriver driver;
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {		
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		homePage.clickToHeaderLinkByName("Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		
		registerPage.clickToButtonByText("Register");
		
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("FirstName"), "First name is required.....");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("LastName"), "Last name is required.");
	}

	@Test
	public void Register_01_Empty_Data() {

	}

	@Test
	public void Register_02_Invalid_Email() {
	}

	@Test
	public void Register_03_Invalid_Password() {
		
	}

	@Test
	public void Register_04_Incorect_Confirm_Password() {
	}

	@Test
	public void Register_05_Success() {
	}

	@Test
	public void Register_06_Verify_Login() {
		
		
	}
	

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		quitBrowserDriver();
	}
}
