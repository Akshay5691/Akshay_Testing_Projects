package PageObjects;

import utilityClasses.ActionsUtilitiy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class HomePageObjects extends ActionsUtilitiy {

	AndroidDriver driver;
	WebDriverWait wait;
	private static HomePageObjects instance;

	public HomePageObjects(AndroidDriver driver) {
		super(driver);
		this.driver = driver;

	}
	public static HomePageObjects getHomePageObject(AndroidDriver driver) {

		  if (instance == null) {
		        instance = new HomePageObjects(driver);
		    }
		    return instance;
	}

	// =================== Locators ===================

	private By countryDropdown() {
		return AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry");
	}

	private By nameField() {
		return AppiumBy.id("com.androidsample.generalstore:id/nameField");
	}

	private By genderRadioMale() {
		return AppiumBy.id("com.androidsample.generalstore:id/radioMale");
	}

	private By shopButton() {
		return AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop");
	}

	private By alertText() {
		return AppiumBy.xpath("//android.widget.Toast[@text=\"Please enter your name\"]");
	}

	private By genderRadioFemale() {
		return AppiumBy.id("com.androidsample.generalstore:id/radioFemale");
	}

	// String Methods

	public String getAlertText() {
		String alertText = getText(alertText());
		return alertText;
	}
	// Action methods

	public void selectCountry(String countryName) {
		click(countryDropdown());
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + countryName + "\"));")).click();
	}

	public void enterName(String name) {
		waitUntilElementVisible(nameField());
		type(nameField(), name);
	}
	
	public String getEnteredName() {
		waitUntilElementVisible(nameField());
		String enteredName = getText(nameField());
		return enteredName;
	}

	public String getSelectedCountry() {
		waitUntilElementVisible(countryDropdown());
		String selectedCountry = getText(countryDropdown());
		return selectedCountry;
	}
	
	public void selectGenderMale() {
		waitUntilElementClickable(genderRadioMale());
		click(genderRadioMale());
	}

	public void selectGenderFemale() {
		waitUntilElementClickable(genderRadioFemale());
		click(genderRadioFemale());
	}

	public void clickShopButton() {
		waitUntilElementClickable(shopButton());
		click(shopButton());
	}

	public boolean isGenderMaleSelected() throws InterruptedException {
		return getAttribute(genderRadioMale(), "checked").equals("true");
	}

	public boolean isGenderFemaleSelected() throws InterruptedException {
		return getAttribute(genderRadioFemale(), "checked").equals("true");
	}

}
