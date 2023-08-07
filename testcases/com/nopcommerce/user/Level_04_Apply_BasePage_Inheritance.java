package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_04_Apply_BasePage_Inheritance extends BasePage{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
				
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	@Test
	public void Register_01_Empty_Data() {
		openUrl(driver, "https://demo.nopcommerce.com/");

		clickToElement(driver, "//a[@class='ico-register']");
		
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
		
	}

	@Test
	public void Register_02_Invalid_Email() {
		openUrl(driver, "https://demo.nopcommerce.com/");
		
		clickToElement(driver, "//a[@class='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "John");
		sendkeyToElement(driver, "//input[@id='LastName']","Wick");
		sendkeyToElement(driver, "//input[@id='Email']","abc@123@gmail.com");
		sendkeyToElement(driver, "//input[@id='Password']","12345678");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']","12345678");
		
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}
	@Test
	public void Register_03_Invalid_Password() {
		openUrl(driver, "https://demo.nopcommerce.com/");
		
		clickToElement(driver, "//a[@class='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "John");
		sendkeyToElement(driver, "//input[@id='LastName']","Wick");
		sendkeyToElement(driver, "//input[@id='Email']","John123@gmail.com");
		sendkeyToElement(driver, "//input[@id='Password']","123");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']","123");
		
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_04_Incorect_Confirm_Password() {
		openUrl(driver, "https://demo.nopcommerce.com/");
		
		clickToElement(driver, "//a[@class='ico-register']");
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "John");
		sendkeyToElement(driver, "//input[@id='LastName']","Wick");
		sendkeyToElement(driver, "//input[@id='Email']","John123@gmail.com");
		sendkeyToElement(driver, "//input[@id='Password']","123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']","123789");
		
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	}

	@Test
	public void Register_05_Success() {
		openUrl(driver, "https://demo.nopcommerce.com/");
		
		// click register
		clickToElement(driver, "//a[@class='ico-register']");
		
		// nhập email
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "John");
		sendkeyToElement(driver, "//input[@id='LastName']","Wick");
		sendkeyToElement(driver, "//input[@id='Email']",getEmailAddress());
		sendkeyToElement(driver, "//input[@id='Password']","123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']","123456");
		
		// click đky
		clickToElement(driver, "//button[@id='register-button']");
		
		// verify
		
		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
	}
	public String getEmailAddress() {
		Random rand = new Random();
		return "user" + rand.nextInt(9999) + "@gmail.com";
	}
}
