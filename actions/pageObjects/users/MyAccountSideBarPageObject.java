package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.users.MyAccountSideBarPageUI;

public class MyAccountSideBarPageObject extends BasePage{
	WebDriver driver;
	
	public MyAccountSideBarPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public CustomerPageObject openCustomerInfoPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.Customer_Info_Link);
		clickToElement(driver, MyAccountSideBarPageUI.Customer_Info_Link);
		return PageGeneratorManager.getCustomerPage(driver);
	}
	
	public AddressesPageObject openAddressesPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.Addresses_Link);
		clickToElement(driver, MyAccountSideBarPageUI.Addresses_Link);
		return PageGeneratorManager.getAddressesPage(driver);
	}
	
	public DownloadableProductPageObject openDownloadableProductPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.Downloadable_Product_Link);
		clickToElement(driver, MyAccountSideBarPageUI.Downloadable_Product_Link);
		return PageGeneratorManager.getDownloadableProductPage(driver);
	}
	
	public RewardPointPageObject openRewardPointPage() {
		waitForElementClickable(driver, MyAccountSideBarPageUI.Reward_Point_Link);
		clickToElement(driver, MyAccountSideBarPageUI.Reward_Point_Link);
		return PageGeneratorManager.getRewardPointPage(driver);
	}
	
	public MyAccountSideBarPageObject openDynamicSideBarPage(String pageName) {
		waitForElementClickable(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);
		clickToElement(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getCustomerPage(driver);
		case "Addresses":
			return PageGeneratorManager.getAddressesPage(driver);
		case "Orders":
			return PageGeneratorManager.getOrderPage(driver);
		case "Downloadable products":
			return PageGeneratorManager.getDownloadableProductPage(driver);
		case "Back in stock subscriptions":
			return PageGeneratorManager.getBackInStockPage(driver);
		case "Reward points":
			return PageGeneratorManager.getRewardPointPage(driver);
		case "Change password":
			return PageGeneratorManager.getChangePasswordPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getMyProductReviewPage(driver);
		default:
			new RuntimeException("Sidebar page name incorrect");
			return null;
		}
	}
	
	public void openDynamicSideBarPageByName(String pageName) {
		waitForElementClickable(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);
		clickToElement(driver, MyAccountSideBarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);
	}
}
