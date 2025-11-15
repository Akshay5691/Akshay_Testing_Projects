package TestPages;

import org.testng.annotations.Test;

import basePage.BasePage;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import pageObjects.CartPageMethods;
import pageObjects.HomePageMethods;
import pageObjects.PlaceOrderPageMethods;
import pageObjects.ProceedPageMethods;

public class CartPageTestCases extends BasePage {

	HomePageMethods homePage = new HomePageMethods(driver);
	CartPageMethods cartPage = new CartPageMethods(driver);
	PlaceOrderPageMethods placeOrderPage = new PlaceOrderPageMethods(driver);
	ProceedPageMethods proceedPage = new ProceedPageMethods(driver);
	String orange = "Orange";

	@Test
	public void verifyItemQuantityIncreasingWhileAddingSameItem() {
		try {
			homePage.searchItem(orange);
			homePage.clickOnAddToCart(orange);
			homePage.clickOnAddToCart(orange);
			homePage.clickOnCartBag();

			int actualQuantity = cartPage.getItemQuantity();
			int expectedQuantity = 2;

			Assert.assertEquals(actualQuantity, expectedQuantity, "Item quantity not increased as expected.");
			System.out.println("verifyItemQuantityIncreasingWhileAddingSameItem : Passed");
		} catch (Exception e) {
			System.out.println("verifyItemQuantityIncreasingWhileAddingSameItem : Failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyUserIsAbleToSeeItemInCart() {
		try {
			homePage.searchItemAndAddToCart("Apple");
			homePage.clickOnCartBag();

			String actualItem = cartPage.getItemNameText("Apple");
			String expectedItem = "Apple";

			Assert.assertEquals(actualItem, expectedItem, "Item not present in cart.");
			System.out.println("verifyUserIsAbleToSeeItemInCart : Passed");
		} catch (Exception e) {
			System.out.println("verifyUserIsAbleToSeeItemInCart : Failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyUserIsAbleToRemoveItemFromCart() {
		try {
			homePage.searchItemAndAddToCart("Apple");
			homePage.clickOnCartBag();
			cartPage.clickOnRemoveButton();

			String emptyPageMessage = cartPage.getEmptyCartMessageText();
			String expectedMessage = "You cart is empty!";
			Assert.assertEquals(emptyPageMessage, expectedMessage, "Item not removed from cart.");
			System.out.println("verifyUserIsAbleToRemoveItemFromCart : Passed");

		} catch (Exception e) {
			System.out.println("verifyUserIsAbleToRemoveItemFromCart : Failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyUserIsAbleToAddMoreThanOneQuantityOfItemToCartUsingPlusButton() {
		try {
			homePage.searchItem(orange);
			homePage.clickOnPlusButtonAddToCart();
			homePage.clickOnAddToCart(orange);
			homePage.clickOnCartBag();

			int actualQuantity = cartPage.getItemQuantity();
			int expectedQuantity = 2;
			Assert.assertEquals(actualQuantity, expectedQuantity, "Item quantity not increased using plus button.");
			System.out.println("verifyUserIsAbleToAddMoreThanOneQuantityOfItemToCartUsingPlusButton : Passed");

		} catch (Exception e) {
			System.out.println("verifyUserIsAbleToAddMoreThanOneQuantityOfItemToCartUsingPlusButton : Failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyUserIsAbleToCheckOut() {
		try {
			homePage.searchItemAndAddToCart(orange);
			homePage.clickOnCartBag();
			cartPage.clickOnProceedToCheckoutButton();

			String placeOrderButton = placeOrderPage.getPlaceOrderButton();
			String placeOrderButtonText = "Place Order";
			Assert.assertEquals(placeOrderButtonText, placeOrderButton,
					"Item quantity not increased using plus button.");
			System.out.println("verifyUserIsAbleToCheckOut : Passed");
		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
			System.out.println("verifyUserIsAbleToCheckOut : Failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyAddingMoreQuantityOfItemToAddToCartUsingQuantityBox() {
		try {
			homePage.searchItem(orange);
			Thread.sleep(2000);
			homePage.clearQuantityBox();
			homePage.enterQuantityInBox("10");
			homePage.clickOnAddToCart(orange);
			homePage.clickOnCartBag();

			int actualQuantity = cartPage.getItemQuantity();
			int expectedQuantity = 10;
			Assert.assertEquals(actualQuantity, expectedQuantity, "Item quantity not updated in quantity box.");
			System.out.println("verifyAddingMoreQuantityOfItemToAddToCartUsingQuantityBox : Passed");

		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
