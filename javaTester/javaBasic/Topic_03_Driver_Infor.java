package javaBasic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

public class Topic_03_Driver_Infor {
	static WebDriver driver = null;
	
	public static void main(String[] args) {
		// GUID
		
		// Chrome
		// Session ID = 803041dc348cc42150367bdfe374c296
		// Driver ID = ChromeDriver: chrome on windows (803041dc348cc42150367bdfe374c296)
		driver = new ChromeDriver();
		SessionId sesionId = ((RemoteWebDriver) driver).getSessionId();
		System.out.println("Session ID = " + sesionId);
		System.out.println("Driver ID = " + driver.toString());
		driver.quit();
		
		// Firefox
		// Session ID = 09463baf-6c3e-417c-8dbc-b1a19e10a891
		// Driver ID = FirefoxDriver: firefox on windows (09463baf-6c3e-417c-8dbc-b1a19e10a891)

		driver = new FirefoxDriver();
		sesionId = ((RemoteWebDriver) driver).getSessionId();
		System.out.println("Session ID = " + sesionId);
		System.out.println("Driver ID = " + driver.toString());
		driver.quit();
	}
}
