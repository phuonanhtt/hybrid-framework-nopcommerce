package pageObjects.users;

import org.openqa.selenium.WebDriver;

public class OrderPageObject extends MyAccountSideBarPageObject{
	private WebDriver driver;

	public OrderPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
}
