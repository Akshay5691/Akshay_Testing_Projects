package PageObjects;
import utilityClasses.ActionsUtilitiy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class HomePageObjects extends ActionsUtilitiy {

   

   
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
	    	super(driver);
	    	   this.driver = driver;
	         
	    }


	  
	    // ===================  Locators ===================
	    
	    private By countryDropdownLocator() { return AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry"); }
	    private By nameFieldLocator() { return AppiumBy.id("com.androidsample.generalstore:id/nameField"); }
	    private By genderRadioMaleLocator() { return AppiumBy.id("com.androidsample.generalstore:id/radioMale"); }
	    private By shopButtonLocator() { return AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"); }
	    private By alertTextLocator() { return AppiumBy.xpath("//android.widget.Toast[@text=\"Please enter your name\"]"); }
        private By genderRadioFemaleLocator() { return AppiumBy.id("com.androidsample.generalstore:id/radioFemale"); }
	    // ===================  WebElements ===================
	    
	    private WebElement countryDropdown() { return driver.findElement(countryDropdownLocator()); }
	    private WebElement nameField() { return driver.findElement(nameFieldLocator()); }
	    private WebElement genderRadioMale() { return driver.findElement(genderRadioMaleLocator()); }
	    private WebElement shopButton() { return driver.findElement(shopButtonLocator()); }
	    private WebElement alertText() { return driver.findElement(alertTextLocator()); }
	    private WebElement genderRadioFemale() { return driver.findElement(genderRadioFemaleLocator()); }
	   
	   
	   
                        // String Methods
	    
      public String getAlertText() {
	    String alertText = alertText().getText();
	    return alertText;
     }       
	    
	    
	                           //   Action methods
	    
	    public void selectCountry(String countryName) {
	        click(countryDropdown());
	        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + countryName + "\"));"))
	           .click();
	    }
	    public void enterName(String name) {   
	    	waitUntilElementVisible(nameFieldLocator());
	        type(nameField(), name);
	    }
	    public void selectGenderMale() {
	    	waitUntilElementClickable(genderRadioMaleLocator());
	        click(genderRadioMale());
	    }
	    public void selectGenderFemale() {
	    	waitUntilElementClickable(genderRadioFemaleLocator());
	    	genderRadioFemale().click(); 
	   }
	    
	    public void clickShopButton() {
	    	waitUntilElementClickable(shopButtonLocator());
	        click(shopButton());
	    }
	    public boolean isGenderMaleSelected() throws InterruptedException {
	        return getAttribute(genderRadioMale(),"checked").equals("true");
	    }
	    public boolean isGenderFemaleSelected() throws InterruptedException { 
	        return getAttribute(genderRadioFemale(),"checked").equals("true");
	    }
	  
	   
    
    
   }


