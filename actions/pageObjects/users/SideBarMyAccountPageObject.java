package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.users.SideBarMyAccountPageUI;

public class SideBarMyAccountPageObject extends BasePage{
	WebDriver driver;
	
	public SideBarMyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public CustomerPageObject openCustomerInfoPage() {
		waitForElementClickable(driver, SideBarMyAccountPageUI.Customer_Info_Link);
		clickToElement(driver, SideBarMyAccountPageUI.Customer_Info_Link);
		return PageGeneratorManager.getCustomerPage(driver);
	}
	
	public AddressesPageObject openAddressesPage() {
		waitForElementClickable(driver, SideBarMyAccountPageUI.Addresses_Link);
		clickToElement(driver, SideBarMyAccountPageUI.Addresses_Link);
		return PageGeneratorManager.getAddressesPage(driver);
	}
	
	public DownloadableProductPageObject openDownloadableProductPage() {
		waitForElementClickable(driver, SideBarMyAccountPageUI.Downloadable_Product_Link);
		clickToElement(driver, SideBarMyAccountPageUI.Downloadable_Product_Link);
		return PageGeneratorManager.getDownloadableProductPage(driver);
	}
	
	public RewardPointPageObject openRewardPointPage() {
		waitForElementClickable(driver, SideBarMyAccountPageUI.Reward_Point_Link);
		clickToElement(driver, SideBarMyAccountPageUI.Reward_Point_Link);
		return PageGeneratorManager.getRewardPointPage(driver);
	}

}
