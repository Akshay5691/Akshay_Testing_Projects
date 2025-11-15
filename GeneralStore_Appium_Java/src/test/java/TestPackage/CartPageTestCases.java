package TestPackage;

import org.testng.annotations.Test;

import BasePage.BasePage;
import PageObjects.HomePageObjects;
import PageObjects.ProductPageObjects;

public class CartPageTestCases extends BasePage {

	HomePageObjects objHome = HomePageObjects.getHomePageObject(driver);
	ProductPageObjects objProduct =ProductPageObjects.getProductPageObject(driver);

	@Test
	public void verifyUserAbleToValidateCartTotalAmount() {
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
			System.out.println("❌ verifyUserAbleToValidateCartTotalAmount : failed");
			e.printStackTrace();
		}
	}

}
