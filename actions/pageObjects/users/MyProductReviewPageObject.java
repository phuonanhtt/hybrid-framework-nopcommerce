package pageObjects.users;

import org.openqa.selenium.WebDriver;

public class MyProductReviewPageObject extends MyAccountSideBarPageObject{
	private WebDriver driver;

	public MyProductReviewPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
}
