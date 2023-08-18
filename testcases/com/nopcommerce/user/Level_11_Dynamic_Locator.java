package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.users.AddressesPageObject;
import pageObjects.users.CustomerPageObject;
import pageObjects.users.DownloadableProductPageObject;
import pageObjects.users.HomePageObject;
import pageObjects.users.LoginPageObject;
import pageObjects.users.RegisterPageObject;
import pageObjects.users.RewardPointPageObject;

public class Level_11_Dynamic_Locator extends BaseTest {
	private WebDriver driver;
	private String emailAddress = getEmailAddress();
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	
	// Thuộc side bar nên gọi các hàm tron side bar ra được
	private CustomerPageObject customerPage;
	private AddressesPageObject addressesPage;
	private DownloadableProductPageObject downloadableProductPage;
	private RewardPointPageObject rewardPointPage;
	
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
		// Customer info → Downloadable products
		downloadableProductPage = (DownloadableProductPageObject) customerPage.openDynamicSideBarPage("Downloadable products");
		downloadableProductPage.sleepInSecond(3);
		// Downloadable products → Addresses
		addressesPage = (AddressesPageObject) downloadableProductPage.openDynamicSideBarPage("Addresses");
		addressesPage.sleepInSecond(3);
		// Addresses → Reward points
		rewardPointPage = (RewardPointPageObject) addressesPage.openDynamicSideBarPage("Reward points");
		rewardPointPage.sleepInSecond(3);
		// Reward points → Customer info 
		customerPage = (CustomerPageObject) rewardPointPage.openDynamicSideBarPage("Customer info");
		customerPage.sleepInSecond(3);
		// Customer info → Addresses
		addressesPage = (AddressesPageObject) customerPage.openDynamicSideBarPage("Addresses");
		addressesPage.sleepInSecond(3);
		// Addresses → Downloadable products
		downloadableProductPage = (DownloadableProductPageObject) addressesPage.openDynamicSideBarPage("Downloadable products");
	}
	
	@Test
	public void User_03_Page_Navigation() {
		// Customer info → Downloadable products
		customerPage.openDynamicSideBarPageByName("Downloadable products");
		downloadableProductPage = PageGeneratorManager.getDownloadableProductPage(driver);
		downloadableProductPage.sleepInSecond(3);
		// Downloadable products → Addresses
		downloadableProductPage.openDynamicSideBarPageByName("Addresses");
		addressesPage = PageGeneratorManager.getAddressesPage(driver);
		addressesPage.sleepInSecond(3);
		// Addresses → Reward points
		addressesPage.openDynamicSideBarPageByName("Reward points");
		rewardPointPage = PageGeneratorManager.getRewardPointPage(driver);
		rewardPointPage.sleepInSecond(3);
		// Reward points → Customer info 
		rewardPointPage.openDynamicSideBarPageByName("Customer info");
		customerPage = PageGeneratorManager.getCustomerPage(driver);
		customerPage.sleepInSecond(3);
		// Customer info → Addresses
		customerPage.openDynamicSideBarPageByName("Addresses");
		addressesPage = PageGeneratorManager.getAddressesPage(driver);
		addressesPage.sleepInSecond(3);
		// Addresses → Downloadable products
		addressesPage.openDynamicSideBarPageByName("Downloadable products");
		
	}
	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}
}
