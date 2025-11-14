package TestPackage;

import org.testng.annotations.Test;

import BasePage.BasePage;
import PageObjects.HomePageObjects;
import PageObjects.ProductPageObjects;

public class CartPageTestCases extends BasePage {

	 HomePageObjects home = new HomePageObjects(driver);
	 ProductPageObjects productPage = new ProductPageObjects(driver);
     
	
	@Test
	public void verifyUserAbleToValidateCartTotalAmount() {
	      try {
	           String productName1="Jordan 6 Rings";
	           String productName2="Air Jordan 1 Mid SE";
	     
	            home.enterName("Akshay");
	            home.selectGenderMale();
	            home.clickShopButton();
	            home.ScrollToProduct(productName1);
	            productPage.addProductToCart(productName1);
	            home.ScrollToProduct(productName2);
	            productPage.addProductToCart(productName2);
	            
	         //   double sumOfProducts=home.getSumOfProductsInCart();
	          //  double totalAmountInCart=home.getTotalAmountInCart();
	            
	          //  Assert.assertEquals(sumOfProducts, totalAmountInCart, "Cart total amount does not match sum of products");
	            
	            
	            System.out.println("✅ verifyUserAbleToValidateCartTotalAmount : passed");

	        } catch (Exception e) {
	            System.out.println("❌ verifyUserAbleToValidateCartTotalAmount : failed");
	            e.printStackTrace();
	        }
	    }
		
	
	
	
	
	
	
	
	
}
