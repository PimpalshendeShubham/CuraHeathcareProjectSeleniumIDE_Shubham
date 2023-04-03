package testngproject;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTest {

	WebDriver driver;

	@BeforeClass
	void initSetUP() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ASUS\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://katalon-demo-cura.herokuapp.com/");
	}

	@Test
	void verifyMakeAppointmentButtonWithLogin() {
		driver.manage().window().setSize(new Dimension(1550, 838));

		driver.findElement(By.id("menu-toggle")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("txt-username")).click();
		driver.findElement(By.id("txt-username")).sendKeys("John Doe");
		driver.findElement(By.id("txt-password")).click();
		driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.id("btn-login")).click();
		driver.findElement(By.id("btn-make-appointment")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), ("Make Appointment"));
		driver.findElement(By.cssSelector(".fa-bars")).click();
		driver.findElement(By.linkText("Logout")).click();
	}

	@Test
	void verifyMakeAppointmentButtonWithoutLogin() {
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		driver.manage().window().setSize(new Dimension(1212, 646));
		driver.findElement(By.id("btn-make-appointment")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".lead")).getText(),
				("Please login to make appointment."));
	}

	@Test
	void verifyHomePageUI() {
		driver.manage().window().setSize(new Dimension(1550, 838));
		Assert.assertEquals(driver.findElement(By.cssSelector("h1")).getText(), ("CURA Healthcare Service"));
		Assert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(), ("We Care About Your Health"));
		Assert.assertEquals(driver.findElement(By.id("btn-make-appointment")).getText(), ("Make Appointment"));

	}

	@Test
	void verifyHomePageFooterUI() {
		driver.manage().window().setSize(new Dimension(1550, 838));
		Assert.assertEquals(driver.findElement(By.cssSelector("strong")).getText(), ("CURA Healthcare Service"));
		Assert.assertEquals(driver.findElement(By.linkText("info@katalon.com")).getText(), ("info@katalon.com"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".text-muted")).getText(),
				("Copyright © CURA Healthcare Service 2023"));

	}

	@AfterClass
	void teardown() {

		driver.close();
	}
}