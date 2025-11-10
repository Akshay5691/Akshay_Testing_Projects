package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageMethods {

     WebDriver driver; 
     WebDriverWait wait;
  
    public HomePageMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); 
        PageFactory.initElements(driver, this); // Initialize elements
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
    
    @FindBy(xpath = "//input[@class='search-keyword']")
    private WebElement searchBox;

    @FindBy(xpath = "//button[@class='search-button']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[text()='ADD TO CART']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//a[@class='cart-icon']")
    private WebElement cartBagIcon;

    @FindBy(xpath = "(//div//a[@class='increment'])[1]")
    private WebElement plusButtonAddToCart;

    @FindBy(xpath = "(//div//a[@class='decrement'])[1]")
    private WebElement minusButtonAddToCart;

    @FindBy(xpath = "(//input[@class='quantity'])[1]")
    private WebElement quantityBoxOnItem;

    @FindBy(xpath = "(//tbody//tr//td[3])[1]")
    private WebElement itemNumber;

    @FindBy(xpath = "(//tbody//tr[2]//td[3])[1]")
    private WebElement itemPrice;

    @FindBy(xpath = "//input[@class='quantity']")
    private WebElement defaultQuantity;

    @FindBy(xpath = "//div//h4[text()='Orange - 1 Kg']")
    private WebElement itemOrange;

    // =================== 🔹 Action Methods ===================

    public void searchItem(String itemName) {
        searchBox.sendKeys(itemName);
        searchButton.click();
    }

    public void searchItemAndAddToCart(String itemName) {
        searchBox.sendKeys(itemName);
        searchButton.click();
        waitUntilElementVisible("//button[text()='ADD TO CART']");
        addToCartButton.click();
        searchBox.clear();
    }

    public void clickOnCartBag() {
        cartBagIcon.click();
    }

    public void clickOnAddToCart() {
        addToCartButton.click();
    }

    public void clickMinusButtonAddToCart() {
        minusButtonAddToCart.click();
    }

    public void clickOnPlusButtonAddToCart() {
        plusButtonAddToCart.click();
    }

    public void clickOnQuantityBox(String itemNumber) {
        quantityBoxOnItem.sendKeys(itemNumber);
        quantityBoxOnItem.click();
    }

    public int getItemNumber() {
        return Integer.parseInt(itemNumber.getText());
    }

    public int getItemPrice() {
        return Integer.parseInt(itemPrice.getText());
    }

    public int getItemQuantityInQuantityBox() {
        return Integer.parseInt(defaultQuantity.getAttribute("value"));
    }

    public String getItemOrange() {
        String itemName = itemOrange.getText();
        return itemName.split(" ")[0];
    }

    public void waitUntilElementVisible(String elementXpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.xpath(elementXpath)));
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
   

