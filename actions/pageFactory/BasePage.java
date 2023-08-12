package pageFactory;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void acceptToAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelToAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();

	}

	public void sendkeyToAlert(WebDriver driver, String valueToSendkey) {
		waitForAlertPresence(driver).sendKeys(valueToSendkey);
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent());
	}

	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allID = driver.getWindowHandles();
		for (String id : allID) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				sleepInSecond(2);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allID = driver.getWindowHandles();
		for (String id : allID) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowWithoutExpectedID(WebDriver driver, String expectedID) {
		Set<String> allIDs = driver.getWindowHandles();

		for (String id : allIDs) {
			if (!id.equals(expectedID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(expectedID);
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}
	
	public void waitForElementVisible(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public String getElementAttribute(WebElement element, String attributeName) {
		return element.getAttribute(attributeName);
	}
	
	public String getElementText(WebElement element) {
		return element.getText();
	}
	
	public void sendkeyToElement(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);

	}


}
