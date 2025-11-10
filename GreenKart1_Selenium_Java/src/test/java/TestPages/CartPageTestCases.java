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

	    @Test
	    public void verifyItemQuantityIncreasingWhileAddingSameItem() {
	        try {
	            homePage.searchItem("orange");
	            Thread.sleep(3000);
	            homePage.clickOnAddToCart();
	            Thread.sleep(6000);
	            homePage.clickOnAddToCart();
	            Thread.sleep(2000);
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
	            homePage.searchItemAndAddToCart("apple");
	            homePage.clickOnCartBag();

	            String actualItem = cartPage.getItemName();
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
	            String emptyPageMessage = cartPage.getEmptyPageMessageInCart();
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
	            homePage.searchItem("orange");
	            Thread.sleep(3000);
	            homePage.clickOnPlusButtonAddToCart();
	            Thread.sleep(3000);
	            homePage.clickOnAddToCart();
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
	            homePage.searchItemAndAddToCart("orange");
	            homePage.clickOnCartBag();
	            cartPage.clickOnProceedToCheckOutButton();
	            Thread.sleep(2000);

	            WebElement placeOrderButton = placeOrderPage.placeOrderButton;
	            Assert.assertNotNull(placeOrderButton, "User is not able to checkout.");
	            System.out.println("verifyUserIsAbleToCheckOut : Passed");
	        } catch (Exception e) {
	            System.out.println("verifyUserIsAbleToCheckOut : Failed");
	            e.printStackTrace();
	        }
	    }

	    @Test
	    public void verifyAddingMoreQuantityOfItemToAddToCartUsingQuantityBox() {
	        try {
	            homePage.searchItem("orange");
	            Thread.sleep(3000);
	            homePage.clearQuantityBox();
	            homePage.enterQuantityInBox("10");
	            homePage.clickOnAddToCart();
	            homePage.clickOnCartBag();

	            int actualQuantity = cartPage.getItemQuantity();
	            int expectedQuantity = 10;

	            Assert.assertEquals(actualQuantity, expectedQuantity, "Item quantity not updated in quantity box.");
	            System.out.println("verifyAddingMoreQuantityOfItemToAddToCartUsingQuantityBox : Passed");
	        } catch (Exception e) {
	            System.out.println("verifyAddingMoreQuantityOfItemToAddToCartUsingQuantityBox : Failed");
	            e.printStackTrace();
	        }
	    }

}
