package TestPackage;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BasePage.BasePage;
import PageObjects.CartPageObjects;
import PageObjects.HomePageObjects;
import PageObjects.ProductPageObjects;

public class ProductPageTestCases extends BasePage {

	HomePageObjects objHome;
	ProductPageObjects objProduct;

	  @BeforeClass(alwaysRun = true)
	    public void setUpPageObjects() {
	        objHome = HomePageObjects.getHomePageObject(driver);
	        objProduct = ProductPageObjects.getProductPageObject(driver);
	    }
	@Test
	public void verifyUserAbleToAddProductToCart(Method Method) {
		try {
			String productName = "Jordan 6 Rings";

			objHome.enterName("Akshay");
			objHome.clickShopButton();
			objHome.ScrollToProduct(productName);
			objProduct.addProductToCart(productName);
            
			System.out.println(Method.getName() + ": passed");

		} catch (Exception e) {
			System.out.println(Method.getName() + ": failed");
			e.printStackTrace();
		}
	}

}
