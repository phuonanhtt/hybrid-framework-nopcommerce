package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageFactory.BasePage;


public class RegisterPageObject extends BasePage{
	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "button#register-button")
	WebElement registerButton;
	
	@FindBy(css  = "input#FirstName")
	WebElement firstNameTextbox;
	
	@FindBy(css  = "input#LastName")
	WebElement lastNameTextbox;
	
	@FindBy(css  = "input#Email")
	WebElement emailTextbox;
	
	@FindBy(css  = "input#Password")
	WebElement passwordTextbox;
	
	@FindBy(css  = "input#ConfirmPassword")
	WebElement confirmpasswordTextbox;
	
	@FindBy(css  = "span#FirstName-error")
	WebElement firstNameErrorMsg;
	
	@FindBy(css  = "span#LastName-error")
	WebElement lastNameErrorMsg;
	
	@FindBy(css  = "span#Email-error")
	WebElement emailErrorMsg;
	
	@FindBy(css  = "span#Password-error")
	WebElement passwordErrorMsg;
	
	@FindBy(css  = "span#ConfirmPassword-error")
	WebElement confirmPasswordErrorMsg;
	
	@FindBy(css  = "div.result")
	WebElement registerSuccessMsg;
	
	@FindBy(css  = "div.header-logo img")
	WebElement homePageLogo;
	
	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
	}

	public String getFirstNameErrorMessage() {
		waitForElementVisible(driver, firstNameErrorMsg);
		return getElementText(firstNameErrorMsg);
	}

	public String getLastNameErrorMessage() {
		waitForElementVisible(driver, lastNameErrorMsg);
		return getElementText(lastNameErrorMsg);
	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, emailErrorMsg);
		return getElementText(emailErrorMsg);
	}

	public String getPasswordErrorMessage() {
		waitForElementVisible(driver, passwordErrorMsg);
		return getElementText(passwordErrorMsg);
	}

	public String getConfirmPasswordErrorMessage() {
		waitForElementVisible(driver, confirmPasswordErrorMsg);
		return getElementText(confirmPasswordErrorMsg);
	}

	public HomePageObject clickToHomePageLogo() {
		waitForElementVisible(driver, homePageLogo);
		clickToElement(driver, homePageLogo);
		return new HomePageObject(driver);
	}

	public void enterToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, firstNameTextbox);
		sendkeyToElement(firstNameTextbox, firstName);
		
	}

	public void enterToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextbox);
		sendkeyToElement(lastNameTextbox, lastName);
		
	}

	public void enterToEmailTextbox(String email) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(emailTextbox, email);
		
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(passwordTextbox, password);
		
	}

	public void enterToConfirmPasswordTextbox(String password) {
		waitForElementVisible(driver, confirmpasswordTextbox);
		sendkeyToElement(confirmpasswordTextbox, password);
		
	}

	public String getSuccessMessage() {
		waitForElementVisible(driver, registerSuccessMsg);
		return getElementText(registerSuccessMsg);
	}

}
