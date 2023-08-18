package javaBasic;

import org.testng.annotations.Test;

public class Topic_05_Rest_Parameter {
	// Click vào page address: //div[@class='listbox']//a[text()='Addresses']
	// click vào page order: //div[@class='listbox']//a[text()='Orders']
	String addressLink = "//div[@class='listbox']//a[text()='Addresses']";
	String orderLink = "//div[@class='listbox']//a[text()='Orders']";
	
	String dynamicSidebar = "//div[@class='listbox']//a[text()='%s']";
	String dynamicLink = "//div[@class='%s']//a[text()='%s']";
	
	String africaString = "//td[@data-key='females' and text()='12253515']/following-sibling::td[@data-key='country' and text()='AFRICA']"
			+ "/following-sibling::td[@data-key='males' and text()='12599691']/following-sibling::td[@data-key='total' and text()='24853148']";
	
	String dynamicCountry = "//td[@data-key='females' and text()='%s']/following-sibling::td[@data-key='country' and text()='%s']"
			+ "/following-sibling::td[@data-key='males' and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']";
	
	@Test
	public void TC_01_Rest_Param() {
		// cố định
		clickToElement(addressLink);
		clickToElement(orderLink);
		
		// 1 param
		clickToElement(dynamicSidebar, "Addresses");
		clickToElement(dynamicSidebar, "Orders");
		clickToElement(dynamicSidebar, "Customer info");
		
		// 2 param
		clickToElement(dynamicLink, "header", "My account");
		clickToElement(dynamicLink, "footer", "Blog");
		
		// 4 param
		clickToElement(dynamicCountry, "12253515", "AFRICA", "12599691", "24853148");
		clickToElement(dynamicCountry, "764956", "Arab Rep of Egypt", "802948", "1567904");
	}
	
	// Hàm để click vào 1 element cố định
	public void clickToElement(String locatorValue) {
		System.out.println("Click to: " + locatorValue);
	}
	
	// Hàm để click vào 1 element ko cố định (dynamic) vs 1 tham số
	public void clickToElement(String locatorValue, String pageName) {
		System.out.println("Locator trước khi pass value: " + locatorValue);
		locatorValue = String.format(locatorValue, pageName);
		System.out.println("Locator sau khi pass value: " + locatorValue);
	}
	
	// Hàm để click vào 1 element ko cố định (dynamic) vs 2 tham số
	public void clickToElement(String locatorValue, String pageType, String pageName) {
		locatorValue = String.format(locatorValue, pageType, pageName);
		System.out.println("Click to: " + locatorValue);
	}
	
	// Hàm để click vào 1 element ko cố định (dynamic) vs 4 tham số
	public void clickToElement(String locatorValue, String female, String country, String male, String total) {
		locatorValue = String.format(locatorValue, female, country, male, total);
		System.out.println("Click to: " + locatorValue);
	}
	
	// Var arguments = Rest parameter
	public void clickToElement(String locatorValue, String... values) {
		locatorValue = String.format(locatorValue, (Object[]) values);
		System.out.println("Click to: " + locatorValue);
	}
	
}
