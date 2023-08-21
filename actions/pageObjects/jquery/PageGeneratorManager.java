package pageObjects.jquery;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static HomepageObject getHomepage(WebDriver driver) {
		return new HomepageObject(driver);
	}
}
