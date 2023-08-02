package com.nopcommerce.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_01_Register {
	// Yc: Làm sao để có thể chạy đc
	// k quan tâm tối ưu
	// có thể lặp lại nhiều step giống nhau
	
	// RY: Repeat Yourself
	// DRY: Don't Repeat Yourself
	
	// ưu điểm:
	// Tốc độ nhanh - chp phép lặp lại nhiều lần
	// code mẫu vài TC để hiểu được cách dùng tool nào đó (PoC)
	// POC là 1 bài tập trong đó tập trung vào việc xác định xem 1 ý tưởng có thể biến thành hiện thực không
	
	// nhược điểm:
	// sự lặp lại rất nhiều step: Locator/ Hàm selenium
	// phí bảo trì (maintain) tăng lên khi có sự thay đổi: logic/ business/ thư viện/ UI
	// Không phù hợp với framework
	// Không phù hợp để làm dự án auto có time dàu/ mang lại nhiều value
	
	//Lỗi có thể ở 2 mức
	// compile
	// runtime
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void Register_01_Empty_Data() {
		driver.get("https://demo.nopcommerce.com/");
		
		// click register
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		// click đky
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		// verify
		Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");
		
	}

	@Test
	public void Register_02_Invalid_Email() {
		driver.get("https://demo.nopcommerce.com/");
		
		// click register
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		// nhập email
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Wick");
		driver.findElement(By.cssSelector("input#Email")).sendKeys("abc@123@gmail.com");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("12345678");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("12345678");
		
		// click đky
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		// verify
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Wrong email");
	}
	@Test
	public void Register_03_Invalid_Password() {
		driver.get("https://demo.nopcommerce.com/");
		
		// click register
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		// nhập email
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Wick");
		driver.findElement(By.cssSelector("input#Email")).sendKeys("John123@gmail.com");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
		
		// click đky
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		// verify
		//var text = $$("span#Password-error")[0];
		//text.textContent
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_04_Incorect_Confirm_Password() {
		driver.get("https://demo.nopcommerce.com/");
		
		// click register
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		// nhập email
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Wick");
		driver.findElement(By.cssSelector("input#Email")).sendKeys("John123@gmail.com");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123789");
		
		// click đky
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		// verify
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
	}

	@Test
	public void Register_05_Success() {
		driver.get("https://demo.nopcommerce.com/");
		
		// click register
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		// nhập email
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Wick");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(getEmailAddress());
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		// click đky
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		// verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getEmailAddress() {
		Random rand = new Random();
		return "user" + rand.nextInt(9999) + "@gmail.com";
	}
}
