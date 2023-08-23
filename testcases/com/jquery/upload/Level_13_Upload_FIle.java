package com.jquery.upload;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jquery.PageGeneratorManager;
import pageObjects.jquery.UploadPageObject;

public class Level_13_Upload_FIle extends BaseTest{
	private WebDriver driver;
	UploadPageObject uploadPage;
	String starImg = "star.jpg";
	String spaceImg = "space.jpg";
	String treeImg = "tree.jpg";
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {		
		driver = getBrowserDriver(browserName, url);
		uploadPage = PageGeneratorManager.getUploadpage(driver);
	}
	
	@Test
	public void TC_01_Upload_Single_File() {
		uploadPage.uploadMultipleFiles(driver, starImg);
		uploadPage.sleepInSecond(2);
		
		uploadPage.uploadMultipleFiles(driver, spaceImg);
		uploadPage.sleepInSecond(2);
		
		uploadPage.uploadMultipleFiles(driver, treeImg);
		uploadPage.sleepInSecond(2);
		
		Assert.assertTrue(uploadPage.isFileLoadingSuccess(starImg));
		Assert.assertTrue(uploadPage.isFileLoadingSuccess(spaceImg));
		Assert.assertTrue(uploadPage.isFileLoadingSuccess(treeImg));
		
		uploadPage.clickStartButtonEachfile();
		
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(starImg));
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(spaceImg));
		Assert.assertTrue(uploadPage.isFileUploadedSuccess(treeImg));
	}
	
	@Test
	public void TC_02_Upload_Multiple_File() {
		
	}
	
	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}
}
