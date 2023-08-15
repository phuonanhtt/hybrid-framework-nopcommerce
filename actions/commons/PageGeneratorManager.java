package commons;

import org.openqa.selenium.WebDriver;

import pageFactory.AdminLoginPageObject;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class PageGeneratorManager {
	// Tất cả các class trong pageObjects package đều phải có 1 hàm để gọi khởi tạo ra
	
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	public static CustomerPageObject getCustomerPage(WebDriver driver) {
		return new CustomerPageObject(driver);
	}
	
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	
	
}
