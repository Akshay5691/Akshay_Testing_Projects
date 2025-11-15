package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import BasePage.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utilityClasses.ActionsUtilitiy;

public class ProductPageObjects extends ActionsUtilitiy {

	AndroidDriver driver;

	public ProductPageObjects(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}

	// =================== 🔹 Locators ===================

	private By addToCartButton(String productName) {
		return AppiumBy.xpath("//android.widget.TextView[@text='" + productName
				+ "']/parent::android.widget.LinearLayout//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']");
	}

	// =================== 🔹 Actions ===================

	public void addProductToCart(String productName) {
		waitUntilElementClickable(addToCartButton(productName));
		click(addToCartButton(productName));
	}

}
