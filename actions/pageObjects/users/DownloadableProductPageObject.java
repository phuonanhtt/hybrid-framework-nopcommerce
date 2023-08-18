package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class DownloadableProductPageObject extends MyAccountSideBarPageObject{
	private WebDriver driver;

	public DownloadableProductPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
}
