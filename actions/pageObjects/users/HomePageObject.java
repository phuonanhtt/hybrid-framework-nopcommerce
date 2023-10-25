package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BaseElement;
import commons.PageGeneratorManager;
import pageUIs.users.HomePageUI;

public class HomePageObject extends BaseElement{
	// Chứa những actions của page đó: click/ select/ verify/ getText/...
	private WebDriver driver;
	// Hàm khởi tạo của 1 class
	// cùng tên với class
	// không có kiểu trả về
	// chạy đùa tiên khi class được gọi tới
	// 1 class nếu hàm khởi tạo ko được define thì mặc định vẫn có 1 hàm khởi tạo rỗng
	// nếu được define nó sẽ ưu tiên gọi tới hàm được define đó (ko dùng mặc định nữa)
	// có thể có 1 hoặc nhiều tham số, hoặc 0 tham số
	// 1 class có thể có nhiều hàm khởi tạo
	
	// Nếu class hiện tại đang kế thừa 1 class cha - thì khi tạo hàm khởi tạo nó sẽ có từ khóa super
	// => gọi tới hàm khởi tạo của class cha
	// nếu class hiện tại không kế thừa class nào thì mặc định nó sẽ kế thừa class Object
//	public HomePageObject(WebDriver driver, WebDriverWait explicitWait) {
//		this.driver = driver;
//		this.explicitWait = explicitWait;
//	}
	
	public HomePageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public RegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public LoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}

	public CustomerPageObject clickToMyAccountLink() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getCustomerPage(driver);
	}

	public boolean isRegisterLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.REGISTER_LINK);
		return isElementDisplayed(driver, HomePageUI.REGISTER_LINK);
	}


}
