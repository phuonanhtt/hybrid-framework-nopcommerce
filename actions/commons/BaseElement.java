package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.users.HomePageObject;
import pageUIs.users.BaseElementUI;

public class BaseElement extends BasePage {
	WebDriver driver;
	
	protected BaseElement(WebDriver driver) {
		this.driver = driver;
	}
	
	// bất kỳ hàm nào cũng nhìn thấy và gọi ra được 
	public HomePageObject clickToHomePageLogo() {
		waitForElementVisible(driver, BaseElementUI.HOME_PAGE_LOGO_IMAGE);
		clickToElement(driver, BaseElementUI.HOME_PAGE_LOGO_IMAGE);
		return PageGeneratorManager.getHomePage(driver);
		
	}
	
	// bất kỳ hàm nào cũng nhìn thấy và gọi ra được 
	public void clickToHeaderLinkByName(String pageName) {
		waitForElementVisible(driver, BaseElementUI.DYNAMIC_HEADER_LINK_BY_NAME, pageName);
		clickToElement(driver, BaseElementUI.DYNAMIC_HEADER_LINK_BY_NAME, pageName);
	}
	
	// thao tác với bất kì 1 button ở page nào đó
	public void clickToButtonByText(String buttonText) {
		waitForElementClickable(driver, BaseElementUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, BaseElementUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		
	}
	
	// Get ra error message của textbox
	public String getTextboxErrorMessageByID(String errorMessageID) {
		waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_ERROR_MESSAGE_BY_ID, errorMessageID);
		return getElementText(driver, BaseElementUI.DYNAMIC_TEXTBOX_ERROR_MESSAGE_BY_ID, errorMessageID);
	}
	
	public void enterToTextboxByID(String textboxID, String valueToSendKey) {
		waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_ID, valueToSendKey, textboxID);
		
	}
	
	// get ra attribute của textbox tại bất kỳ page nào
	public String getTextboxAttributeByID(String textboxID) {
		waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
	}


}
