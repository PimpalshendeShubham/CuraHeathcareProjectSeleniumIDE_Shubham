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

public class LoginPageTest {

	WebDriver driver;

	@BeforeClass
	void initSetUP() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ASUS\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://katalon-demo-cura.herokuapp.com/");
	}
	@Test(description = "Validating login functionality with valid credentials")
	void loginWithValidCredentials() {

		driver.manage().window().setSize(new Dimension(1280, 672));
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		driver.manage().window().setSize(new Dimension(1550, 838));
		driver.findElement(By.cssSelector(".fa-bars")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("txt-username")).click();
		driver.findElement(By.id("txt-username")).sendKeys("John Doe");
		driver.findElement(By.id("txt-password")).click();
		driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.id("btn-login")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), ("Make Appointment"));
		driver.findElement(By.cssSelector(".fa-bars")).click();
		driver.findElement(By.linkText("Logout")).click();
	}

	@Test(description = "Validating login functionality with valid username and Invalid Password")
	void loginWithInvalidPassword() {

		driver.manage().window().setSize(new Dimension(1550, 838));
		driver.findElement(By.cssSelector(".fa-bars")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("txt-username")).click();
		driver.findElement(By.id("txt-username")).sendKeys("John Doe");
		driver.findElement(By.id("txt-password")).click();
		driver.findElement(By.id("txt-password")).sendKeys("ThisIs");
		driver.findElement(By.id("btn-login")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".text-danger")).getText(),
				("Login failed! Please ensure the username and password are valid."));

	}

	@Test(description = "Validating login functionality with Invalid username and valid Password")
	void loginWithInvalidUsername() {

		driver.manage().window().setSize(new Dimension(1550, 838));
		driver.findElement(By.id("menu-toggle")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("txt-username")).click();
		driver.findElement(By.id("txt-username")).sendKeys("John");
		driver.findElement(By.id("txt-password")).click();
		driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.id("btn-login")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".text-danger")).getText(),
				("Login failed! Please ensure the username and password are valid."));
	}

	@Test(description = "Validating login functionality with Blank Username and password")
	void loginWithBlankCredentials() {
		driver.manage().window().setSize(new Dimension(1212, 646));
		driver.findElement(By.cssSelector(".fa-bars")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("btn-login")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".text-danger")).getText(),
				("Login failed! Please ensure the username and password are valid."));
	}

	@Test(description = "Validating login UI")
	void verifyTextOnLoginPage() {
		driver.manage().window().setSize(new Dimension(1550, 838));
		driver.findElement(By.cssSelector(".fa-bars")).click();
		driver.findElement(By.linkText("Login")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), ("Login"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".lead")).getText(),
				("Please login to make appointment."));
		Assert.assertEquals(driver.findElement(By.cssSelector(".form-group:nth-child(2) > .col-sm-4")).getText(),
				("Username"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".form-group:nth-child(3) > .col-sm-4")).getText(),
				("Password"));
		Assert.assertEquals(driver.findElement(By.id("btn-login")).getText(), ("Login"));
	}

	@AfterClass
	void teardown() {

		driver.close();
	}

}
