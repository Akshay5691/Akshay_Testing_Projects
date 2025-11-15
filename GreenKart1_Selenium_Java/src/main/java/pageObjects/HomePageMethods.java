package pageObjects;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ActionsUtilitiy;

public class HomePageMethods extends ActionsUtilitiy {

	WebDriver driver;
	WebDriverWait wait;

	public HomePageMethods(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}
	
    // driver.findelement(By.Tagname("label")).above(element);

	// =================== 🔹 Locators ===================

	private By searchBox() {
		return By.xpath("//input[@class='search-keyword']");
	}

	private By searchButton() {
		return By.xpath("//button[@class='search-button']");
	}

	private By addToCartButton(String product) {
		return By.xpath("//h4[text()='" + product + " - 1 Kg']/following-sibling::div//button[text()='ADD TO CART']");
	}

	private By cartBagIcon() {
		return By.xpath("//a[@class='cart-icon']");
	}

	private By plusButtonAddToCart() {
		return By.xpath("(//div//a[@class='increment'])[1]");
	}

	private By minusButtonAddToCart() {
		return By.xpath("(//div//a[@class='decrement'])[1]");
	}

	private By quantityBoxOnItem() {
		return By.xpath("(//input[@class='quantity'])[1]");
	}

	private By itemNumber() {
		return By.xpath("(//tbody//tr//td[3])[1]");
	}

	private By itemPrice() {
		return By.xpath("(//tbody//tr[2]//td[3])[1]");
	}

	private By defaultQuantity() {
		return By.xpath("//input[@class='quantity']");
	}

	// ✅ Dynamic locator using parameter
	private By productName(String itemName) {
		return By.xpath("//h4[contains(text(),'" + itemName + "')]");
	}

	// =================== 🔹 Action Methods ===================

	public void searchItem(String itemName) {

		waitUntilElementVisible(searchBox());
		type(searchBox(), itemName);
		waitUntilElementClickable(searchButton());
		click(searchButton());
	}

	public void searchItemAndAddToCart(String itemName) {
		waitUntilElementVisible(searchBox());
		searchItem(itemName);
		waitUntilElementClickable(addToCartButton(itemName));
		clickOnAddToCart(itemName);
	}

	public void clickOnCartBag() {
		waitUntilElementClickable(cartBagIcon());
		click(cartBagIcon());
	}

	public void clickOnAddToCart(String product) {
		waitUntilElementClickable(addToCartButton(product));
		click(addToCartButton(product));

	}

	public void clickMinusButtonAddToCart() {
		waitUntilElementClickable(minusButtonAddToCart());
		click(minusButtonAddToCart());
	}

	public void clickOnPlusButtonAddToCart() {
		waitUntilElementClickable(plusButtonAddToCart());
		click(plusButtonAddToCart());
	}

	public void clickOnQuantityBox(String value) {
		waitUntilElementVisible(quantityBoxOnItem());
		clear(quantityBoxOnItem());
		type(quantityBoxOnItem(), value);
		waitUntilElementClickable(quantityBoxOnItem());
		click(quantityBoxOnItem());
	}

	public int getItemNumberValue() {
		waitUntilElementVisible(itemNumber());
		return getIntValue(itemNumber());
	}

	public int getItemPriceValue() {
		waitUntilElementVisible(itemPrice());
		return getIntValue(itemPrice());
	}

	public int getItemQuantityInBox() {
		waitUntilElementVisible(defaultQuantity());
		return getIntValueByAttribute(defaultQuantity(), "value");
	}

	public String getProductName(String itemName) {
		waitUntilElementVisible(productName(itemName));
		String productText = getText(productName(itemName));
		return productText.split(" ")[0];
	}

	public void searchMultipleItems(String[] items) {
		for (String item : items) {
			searchItem(item);
			clickOnAddToCart(item);
		}
	}

	public void clearQuantityBox() {
		waitUntilElementVisible(quantityBoxOnItem());
		clear(quantityBoxOnItem());
	}

	public void enterQuantityInBox(String value) {
		waitUntilElementVisible(quantityBoxOnItem());
		type(quantityBoxOnItem(), value);
	}

}
