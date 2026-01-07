package PageObjects;

import org.openqa.selenium.By;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utilityClasses.ActionsUtilitiy;

public class ProductPage extends ActionsUtilitiy {

	AndroidDriver driver;
   public static ProductPage instance;
   
	public ProductPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public static ProductPage getProductPageObject(AndroidDriver driver) {

		if (instance == null) {
			instance = new ProductPage(driver);
		}
		return instance;
	}
	
	// =================== 🔹 Locators ===================

	private By addToCartButton(String productName) {
		return AppiumBy.xpath("//android.widget.TextView[@text='" + productName
				+ "']/parent::android.widget.LinearLayout//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']");
	}

	private By cartIcon() {
		return AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart");
	}
	
	private By cartIconCount() {
		return AppiumBy.id("com.androidsample.generalstore:id/counterText");
	}
	private By productPageTitle() {
		return AppiumBy.id("com.androidsample.generalstore:id/toolbar_title");
	}
	
	// =================== 🔹 Actions ===================

	public void addProductToCart(String productName) {
		waitUntilElementClickable(addToCartButton(productName));
		click(addToCartButton(productName));
	}
	public void clickOnCartIcon() {
		waitUntilElementClickable(cartIcon());
		click(cartIcon());
	}
	public String getAddedToCartButtonText(String productName) {
		waitUntilElementVisible(addToCartButton(productName));
		String buttonText = getText(addToCartButton(productName));
		return buttonText;
	}
	public int getCartIconCount() {
		waitUntilElementVisible(cartIconCount());
		int count = Integer.parseInt(getText(cartIconCount()));
		return count;
	}
	public String getProductPageTitle() {
		waitUntilElementVisible(productPageTitle());
		String title = getText(productPageTitle());
		return title;
	}

}
