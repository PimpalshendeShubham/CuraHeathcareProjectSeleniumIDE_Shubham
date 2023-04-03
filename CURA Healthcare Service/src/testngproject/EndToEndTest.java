package testngproject;

import static org.junit.Assert.assertThat;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EndToEndTest {

	WebDriver driver;

	@BeforeClass
	void initSetUP() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ASUS\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://katalon-demo-cura.herokuapp.com/");
	}

	@Test
	void verifyEndToEndFlow() {
		driver.manage().window().setSize(new Dimension(1550, 838));
		driver.findElement(By.id("btn-make-appointment")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".lead")).getText(),
				("Please login to make appointment."));
		driver.findElement(By.id("txt-username")).click();
		driver.findElement(By.id("txt-username")).sendKeys("John Doe");
		driver.findElement(By.id("txt-username")).sendKeys(Keys.DOWN);
		driver.findElement(By.id("txt-username")).sendKeys(Keys.TAB);
		driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.id("btn-login")).click();
		{
			WebElement dropdown = driver.findElement(By.id("combo_facility"));
			dropdown.findElement(By.xpath("//option[. = 'Hongkong CURA Healthcare Center']")).click();
		}
		driver.findElement(By.cssSelector("option:nth-child(2)")).click();
		driver.findElement(By.id("chk_hospotal_readmission")).click();
		driver.findElement(By.id("radio_program_medicaid")).click();
		driver.findElement(By.cssSelector(".glyphicon")).click();
		driver.findElement(By.cssSelector("tr:nth-child(4) > .day:nth-child(5)")).click();
		driver.findElement(By.cssSelector(".input-group-addon")).click();
		driver.findElement(By.id("txt_visit_date")).click();
		driver.findElement(By.cssSelector("tr > .active")).click();
		driver.findElement(By.id("btn-book-appointment")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), ("Appointment Confirmation"));
		Assert.assertEquals(driver.findElement(By.id("visit_date")).getText(), ("23/03/2023"));
		driver.findElement(By.linkText("Go to Homepage")).click();
		driver.findElement(By.id("menu-toggle")).click();
		driver.findElement(By.linkText("History")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), ("History"));
		Assert.assertEquals(driver.findElement(By.cssSelector(".panel-body")).getText(),
				("Facility\nHongkong CURA Healthcare Center\nApply for hospital readmission\nYes\nHealthcare Program\nMedicaid\nComment"));
		driver.findElement(By.linkText("Go to Homepage")).click();
		driver.findElement(By.cssSelector(".fa-bars")).click();
		driver.findElement(By.linkText("Logout")).click();
	}

	@AfterClass
	void teardown() {

		driver.close();
	}

}
