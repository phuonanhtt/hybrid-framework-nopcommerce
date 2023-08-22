package pageObjects.jquery;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import graphql.PublicApi;
import pageUIs.jquery.HomePageUI;

public class HomepageObject extends BasePage{
	private WebDriver driver;

	public HomepageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputToColumnTextboxByName(String columnName, String valueToSend) {
		waitForElementVisible(driver, HomePageUI.COLUMN_TEXTBOX_BY_NAME, columnName);
		sendkeyToElement(driver, HomePageUI.COLUMN_TEXTBOX_BY_NAME, valueToSend, columnName);
//		sendKeyBoardToElement(driver, HomePageUI.COLUMN_TEXTBOX_BY_NAME, Keys.ENTER);
	}
	
	public void clickToPageByNumber(String pageNumber) {
		waitForElementClickable(driver, HomePageUI.PAGE_LINK_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.PAGE_LINK_BY_NUMBER, pageNumber);
	}
	
	public boolean isPAgeActiveByNumber(String pageNumber) {
		waitForElementVisible(driver, HomePageUI.PAGE_LINK_ACTIVE_BY_NUMBER, pageNumber);
		return isElementDisplayed(driver, HomePageUI.PAGE_LINK_ACTIVE_BY_NUMBER, pageNumber);
	}
	
	public boolean isRowValuesDisplayed(String female, String country, String male, String total) {
		waitForElementVisible(driver, HomePageUI.DYNAMIC_ROW_VALUES, female, country, male, total);
		return isElementDisplayed(driver, HomePageUI.DYNAMIC_ROW_VALUES, female, country, male, total);
	}
	
	public void clickToRowActionByCountryName(String country, String rowAction) {
		waitForElementClickable(driver, HomePageUI.ROW_ACTION_BY_COUNTRY_NAME, country, rowAction);
		clickToElement(driver, HomePageUI.ROW_ACTION_BY_COUNTRY_NAME, country, rowAction);
	}
	
	public void closeEditPopup() {
		waitForElementVisible(driver, HomePageUI.CLOSE_BUTTON_EDIT_POPUP_STRING);
		clickToElement(driver, HomePageUI.CLOSE_BUTTON_EDIT_POPUP_STRING);
	}

	public void getAllPageValuesByColumnName(String columnName) {
		
		List<String> allValues = new ArrayList<String>();
		
		// b1: Lấy ra tất cả các page 
		List<WebElement> allPageLinks = getLisElement(driver, HomePageUI.ALL_PAGE_LINK);
		
		int columnIndex =  getListElementSize(driver, HomePageUI.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		// b2: duyệt qua từng page
		for (WebElement pageLink : allPageLinks) {
			pageLink.click();
			sleepInSecond(2);
			
			// b3: lấy ra tất cả các giá trị của 1 cột trong page đó -> lưu nó vào List/ Set...
			List<WebElement> allRowValues = getLisElement(driver, HomePageUI.ALL_VALUES_BY_COLUMN_NAME, String.valueOf(columnIndex));
			
			for (WebElement rowValue : allRowValues) {
				allValues.add(rowValue.getText());
			}
			
			// im ra hết tất cả giá trị của 1 cột trong all page
			for (String value : allValues) {
				System.out.println(value);
			}
			
		}
		
	}
}
