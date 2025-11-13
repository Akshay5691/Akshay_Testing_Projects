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

	    // ✅ Constructor — initializes all elements
	    public CartPageMethods(WebDriver driver) {
	    	
	    	super(driver);
	    	 this.driver = driver;
	    }
	 // =================== 🔹 Locators ===================

	    private By removeButtonLocator() { return By.xpath("(//div//a[@class='product-remove'])[1]"); }
	    private By proceedToCheckoutButtonLocator() { return By.xpath("//button[text()='PROCEED TO CHECKOUT']"); }
	    private By itemQuantityLocator() { return By.xpath("(//p[@class='quantity'])[1]"); }
	    private By emptyCartMessageLocator() { return By.xpath("(//div//h2[contains(text(),'You cart is empty!')])[1]"); }

	    private By itemNameLocator(String itemName) { return By.xpath("//p[contains(text(),'" + itemName + "')]"); }

	    // =================== 🔹 WebElements ===================

	    private WebElement removeButton() { return driver.findElement(removeButtonLocator()); }
	    private WebElement proceedToCheckoutButton() { return driver.findElement(proceedToCheckoutButtonLocator()); }
	    private WebElement itemQuantity() { return driver.findElement(itemQuantityLocator()); }
	    private WebElement itemName(String itemName) { return driver.findElement(itemNameLocator(itemName)); }
	    private WebElement emptyCartMessage() { return driver.findElement(emptyCartMessageLocator()); }

	    // =================== 🔹 Action Methods ===================

	    public void clickOnRemoveButton() {
	        waitUntilElementClickable(removeButtonLocator());
	        click(removeButton());
	    }

	    public void clickOnProceedToCheckoutButton() {
	        waitUntilElementClickable(proceedToCheckoutButtonLocator());
	        click(proceedToCheckoutButton());
	    }

	    public String getItemQuantityText() {
	        waitUntilElementVisible(itemQuantityLocator());
	        return getText(itemQuantity());
	    }

	    public int getItemQuantity() {
	        waitUntilElementVisible(itemQuantityLocator());
	        String quantityText = getText(itemQuantity()); 
	        int itemQuantity =Integer.parseInt(quantityText.split(" ")[0]);
	        return itemQuantity;
	    }

	    public String getItemNameText(String itemNameText) {
	        waitUntilElementVisible(itemNameLocator(itemNameText));
	        String nameText = getText(itemName(itemNameText));
	        return nameText.split(" ")[0];
	    }

	    public String getEmptyCartMessageText() {
	        waitUntilElementVisible(emptyCartMessageLocator());
	        return getText(emptyCartMessage());
	    }

	    // Generic reusable element getter
	    public WebElement element(By locator) {
	        return driver.findElement(locator);
	    }
	    }

	


