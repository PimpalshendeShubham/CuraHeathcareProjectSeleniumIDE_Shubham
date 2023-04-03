package testngproject;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MakeAppointmentPageTest {
	WebDriver driver;

	@BeforeClass
	void initSetUP() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ASUS\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://katalon-demo-cura.herokuapp.com/");
	}

	@Test
	void verifyAppoinmentBookingWithLogin() {

		driver.manage().window().setSize(new Dimension(1550, 838));
		driver.findElement(By.id("menu-toggle")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("txt-username")).click();
		driver.findElement(By.id("txt-username")).sendKeys("John Doe");
		driver.findElement(By.id("txt-password")).click();
		driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.id("btn-login")).click();
		driver.findElement(By.id("btn-make-appointment")).click();
		driver.findElement(By.id("combo_facility")).click();
		{
			WebElement dropdown = driver.findElement(By.id("combo_facility"));
			dropdown.findElement(By.xpath("//option[. = 'Hongkong CURA Healthcare Center']")).click();
		}
		driver.findElement(By.cssSelector("option:nth-child(2)")).click();
		driver.findElement(By.id("chk_hospotal_readmission")).click();
		Assert.assertTrue(driver.findElement(By.id("chk_hospotal_readmission")).isSelected());
		driver.findElement(By.id("radio_program_medicaid")).click();
		driver.findElement(By.id("txt_visit_date")).click();
		driver.findElement(By.cssSelector("tr:nth-child(2) > .day:nth-child(4)")).click();
		driver.findElement(By.id("btn-book-appointment")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), ("Appointment Confirmation"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".lead")).getText(),
				("Please be informed that your appointment has been booked as following:"));
		Assert.assertEquals(driver.findElement(By.id("facility")).getText(), ("Hongkong CURA Healthcare Center"));
		Assert.assertEquals(driver.findElement(By.id("program")).getText(), ("Medicaid"));
		driver.findElement(By.cssSelector(".fa-bars")).click();
		driver.findElement(By.linkText("Logout")).click();
	}

	@Test
	void verifyAppoinmentBookingWithoutLogin() {
		driver.manage().window().setSize(new Dimension(1550, 838));
		driver.findElement(By.id("btn-make-appointment")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".lead")).getText(),
				("Please login to make appointment."));
		driver.findElement(By.id("txt-username")).click();
		driver.findElement(By.id("txt-username")).sendKeys("John Doe");
		driver.findElement(By.id("txt-password")).click();
		driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.id("btn-login")).click();
		driver.findElement(By.id("combo_facility")).click();
		{
			WebElement dropdown = driver.findElement(By.id("combo_facility"));
			dropdown.findElement(By.xpath("//option[. = 'Seoul CURA Healthcare Center']")).click();
		}
		driver.findElement(By.cssSelector("option:nth-child(3)")).click();
		driver.findElement(By.id("txt_visit_date")).click();
		driver.findElement(By.cssSelector(".datepicker-days .next")).click();
		driver.findElement(By.cssSelector(".datepicker-days .next")).click();
		{
			WebElement element = driver.findElement(By.cssSelector(".datepicker-days .next"));
			Actions builder = new Actions(driver);
			builder.doubleClick(element).perform();
		}
		driver.findElement(By.cssSelector("tr:nth-child(4) > .day:nth-child(3)")).click();
		driver.findElement(By.id("txt_comment")).click();
		driver.findElement(By.id("btn-book-appointment")).click();
		driver.findElement(By.cssSelector(".fa-bars")).click();
		driver.findElement(By.linkText("Logout")).click();
	}

	@Test
	void verifyAppoinmentBookingUI() {
		driver.manage().window().setSize(new Dimension(1212, 648));
		driver.findElement(By.cssSelector(".fa-bars")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("txt-username")).click();
		driver.findElement(By.id("txt-username")).sendKeys("John Doe");
		driver.findElement(By.id("txt-password")).click();
		driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.id("btn-login")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), ("Make Appointment"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".form-group:nth-child(1) > .col-sm-offset-3")).getText(),
				("Facility"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".checkbox-inline")).getText(),
				("Apply for hospital readmission"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".form-group:nth-child(3) > .col-sm-offset-3")).getText(),
				("Healthcare Program"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".radio-inline:nth-child(1)")).getText(), ("Medicare"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".radio-inline:nth-child(2)")).getText(), ("Medicaid"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".radio-inline:nth-child(3)")).getText(), ("None"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".form-group:nth-child(4) > .col-sm-offset-3")).getText(),
				("Visit Date (Required)"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".form-group:nth-child(5) > .col-sm-offset-3")).getText(),
				("Comment"));
		Assert.assertEquals(driver.findElement(By.id("btn-book-appointment")).getText(), ("Book Appointment"));
		driver.findElement(By.cssSelector(".fa-bars")).click();
		driver.findElement(By.linkText("Logout")).click();
	}

	@AfterClass
	void teardown() {

		driver.close();
	}
}
