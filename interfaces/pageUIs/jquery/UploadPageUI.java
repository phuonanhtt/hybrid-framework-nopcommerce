package pageUIs.jquery;

public class UploadPageUI {
	public static final String FILE_LOADED_BY_NAME = "xpath=//p[@class='name' and text()='%s']";
	public static final String MULTIPLE_START_BUTTON = "css=table button.start";
	public static final String FILE_UPLOADED_BY_NAME = "xpath=//p[@class='name']/a[text()='%s']";
	
	public static final String SPINNER_LOADING_ICON = "CSS=div.spinner-border";
	public static final String UPLOADING_PROGRESS_BAR_ICON = "CSS=div.progress-bar";
	public static final String UPLOADED_SUCCESS_MESSAGE = "CSS=div.mainUploadSuccess div.border-success";
	public static final String UPLOADED_LINK = "CSS=div.mainUploadSuccessLink a.ajaxLink";
	public static final String CONTENT_TABLE_UPLOADED = "css=div#filesContentTable";
	public static final String DYNAMIC_DOWNLOAD_BUTTON_BY_FILE_NAME = "xpath=//span[text()='%s']/ancestor::div[contains(@class,'text-center')]/following-sibling::div//span[text()='Download']";
}
