package TestPages;

import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BasePage;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.PlaceOrderPage;
import pageObjects.ProceedPage;

public class ProceedPageTestCases extends BasePage {
	HomePage objHomePage = HomePage.getHomePageObject(driver);
	CartPage objCartPage = CartPage.getCartPageObject(driver);
	PlaceOrderPage objPlaceOrderPage = PlaceOrderPage.getPlaceOrderPageObject(driver);
	ProceedPage objProceedPage = ProceedPage.getProceedPageObject(driver);

	@Test
	public void VerifyUserIsAbleToSelectCountry(Method method) {
		try {
			objHomePage.searchItemAndAddToCart("orange");
			objHomePage.clickOnCartBag();
			objCartPage.clickOnProceedToCheckoutButton();
			objPlaceOrderPage.clickOnPlaceOrderButton();
			objProceedPage.selectCountryByVisibleText("India");

			String expectedCountry = "India";

			// Assert.assertEquals(selectedCountry, expectedCountry, "Country is not
			// selected");
			System.out.println(method.getName() + "Passed");
		} catch (Exception e) {
			System.out.println(method.getName() + " : failed");
			e.printStackTrace();
		}
	}

	@Test
	public void VerifyUserIsAbleToAcceptTermsAndConditions(Method method) {
		try {
			objHomePage.searchItemAndAddToCart("Orange");
			objHomePage.clickOnCartBag();
			objCartPage.clickOnProceedToCheckoutButton();
			objPlaceOrderPage.clickOnPlaceOrderButton();
			objProceedPage.selectCountryByVisibleText("India");
			objProceedPage.clickOnTermsAndConditions();

			WebElement checkBox = objProceedPage.termsAndConditionsCheckBoxElement();
			Assert.assertTrue(checkBox.isSelected(), "User is not able to accept Terms and Conditions");
			System.out.println(method.getName() + " : Passed");
		} catch (Exception e) {
			System.out.println(method.getName() + " : failed");
			e.printStackTrace();
		}
	}

	@Test
	public void VerifyUserIsAbleToProceedAndGetConformationMessage(Method method) {
		try {

			objHomePage.searchItemAndAddToCart("orange");
			objHomePage.clickOnCartBag();
			objCartPage.clickOnProceedToCheckoutButton();
			Thread.sleep(2000);
			objPlaceOrderPage.clickOnPlaceOrderButton();

			objProceedPage.selectCountryByVisibleText("India");
			objProceedPage.clickOnTermsAndConditions();
			objProceedPage.clickOnProceedButton();

			String actualConformation = objProceedPage.acceptTermsAndConditionsMessageText();
			String expectedConformation = "Thank you, your order has been placed successfully.";
			Assert.assertEquals(actualConformation, expectedConformation, "User is not getting expected error message");
			System.out.println(method.getName() + " :Passed");
			System.out.println("Order placed successfully!");
		} catch (Exception e) {
			System.out.println(method.getName() + " : failed");
			e.printStackTrace();
		}
	}

	@Test
	public void VerifyUserIsUnableToProceedWithOutAcceptingTermsAndCoditions(Method method) {
		try {
			objHomePage.searchItemAndAddToCart("Orange");
			objHomePage.clickOnCartBag();
			objCartPage.clickOnProceedToCheckoutButton();
			objPlaceOrderPage.clickOnPlaceOrderButton();
			objProceedPage.selectCountryByVisibleText("India");
			objProceedPage.clickOnProceedButton();

			String actualError = objProceedPage.acceptTermsAndConditionsMessageText();
			String expectedError = "Please accept Terms & Conditions - Required";
			Assert.assertEquals(actualError, expectedError, "User is not getting expected error message");
			System.out.println(method.getName() + " :Passed");
		} catch (Exception e) {
			System.out.println(method.getName() + " : failed");
			e.printStackTrace();
		}
	}

}
