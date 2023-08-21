package com.jquery.table;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jquery.HomepageObject;
import pageObjects.jquery.PageGeneratorManager;

public class Level_12_Handle_DataTable extends BaseTest {
	private WebDriver driver;
	HomepageObject homePage;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {		
		driver = getBrowserDriver(browserName, url);
		
		homePage = PageGeneratorManager.getHomepage(driver);
	}

//	@Test
	public void TC_01_Search() {	
		// Search dữ liệu trong 1 table (trên header)
		homePage.inputToColumnTextboxByName("Females", "283821");
		homePage.sleepInSecond(2);
		
		homePage.inputToColumnTextboxByName("Country", "Antigua and Barbuda");
		homePage.sleepInSecond(2);
		
		homePage.inputToColumnTextboxByName("Males", "802948");
		homePage.sleepInSecond(2);
		
		homePage.inputToColumnTextboxByName("Total", "791312");
		homePage.sleepInSecond(2);
	}
	
//	@Test
	public void TC_02_Paging() {
		// Click to any page
		homePage.clickToPageByNumber("10");
		homePage.sleepInSecond(2);
		Assert.assertTrue(homePage.isPAgeActiveByNumber("10"));
		
		homePage.clickToPageByNumber("4");
		homePage.sleepInSecond(2);
		Assert.assertTrue(homePage.isPAgeActiveByNumber("4"));
		
		homePage.clickToPageByNumber("20");
		homePage.sleepInSecond(2);
		Assert.assertTrue(homePage.isPAgeActiveByNumber("16"));
	}
	
//	@Test
	public void TC_03_Displayed() {
		// Verify for any row
		Assert.assertTrue(homePage.isRowValuesDisplayed("276880", "Angola", "276472", "553353"));
		
		Assert.assertTrue(homePage.isRowValuesDisplayed("338282", "Argentina", "349238", "687522"));
	}
	
//	@Test
	public void TC_04_Icon_Button_Checkbox() {
		// Click vào icon/ button/ checkbox của any row
		// Tìm được 1 key là duy nhất của row đó so với row khác
		homePage.clickToRowActionByCountryName("Aruba", "remove");
		homePage.clickToRowActionByCountryName("AFRICA", "remove");
		homePage.clickToRowActionByCountryName("Antigua and Barbuda", "remove");
		homePage.clickToRowActionByCountryName("Armenia", "remove");
		homePage.refreshCurrentPage(driver);
		
		homePage.clickToRowActionByCountryName("Argentina", "edit");
		homePage.sleepInSecond(1);
		homePage.closeEditPopup();
		
		homePage.clickToRowActionByCountryName("Armenia", "edit");
		homePage.refreshCurrentPage(driver);
		
		homePage.clickToRowActionByCountryName("Antigua and Barbuda", "edit");
		homePage.refreshCurrentPage(driver);
	}

	@Test
	public void TC_05_Get_All_Column_Values() {
		homePage.getAllPageValuesByColumnName("Country");
		// b1: Lấy ra tất cả các page 
		// b2: duyệt qua từng page
		// b3: lấy ra tất cả các giá trị của 1 cột trong page đó -> lưu nó vào List/ Set...
		// b4: duyệt hết các page còn lại
		
	}
	
	@Test
	public void TC_06_Action_By_Index() {
		
	}
	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}
}
