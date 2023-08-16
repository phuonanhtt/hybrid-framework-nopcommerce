package commons;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.admin.AdminLoginPageObject;
import pageObjects.users.HomePageObject;
import pageUIs.users.BasePageUI;

public class BasePage {
	// Chứa những hàm dùng chung cho cả layer page objects

	// 1 - Access Modifier: public/ protected/ private/ default

	// 2 - Kiểu dữ liệu của hàm (Data type): void/ int/ String/ boolean/ WebElement/ List<WebElement>/..
	// Nó sẽ liên quan đến cái chức năng mình viết trong thân hàm

	// 3 - Tên hàm: Đặt tên có nghĩa theo chức năng đang cần viết
	// Đặt tên gọn gàng giống viết văn nói
	// Convention tuân theo chuẩn của từng ngôn ngữ lập trình (Java)
	// camelCase: từ đầu tiên viết thường - chữ cái đầu tiên của các từ tiếp theo sẽ viết hoa

	// 4 - Có tham số hay ko (tùy vào chức năng cần viết)
	// Dựa vào hàm của Selenium mình gọi ra để dùng là gì
	// Web Browser: WebDriver driver
	// Web Element: WebDriver driver, String locator

	// 5 - Kiểu dữ liệu trả về cho hàm
	// Nếu như có return dữ liệu thì sẽ khớp vs kiểu dữ liệu ở số 2
	// Nếu như có return thì nó là cái step cuối cùng
	
	// 60 hàm được wrapper lại từ thư viện selenium
	public static BasePage getBasePage() {
		return new BasePage();
	}
	
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
		return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.alertIsPresent());
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

	public static By getByLocator(String locatorValue) {
		By by = null;

		if (locatorValue.startsWith("xpath=") || locatorValue.startsWith("XPATH=") 
				|| locatorValue.startsWith("Xpath=") || locatorValue.startsWith("XPath=")) {
			by = By.xpath(locatorValue.substring(6));

		} else if(locatorValue.startsWith("css=") || locatorValue.startsWith("CSS=") || locatorValue.startsWith("Css=")){
			by = By.cssSelector(locatorValue.substring(4));
		} else if(locatorValue.startsWith("ID=") || locatorValue.startsWith("Id=") || locatorValue.startsWith("id=")){
			by = By.id(locatorValue.substring(3));
		} else if(locatorValue.startsWith("name=") || locatorValue.startsWith("NAME=") || locatorValue.startsWith("Name=")){
			by = By.name(locatorValue.substring(5));
		} else if(locatorValue.startsWith("class=") || locatorValue.startsWith("CLASS=") || locatorValue.startsWith("Class=")){
			by = By.className(locatorValue.substring(6));
		} else if(locatorValue.startsWith("tagname=") || locatorValue.startsWith("TAGNAME=") || locatorValue.startsWith("Tagname=")){
			by = By.tagName(locatorValue.substring(8));
		} else {
			throw new RuntimeException("Locator type is not valid");
		}
		return by;
	}
	public By getByXpath(String xPathExpression) {
		return By.xpath(xPathExpression);
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}

	public List<WebElement> getLisElement(WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}

	public String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}

	public String getFirstSelectedOptionText(WebDriver driver, String locator) {
		return new Select(getElement(driver, locator)).getFirstSelectedOption().getText();
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
		return getElement(driver, locator).getCssValue(propertyName);
	}

	public String getHexaColorByRGBA(String rgbaColor) {
		return Color.fromString(rgbaColor).asHex().toUpperCase();
	}

	public int getListElementSize(WebDriver driver, String locator) {
		return getLisElement(driver, locator).size();
	}

	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}

	public void checkToCheckboxRadio(WebDriver driver, String locator) {
		if (!getElement(driver, locator).isSelected()) {
			clickToElement(driver, locator);
		}
	}

	public void unCheckToCheckbox(WebDriver driver, String locator) {
		if (getElement(driver, locator).isSelected()) {
			clickToElement(driver, locator);
		}
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);

	}

	public void selectDropdown(WebDriver driver, String locator, String itemText) {
		new Select(getElement(driver, locator)).selectByVisibleText(itemText);
	}

	public Boolean isDropdownMultiple(WebDriver driver, String locator) {
		return new Select(getElement(driver, locator)).isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedText) {
		clickToElement(driver, parentLocator);
		sleepInSecond(1);

		List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));

		for (WebElement tempElement : allItems) {
			if (tempElement.getText().equals(expectedText)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
				sleepInSecond(1);

				tempElement.click();
				sleepInSecond(1);

				break;
			}
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return getElement(driver, locator).isDisplayed();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}

	public boolean isElementEnable(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	public boolean isPageLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		// điều kiện 1
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		// điều kiện 2
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(getElement(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String locator) {
		new Actions(driver).moveToElement(getElement(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		new Actions(driver).doubleClick(getElement(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		new Actions(driver).contextClick(getElement(driver, locator)).perform();

	}

	public void dragAnđropToElement(WebDriver driver, String sourceXpath, String targetXpath) {
		new Actions(driver).dragAndDrop(getElement(driver, sourceXpath), getElement(driver, targetXpath)).perform();

	}

	public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
		new Actions(driver).sendKeys(getElement(driver, locator), key).perform();
		;
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(WebDriver driver, String locator) {
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void scrollToElementOnDown(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(driver, locator));
	}

	public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(driver, locator));
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}
	
	public void waitForListElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
	}
	public void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}
	
	public void waitElementInvisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}
	
	public HomePageObject clickToUserLogoutLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.USER_LOGOUT_LINK);
		clickToElement(driver, BasePageUI.USER_LOGOUT_LINK);
		return PageGeneratorManager.getHomePage(driver);
	}

	public AdminLoginPageObject clickToAdminLogoutLink(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADMIN_LOGOUT_LINK);
		clickToElement(driver, BasePageUI.ADMIN_LOGOUT_LINK);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}
	private long timeout = GlobalConstants.LONG_TIMEOUT;
}
