package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.AddressesPageObject;
import pageObjects.users.CustomerPageObject;
import pageObjects.users.HomePageObject;
import pageObjects.users.LoginPageObject;
import pageObjects.users.RegisterPageObject;
import pageObjects.DownloadableProductPageObject;
import pageObjects.RewardPointPageObject;

public class Level_07_Switch_Multiple_Page extends BaseTest {
	private WebDriver driver;
	private String emailAddress = getEmailAddress();
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerPageObject customerPage;
	private DownloadableProductPageObject downloadableProductPage;
	private RewardPointPageObject rewardPointPage;
	private AddressesPageObject addressesPage;
	
	@Parameters({"browser","userUrl","adminUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String userUrl, String adminUrl) {		
		driver = getBrowserDriver(browserName, userUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
		
	}

	@Test
	public void User_01_Register() {		
		registerPage = homePage.clickToRegisterLink();

		registerPage.enterToFirstNameTextbox("John");
		registerPage.enterToLastNameTextbox("Wick");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("12345678");
		registerPage.enterToConfirmPasswordTextbox("12345678");	
		
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");

		homePage = registerPage.clickToHomePageLogo();
		
		loginPage = homePage.clickToLoginLink();
		
		homePage = loginPage.loginAsUser(emailAddress, "12345678");
		
		customerPage = homePage.clickToMyAccountLink();
		
		Assert.assertEquals(customerPage.getFirstNameAttributeValue(), "John");
		Assert.assertEquals(customerPage.getLastNameAttributeValue(), "Wick");
		Assert.assertEquals(customerPage.getEmailAttributeValue(), emailAddress);
	}
	
	@Test
	public void User_02_Switch_Page() {
//		// Customer info → Downloadable products
//		downloadableProductPage = customerPage.openDownloadableProductPage(driver);
//		downloadableProductPage.sleepInSecond(3);
//		// Downloadable products → Addresses
//		addressesPage = downloadableProductPage.openAddressesPage(driver);
//		addressesPage.sleepInSecond(3);
//		// Addresses → Reward points
//		rewardPointPage = addressesPage.openRewardPointPage(driver);
//		registerPage.sleepInSecond(3);
//		// Reward points → Customer info 
//		customerPage = rewardPointPage.openCustomerInfoPage(driver);
//		customerPage.sleepInSecond(3);
//		// Customer info → Addresses
//		addressesPage = customerPage.openAddressesPage(driver);
//		addressesPage.sleepInSecond(3);
//		// Addresses → Downloadable products
//		downloadableProductPage = addressesPage.openDownloadableProductPage(driver);
	}

	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}
}
