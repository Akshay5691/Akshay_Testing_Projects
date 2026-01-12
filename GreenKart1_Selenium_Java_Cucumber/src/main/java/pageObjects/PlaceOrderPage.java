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

public class PlaceOrderPage extends ActionsUtilitiy {

	WebDriver driver;
    public static PlaceOrderPage instance;
	// ✅ Constructor
	public PlaceOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}
	

	// =================== 🔹 Locators ===================

	private By promoCodeBox() {
		return By.xpath("//input[@class='promoCode']");
	}

	private By promoCodeApplyButton() {
		return By.xpath("//button[@class='promoBtn']");
	}

	private By placeOrderButton() {
		return By.xpath("//button[text()='Place Order']");
	}

	private By promoCodeAppliedMessage() {
		return By.xpath("//span[text()='Code applied ..!']");
	}

	private By invalidPromoCodeMessage() {
		return By.xpath("//span[text()='Invalid code ..!']");
	}

	private By discountPercentage() {
		return By.xpath("//span[contains(text(),'%')]");
	}

	// ✅ Dynamic locator example (if you want to locate promo or discount by text)
	private By dynamicMessage(String messageText) {
		return By.xpath("//span[contains(text(),'" + messageText + "')]");
	}

	// =================== 🔹 Action Methods ===================

	public void enterPromoCodeAndApply(String promoCode) {
		try {

			type(promoCodeBox(), promoCode);
			
			click(promoCodeApplyButton());
			Thread.sleep(5000); // Wait for message to appear
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickOnPlaceOrderButton() {
		waitUntilElementClickable(placeOrderButton());
		click(placeOrderButton());
	}

	public String promoCodeAppliedMessageText() {
		waitUntilElementVisible(promoCodeAppliedMessage());
		return getText(promoCodeAppliedMessage());
	}

	public String invalidPromoCodeMessageText() {
		waitUntilElementVisible(invalidPromoCodeMessage());
		return getText(invalidPromoCodeMessage());
	}

	public int discountPercentageValue() {

		String discountText = getText(discountPercentage()); // e.g., "10%"
		return Integer.parseInt(discountText.replace("%", "").trim());
	}

	public String getDynamicMessageText(String messageText) {

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
