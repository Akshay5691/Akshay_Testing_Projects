package PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePageObjects {

   

   
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
    
	
	   AndroidDriver driver;
	   WebDriverWait wait;

	    public HomePageObjects(AndroidDriver driver) {
	    	   this.driver = driver;
	    	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	    	 wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     
	    }


	    // ✅ Web elements (Appium elements) with @AndroidFindBy
	    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	    private WebElement countryDropdown;

	    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	    private WebElement nameField;

	    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	    private WebElement genderRadioMale;

	    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	    private WebElement shopButton;
	    
	    private WebElement addToCartButton(String productName) {
	        String xpath = "//android.widget.TextView[@text='" + productName + "']" +
                    "/parent::android.widget.LinearLayout" +
                    "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']";
	        		return driver.findElement(AppiumBy.xpath(xpath));
	    }

	    // ✅ Action methods
	    public void selectCountry(String countryName) {
	        countryDropdown.click();
	        driver.findElement(AppiumBy.androidUIAutomator(
	                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + countryName + "\"));"))
	                .click();
	    }

	    public void enterName(String name) {
	        nameField.sendKeys(name);
	    }

	    public void selectGenderMale() {
	        genderRadioMale.click();
	    }

	    public void clickShopButton() {
	        shopButton.click();
	    }
	    public void ScrollToProduct(String Product) {
	        driver.findElement(AppiumBy.androidUIAutomator(
	                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + Product + "\"));"));
	    }
	    
	    public void addProductToCart(String productName) {
	 
	    	wait.until(ExpectedConditions.elementToBeClickable(addToCartButton(productName)));
	    	addToCartButton(productName).click();
	    }
    
    
   }


