package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class AddressesPageObject extends MyAccountSideBarPageObject{
	private WebDriver driver;

	public AddressesPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
}
