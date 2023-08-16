package javaBasic;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_04_By {
//	public static void main(String[] args) {
//		// Đầu vào là xpath/ css/ id/ name/ class/ tagname / linkText/ partialLinkText
//		// Đầu ra nó sẽ lấy đúng loại By tương ứng
//
//		// xpath = //input[@id='name'] -> By.xpath
//		// css = input#name -> By.cssSelector
//		String locatorValue = "xpath=//input[@id='name']";
//		System.out.println(getByLocator(locatorValue));
//		
//		locatorValue = "XPath=//input[@id='name']";
//		System.out.println(getByLocator(locatorValue));
//		
//		locatorValue = "XPATH=//input[@id='name']";
//		System.out.println(getByLocator(locatorValue));
//	}
	
	@Test
	public void TC_01_Locator_Lower_Case() {
		String locatorValue = "xpath=//input[@id='name']";
		Assert.assertEquals(getByLocator(locatorValue), By.xpath("//input[@id='name']"));
	}
	
	@Test
	public void TC_02_Locator_Upper_Case() {
		String locatorValue = "XPATH=//input[@id='name']";
		Assert.assertEquals(getByLocator(locatorValue), By.xpath("//input[@id='name']"));
	}
	
	@Test
	public void TC_03_Locator_Camel_Case() {
		String locatorValue = "XPath=//input[@id='name']";
		Assert.assertEquals(getByLocator(locatorValue), By.xpath("//input[@id='name']"));
	}
	
	@Test
	public void TC_03_Locator_Invalid_Case() {
		String locatorValue = "classname=email";
		Assert.assertNotEquals(getByLocator(locatorValue), By.xpath("email"));
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
}
