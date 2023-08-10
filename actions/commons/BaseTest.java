package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	// Chứa những hàm dùng chung cho cả layer testcases
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	
	protected WebDriver getBrowserDriver(String browserName) {
		// equals: Kiểm tra giá trị có phân biệt hoa thường
		// equalsIgnoreCase: Ko phân biệt hoa thường
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case CHROME:
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//			driver = new ChromeDriver();
			
			// 5.x
			// Tự tải chromedriver tương ứng vs Chrome browser + khởi tạo driver lên
//			driver = WebDriverManager.chromedriver().driverVersion("114.0.5735.90").create();
			driver = WebDriverManager.chromedriver().create();
			break;
		case FIREFOX:
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//			driver = new FirefoxDriver();
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case EDGE:
//			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
//			driver = new EdgeDriver();
			driver = WebDriverManager.edgedriver().create();
			break;
		case OPERA:
//			System.setProperty("webdriver.opera.driver", projectPath + "\\browserDrivers\\operadriver.exe");
//			driver = new EdgeDriver();
			driver = WebDriverManager.operadriver().create();
			break;
		default:
			throw new RuntimeException("Browser name is not valid");
		}
		
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		return driver;
	}
	protected String getEmailAddress() {
		Random rand = new Random();
		return "user" + rand.nextInt(9999) + "@gmail.com";
	}
	
	protected void quitBrowserDriver() {
		if (driver != null) {
			driver.quit();
		} 
	}
}
