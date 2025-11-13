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
    			this.driver = driver;
       
    }
    
       //=================== 🔹 Locators ===================
    
    	private By termsAndConditionsCheckBoxLocator() { return By.xpath("//input[@class='chkAgree']"); }
    public By proceedButtonLocator() { return By.xpath("//button[text()='Proceed']"); }
    private By countryDropdownLocator() { return By.xpath("//select[@style='width: 200px;']"); }
    private By acceptTermsAndConditionsMessageLocator() { return By.xpath("//span//b[text()='Please accept Terms & Conditions - Required']"); }

    // =================== 🔹 WebElements ===================

    private WebElement termsAndConditionsCheckBox() { return driver.findElement(termsAndConditionsCheckBoxLocator()); }
    private WebElement proceedButton() { return driver.findElement(proceedButtonLocator()); }
    private WebElement countryDropdown() { return driver.findElement(countryDropdownLocator()); }
    private WebElement acceptTermsAndConditionsMessage() { return driver.findElement(acceptTermsAndConditionsMessageLocator()); }

    // =================== 🔹 Action Methods ===================

    public void clickOnTermsAndConditions() {
        waitUntilElementClickable(termsAndConditionsCheckBoxLocator());
        click(termsAndConditionsCheckBox());
    }

    public void clickOnProceedButton() {
        waitUntilElementClickable(proceedButtonLocator());
        click(proceedButton());
    }

    public void selectCountry(String countryName) {
        waitUntilElementVisible(countryDropdownLocator());
        Select select = new Select(countryDropdown());
        select.selectByVisibleText(countryName);
    }

    public String acceptTermsAndConditionsMessageText() {
        waitUntilElementVisible(acceptTermsAndConditionsMessageLocator());
        return getText(acceptTermsAndConditionsMessage());
    }

    public WebElement proceedButtonElement() {
        waitUntilElementVisible(proceedButtonLocator());
        return proceedButton();
    }

    public WebElement termsAndConditionsCheckBoxElement() {
        waitUntilElementVisible(termsAndConditionsCheckBoxLocator());
        return termsAndConditionsCheckBox();
    }

    public String getproceedButton() {
		
		return getText(proceedButton());
	}

}
