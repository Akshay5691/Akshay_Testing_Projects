package TestPackage;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BasePage.BasePage;
import PageObjects.CartPageObjects;
import PageObjects.HomePageObjects;
import PageObjects.ProductPageObjects;

public class CartPageTestCases extends BasePage {

	private static final Logger log = LogManager.getLogger(HomePageTestCases.class);

	HomePageObjects objHome;
	ProductPageObjects objProduct;
	CartPageObjects objCart;
	
	
	  @BeforeMethod(alwaysRun = true)
	    public void setUpPageObjects() {
	        objHome = HomePageObjects.getHomePageObject(driver);
	        objProduct = ProductPageObjects.getProductPageObject(driver);
	        objCart = CartPageObjects.getCartPageObject(driver);
	    }
	  
	  
  public void verifyUserIsAbleToValidateProduct(Method Method) {
	  
	  try {
			String productName = "Jordan 6 Rings";

			objHome.enterName("Akshay");
			objHome.clickShopButton();
			objHome.ScrollToProduct(productName);
			objProduct.addProductToCart(productName);
			objProduct.clickOnCartIcon();
			String product=objCart.getProductNameInCart(productName);
			Assert.assertEquals(product, productName, "Product not added to cart");
          
			log.info(Method.getName() + " : passed");
		} catch (Exception e) {
			log.error(Method.getName() + " : failed");
			e.printStackTrace();
		}  
	  
  }	  
	  
	@Test
	public void verifyUserAbleToValidateCartTotalAmount(Method Method) {
		try {
			String productName1 = "Air Jordan 1 Mid SE";
			String productName2 = "Jordan 6 Rings";

			objHome.enterName("Akshay");
			objHome.selectGenderMale();
			objHome.clickShopButton();
			objHome.ScrollToProduct(productName1);
			objProduct.addProductToCart(productName1);
			objHome.ScrollToProduct(productName2);
			objProduct.addProductToCart(productName2);

			 			 objProduct.clickOnCartIcon();
			double totalAmount= objCart.getTotalAmountInCart();
			
						double expectedTotal = 165.0 + 120.0;
 		 Assert.assertEquals(totalAmount, expectedTotal, "Total amount in cart does not match expected value");

		} catch (Exception e) {
			System.out.println(Method.getName() + ": failed");
			e.printStackTrace();
		}
	}
	@Test
	public void verifyUserAbleValidateProductPriceInCart(Method Method) {
		try {
			String productName = "Jordan 6 Rings";

			objHome.enterName("Akshay");
			objHome.clickShopButton();
			objHome.ScrollToProduct(productName);
			objProduct.addProductToCart(productName);
             objProduct.clickOnCartIcon();
            double productPrice= objCart.getProductPriceInCart(productName);
			
            			double expectedPrice = 165.0;
 		 Assert.assertEquals(productPrice, expectedPrice, "Product price in cart does");
			System.out.println("✅ verifyUserAbleValidateProductPriceInCart : passed");

		} catch (Exception e) {
			System.out.println(Method.getName() + ": failed");
			e.printStackTrace();
		}
	}
	@Test
	public void verifyUserAbleToAcceptTermsAndConditions(Method Method) {
		try {
			String productName = "Jordan 6 Rings";

			objHome.enterName("Akshay");
			objHome.clickShopButton();
			objHome.ScrollToProduct(productName);
			objProduct.addProductToCart(productName);
			 objProduct.clickOnCartIcon();
			 objCart.acceptTermsAndConditions();
			boolean isAccepted = true;
			 boolean actualAcceptanceStatus= objCart.isTermsAndConditionsAccepted();
			 Assert.assertEquals(actualAcceptanceStatus, isAccepted, "Terms and Conditions not accepted");
			
		} catch (Exception e) {
			System.out.println(Method.getName() + ": failed");
			e.printStackTrace();
		}
	}

}
