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
	    }

	    // =================== 🔹 Locators ===================

	    private By removeButton = By.xpath("(//div//a[@class='product-remove'])[1]");
	    private By proceedToCheckoutButton = By.xpath("//button[text()='PROCEED TO CHECKOUT']");
	    private By itemQuantity = By.xpath("(//p[@class='quantity'])[1]");
	    private By itemName = By.xpath("//p[contains(text(),'Apple - 1 Kg')]");
	    private By emptyCartMessage = By.xpath("(//div//h2[contains(text(),'Your cart is empty!')])[1]");

	    // =================== 🔹 Action Methods ===================

	    /** Click on Remove Button */
	    public void clickOnRemoveButton() {
	        waitUntilElementClickable(removeButton);
	        click(removeButton);
	    }

	    /** Click on Proceed To Checkout Button */
	    public void clickOnProceedToCheckoutButton() {
	        waitUntilElementClickable(proceedToCheckoutButton);
	        click(proceedToCheckoutButton);
	    }

	   
	    public String getItemQuantityString() {
	        waitUntilElementVisible(itemQuantity);
	        return getText(itemQuantity);
	    }

	  
	    public int getItemQuantity() {
	        waitUntilElementVisible(itemQuantity);
	        String quantityText = getText(itemQuantity); // e.g., "1 No(s)"
	        return Integer.parseInt(quantityText.split(" ")[0]);
	    }

	  
	    public String getItemName() {
	        waitUntilElementVisible(itemName);
	        String nameText = getText(itemName); // e.g., "Apple - 1 Kg"
	        return nameText.split(" ")[0];
	    }

	
	    public String getEmptyCartMessage() {
	        waitUntilElementVisible(emptyCartMessage);
	        return getText(emptyCartMessage);
	    }

		public String getEmptyPageMessageInCart() {
			// TODO Auto-generated method stub
			return null;
		}
		public WebElement getElement(By locator) {
		    return driver.findElement(locator);
		}
	  
	   
	    }

	


