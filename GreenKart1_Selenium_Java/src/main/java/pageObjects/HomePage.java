package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ActionsUtilitiy;

public class HomePage extends ActionsUtilitiy {

	WebDriver driver;


	public HomePage(WebDriver driver) {
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
		type(searchBox(), itemName);
		click(searchButton());
	}

	public void clickOnCartBag() {
		click(cartBagIcon());
	}

	public void clickOnAddToCart(String product) {
		click(addToCartButton(product));
	}

	public void searchItemAndAddToCart(String itemName) {
		searchItem(itemName);
		clickOnAddToCart(itemName);
	}

	public void clickMinusButtonAddToCart() {
		click(minusButtonAddToCart());
	}

	public void clickOnPlusButtonAddToCart() {
		click(plusButtonAddToCart());
	}

	public void clickOnQuantityBox(String value) {
		clear(quantityBoxOnItem());
		type(quantityBoxOnItem(), value);
		click(quantityBoxOnItem());
	}

	public int getItemNumberValue() {
		return getIntValue(itemNumber());
	}

	public int getItemPriceValue() {
		return getIntValue(itemPrice());
	}

	public int getItemQuantityInBox() {
		return getIntValueByAttribute(defaultQuantity(), "value");
	}

	public String getProductName(String itemName) {
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
		clear(quantityBoxOnItem());
	}

	public void enterQuantityInBox(String value) {
		type(quantityBoxOnItem(), value);
	}

}