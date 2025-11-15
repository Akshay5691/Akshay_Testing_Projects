package TestPages;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import basePage.BasePage;
import pageObjects.CartPageMethods;
import pageObjects.HomePageMethods;
import pageObjects.PlaceOrderPageMethods;
import pageObjects.ProceedPageMethods;

public class PlaceOrderPageTestCases extends BasePage {

	HomePageMethods homePage =HomePageMethods.getHomePageObject(driver);
	CartPageMethods cartPage = CartPageMethods.getCartPageObject(driver);
	PlaceOrderPageMethods placeOrderPage = PlaceOrderPageMethods.getPlaceOrderPageObject(driver);
	ProceedPageMethods proceedPage =ProceedPageMethods.getProceedPageObject(driver);
	String orange = "Orange";

	@Test
	public void verifyUserIsAbleToApplyPromoCode() {
		try {
			homePage.searchItemAndAddToCart(orange);
			homePage.clickOnCartBag();
			cartPage.clickOnProceedToCheckoutButton();
			placeOrderPage.enterPromoCodeAndApply("rahulshettyacademy");

			String actualMessage = placeOrderPage.promoCodeAppliedMessageText();
			String expectedMessage = "Code applied ..!";
			Assert.assertEquals(actualMessage, expectedMessage, "Promo code not applied");
			System.out.println("verifyUserIsAbleToApplyPromoCode : Passed");
		} catch (Exception e) {
			System.out.println("verifyUserIsAbleToApplyPromoCode : Failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyUserIsUnableToApplyPromoCodeWithInvalidPromoCode() {
		try {
			homePage.searchItemAndAddToCart(orange);
			homePage.clickOnCartBag();
			cartPage.clickOnProceedToCheckoutButton();
			placeOrderPage.enterPromoCodeAndApply("rahulshetty");

			String actualMessage = placeOrderPage.invalidPromoCodeMessageText();
			String expectedMessage = "Invalid code ..!";
			Assert.assertEquals(actualMessage, expectedMessage, "Invalid promo code accepted");
			System.out.println("verifyUserIsUnableToApplyPromoCodeWithInvalidPromoCode : Passed");

		} catch (Exception e) {
			System.out.println("verifyUserIsUnableToApplyPromoCodeWithInvalidPromoCode : Failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyUserIsAbleToGetDiscountWithValidPromoCode() {
		try {
			homePage.searchItemAndAddToCart(orange);
			homePage.clickOnCartBag();
			cartPage.clickOnProceedToCheckoutButton();
			placeOrderPage.enterPromoCodeAndApply("rahulshettyacademy");

			int actualDiscount = placeOrderPage.discountPercentageValue();
			int expectedDiscount = 10;
			Assert.assertEquals(actualDiscount, expectedDiscount, "Discount not applied correctly");
			System.out.println("verifyUserIsAbleToGetDiscountWithValidPromoCode : Passed");
		} catch (Exception e) {
			System.out.println("verifyUserIsAbleToGetDiscountWithValidPromoCode : Failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyUserIsUnableToGetDiscountWithInvalidPromoCode() {
		try {
			homePage.searchItemAndAddToCart(orange);
			homePage.clickOnCartBag();
			cartPage.clickOnProceedToCheckoutButton();
			placeOrderPage.enterPromoCodeAndApply("rahulshetty");

			int actualDiscount = placeOrderPage.discountPercentageValue();
			int expectedDiscount = 0;
			Assert.assertEquals(actualDiscount, expectedDiscount, "Discount applied for invalid code");
			System.out.println("verifyUserIsUnableToGetDiscountWithInvalidPromoCode : Passed");
		} catch (Exception e) {
			System.out.println("verifyUserIsUnableToGetDiscountWithInvalidPromoCode : Failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyUserIsAbleToPlaceOrderAfterPromoCodeApply() {
		try {
			homePage.searchItemAndAddToCart(orange);
			homePage.clickOnCartBag();
			cartPage.clickOnProceedToCheckoutButton();
			placeOrderPage.enterPromoCodeAndApply("rahulshettyacademy");
			placeOrderPage.clickOnPlaceOrderButton();

			String proceedButton = proceedPage.getproceedButton();
			String proceedButtonText = "Proceed";
			Assert.assertEquals(proceedButtonText, proceedButton, "Item quantity not increased using plus button.");
			System.out.println("verifyUserIsAbleToPlaceOrderAfterPromoCodeApply : Passed");
		} catch (Exception e) {
			System.out.println("verifyUserIsAbleToPlaceOrderAfterPromoCodeApply : Failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyUserIsAbleToPlaceOrderWithoutPromoCodeApply() {
		try {
			homePage.searchItemAndAddToCart(orange);
			homePage.clickOnCartBag();
			cartPage.clickOnProceedToCheckoutButton();
			placeOrderPage.clickOnPlaceOrderButton();

			String proceedButton = proceedPage.getproceedButton();
			String proceedButtonText = "Proceed";
			Assert.assertEquals(proceedButtonText, proceedButton, "Item quantity not increased using plus button.");
			System.out.println("verifyUserIsAbleToPlaceOrderWithoutPromoCodeApply : Passed");

		} catch (Exception e) {
			System.out.println("verifyUserIsAbleToPlaceOrderWithoutPromoCodeApply : Failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyItemsQuantityInGridTable() {
		try {
			String[] items = { "Apple", "Orange", "Carrot", "Tomato", "Orange" };
			homePage.searchMultipleItems(items);
			homePage.clickOnCartBag();
			cartPage.clickOnProceedToCheckoutButton();
			Thread.sleep(2000);

			int actualQuantity = placeOrderPage.getQuantityOfItemFromGrid("Orange");
			int expectedQuantity = 2;

			Assert.assertEquals(actualQuantity, expectedQuantity, "Item quantity mismatch between cart and grid table");
			System.out.println("verifyItemsQuantityInGridTable : Passed");
		} catch (Exception e) {
			System.out.println("verifyItemsQuantityInGridTable : Failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyItemsCostInGridTable() {
		try {
			String[] items = { "Apple", "Orange", "Carrot", "Tomato", "Orange" };
			homePage.searchMultipleItems(items);
			homePage.clickOnCartBag();
			cartPage.clickOnProceedToCheckoutButton();
			Thread.sleep(2000);

			int actualCost = placeOrderPage.getCostOfItemFromGrid("Orange");
			int expectedCost = 75;

			Assert.assertEquals(actualCost, expectedCost, "Item cost mismatch between cart and grid table");
			System.out.println("verifyItemsCostInGridTable : Passed");
		} catch (Exception e) {
			System.out.println("verifyItemsCostInGridTable : Failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyItemTotalCostInGridTable() {
		try {
			String[] items = { "Apple", "Orange", "Carrot", "Tomato", "Orange" };
			homePage.searchMultipleItems(items);
			homePage.clickOnCartBag();
			cartPage.clickOnProceedToCheckoutButton();
			Thread.sleep(2000);

			int actualTotalCost = placeOrderPage.getTotalCostOfItemFromGrid("Orange");
			int expectedTotalCost = 150;

			Assert.assertEquals(actualTotalCost, expectedTotalCost, "Total cost mismatch between cart and grid table");
			System.out.println("verifyItemTotalCostInGridTable : Passed");
		} catch (Exception e) {
			System.out.println("verifyItemTotalCostInGridTable : Failed");
			e.printStackTrace();
		}
	}
}
