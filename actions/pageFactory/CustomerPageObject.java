package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CustomerPageObject extends BasePage{
	// TestNG: @beforeClass/ @Test/ @Parameter/...
	// UI: annotation
	// @FindBy/ @FindBys/ @FindAll /@CacheLookup
	
	WebDriver driver;
	
	public CustomerPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@id='FirstName']")
	WebElement firstNameTextbox;
	
	@FindBy(xpath = "//input[@id='LastName']")
	WebElement lastNameTextbox;
	
	@FindBy(css = "input#Email")
	WebElement emailTextbox;
	
	@FindBy(css = "input#login")
	WebElement loginButton;
	
	// Action
	public void clickToLoginButton() {
		clickToElement(driver, loginButton);
	}
	
	public String getFirstNameAttributeValue() {
		waitForElementVisible(driver, firstNameTextbox);
		return getElementAttribute(firstNameTextbox, "value");
	}

	public String getLastNameAttributeValue() {
		waitForElementVisible(driver, lastNameTextbox);
		return getElementAttribute(lastNameTextbox, "value");
	}

	public String getEmailAttributeValue() {
		waitForElementVisible(driver, emailTextbox);
		return getElementAttribute(emailTextbox, "value");
	}
}
