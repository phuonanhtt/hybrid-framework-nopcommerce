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
}
