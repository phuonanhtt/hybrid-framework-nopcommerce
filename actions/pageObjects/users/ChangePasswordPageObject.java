package pageObjects.users;

import org.openqa.selenium.WebDriver;

public class ChangePasswordPageObject extends MyAccountSideBarPageObject{
	private WebDriver driver;

	public ChangePasswordPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
}
