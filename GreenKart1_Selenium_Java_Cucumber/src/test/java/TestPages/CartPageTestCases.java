package TestPages;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import basePage.BasePage;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import pageObjects.CartPageMethods;
import pageObjects.HomePageMethods;
import pageObjects.PlaceOrderPageMethods;
import pageObjects.ProceedPageMethods;

public class CartPageTestCases extends BasePage {

	private static final Logger log = LogManager.getLogger(CartPageTestCases.class);

	HomePageMethods homePage;
	CartPageMethods cartPage;
	PlaceOrderPageMethods placeOrderPage;
	
	@BeforeClass(alwaysRun = true)
	public void pageObjectInit() {
		homePage = HomePageMethods.getHomePageObject(driver);
		cartPage = CartPageMethods.getCartPageObject(driver);
		placeOrderPage = PlaceOrderPageMethods.getPlaceOrderPageObject(driver);
	}
	String orange = "Orange";

	@Test
	public void verifyItemQuantityIncreasingWhileAddingSameItem(Method method) {
		try {
			homePage.searchItem(orange);
			homePage.clickOnAddToCart(orange);
			homePage.clickOnAddToCart(orange);
			homePage.clickOnCartBag();

			int actualQuantity = cartPage.getItemQuantity();
			int expectedQuantity = 2;

			Assert.assertEquals(actualQuantity, expectedQuantity, "Item quantity not increased as expected.");
			log.info( method + " : Passed");
		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
			System.out.println("verifyItemQuantityIncreasingWhileAddingSameItem : Failed");
		
		}
	}

	@Test
	public void verifyUserIsAbleToSeeItemInCart(Method method) {
		try {
			homePage.searchItemAndAddToCart("Apple");
			homePage.clickOnCartBag();

			String actualItem = cartPage.getItemNameText("Apple");
			String expectedItem = "Apple";

			Assert.assertEquals(actualItem, expectedItem, "Item not present in cart.");
			log.info( method+ " : Passed");
		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
			System.out.println("verifyUserIsAbleToSeeItemInCart : Failed");
			
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
			Assert.fail("Exception occurred: " + e.getMessage());
			System.out.println("verifyUserIsAbleToRemoveItemFromCart : Failed");
		
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
			Assert.fail("Exception occurred: " + e.getMessage());
			System.out.println("verifyUserIsAbleToAddMoreThanOneQuantityOfItemToCartUsingPlusButton : Failed");
		
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
			System.out.println("verifyAddingMoreQuantityOfItemToAddToCartUsingQuantityBox : Failed");
		}
	}

}
