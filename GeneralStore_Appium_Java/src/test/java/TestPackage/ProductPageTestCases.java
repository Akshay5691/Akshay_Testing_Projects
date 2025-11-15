package TestPackage;

import org.testng.annotations.Test;

import BasePage.BasePage;
import PageObjects.CartPageObjects;
import PageObjects.HomePageObjects;
import PageObjects.ProductPageObjects;

public class ProductPageTestCases extends BasePage {

	HomePageObjects objHome = HomePageObjects.getHomePageObject(driver);
	ProductPageObjects objProduct = ProductPageObjects.getProductPageObject(driver);

	@Test
	public void verifyUserAbleToAddProductToCart() {
		try {
			String productName = "Jordan 6 Rings";

			objHome.enterName("Akshay");
			objHome.clickShopButton();
			objHome.ScrollToProduct(productName);
			objProduct.addProductToCart(productName);
            
			System.out.println("✅ verifyUserCanSelectCountryAndStartShopping : passed");

		} catch (Exception e) {
			System.out.println("❌ verifyUserCanSelectCountryAndStartShopping : failed");
			e.printStackTrace();
		}
	}

}
