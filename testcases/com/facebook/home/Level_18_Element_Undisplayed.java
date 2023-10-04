package com.facebook.home;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.HomePageObject;
import pageObjects.facebook.PageGeneratorManager;

public class Level_18_Element_Undisplayed extends BaseTest {
	private WebDriver driver;
	HomePageObject homePage;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {		
		driver = getBrowserDriver(browserName, urlValue);
		
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Home_01_Element_Displayed() {	
		homePage.clickToCreateNewAccountButton();
		
		verifyTrue(homePage.isFirstNameTextboxDisplayed());
		verifyTrue(homePage.isSurNameTextboxDisplayed());
		verifyTrue(homePage.isEmailTextboxDisplayed());
		verifyTrue(homePage.isPasswordTextboxDisplayed());
		
		homePage.enterToEmailTextbox("autofc@gmail.com");
		
		log.info("Verify Confirm Email textbox is displayed");
		verifyTrue(homePage.isConfirmEmailTextboxDisplayed());
		
	}
	
	@Test
	public void Home_02_Element_Undisplayed_In_HTML() {	
		homePage.enterToEmailTextbox("");
		
		log.info("Verify Confirm Email textbox is not displayed");
		verifyFalse(homePage.isConfirmEmailTextboxDisplayed());
	}
	
	@Test
	public void Home_03_Element_Undisplayed_Not_In_HTML() {	
		
	}
	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}
}
