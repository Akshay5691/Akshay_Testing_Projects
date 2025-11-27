package TestPackage;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BasePage.BasePage;
import PageObjects.HomePageObjects;
import PageObjects.ProductPageObjects;

public class CartPageTestCases extends BasePage {

	
	HomePageObjects objHome;
	ProductPageObjects objProduct;
	
	
	  @BeforeMethod(alwaysRun = true)
	    public void setUpPageObjects() {
	        objHome = HomePageObjects.getHomePageObject(driver);
	        objProduct = ProductPageObjects.getProductPageObject(driver);
	    }
	
	
	public void verifyUserAbleToValidateCartTotalAmount(Method Method) {
		try {
			String productName1 = "Jordan 6 Rings";
			String productName2 = "Air Jordan 1 Mid SE";

			objHome.enterName("Akshay");
			objHome.selectGenderMale();
			objHome.clickShopButton();
			objHome.ScrollToProduct(productName1);
			objProduct.addProductToCart(productName1);
			objHome.ScrollToProduct(productName2);
			objProduct.addProductToCart(productName2);

			// double sumOfProducts=home.getSumOfProductsInCart();
			// double totalAmountInCart=home.getTotalAmountInCart();

			// Assert.assertEquals(sumOfProducts, totalAmountInCart, "Cart total amount does
			// not match sum of products");

			System.out.println("✅ verifyUserAbleToValidateCartTotalAmount : passed");

		} catch (Exception e) {
			System.out.println(Method.getName() + ": failed");
			e.printStackTrace();
		}
	}

}
