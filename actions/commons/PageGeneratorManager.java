package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.HomePageObject;

public class PageGeneratorManager {
	
	public HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
}
