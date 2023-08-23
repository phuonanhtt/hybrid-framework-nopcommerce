package pageUIs.jquery;

public class HomePageUI {
	public static final String COLUMN_TEXTBOX_BY_NAME = "xpath=//div[text()='%s']/parent::div/following-sibling::input";
	public static final String PAGE_LINK_BY_NUMBER = "xpath=//a[@class='qgrd-pagination-page-link' and text()='%s']";
	public static final String PAGE_LINK_ACTIVE_BY_NUMBER	= "xpath=//a[@class='qgrd-pagination-page-link active' and text()='%s']";
	public static final String DYNAMIC_ROW_VALUES = "xpath=//td[@data-key='females' and text()='%s']/following-sibling::td[@data-key='country' and text()='%s']/following-sibling::td[@data-key='males' and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']";
	public static final String ROW_ACTION_BY_COUNTRY_NAME = "xpath=//td[@data-key='country' and text()='%s']/preceding-sibling::td[@class='qgrd-actions']/button[contains(@class,'%s')]";
	public static final String CLOSE_BUTTON_EDIT_POPUP_STRING = "css=button.qgrd-modal-dismiss";
	
	public static final String ALL_PAGE_LINK = "XPATH=//a[contains(@class,'qgrd-pagination-page-link')]";
	public static final String COLUMN_INDEX_BY_COLUMN_NAME = "XPATH=//div[text()='%s']/ancestor::th/preceding-sibling::th";
	public static final String ALL_VALUES_BY_COLUMN_NAME = "XPATH=//tr/td[%s]";
	
	public static final String DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME = "XPATH=//th[text()='%s']/preceding-sibling::th";
	public static final String DYNAMIC_TEXTBOX_BY_ROW_INDEX_AND_COLUMN_INDEX = "XPATH=//tr[%s]/td[%s]/input";
	public static final String DYNAMIC_DROPDOWN_BY_ROW_INDEX_AND_COLUMN_INDEX = "XPATH=//tr[%s]/td[%s]//select";
	public static final String DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_COLUMN_INDEX = "XPATH=//tr[%s]/td[%s]//input[@type='checkbox']";
	
	public static final String DYNAMIC_REGISTER_TEXTBOX = "xpath=//input[@title='%s']";
	public static final String REGISTER_BUTTON = "css=button[title='Register']";
	public static final String REGISTER_SUCCESS_MESSAGE = "css=li.success-msg span";
	public static final String ACCOUNT_NAME_TEXT = "XPATH=//h3[text()='Contact Information']/parent::div/following-sibling::div/p";
	public static final String ADMIN_DYNAMIC_TEXTBOX = "css=input#%s";
	public static final String ADMIN_LOGIN_BUTTON = "css=input[title='Login']";
	public static final String ADMIN_CLOSE_POPUP_LINK = "xpath=//div[@class='message-popup-head']/a[@title='close']";
	public static final String ADMIN_SEARCH_BY_NAME_TEXTBOX = "css=input#customerGrid_filter_name";
	public static final String ADMIN_SEARCH_BY_EMAIL_TEXTBOX = "css=input#customerGrid_filter_email";
	public static final String ADMIN_SEARCH_BUTTON = "css=button[title='Search']";
	
	public static final String ADMIN_COLUMN_INDEX_BY_COLUMN_NAME = "XPATH=//span[text()='%s']/ancestor::th/preceding-sibling::th";
	public static final String ADMIN_ALL_VALUES_BY_COLUMN_NAME = "XPATH=table[@id='customerGrid_table']//td[%s]";
	
}
