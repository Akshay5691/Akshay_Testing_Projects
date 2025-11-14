package TestPackage;

import org.testng.annotations.Test;

import BasePage.BasePage;
import PageObjects.HomePageObjects;

public class ProductPageTestCases extends BasePage {
	
	
	HomePageObjects home = new HomePageObjects(driver);
	
	  @Test
	    public void verifyUserAbleToAddProductToCart() {
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
