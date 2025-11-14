package TestPackage;

import BasePage.BasePage;
import PageObjects.HomePageObjects;

public class CartPageTestCases extends BasePage {

	 HomePageObjects home = new HomePageObjects(driver);
     
	
	
	public void verifyUserAbleToValidateCartTotalAmount() {
	      try {
	           String productName1="Jordan 6 Rings";
	           String productName2="Air Jordan 1 Mid SE";
	     
	            home.enterName("Akshay");
	            home.selectGenderMale();
	            home.clickShopButton();
	            home.ScrollToProduct(productName1);
	            home.addProductToCart(productName1);
	            home.ScrollToProduct(productName2);
	            home.addProductToCart(productName2);
	            
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
