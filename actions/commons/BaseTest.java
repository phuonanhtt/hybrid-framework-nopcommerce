package commons;

import java.io.File;
import java.time.Duration;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	// Chứa những hàm dùng chung cho cả layer testcases
	private WebDriver driver;
	private long timeout = GlobalConstants.LONG_TIMEOUT;
	protected final Logger log;
	
	protected BaseTest() {
		log = LogManager.getLogger(getClass());
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case CHROME:
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case EDGE:
			driver = new EdgeDriver();
			break;
		default:
			throw new RuntimeException("Browser name is not valid");
		}
		
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
		driver.manage().window().maximize();
		
		return driver;
	}
	protected WebDriver getBrowserDriver(String browserName, String url) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case CHROME:
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case EDGE:
			driver = new EdgeDriver();
			break;
		default:
			throw new RuntimeException("Browser name is not valid");
		}
		
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
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
	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info("--------------- PASSED ---------------");
		} catch (Throwable e) {
			pass = false;
			log.info("--------------- FAILED ---------------");
			// add report testNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			// add reportNG
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info("--------------- PASSED ---------------");
		} catch (Throwable e) {
			pass = false;
			log.info("--------------- FAILED ---------------");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info("--------------- PASSED ---------------");
		} catch (Throwable e) {
			pass = false;
			log.info("--------------- FAILED ---------------");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	
	@BeforeSuite
	public void deleteFileReportNG() {
		log.info("Starting delete all file in ReportNG screenshot");
		deleteAllFileInFolder();
		log.info("Deleted success");
	}
	
	public void deleteAllFileInFolder() {
		try {
			String pathFolderDownload = GlobalConstants.REPORTNG_IMAGE_PART;
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}
