package pageObjects.users;

import org.openqa.selenium.WebDriver;

public class BackInStockPageObject extends MyAccountSideBarPageObject{
	private WebDriver driver;

	public BackInStockPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
}
