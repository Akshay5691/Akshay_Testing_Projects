package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ActionsUtilitiy;

public class PlaceOrderPageMethods extends ActionsUtilitiy {

	   WebDriver driver;

	    // ✅ Constructor
	    public PlaceOrderPageMethods(WebDriver driver) {
	    	super(driver);
	    		        this.driver = driver;
	      
	    }

	 // =================== 🔹 Locators ===================

	    private By promoCodeBoxLocator() { return By.xpath("//input[@class='promoCode']"); }
	    private By promoCodeApplyButtonLocator() { return By.xpath("//button[@class='promoBtn']"); }
	    private By placeOrderButtonLocator() { return By.xpath("//button[text()='Place Order']"); }
	    private By promoCodeAppliedMessageLocator() { return By.xpath("//span[text()='Code applied ..!']"); }
	    private By invalidPromoCodeMessageLocator() { return By.xpath("//span[text()='Invalid code ..!']"); }
	    private By discountPercentageLocator() { return By.xpath("//span[contains(text(),'%')]"); }

	    // ✅ Dynamic locator example (if you want to locate promo or discount by text)
	    private By dynamicMessageLocator(String messageText) { return By.xpath("//span[contains(text(),'" + messageText + "')]"); }

	    // =================== 🔹 WebElements ===================

	    private WebElement promoCodeBox() { return driver.findElement(promoCodeBoxLocator()); }
	    private WebElement promoCodeApplyButton() { return driver.findElement(promoCodeApplyButtonLocator()); }
	    private WebElement placeOrderButton() { return driver.findElement(placeOrderButtonLocator()); }
	    private WebElement promoCodeAppliedMessage() { return driver.findElement(promoCodeAppliedMessageLocator()); }
	    private WebElement invalidPromoCodeMessage() { return driver.findElement(invalidPromoCodeMessageLocator()); }
	    private WebElement discountPercentage() { return driver.findElement(discountPercentageLocator()); }
	    private WebElement dynamicMessage(String messageText) { return driver.findElement(dynamicMessageLocator(messageText)); }

	    // =================== 🔹 Action Methods ===================

	    public void enterPromoCodeAndApply(String promoCode) {       
	        try {
	        	 waitUntilElementVisible(promoCodeBoxLocator());
	 	        type(promoCodeBox(), promoCode);
	 	        waitUntilElementClickable(promoCodeApplyButtonLocator());
	 	        click(promoCodeApplyButton());
				Thread.sleep(5000);   // Wait for message to appear
			} catch (InterruptedException e) {			
				e.printStackTrace();
			} 
	    }

	    public void clickOnPlaceOrderButton() {
	        waitUntilElementClickable(placeOrderButtonLocator());
	        click(placeOrderButton());
	    }

	    public String promoCodeAppliedMessageText() {
	        waitUntilElementVisible(promoCodeAppliedMessageLocator());
	        return getText(promoCodeAppliedMessage());
	    }

	    public String invalidPromoCodeMessageText() {
	        waitUntilElementVisible(invalidPromoCodeMessageLocator());
	        return getText(invalidPromoCodeMessage());
	    }

	    public int discountPercentageValue() {
	        waitUntilElementVisible(discountPercentageLocator());
	        String discountText = getText(discountPercentage()); // e.g., "10%"
	        return Integer.parseInt(discountText.replace("%", "").trim());
	    }

	    // ✅ Example: Get dynamic promo or status message
	    public String getDynamicMessageText(String messageText) {
	        waitUntilElementVisible(dynamicMessageLocator(messageText));
	        return getText(dynamicMessage(messageText));
	    }
         public String getPlaceOrderButton() {
			
			return getText(placeOrderButton());
		}

	    // =================== 🔹 Cart Grid Methods ===================

	    public int getQuantityOfItemFromGrid(String item) {
	        List<WebElement> cartRows = driver.findElements(By.xpath("//table//tbody//tr"));
	        for (WebElement row : cartRows) {
	            List<WebElement> columns = row.findElements(By.tagName("td"));
	            String actualProductName = columns.get(1).getText();
	            if (actualProductName.contains(item)) {
	                return Integer.parseInt(columns.get(2).getText());
	            }
	        }
	        return 0;
	    }

	    public int getCostOfItemFromGrid(String item) {
	        List<WebElement> cartRows = driver.findElements(By.xpath("//table//tbody//tr"));
	        for (WebElement row : cartRows) {
	            List<WebElement> columns = row.findElements(By.tagName("td"));
	            String itemName = columns.get(1).getText();
	            if (itemName.contains(item)) {
	                return Integer.parseInt(columns.get(3).getText());
	            }
	        }
	        return 0;
	    }

	    public int getTotalCostOfItemFromGrid(String item) {
	        List<WebElement> cartRows = driver.findElements(By.xpath("//table//tbody//tr"));
	        for (WebElement row : cartRows) {
	            List<WebElement> columns = row.findElements(By.tagName("td"));
	            String itemName = columns.get(1).getText();
	            if (itemName.contains(item)) {
	                return Integer.parseInt(columns.get(4).getText());
	            }
	        }
	        return 0;
	    }

		
	
}
