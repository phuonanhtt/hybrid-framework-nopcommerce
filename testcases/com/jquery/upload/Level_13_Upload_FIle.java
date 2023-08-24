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
	
	String[] fileNames = {starImg, spaceImg, treeImg};
	
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
		uploadPage.refreshCurrentPage(driver);
		
		uploadPage.uploadMultipleFiles(driver, fileNames);
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
	public void TC_03_Upload_Gofile() {
		uploadPage.openUrl(driver, "https://gofile.io/uploadFiles");
		
		// wait cho tất cả icon loading biến mất
		Assert.assertTrue(uploadPage.isAllLoadingIconDisappear());
		
		// upload 3 file
		uploadPage.uploadMultipleFiles(driver, fileNames);
		// wait cho tất cả upload file biến mất
		Assert.assertTrue(uploadPage.isAllUploadProgressBarDisappear());
		
		// wait for text + verify
		Assert.assertEquals(uploadPage.getUploadedSuccessMsg(),  "Your files have been successfully uploaded");
		
		// click vào link file upload
		uploadPage.clickToUploadedLink();
		
		// wait cho table chứa tất cả các file upload được visible
		Assert.assertTrue(uploadPage.isContenTableDisplayed());
		
		// verify download button display	
		Assert.assertTrue(uploadPage.isDownloadButtonDisplayed(starImg));
		Assert.assertTrue(uploadPage.isDownloadButtonDisplayed(spaceImg));
		Assert.assertTrue(uploadPage.isDownloadButtonDisplayed(treeImg));
	}
	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}
}
