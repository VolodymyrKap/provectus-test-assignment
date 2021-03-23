package com.provectus.studentregistrationform;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StudentRegistrationForm {
	WebDriver driver;
	
	@FindBy(id="firstName")
	WebElement firstName;
	
	@FindBy(id="lastName")
	WebElement lastName;
	
	@FindBy(id="gender-radio-1")
	WebElement genderMale;
	
	@FindBy(xpath="//label[@for='gender-radio-1']")
	WebElement genderMaleLabel;
	
	@FindBy(id="gender-radio-2")
	WebElement genderFemale;
	
	@FindBy(xpath="//label[@for='gender-radio-2']")
	WebElement genderFemaleLabel;
	
	@FindBy(id="gender-radio-3")
	WebElement genderOther;
	
	@FindBy(xpath="//label[@for='gender-radio-3']")
	WebElement genderOtherLabel;
	
	@FindBy(id="userNumber")
	WebElement mobileNumber;
	
	@FindBy(id="submit")
	WebElement submitButton;
	
	public void setFirstName(String firstName) {
		this.firstName.sendKeys(firstName);
	}
	
	public void setLastName(String lastName) {
		this.lastName.sendKeys(lastName);
	}
	
	public void setPhoneNumber(String phoneNumber) {
		mobileNumber.sendKeys(phoneNumber);
	}
	
	public void setGender(Gender gender) throws Exception {
		Actions action = new Actions(driver);
		if (gender == Gender.MALE) { 
			action.moveToElement(genderMale).click().perform();
		} else if (gender == Gender.FEMALE) {
			action.moveToElement(genderFemale).click().perform();
		} else if (gender == Gender.OTHER) {
			action.moveToElement(genderOther).click().perform();
		} else {
			throw new Exception("Incorrect gender specified");
		}
		
	}
	
	public void submit() {
		submitButton.sendKeys(Keys.ENTER);
	}
	
	public StudentRegistrationForm(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
