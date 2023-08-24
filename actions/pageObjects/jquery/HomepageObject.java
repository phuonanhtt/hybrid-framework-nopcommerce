package pageObjects.jquery;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
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
	// Get ra từ UI
	public List<String> getAllPageValuesByColumnName(String columnName) {
		
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
		}
		return allValues;
	}
	
	// Get ra từ DB
	public List<String> getAllPageValuesByColumnNameInDB(String columnName) {
		
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
		}
		// Sort ASC. DESC
		return allValues;
	}

	public void enterToTextboxByColumnNameAndRowIndex(String columnName, String rowIndex, String valueToSend) {
		int columnIndex =  getListElementSize(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		
		waitForElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
		sendkeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_INDEX_AND_COLUMN_INDEX, valueToSend, rowIndex, String.valueOf(columnIndex));
	}

	public void selectDropdownByColumnNameAndRowIndex(String columnName, String rowIndex, String valueToSelect) {
		int columnIndex =  getListElementSize(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		
		waitForElementClickable(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
		selectDropdown(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_INDEX_AND_COLUMN_INDEX, valueToSelect, rowIndex, String.valueOf(columnIndex));
	}

	public void clickToCheckboxByColumnNameAndRowIndex(String columnName, String rowIndex) {
		int columnIndex =  getListElementSize(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		
		waitForElementClickable(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
		checkToCheckboxRadio(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_COLUMN_INDEX, rowIndex, String.valueOf(columnIndex));
	}
	
	// Guru
	public void createAnAccount(String firstName, String lastName, String emailAddress, String password, String confirmPassword) {
		waitForElementVisible(driver, HomePageUI.DYNAMIC_REGISTER_TEXTBOX, "First Name");
		sendkeyToElement(driver, HomePageUI.DYNAMIC_REGISTER_TEXTBOX, firstName, "First Name");
		
		waitForElementVisible(driver, HomePageUI.DYNAMIC_REGISTER_TEXTBOX, "Last Name");
		sendkeyToElement(driver, HomePageUI.DYNAMIC_REGISTER_TEXTBOX, lastName, "Last Name");
		
		waitForElementVisible(driver, HomePageUI.DYNAMIC_REGISTER_TEXTBOX, "Email Address");
		sendkeyToElement(driver, HomePageUI.DYNAMIC_REGISTER_TEXTBOX, emailAddress, "Email Address");
		
		waitForElementVisible(driver, HomePageUI.DYNAMIC_REGISTER_TEXTBOX, "Password");
		sendkeyToElement(driver, HomePageUI.DYNAMIC_REGISTER_TEXTBOX, password, "Password");
		
		waitForElementVisible(driver, HomePageUI.DYNAMIC_REGISTER_TEXTBOX, "Confirm Password");
		sendkeyToElement(driver, HomePageUI.DYNAMIC_REGISTER_TEXTBOX, confirmPassword, "Confirm Password");
		
		waitForElementClickable(driver, HomePageUI.REGISTER_BUTTON);
		clickToElement(driver, HomePageUI.REGISTER_BUTTON);
	}
	
	public String getSuccessMessage() {
		waitForElementVisible(driver, HomePageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, HomePageUI.REGISTER_SUCCESS_MESSAGE);
	}
	
	public String getAccountName() {
		waitForElementVisible(driver, HomePageUI.ACCOUNT_NAME_TEXT);
		return getElementText(driver, HomePageUI.ACCOUNT_NAME_TEXT);
	}
	
	public void loginToAdminSite(String userName, String password) {
		waitForElementVisible(driver, HomePageUI.ADMIN_DYNAMIC_TEXTBOX, "username");
		sendkeyToElement(driver, HomePageUI.ADMIN_DYNAMIC_TEXTBOX, userName, "username");
		
		waitForElementVisible(driver, HomePageUI.ADMIN_DYNAMIC_TEXTBOX, "login");
		sendkeyToElement(driver, HomePageUI.ADMIN_DYNAMIC_TEXTBOX, password, "login");
		
		waitForElementClickable(driver, HomePageUI.ADMIN_LOGIN_BUTTON);
		clickToElement(driver, HomePageUI.ADMIN_LOGIN_BUTTON);
	}
	
	public void closePopup() {
		waitForElementVisible(driver, HomePageUI.ADMIN_CLOSE_POPUP_LINK);
		clickToElement(driver, HomePageUI.ADMIN_CLOSE_POPUP_LINK);
	}
	
	public void enterToNameSearchTextbox(String userName) {
		waitForElementVisible(driver, HomePageUI.ADMIN_SEARCH_BY_NAME_TEXTBOX);
		sendkeyToElement(driver, HomePageUI.ADMIN_SEARCH_BY_NAME_TEXTBOX, userName);
	}
	
	public void enterToEmailSearchTextbox(String emailAddress) {
		waitForElementVisible(driver, HomePageUI.ADMIN_SEARCH_BY_EMAIL_TEXTBOX);
		sendkeyToElement(driver, HomePageUI.ADMIN_SEARCH_BY_EMAIL_TEXTBOX, emailAddress);
	}
	
	public void searchByNameAndEmail(String userName, String emailAddress) {
		enterToNameSearchTextbox(userName);
		enterToEmailSearchTextbox(emailAddress);
		
		waitForElementClickable(driver, HomePageUI.ADMIN_SEARCH_BUTTON);
		clickToElement(driver, HomePageUI.ADMIN_SEARCH_BUTTON);
	}
	
	public String getValuesDisplayedByColumnName(String columnName) {
		int columnIndex =  getListElementSize(driver, HomePageUI.ADMIN_COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
		
		waitForElementVisible(driver, HomePageUI.ADMIN_ALL_VALUES_BY_COLUMN_NAME, String.valueOf(columnIndex));
		System.out.println(getElementText(driver, HomePageUI.ADMIN_ALL_VALUES_BY_COLUMN_NAME, String.valueOf(columnIndex)));
		return getElementText(driver, HomePageUI.ADMIN_ALL_VALUES_BY_COLUMN_NAME, String.valueOf(columnIndex));
	}
	
	public boolean waitPageLoadingSuccess() {
		return waitForElementInvisible(driver, HomePageUI.LOADING_ICON_SEARCH);
	}
}
