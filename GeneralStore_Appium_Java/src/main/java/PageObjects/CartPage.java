package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utilityClasses.ActionsUtilitiy;

public class CartPage extends ActionsUtilitiy {

	AndroidDriver driver;
	private static CartPage instance;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;

	}

	public static CartPage getCartPageObject(AndroidDriver driver) {

		if (instance == null) {
			instance = new CartPage(driver);
		}
		return instance;
	}

	// =================== 🔹 Locators ===================

	private By termsAndConditionsCheckbox() {
		return AppiumBy.className("android.widget.CheckBox");}

	private By productNameInCart(String productName) {

		return AppiumBy.xpath("//android.widget.TextView[@text='" + productName + "']");
	}

	private By productPrice(String productName) {
		return AppiumBy.xpath("//android.widget.TextView[@text='" + productName
				+ "']/parent::android.widget.LinearLayout//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice']");
	}	
	
	private By totalAmountInCart() {
		return AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl");
	}
	

	// =================== 🔹 Actions ===================
	
	public void acceptTermsAndConditions() {
		waitUntilElementClickable(termsAndConditionsCheckbox());
		click(termsAndConditionsCheckbox());
	}
	 
	public String getProductNameInCart(String productName) {
		waitUntilElementVisible(productNameInCart(productName));
		String actualProductName =getText(productNameInCart(productName));
	   return actualProductName;
	}
	
	public double getProductPriceInCart(String productName) {
		waitUntilElementVisible(productPrice(productName));
		String product=(getText(productPrice(productName)));
			String priceWithoutSymbol = product.replaceAll("[^\\d.]", ""); // Remove currency symbol
		 double price = Double.parseDouble(priceWithoutSymbol);
		    return price;
	}
	
	public double getTotalAmountInCart() {
		waitUntilElementVisible(totalAmountInCart());
		String totalAmountText = getText(totalAmountInCart());
		String amountWithoutSymbol = totalAmountText.replaceAll("[^\\d.]", ""); // Remove currency symbol
		double totalAmount = Double.parseDouble(amountWithoutSymbol);
		return totalAmount;
	}
	
	public boolean isTermsAndConditionsAccepted() {
		WebElement checkbox = element(termsAndConditionsCheckbox());
		return checkbox.getAttribute("checked").equals("true");

}
}