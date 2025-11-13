package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ActionsUtilitiy;

public class ProceedPageMethods  extends ActionsUtilitiy {
	   WebDriver driver;
	  
    public ProceedPageMethods(WebDriver driver) {
    	super(driver);
       
    }
 // =========================================
 // 🔹 Locators (By Locators instead of @FindBy)
 // =========================================

 private By termsAndConditionsCheckBox = By.xpath("//input[@class='chkAgree']");
 public By proceedButton = By.xpath("//button[text()='Proceed']");
 private By countryDropdown = By.xpath("//select[@style='width: 200px;']");
 public By acceptTermsAndConditionsMessage = By.xpath("//span//b[text()='Please accept Terms & Conditions - Required']");


 // =========================================
 // 🔹 Action Methods
 // =========================================

 public void clickOnTermsAndConditions() {
     waitUntilElementClickable(termsAndConditionsCheckBox);
     click(termsAndConditionsCheckBox);
 }
 
 public void clickOnProceedButton() {
     waitUntilElementClickable(proceedButton);
     click(proceedButton);
 }

 public Select selectCountry() {
     waitUntilElementVisible(countryDropdown);
     WebElement dropdownElement = getElement(countryDropdown);
     return new Select(dropdownElement);
 }

 public String getAcceptTheTermsAndConditionsMessage() {
     waitUntilElementVisible(acceptTermsAndConditionsMessage);
     return getText(acceptTermsAndConditionsMessage);
 }

 public WebElement getProceedButtonElement() {
     waitUntilElementVisible(proceedButton);
     return getElement(proceedButton);
 }

 public WebElement getTermsAndConditionsCheckBoxElement() {
     waitUntilElementVisible(termsAndConditionsCheckBox);
     return getElement(termsAndConditionsCheckBox);
 }
 public WebElement getElement(By locator) {
	    return driver.findElement(locator);
	}



}
