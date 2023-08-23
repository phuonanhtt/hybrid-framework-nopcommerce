package com.jquery.table;

import java.util.ArrayList;
import java.util.List;

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
	List<String> allValueUI = new ArrayList<String>();
	List<String> allValueDB = new ArrayList<String>();
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		//https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/
		driver = getBrowserDriver(browserName, url);
		
		homePage = PageGeneratorManager.getHomepage(driver);
	}

	@Test
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
	
	@Test
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
	
	@Test
	public void TC_03_Displayed() {
		// Verify for any row
		Assert.assertTrue(homePage.isRowValuesDisplayed("276880", "Angola", "276472", "553353"));
		
		Assert.assertTrue(homePage.isRowValuesDisplayed("338282", "Argentina", "349238", "687522"));
	}
	
	@Test
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
		// UI: Rest Assured/ Karate
		allValueUI = homePage.getAllPageValuesByColumnName("Country");
		
//		// DB: JTDS
//		allValueDB = homePage.getAllPageValuesByColumnNameInDB("Country");
		
//		Assert.assertEquals(allValueUI, allValueDB);
		
		homePage.getAllPageValuesByColumnName("Total");
		
		
	}
	
	@Test
	public void TC_06_Action_By_Index() {
		homePage.openUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
		
		// Nhập vào textbox tại cột Contact Person dòng thứ 2
		homePage.enterToTextboxByColumnNameAndRowIndex("Contact Person", "2", "Messi");
		homePage.enterToTextboxByColumnNameAndRowIndex("Company", "1", "Barcelona");
		
		// Select dữ liệu tại cột Country dòng thứ 3
		homePage.selectDropdownByColumnNameAndRowIndex("Country", "3", "United Kingdom");
		homePage.selectDropdownByColumnNameAndRowIndex("Country", "1", "Japan");
		
		// Click vào checkbox tại cột NPO dòng thứ 1
		homePage.clickToCheckboxByColumnNameAndRowIndex("NPO?", "2");
		homePage.clickToCheckboxByColumnNameAndRowIndex("NPO?", "3");
		
	}
	
	@Test
	public void TC_07_Guru() {
		homePage.openUrl(driver, "http://live.techpanda.org/index.php/customer/account/create/");
		
		// đăng ký
		String emailAddress = getEmailAddress();
		homePage.createAnAccount("autotest", "fc", emailAddress, "123456", "123456");
		homePage.sleepInSecond(2);
		Assert.assertEquals(homePage.getSuccessMessage(), "Thank you for registering with Main Website Store.");
		
		// qua trang admin search
		homePage.openUrl(driver, "http://live.techpanda.org/index.php/backendlogin/index/");
		homePage.loginToAdminSite("user01", "guru99com");
		homePage.sleepInSecond(2);
		
		// kiểm tra giá trị 
		homePage.closePopup();
		homePage.searchByNameAndEmail("autotest fc", emailAddress);
		homePage.waitPageLoadingSuccess();
		
		Assert.assertTrue(homePage.getValuesDisplayedByColumnName("Name").contains("autotest fc"));
		Assert.assertEquals(homePage.getValuesDisplayedByColumnName("Email"), emailAddress);
		
	}
	@AfterClass
	public void afterClass() {
		quitBrowserDriver();
	}
}
