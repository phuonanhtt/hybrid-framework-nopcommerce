package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.users.CustomerPageObject;
import pageObjects.users.HomePageObject;
import pageObjects.users.LoginPageObject;
import pageObjects.users.RegisterPageObject;

public class Level_10_GLobal_Constants extends BaseTest {
	private WebDriver driver;
	private String emailAddress = getEmailAddress();
	
	private String userUrl = GlobalConstants.DEV_USER_URL;
	private String adminUrl = GlobalConstants.DEV_ADMIN_URL;
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private AdminLoginPageObject adminLoginPage;
	
	// Thuộc side bar nên gọi các hàm tron side bar ra được
	private CustomerPageObject customerPage;
	
	private AdminDashboardPageObject adminDashboardPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {		
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
	public void User_02_Switch_Url() {
		// Customer Page
		
		// Logout khỏi trang user
		homePage = customerPage.clickToUserLogoutLink(driver);
		
		// Chuyển qua trang Admin
		homePage.openUrl(driver, adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		// login 
		adminDashboardPage = adminLoginPage.loginAsAdmin(GlobalConstants.DEV_ADMIN_USERNAME, GlobalConstants.DEV_ADMIN_PASSWORD);
		Assert.assertTrue(adminDashboardPage.isPageLoadedSuccess(driver));
		
		// đki tài khoản ở trang user xong qua admin để verify lại
		// 1 user nào đó mua hàng thanh toán thành công => qua admin verify
		
		// logout khỏi trang admin
		adminLoginPage = adminDashboardPage.clickToAdminLogoutLink(driver);
		// qua trang user
		adminLoginPage.openUrl(driver, userUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
		// login vào
		loginPage = homePage.clickToLoginLink();
		loginPage.loginAsUser(emailAddress, "12345678");
	}

	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}
}
