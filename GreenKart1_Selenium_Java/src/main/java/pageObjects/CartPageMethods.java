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
	        PageFactory.initElements(driver, this);
	    }

	    // =================== 🔹 Web Elements ===================

	    @FindBy(xpath = "(//div//a[@class='product-remove'])[1]")
	    private WebElement removeButton;

	    @FindBy(xpath = "//button[text()='PROCEED TO CHECKOUT']")
	    private WebElement proceedToCheckOutButton;

	    @FindBy(xpath = "(//p[@class='quantity'])[1]")
	    private WebElement itemQuantity;

	    @FindBy(xpath = "//p[text()='Apple - 1 Kg']")
	    private WebElement itemName;

	    @FindBy(xpath = "(//div//h2[text()='You cart is empty!'])[1]")
	    private WebElement emptyPageMessageInCart;

	    // =================== 🔹 Action Methods ===================

	    public void clickOnRemoveButton() {
	    	waitUntilElementClickable(removeButton);
	        removeButton.click();
	    }

	    public void clickOnProceedToCheckOutButton() {
	    	waitUntilElementClickable(proceedToCheckOutButton);
	        proceedToCheckOutButton.click();
	    }

	    public String getItemQuantityString() {
	    	waitUntilElementVisible(itemQuantity);
	        return itemQuantity.getText();
	    }

	    public int getItemQuantity() {
	    	waitUntilElementVisible(itemQuantity);
	        String quantityText = itemQuantity.getText(); // e.g. "1 No(s)"
	        int itemCount = Integer.parseInt(quantityText.split(" ")[0]);
	        return itemCount;
	    }

	    public String getItemName() {
	    	waitUntilElementVisible(itemName);
	        String nameText = itemName.getText(); // e.g. "Apple - 1 Kg1"
	        return nameText.split(" ")[0]; // "Apple"
	    }

	    public String getEmptyPageMessageInCart() {
	    	waitUntilElementVisible(emptyPageMessageInCart);
	        return emptyPageMessageInCart.getText();
	    }

	  
	}
	


