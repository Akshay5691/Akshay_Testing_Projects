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

public class ProceedPageMethods {
	   WebDriver driver;
	   // ✅ Constructor
    public ProceedPageMethods(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // =================== 🔹 Web Elements ===================

    @FindBy(xpath = "//input[@class='chkAgree']")
    private WebElement termsAndConditionsCheckBox;

    @FindBy(xpath = "//button[text()='Proceed']")
    public WebElement proceedButton;

    @FindBy(xpath = "//select[@style='width: 200px;']")
    private WebElement countryDropdown;

    @FindBy(xpath = "//span//b[text()='Please accept Terms & Conditions - Required']")
    public WebElement acceptTermsAndConditionsMessage;

    // =================== 🔹 Action Methods ===================

    public void clickOnTermsAndConditions() {
        termsAndConditionsCheckBox.click();
    }

    public void clickOnProceedButton() {
        proceedButton.click();
    }

    public void waitUntilElementVisible(String elementXpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
    }

    public Select selectCountry() {
        return new Select(countryDropdown);
    }

    public String getAcceptTheTermsAndConditionsMessage() {
        return acceptTermsAndConditionsMessage.getText();
    }

	public WebElement proceedButton() {
		// TODO Auto-generated method stub
		return null;
	}

	public WebElement getTermsAndConditionsCheckBox() {
		// TODO Auto-generated method stub
		return null;
	}

}
