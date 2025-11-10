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
	    public void verifyUserCanSelectCountryAndStartShopping() {
	      try {
	           
	            home.selectCountry("India");
	            home.enterName("Akshay");
	            home.selectGenderMale();
	            home.clickShopButton();

	            System.out.println("✅ verifyUserCanSelectCountryAndStartShopping : passed");

	        } catch (Exception e) {
	            System.out.println("❌ verifyUserCanSelectCountryAndStartShopping : failed");
	            e.printStackTrace();
	        }
	    }
	
	  @Test
	    public void verifyUserCanAddProductToCart() {
	      try {
	           String productName="Jordan 6 Rings";
	     
	            home.enterName("Akshay");
	            home.selectGenderMale();
	            home.clickShopButton();
	            home.ScrollToProduct(productName);
	            home.addProductToCart(productName);
	           
	            
	            System.out.println("✅ verifyUserCanSelectCountryAndStartShopping : passed");

	        } catch (Exception e) {
	            System.out.println("❌ verifyUserCanSelectCountryAndStartShopping : failed");
	            e.printStackTrace();
	        }
	    }
	
	
	
		
	
	
	
	
	
	
}
