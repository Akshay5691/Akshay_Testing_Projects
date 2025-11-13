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
	      
	    }

          	    // =================== 🔹 Locators ===================
	    
	    private By promoCodeBox = By.xpath("//input[@class='promoCode']");
	    private By promoCodeApplyButton = By.xpath("//button[@class='promoBtn']");
	    public By placeOrderButton = By.xpath("//button[text()='Place Order']");
	    private By promoCodeAppliedMessage = By.xpath("//span[text()='Code applied ..!']");
	    private By invalidPromoCodeMessage = By.xpath("//span[text()='Invalid code ..!']");
	    private By discountPercentage = By.xpath("//span[contains(text(),'%')]");

	    // =================== 🔹 Action Methods ===================

	    /** Enter Promo Code and Click Apply */
	    public void enterPromoCodeAndApply(String promoCode) {
	        waitUntilElementVisible(promoCodeBox);
	        type(promoCodeBox, promoCode);
	        waitUntilElementClickable(promoCodeApplyButton);
	        click(promoCodeApplyButton);
	    }

	    /** Click on Place Order Button */
	    public void clickOnPlaceOrderButton() {
	        waitUntilElementClickable(placeOrderButton);
	        click(placeOrderButton);
	    }

	    /** Get Message: Promo Code Applied */
	    public String getPromoCodeAppliedMessage() {
	        waitUntilElementVisible(promoCodeAppliedMessage);
	        return getText(promoCodeAppliedMessage);
	    }

	    /** Get Message: Invalid Promo Code */
	    public String getInvalidPromoCodeMessage() {
	        waitUntilElementVisible(invalidPromoCodeMessage);
	        return getText(invalidPromoCodeMessage);
	    }

	    /** Get Discount Percentage (e.g., "10%") */
	    public int getDiscountPercentage() {
	        waitUntilElementVisible(discountPercentage);
	        String discountText = getText(discountPercentage); // e.g. "10%"
	        return Integer.parseInt(discountText.replace("%", "").trim());
	    }
	    public WebElement getElement(By locator) {
		    return driver.findElement(locator);
		}
	  

	    // =================== 🔹 Cart Grid Methods ===================

	    /** Get Quantity of a Specific Item from the Cart Grid */
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

	    /** Get Cost of a Specific Item from the Cart Grid */
	    public int getCostOfItemFromGrid(String item) {
	        List<WebElement> cartRows = driver.findElements(By.xpath("//table//tbody//tr"));
	        int costOfItem = 0;
	        for (WebElement row : cartRows) {
	            List<WebElement> columns = row.findElements(By.tagName("td"));
	            String itemName = columns.get(1).getText();
	            if (itemName.contains(item)) {
	                costOfItem += Integer.parseInt(columns.get(3).getText());
	            }
	        }
	        return costOfItem;
	    }

	    /** Get Total Cost of a Specific Item from the Cart Grid */
	    public int getTotalCostOfItemFromGrid(String item) {
	        List<WebElement> cartRows = driver.findElements(By.xpath("//table//tbody//tr"));
	        int totalCost = 0;
	        for (WebElement row : cartRows) {
	            List<WebElement> columns = row.findElements(By.tagName("td"));
	            String itemName = columns.get(1).getText();
	            if (itemName.contains(item)) {
	                totalCost += Integer.parseInt(columns.get(4).getText());
	            }
	        }
	        return totalCost;
	    }
	
}
