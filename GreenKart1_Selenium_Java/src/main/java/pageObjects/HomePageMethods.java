package pageObjects;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    
    
    // WebElements..............................................................................................................

 // =================== 🔹 Locators ===================

    private By searchBox = By.xpath("//input[@class='search-keyword']");
    private By searchButton = By.xpath("//button[@class='search-button']");
    private By addToCartButton = By.xpath("//button[text()='ADD TO CART']");
    private By cartBagIcon = By.xpath("//a[@class='cart-icon']");
    private By plusButtonAddToCart = By.xpath("(//div//a[@class='increment'])[1]");
    private By minusButtonAddToCart = By.xpath("(//div//a[@class='decrement'])[1]");
    private By quantityBoxOnItem = By.xpath("(//input[@class='quantity'])[1]");
    private By itemNumber = By.xpath("(//tbody//tr//td[3])[1]");
    private By itemPrice = By.xpath("(//tbody//tr[2]//td[3])[1]");
    private By defaultQuantity = By.xpath("//input[@class='quantity']");
    private By itemOrange = By.xpath("//div//h4[text()='Orange - 1 Kg']");

   
   
    // =================== 🔹 Action Methods ===================

    public void searchItem(String itemName) {
    	waitUntilElementVisible(searchBox);
         type(searchBox, itemName);
       waitUntilElementClickable(searchButton);
        click(searchButton);
    }

    public void searchItemAndAddToCart(String itemName) {
    	  type(searchBox, itemName);
    	  click(searchButton);
       waitUntilElementClickable(addToCartButton);
        click(addToCartButton);
    }

    public void clickOnCartBag() {
    	waitUntilElementClickable(cartBagIcon);
        click(cartBagIcon);
    }

    public void clickOnAddToCart() {
    	waitUntilElementClickable(addToCartButton);
       click( addToCartButton);
    }

    public void clickMinusButtonAddToCart() {
    	waitUntilElementClickable(minusButtonAddToCart);
       click( minusButtonAddToCart);
    }

    public void clickOnPlusButtonAddToCart() {
    	waitUntilElementClickable(plusButtonAddToCart);
        click(plusButtonAddToCart);
    }

    public void clickOnQuantityBox(String itemNumber) {
    	waitUntilElementVisible(quantityBoxOnItem);
         type(quantityBoxOnItem, itemNumber);
        waitUntilElementClickable(quantityBoxOnItem);
        click(quantityBoxOnItem);
    }

    public int getItemNumber() {
    	waitUntilElementVisible(itemNumber);
        return getIntValue(itemNumber);
    }

    public int getItemPrice() {
    	waitUntilElementVisible(itemPrice);
        return getIntValue(itemPrice);
    }

    public int getItemQuantityInQuantityBox() {
    	waitUntilElementVisible(defaultQuantity);
        return getIntValueByAttribute(defaultQuantity,"value");
    }

    public String getItemOrange() {
    	waitUntilElementVisible(itemOrange);
        String itemName = getText(itemOrange);
        return itemName.split(" ")[0];
    }

 
    public void searchMultipleItems(String[] items) {
        for (String item : items) {
            searchItemAndAddToCart(item);
        }
    }

	public void clearQuantityBox() {
		// TODO Auto-generated method stub
		
	}

	public void enterQuantityInBox(String string) {
		// TODO Auto-generated method stub
		
	}
    
  
   }
   

