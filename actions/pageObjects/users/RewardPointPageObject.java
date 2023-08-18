package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class RewardPointPageObject extends MyAccountSideBarPageObject{
	private WebDriver driver;

	public RewardPointPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
}
