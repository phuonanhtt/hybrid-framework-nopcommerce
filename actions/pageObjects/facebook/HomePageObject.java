package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class HomePageObject extends BasePage{
	WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToCreateNewAccountButton() {
		// TODO Auto-generated method stub
		
	}

	public boolean isFirstNameTextboxDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isSurNameTextboxDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEmailTextboxDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPasswordTextboxDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void enterToEmailTextbox(String string) {
		// TODO Auto-generated method stub
		
	}

	public boolean isConfirmEmailTextboxDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
