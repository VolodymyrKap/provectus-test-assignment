package com.provectus.studentregistrationform;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestStudentRegistrationFormTestNG {
	WebDriver driver;
	String baseUrl;
	StudentRegistrationForm srf;

	@Test (enabled = true)
	public void submitForm() throws Exception {
		driver.get(baseUrl);
		String successfulResult = "Thanks for submitting the form";
		Student person = new Student("Oksana", "Butko", Gender.FEMALE, "2125550505");

		// Navigate to Forms
		driver.findElement(By.xpath("//div[@class='card mt-4 top-card'][2]")).click();

		// Click "Practice Form"
		driver.findElement(By.xpath("//span[text()='Practice Form']")).click();

		srf = new StudentRegistrationForm(driver);
		srf.setFirstName(person.getFirstName());
		srf.setLastName(person.getLastName());
		srf.setGender(person.getGender());
		srf.setPhoneNumber(person.getPhoneNumber());
		srf.submit();

		String actualResult = driver.findElement(By.id("example-modal-sizes-title-lg")).getText();
		Assert.assertEquals(actualResult, successfulResult, "Form failed submission");
		Thread.sleep(5000);
	}

	@Test (enabled = true)
	public void verifyMandatoryFields() throws InterruptedException {
		driver.get(baseUrl);
		String mandatoryElementBorderColor = "rgba(220, 53, 69, 1)"; //red color, element is mandatory
		
		// Navigate to Forms
		driver.findElement(By.xpath("//div[@class='card mt-4 top-card'][2]")).click();

		// Click "Practice Form"
		driver.findElement(By.xpath("//span[text()='Practice Form']")).click();
		
		srf = new StudentRegistrationForm(driver);
		srf.submit();
		Thread.sleep(getTransitionDuration(srf.firstName.getCssValue("transition-duration"))); //wait until border color change stops
		
		Assert.assertEquals(srf.firstName.getCssValue("border-bottom-color"), mandatoryElementBorderColor, "Element is not mandatory");
		Assert.assertEquals(srf.lastName.getCssValue("border-bottom-color"), mandatoryElementBorderColor, "Element is not mandatory");
		Assert.assertEquals(srf.genderMaleLabel.getCssValue("color"), mandatoryElementBorderColor, "Element is not mandatory");

	}
	
	private long getTransitionDuration(String td) {
		float firstDuration; //in seconds
		float secondDuration; //in seconds
		
		firstDuration = Float.valueOf(td.substring(0, td.indexOf('s')));
		secondDuration = Float.valueOf(td.substring(td.indexOf(' ') + 1, td.lastIndexOf('s')));
		
		return (long)((firstDuration + secondDuration) * 1000); //total duration in milliseconds.
	}

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.edge.driver", "C:\\Selenium\\Drivers\\edgedriver_win64\\msedgedriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Drivers\\chromedriver_win32\\chromedriver.exe");		
		driver = new EdgeDriver();
		//driver = new ChromeDriver();
		baseUrl = "https://demoqa.com/";
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void cleanUp() throws InterruptedException {
		//Thread.sleep(5000);
		driver.quit();
	}

}
