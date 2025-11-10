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

    HomePageMethods homePage = new HomePageMethods(driver);
    CartPageMethods cartPage = new CartPageMethods(driver);
    PlaceOrderPageMethods placeOrderPage = new PlaceOrderPageMethods(driver);
    ProceedPageMethods proceedPage = new ProceedPageMethods(driver);

    @Test
    public void verifyUserIsAbleToApplyPromoCode() {
        try {
            homePage.searchItemAndAddToCart("orange");
            homePage.clickOnCartBag();
            cartPage.clickOnProceedToCheckOutButton();
            Thread.sleep(2000);

            placeOrderPage.enterPromoCodeAndApply("rahulshettyacademy");
            placeOrderPage.waitUntilElementVisible("//span[text()='Code applied ..!']");
            String actualMessage = placeOrderPage.getPromoCodeAppliedMessage();
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
            homePage.searchItemAndAddToCart("orange");
            homePage.clickOnCartBag();
            cartPage.clickOnProceedToCheckOutButton();
            Thread.sleep(2000);

            placeOrderPage.enterPromoCodeAndApply("rahulshetty");
            placeOrderPage.waitUntilElementVisible("//span[text()='Invalid code ..!']");
            String actualMessage = placeOrderPage.getInvalidPromoCodeApplied();

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
            homePage.searchItemAndAddToCart("orange");
            homePage.clickOnCartBag();
            placeOrderPage.enterPromoCodeAndApply("rahulshettyacademy");
            placeOrderPage.waitUntilElementVisible("//span[text()='Code applied ..!']");

            int actualDiscount = placeOrderPage.getDiscountPercentage();
            cartPage.clickOnProceedToCheckOutButton();
            Thread.sleep(2000);
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
            homePage.searchItemAndAddToCart("orange");
            homePage.clickOnCartBag();
            cartPage.clickOnProceedToCheckOutButton();
            Thread.sleep(2000);

            placeOrderPage.enterPromoCodeAndApply("rahulshetty");
            placeOrderPage.waitUntilElementVisible("//span[text()='Invalid code ..!']");
            int actualDiscount = placeOrderPage.getDiscountPercentage();
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
            homePage.searchItemAndAddToCart("orange");
            homePage.clickOnCartBag();
            cartPage.clickOnProceedToCheckOutButton();
            Thread.sleep(2000);

            placeOrderPage.enterPromoCodeAndApply("rahulshettyacademy");
            placeOrderPage.clickOnPlaceOrderButton();
            Thread.sleep(2000);

            WebElement proceedButton = proceedPage.proceedButton();
            Assert.assertNotNull(proceedButton, "Proceed button not visible after placing order");
            System.out.println("verifyUserIsAbleToPlaceOrderAfterPromoCodeApply : Passed");
        } catch (Exception e) {
            System.out.println("verifyUserIsAbleToPlaceOrderAfterPromoCodeApply : Failed");
            e.printStackTrace();
        }
    }

    @Test
    public void verifyUserIsAbleToPlaceOrderWithoutPromoCodeApply() {
        try {
            homePage.searchItemAndAddToCart("orange");
            homePage.clickOnCartBag();
            cartPage.clickOnProceedToCheckOutButton();
            Thread.sleep(4000);

            placeOrderPage.clickOnPlaceOrderButton();
            WebElement proceedButton = proceedPage.proceedButton();

            Assert.assertNotNull(proceedButton, "Order not placed without promo code");
            System.out.println("verifyUserIsAbleToPlaceOrderWithoutPromoCodeApply : Passed");
        } catch (Exception e) {
            System.out.println("verifyUserIsAbleToPlaceOrderWithoutPromoCodeApply : Failed");
            e.printStackTrace();
        }
    }

    @Test
    public void verifyItemsQuantityInGridTable() {
        try {
            String[] items = {"apple", "orange", "carrot", "tomato", "orange"};
            homePage.searchMultipleItems(items);
            homePage.clickOnCartBag();
            cartPage.clickOnProceedToCheckOutButton();
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
            String[] items = {"apple", "orange", "carrot", "tomato", "orange"};
            homePage.searchMultipleItems(items);
            homePage.clickOnCartBag();
            cartPage.clickOnProceedToCheckOutButton();
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
            String[] items = {"apple", "orange", "carrot", "tomato", "orange"};
            homePage.searchMultipleItems(items);
            homePage.clickOnCartBag();
            cartPage.clickOnProceedToCheckOutButton();
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
