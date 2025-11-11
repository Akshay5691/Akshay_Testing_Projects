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
	        PageFactory.initElements(driver, this);
	    }

	    // =================== 🔹 Web Elements ===================

	    @FindBy(xpath = "//input[@class='promoCode']")
	    private WebElement promoCodeBox;

	    @FindBy(xpath = "//button[@class='promoBtn']")
	    private WebElement promoCodeApplyButton;

	    @FindBy(xpath = "//button[text()='Place Order']")
	    public WebElement placeOrderButton;

	    @FindBy(xpath = "//span[text()='Code applied ..!']")
	    private WebElement promoCodeAppliedMessage;

	    @FindBy(xpath = "//span[text()='Invalid code ..!']")
	    private WebElement invalidPromoCodeMessage;

	    @FindBy(xpath = "//span[text()='10%']")
	    private WebElement discountPercentage;

	    // =================== 🔹 Action Methods ===================

	    public void enterPromoCodeAndApply(String promoCode) {
	        promoCodeBox.sendKeys(promoCode);
	        waitUntilElementClickable(promoCodeApplyButton);
	        promoCodeApplyButton.click();
	    }

	    public void clickOnPlaceOrderButton() {
	       waitUntilElementClickable(placeOrderButton);
	        placeOrderButton.click();
	    }

	    public String getPromoCodeAppliedMessage() {
	    	waitUntilElementVisible(promoCodeAppliedMessage);
	        return promoCodeAppliedMessage.getText();
	    }

	    public String getInvalidPromoCodeApplied() {
	    	waitUntilElementVisible(invalidPromoCodeMessage);
	        return invalidPromoCodeMessage.getText();
	    }

	    public int getDiscountPercentage() {
	    	waitUntilElementVisible(discountPercentage);
	        String discountText = discountPercentage.getText(); // "10%"
	        return Integer.parseInt(discountText.split("%")[0]);
	    }

	   

	    // =================== 🔹 Cart Grid Methods ===================

	    public int getQuantityOfItemFromGrid(String item) {
	        List<WebElement> cartTableRows = driver.findElements(By.xpath("//table//tbody//tr"));
	        int itemQuantity = 0;

	        for (WebElement row : cartTableRows) {
	            List<WebElement> columns = row.findElements(By.xpath("td"));
	            String actualProductName = columns.get(1).getText();

	            if (actualProductName.contains(item)) {
	                String itemQuantityString = columns.get(2).getText();
	                itemQuantity = Integer.parseInt(itemQuantityString);
	                break;
	            }
	        }
	        return itemQuantity;
	    }

	    public int getCostOfItemFromGrid(String item) {
	        List<WebElement> rowsOfTable = driver.findElements(By.xpath("//table//tbody//tr"));
	        int costOfItem = 0;

	        for (WebElement row : rowsOfTable) {
	            List<WebElement> columns = row.findElements(By.xpath("td"));
	            String itemName = columns.get(1).getText();

	            if (itemName.contains(item)) {
	                int itemCost = Integer.parseInt(columns.get(3).getText());
	                costOfItem += itemCost;
	            }
	        }
	        return costOfItem;
	    }

	    public int getTotalCostOfItemFromGrid(String item) {
	        List<WebElement> rowsOfTable = driver.findElements(By.xpath("//table//tbody//tr"));
	        int totalCostOfItem = 0;

	        for (WebElement row : rowsOfTable) {
	            List<WebElement> columns = row.findElements(By.xpath("td"));
	            String itemName = columns.get(1).getText();

	            if (itemName.contains(item)) {
	                int itemTotalCost = Integer.parseInt(columns.get(4).getText());
	                totalCostOfItem += itemTotalCost;
	            }
	        }
	        return totalCostOfItem;
	    }
	
}
