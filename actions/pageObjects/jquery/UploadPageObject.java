package pageObjects.jquery;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jquery.UploadPageUI;

public class UploadPageObject extends BasePage{
	private WebDriver driver;

	public UploadPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isFileLoadingSuccess(String fileName) {
		waitForElementVisible(driver, UploadPageUI.FILE_LOADED_BY_NAME, fileName);
		return isElementDisplayed(driver, UploadPageUI.FILE_LOADED_BY_NAME, fileName);
	}

	public void clickStartButtonEachfile() {
		List<WebElement> startButtons = getLisElement(driver, UploadPageUI.MULTIPLE_START_BUTTON);
		
		for (WebElement startButton : startButtons) {
			waitForElementClickable(driver, startButton);
			clickToElement(driver, startButton);
		}
		
	}

	public boolean isFileUploadedSuccess(String fileName) {
		waitForElementVisible(driver, UploadPageUI.FILE_UPLOADED_BY_NAME, fileName);
		return isElementDisplayed(driver, UploadPageUI.FILE_UPLOADED_BY_NAME, fileName);
	}
	
	public boolean isAllLoadingIconDisappear() {
		return waitForListElementInvisible(driver, UploadPageUI.SPINNER_LOADING_ICON);
	}
	
	public boolean isAllUploadProgressBarDisappear() {
		return waitForListElementInvisible(driver, UploadPageUI.UPLOADING_PROGRESS_BAR_ICON);
	}

	public String getUploadedSuccessMsg() {
		waitForElementVisible(driver, UploadPageUI.UPLOADED_SUCCESS_MESSAGE);
		return getElementText(driver, UploadPageUI.UPLOADED_SUCCESS_MESSAGE);
	}

	public void clickToUploadedLink() {
		waitForElementClickable(driver, UploadPageUI.UPLOADED_LINK);
		clickToElement(driver, UploadPageUI.UPLOADED_LINK);
	}

	public boolean isContenTableDisplayed() {
		waitForElementVisible(driver, UploadPageUI.CONTENT_TABLE_UPLOADED);
		return isElementDisplayed(driver, UploadPageUI.CONTENT_TABLE_UPLOADED);
	}

	public boolean isDownloadButtonDisplayed(String fileName) {
		waitForElementVisible(driver, UploadPageUI.DYNAMIC_DOWNLOAD_BUTTON_BY_FILE_NAME, fileName);
		return isElementDisplayed(driver, UploadPageUI.DYNAMIC_DOWNLOAD_BUTTON_BY_FILE_NAME, fileName);
	}
	
}
