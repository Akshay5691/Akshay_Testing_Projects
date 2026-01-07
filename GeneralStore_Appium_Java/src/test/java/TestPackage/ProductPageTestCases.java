package TestPackage;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import BasePage.BasePage;
import PageObjects.CartPage;
import PageObjects.HomePage;
import PageObjects.ProductPage;

public class ProductPageTestCases extends BasePage {

	HomePage objHome;
	ProductPage objProduct;
	CartPage objCart;
	
	private static final Logger log = LogManager.getLogger(HomePageTestCases.class);


	  @BeforeClass(alwaysRun = true)
	    public void setUpPageObjects() {
	        objHome = HomePage.getHomePageObject(driver);
	        objProduct = ProductPage.getProductPageObject(driver);
	        objCart = CartPage.getCartPageObject(driver);
	    }
	@Test
	public void verifyUserAbleToAddProductToCart(Method Method) {
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
	public void verifyUserAbleToAddMultipleProductsToCart(Method Method) {
		try {
			String productName1 = "Air Jordan 1 Mid SE";
			String productName2 = "Jordan 6 Rings";

			objHome.enterName("Akshay");
			objHome.clickShopButton();
			objHome.ScrollToProduct(productName1);
			objProduct.addProductToCart(productName1);
			objHome.ScrollToProduct(productName2);
			objProduct.addProductToCart(productName2);
			objProduct.clickOnCartIcon();
			String product1=objCart.getProductNameInCart(productName1);
			String product2=objCart.getProductNameInCart(productName2);
			Assert.assertEquals(product1, productName1, "First Product not added to cart");
			Assert.assertEquals(product2, productName2, "Second Product not added to cart");
			
			log.info(Method.getName() + " : passed");
		} catch (Exception e) {
			log.error(Method.getName() + " : failed");
			e.printStackTrace();
		}
	}
	@Test
	public void verifyUserIsAbleToseeAddToCartChangeToAdded(Method Method) {
		try {
			String productName = "Jordan 6 Rings";

			objHome.enterName("Akshay");
			objHome.clickShopButton();
			objHome.ScrollToProduct(productName);
			objProduct.addProductToCart(productName);
			String buttonText=objProduct.getAddedToCartButtonText(productName);
			Assert.assertEquals(buttonText, "ADDED TO CART", "Button text did not change to ADDED");
			
			log.info(Method.getName() + " : passed");
		} catch (Exception e) {
			log.error(Method.getName() + " : failed");
			e.printStackTrace();
		}
	}
	@Test
	public void VerifyUserIsAbleToSeeCartIconCountWithNumberOfItems(Method Method) {
		try {
			String productName = "Jordan 6 Rings";

			objHome.enterName("Akshay");
			objHome.clickShopButton();
			objHome.ScrollToProduct(productName);
			objProduct.addProductToCart(productName);
			int itemCount=objProduct.getCartIconCount();
			Assert.assertEquals(itemCount, 1, "Cart icon did not show correct number of items");
			
			log.info(Method.getName() + " : passed");
		} catch (Exception e) {
			log.error(Method.getName() + " : failed");
			e.printStackTrace();
		}
	}
	
	public void verifyUserIsAbleToCartIconCountIncreasingWhenMultipleProductsAdded(Method Method) {
		try {
			String productName1 = "Air Jordan 1 Mid SE";
			String productName2 = "Jordan 6 Rings";

			objHome.enterName("Akshay");
			objHome.clickShopButton();
			objHome.ScrollToProduct(productName1);
			objProduct.addProductToCart(productName1);
			objHome.ScrollToProduct(productName2);
			objProduct.addProductToCart(productName2);
			int itemCount=objProduct.getCartIconCount();
			
			Assert.assertEquals(itemCount, 2, "Cart icon did not show correct number of items");
			
			log.info(Method.getName() + " : passed");
		} catch (Exception e) {
			log.error(Method.getName() + " : failed");
			e.printStackTrace();
		}
	}
	

}
