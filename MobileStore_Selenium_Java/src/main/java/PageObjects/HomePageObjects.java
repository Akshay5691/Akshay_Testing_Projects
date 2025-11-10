package PageObjects;

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

public class HomePageObjects {

     WebDriver driver; 
     WebDriverWait wait;
  
    public HomePageObjects(WebDriver driver) {
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
    
    
    
    @FindBy(name = "search")                                                         //name Locator
    private WebElement
    searchBox;

     @FindBy(xpath="//button[@class='btn btn-default btn-lg']")
   private WebElement
    searchButton;
    
    @FindBy(linkText = "iPhone")                                                    //Link Text Locator
    private WebElement  
    iphoneProduct;
    
    @FindBy(id="description")                                                        // id locator
    private WebElement
    checkBoxToSearchInDescription;
    
    @FindBy(xpath="//input[@type='button'and @id='button-search']")                   //  1Xpath - and - function  
    private WebElement 
    searchButtonToCheckProductInDescription;
    
    @FindBy(xpath="//div[@class='caption']/h4/a")                                    // 1xpath parent to child
    private List<WebElement>
    allSearchedProducts;
    
    @FindBy(xpath="//button[contains(@class,'btn btn-inverse btn-block btn-lg')]")    // Xpath1- contains - function
    private  WebElement   
    cartButton;
    
    @FindBy(xpath="(//button/span[@class='hidden-xs hidden-sm "
   	+ "hidden-md' and text()='Add to Cart'])[1]")                                    // Xpath1- and - function with index
    private  WebElement   
    addToCartButtonOnProduct;
    
    @FindBy(linkText="Qafox.com")                                                   // link Text
    private  WebElement   
    logoQaFox;
    
    @FindBy(xpath = "(//button[starts-with(@data-original-title,'Add to Wish')])[1]")        //Xpath1 - Starts with - function
    private WebElement                                                                       // index
    addToWishListButtonOnProduct;
   
    
    // Actions (Methods).....................................................................................................
    
    public void clickOnSearchBox() {
        searchBox.click();
    }
    
    public void enterTextInSearchBox(String item) {
        searchBox.sendKeys(item);
    }

    public void clickOnSearchButton() {
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-default btn-lg']")));
        searchButton.click();
    }
 
  public void selectTheCheckBoxToSearchProductInDescription() {
	  checkBoxToSearchInDescription.click();
    }
  
   public void clickSearchButtonToCheckProductInDescription() {
	   searchButtonToCheckProductInDescription.click();
   }
   
  // ...........................................................................................................
   
   //return strings 
   public String getProductText() { 
 	 String iphoneText= iphoneProduct.getText();
 	  return iphoneText;
 	  }
   
   public List<String> allSearchedProductsWithDescription() {
	    List<String> allProductsSearchedWithDescription = new ArrayList<>();

	    for (WebElement product : allSearchedProducts) {
	    	allProductsSearchedWithDescription.add(product.getText());
	    }

	    return allProductsSearchedWithDescription;
	}
   }
   

