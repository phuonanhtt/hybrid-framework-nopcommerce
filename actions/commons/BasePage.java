package commons;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		return new WebDriverWait(driver, 30).until(ExpectedConditions.alertIsPresent());
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

	public By getByXpath(String xPathExpression) {
		return By.xpath(xPathExpression);
	}

	public WebElement getElement(WebDriver driver, String xPathExpression) {
		return driver.findElement(getByXpath(xPathExpression));
	}

	public List<WebElement> getLisElement(WebDriver driver, String xPathExpression) {
		return driver.findElements(getByXpath(xPathExpression));
	}

	public String getElementText(WebDriver driver, String xPathExpression) {
		return getElement(driver, xPathExpression).getText();
	}

	public String getFirstSelectedOptionText(WebDriver driver, String xPathExpression) {
		return new Select(getElement(driver, xPathExpression)).getFirstSelectedOption().getText();
	}

	public String getElementAttribute(WebDriver driver, String xPathExpression, String attributeName) {
		return getElement(driver, xPathExpression).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String xPathExpression, String propertyName) {
		return getElement(driver, xPathExpression).getCssValue(propertyName);
	}

	public String getHexaColorByRGBA(String rgbaColor) {
		return Color.fromString(rgbaColor).asHex().toUpperCase();
	}

	public int getListElementSize(WebDriver driver, String xPathExpression) {
		return getLisElement(driver, xPathExpression).size();
	}

	public void clickToElement(WebDriver driver, String xPathExpression) {
		getElement(driver, xPathExpression).click();
	}

	public void checkToCheckboxRadio(WebDriver driver, String xPathExpression) {
		if (!getElement(driver, xPathExpression).isSelected()) {
			clickToElement(driver, xPathExpression);
		}
	}

	public void unCheckToCheckbox(WebDriver driver, String xPathExpression) {
		if (getElement(driver, xPathExpression).isSelected()) {
			clickToElement(driver, xPathExpression);
		}
	}

	public void sendkeyToElement(WebDriver driver, String xPathExpression, String value) {
		getElement(driver, xPathExpression).clear();
		getElement(driver, xPathExpression).sendKeys(value);

	}

	public void selectDropdown(WebDriver driver, String xPathExpression, String itemText) {
		new Select(getElement(driver, xPathExpression)).selectByVisibleText(itemText);
	}

	public Boolean isDropdownMultiple(WebDriver driver, String xPathExpression) {
		return new Select(getElement(driver, xPathExpression)).isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String xPathParent, String xPathChild, String expectedText) {
		clickToElement(driver, xPathParent);
		sleepInSecond(1);

		List<WebElement> allItems = new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(xPathChild)));

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

	public boolean isElementDisplayed(WebDriver driver, String xPathExpression) {
		return getElement(driver, xPathExpression).isDisplayed();
	}

	public boolean isElementSelected(WebDriver driver, String xPathExpression) {
		return getElement(driver, xPathExpression).isSelected();
	}

	public boolean isElementEnable(WebDriver driver, String xPathExpression) {
		return getElement(driver, xPathExpression).isEnabled();
	}

	public void switchToFrame(WebDriver driver, String xPathExpression) {
		driver.switchTo().frame(getElement(driver, xPathExpression));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String xPathExpression) {
		new Actions(driver).moveToElement(getElement(driver, xPathExpression)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String xPathExpression) {
		new Actions(driver).doubleClick(getElement(driver, xPathExpression)).perform();
	}

	public void rightClickToElement(WebDriver driver, String xPathExpression) {
		new Actions(driver).contextClick(getElement(driver, xPathExpression)).perform();

	}

	public void dragAnđropToElement(WebDriver driver, String sourceXpath, String targetXpath) {
		new Actions(driver).dragAndDrop(getElement(driver, sourceXpath), getElement(driver, targetXpath)).perform();

	}

	public void sendKeyBoardToElement(WebDriver driver, String xPathExpression, Keys key) {
		new Actions(driver).sendKeys(getElement(driver, xPathExpression), key).perform();
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

	public void hightlightElement(WebDriver driver, String xPathExpression) {
		WebElement element = getElement(driver, xPathExpression);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xPathExpression) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, xPathExpression));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(WebDriver driver, String xPathExpression) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, xPathExpression));
	}

	public void scrollToElementOnDown(WebDriver driver, String xPathExpression) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(driver, xPathExpression));
	}

	public void setAttributeInDOM(WebDriver driver, String xPathExpression, String attributeName, String attributeValue) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(driver, xPathExpression));
	}

	public void removeAttributeInDOM(WebDriver driver, String xPathExpression, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, xPathExpression));
	}

	public void sendkeyToElementByJS(WebDriver driver, String xPathExpression, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, xPathExpression));
	}

	public String getAttributeInDOM(WebDriver driver, String xPathExpression, String attributeName) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(driver, xPathExpression));
	}

	public String getElementValidationMessage(WebDriver driver, String xPathExpression) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, xPathExpression));
	}

	public boolean isImageLoaded(WebDriver driver, String xPathExpression) {
		return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(driver, xPathExpression));
	}

	public void waitForElementVisible(WebDriver driver, String xPathExpression) {
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xPathExpression)));
	}
	
	public void waitForListElementVisible(WebDriver driver, String xPathExpression) {
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xPathExpression)));
	}
}
