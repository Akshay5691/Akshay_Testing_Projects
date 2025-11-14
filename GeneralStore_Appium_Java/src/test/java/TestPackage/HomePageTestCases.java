package TestPackage;





import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import BasePage.BasePage;
import PageObjects.HomePageObjects;
import io.appium.java_client.AppiumBy;;

public class HomePageTestCases extends BasePage {

	 HomePageObjects home = new HomePageObjects(driver);
            
	  @Test
	    public void verifyUserCanSelectCountryAndStartShopping(Method Method) {
	      try {
	            home.selectCountry("India");
	            home.enterName("Akshay");
	            home.selectGenderMale();
	            home.clickShopButton();

	            System.out.println(Method.getName()+": passed");
	        } catch (Exception e) {
	            System.out.println("❌ verifyUserCanSelectCountryAndStartShopping : failed");
	            e.printStackTrace();
	        }
	    }
	@Test
	public  void verifyUserCannotProceedWithoutEnteringName(Method Method) {
	      try {         
	            home.selectGenderMale();
	            home.clickShopButton();

	            String actualAlertText = home.getAlertText();
	            String alertText = "Please enter your name";
	            Assert.assertEquals(actualAlertText,alertText , "Alert text does not match expected value");
	            System.out.println( Method.getName()+" : passed");
	        } catch (Exception e) {
	            System.out.println("❌ verifyUserCannotProceedWithoutEnteringName : failed");
	            e.printStackTrace();
	        }
	    }
	@Test
	public void  verifyUserIsAbleToSelectOnlyOneGenderOption(Method Method) {
	      try {  
	    	  
	      	    home.enterName("Akshay");
	            home.selectGenderFemale();
	            
	            boolean isMaleSelected = home.isGenderMaleSelected();
	            Assert.assertFalse(isMaleSelected,"male gender option is selected");
	            System.out.println(Method.getName()+": passed");
	        } catch (Exception e) {
	            System.out.println("❌ verifyUserIsAbleToSelectOnlyOneGenderOption : failed");
	            e.printStackTrace();
	        }
	}
	@Test
	public void verifyUserIsAbleToSelectGenderFemaleOption() {
	      try {   	  
	      	    home.enterName("Akshay");
	            home.selectGenderFemale();
	            
	            boolean isFeMaleSelected = home.isGenderFemaleSelected();
	            Assert.assertTrue(isFeMaleSelected,"Gender female option is not selected");
	            System.out.println("✅ verifyUserIsAbleToSelectGenderFemaleOption : passed");
	          }  catch (Exception e) {
		            System.out.println("❌ verifyUserIsAbleToSelectOnlyOneGenderOption : failed");
		            e.printStackTrace();
		        }
	      
	
}
}
