package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ActionsUtilitiy;

public class CartPageMethods extends ActionsUtilitiy {

	WebDriver driver;
	public static CartPageMethods instance;

	// ✅ Constructor — initializes all elements
	public CartPageMethods(WebDriver driver) {

		super(driver);
		this.driver = driver;
	}
	
	public static CartPageMethods getCartPageObject(WebDriver driver) {
		
		if (instance == null) {
	        instance = new CartPageMethods(driver);
	    }
	    return instance;
	}
	// =================== 🔹 Locators ===================

	private By removeButton() {
		return By.xpath("(//div//a[@class='product-remove'])[1]");
	}

	private By proceedToCheckoutButton() {
		return By.xpath("//button[text()='PROCEED TO CHECKOUT']");
	}

	private By itemQuantity() {
		return By.xpath("(//p[@class='quantity'])[1]");
	}

	private By emptyCartMessage() {
		return By.xpath("(//div//h2[contains(text(),'You cart is empty!')])[1]");
	}

	private By itemName(String itemName) {
		return By.xpath("//p[contains(text(),'" + itemName + "')]");
	}

	// =================== 🔹 Action Methods ===================

	public void clickOnRemoveButton() {
		click(removeButton());
	}

	public void clickOnProceedToCheckoutButton() {
		click(proceedToCheckoutButton());
	}

	public String getItemQuantityText() {
		return getText(itemQuantity());
	}

	public int getItemQuantity() {
		String quantityText = getText(itemQuantity());
		int itemQuantity = Integer.parseInt(quantityText.split(" ")[0]);
		return itemQuantity;
	}

	public String getItemNameText(String itemNameText) {
		String nameText = getText(itemName(itemNameText));
		return nameText.split(" ")[0];
	}

	public String getEmptyCartMessageText() {
		return getText(emptyCartMessage());
	}

	// Generic reusable element getter

}