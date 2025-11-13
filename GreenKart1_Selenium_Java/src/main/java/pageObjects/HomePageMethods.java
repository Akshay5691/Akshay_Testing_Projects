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

   
    // 1Css selector --- tag name[attribute='value'] | Xpath1   ----//tag name[@Atribute='value']
    //                                               |
    //               ----Tag name#id                 |  text            ----//tag name[text()='text']
    //               ---- .classname1                |  contains        -----//tag name[contains(@class,'value')]
    //                                               | contains text    --//tag name[contains(text(),'text')]
    //               ---- tag name child             |   and            -----//tag name[[@Atribute='value' and @Atribute='value']
    //               ----- tagname.classname         |    or            ----//tag name[[@Atribute='value' or @Atribute='value']
   //                                                | starts with text   -- //tag name[starts-with(text(),'value')]
    //                -----tagname.classname         | starts with       --//tag name[starts-with(@Atribute,'value')]
   //      ----tagname1[attribute='value'] childtag1 | ends-with
  //.................................................|........................................................
    //                                               
    //       select all tags  after that tag         | following        ---//tag name[@Atribute='value']/following::tag
    //select all tag with after same parent(siblings)| following sibling  --
   //       select all tags  before  that tag        |  preceding        -- 
    //select all tag with before same parent(siblings)  | preceding sibling  --
    //  select child and grand child                 | ancestor , descendant 
    //            select parent and child            |parent , child   ----parent frame to child element in frame
    //                                            | parent , child  //tag name[@Atribute='value'] /tag name[@Atribute='value']
    
    
    // driver.findelement(By.Tagname("label")).above(element);
    
 // =================== 🔹 Locators ===================

    private By searchBoxLocator() { return By.xpath("//input[@class='search-keyword']"); }
    private By searchButtonLocator() { return By.xpath("//button[@class='search-button']"); }
    private By addToCartButtonLocator(String product) { return By.xpath("//h4[text()='"+product+" - 1 Kg']/following-sibling::div//button[text()='ADD TO CART']"); }
    private By cartBagIconLocator() { return By.xpath("//a[@class='cart-icon']"); }
    private By plusButtonAddToCartLocator() { return By.xpath("(//div//a[@class='increment'])[1]"); }
    private By minusButtonAddToCartLocator() { return By.xpath("(//div//a[@class='decrement'])[1]"); }
    private By quantityBoxOnItemLocator() { return By.xpath("(//input[@class='quantity'])[1]"); }
    private By itemNumberLocator() { return By.xpath("(//tbody//tr//td[3])[1]"); }
    private By itemPriceLocator() { return By.xpath("(//tbody//tr[2]//td[3])[1]"); }
    private By defaultQuantityLocator() { return By.xpath("//input[@class='quantity']"); }

    // ✅ Dynamic locator using parameter
    private By productNameLocator(String itemName) { 
        return By.xpath("//h4[contains(text(),'" + itemName + "')]"); 
    }

    // =================== 🔹 WebElements ===================

    private WebElement searchBox() { return driver.findElement(searchBoxLocator()); }
    private WebElement searchButton() { return driver.findElement(searchButtonLocator()); }
    private WebElement addToCartButton(String product) { return driver.findElement(addToCartButtonLocator(product)); }
    private WebElement cartBagIcon() { return driver.findElement(cartBagIconLocator()); }
    private WebElement plusButtonAddToCart() { return driver.findElement(plusButtonAddToCartLocator()); }
    private WebElement minusButtonAddToCart() { return driver.findElement(minusButtonAddToCartLocator()); }
    private WebElement quantityBoxOnItem() { return driver.findElement(quantityBoxOnItemLocator()); }
    private WebElement itemNumber() { return driver.findElement(itemNumberLocator()); }
    private WebElement itemPrice() { return driver.findElement(itemPriceLocator()); }
    private WebElement defaultQuantity() { return driver.findElement(defaultQuantityLocator()); }
    private WebElement productName(String itemName) { return driver.findElement(productNameLocator(itemName)); }

    // =================== 🔹 Action Methods ===================

    public void searchItem(String itemName) {
    	
        waitUntilElementVisible(searchBoxLocator());
        type(searchBox(), itemName);
        waitUntilElementClickable(searchButtonLocator());
        click(searchButton());
    }
 public void searchItemAndAddToCart(String itemName) {
	   waitUntilElementVisible(searchBoxLocator());
	 	 searchItem(itemName);
	 	 waitUntilElementClickable(addToCartButtonLocator(itemName));
	    clickOnAddToCart(itemName);
 }

    public void clickOnCartBag() {
        waitUntilElementClickable(cartBagIconLocator());
        click(cartBagIcon());
    }

    public void clickOnAddToCart(String product) {
        waitUntilElementClickable(addToCartButtonLocator(product));
        click(addToCartButton(product));
      
    }

    public void clickMinusButtonAddToCart() {
        waitUntilElementClickable(minusButtonAddToCartLocator());
        click(minusButtonAddToCart());
    }

    public void clickOnPlusButtonAddToCart() {
        waitUntilElementClickable(plusButtonAddToCartLocator());
        click(plusButtonAddToCart());
    }

    public void clickOnQuantityBox(String value) {
        waitUntilElementVisible(quantityBoxOnItemLocator());
        clear(quantityBoxOnItem());
        type(quantityBoxOnItem(), value);
        waitUntilElementClickable(quantityBoxOnItemLocator());
        click(quantityBoxOnItem());
    }

    public int getItemNumberValue() {
        waitUntilElementVisible(itemNumberLocator());
        return getIntValue(itemNumber());
    }

    public int getItemPriceValue() {
        waitUntilElementVisible(itemPriceLocator());
        return getIntValue(itemPrice());
    }

    public int getItemQuantityInBox() {
        waitUntilElementVisible(defaultQuantityLocator());
        return getIntValueByAttribute(defaultQuantity(), "value");
    }

    public String getProductName(String itemName) {
        waitUntilElementVisible(productNameLocator(itemName));
         String productText =getText(productName(itemName));
         return productText.split(" ")[0];
    }

    public void searchMultipleItems(String[] items) {
        for (String item : items) {
            searchItem(item);
            clickOnAddToCart(item);
        }
    }

    public void clearQuantityBox() {
        waitUntilElementVisible(quantityBoxOnItemLocator());
        clear(quantityBoxOnItem());
    }

    public void enterQuantityInBox(String value) {
        waitUntilElementVisible(quantityBoxOnItemLocator());
        type(quantityBoxOnItem(), value);
    }

    // Example utility
    private void clear(WebElement element) {
        element.clear();
    }


	public String getItemOrangeName() {
		// TODO Auto-generated method stub
		return null;
	}
   }
   

