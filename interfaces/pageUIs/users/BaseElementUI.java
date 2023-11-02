package pageUIs.users;

public class BaseElementUI {
	public static final String HOME_PAGE_LOGO_IMAGE = "css=div.header-logo img";
	public static final String USER_LOGOUT_LINK = "xpath=//a[@class='ico-logout']";
	public static final String ADMIN_LOGOUT_LINK = "xpath=//a[text()='Logout']";
	public static final String UPLOAD_FILE_TYPE = "css=input[type='file']";
	
	// 6 link trên header (trước/sau login)
	public static final String DYNAMIC_HEADER_LINK_BY_NAME = "xpath=//div[@class='header-links']//a[contains(string(),'%s')]";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_ERROR_MESSAGE_BY_ID = "id=%s-error";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "css=input[id='%s']";
}
