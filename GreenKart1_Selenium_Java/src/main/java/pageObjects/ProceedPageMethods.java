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

public class ProceedPageMethods extends ActionsUtilitiy {
	WebDriver driver;

	public ProceedPageMethods(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	// =================== 🔹 Locators ===================

	private By termsAndConditionsCheckBox() {
		return By.xpath("//input[@class='chkAgree']");
	}

	public By proceedButton() {
		return By.xpath("//button[text()='Proceed']");
	}

	private By countryDropdown() {
		return By.xpath("//select[@style='width: 200px;']");
	}

	private By acceptTermsAndConditionsMessage() {
		return By.xpath("//span//b[text()='Please accept Terms & Conditions - Required']");
	}

	// =================== 🔹 Action Methods ===================

	public void clickOnTermsAndConditions() {
		waitUntilElementClickable(termsAndConditionsCheckBox());
		click(termsAndConditionsCheckBox());
	}

	public void clickOnProceedButton() {
		waitUntilElementClickable(proceedButton());
		click(proceedButton());
	}

	public void selectCountryByVisibleText(String countryName) {
		waitUntilElementVisible(countryDropdown());
		selectByVisibleText(countryDropdown(), countryName);

	}

	public String acceptTermsAndConditionsMessageText() {
		waitUntilElementVisible(acceptTermsAndConditionsMessage());
		return getText(acceptTermsAndConditionsMessage());
	}

	public WebElement proceedButtonElement() {
		waitUntilElementVisible(proceedButton());
		return driver.findElement(proceedButton());
	}

	public WebElement termsAndConditionsCheckBoxElement() {
		waitUntilElementVisible(termsAndConditionsCheckBox());
		return driver.findElement(termsAndConditionsCheckBox());

	}

	public String getproceedButton() {

		return getText(proceedButton());
	}

}
