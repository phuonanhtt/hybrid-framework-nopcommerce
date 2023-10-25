package pageObjects.users;

import org.openqa.selenium.WebDriver;

public class RewardPointPageObject extends MyAccountSideBarPageObject{
	private WebDriver driver;

	public RewardPointPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
}
