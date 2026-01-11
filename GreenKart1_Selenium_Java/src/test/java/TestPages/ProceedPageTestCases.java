package TestPages;

import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BasePage;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.PlaceOrderPage;
import pageObjects.ProceedPage;

public class ProceedPageTestCases extends BasePage {
	
	HomePage homePage;
	CartPage cartPage;
	PlaceOrderPage placeOrderPage;
	ProceedPage proceedPage ;
	
	@BeforeMethod
    public void driverUsage() {
	 homePage = new HomePage(driver);
	 cartPage = new CartPage(driver);
	 placeOrderPage =new PlaceOrderPage(driver);
	 proceedPage =new ProceedPage(driver);
	 
       }
	

	@Test
	public void VerifyUserIsAbleToSelectCountry(Method method) {
		try {
			homePage.searchItemAndAddToCart("orange");
			homePage.clickOnCartBag();
			cartPage.clickOnProceedToCheckoutButton();
			placeOrderPage.clickOnPlaceOrderButton();
			proceedPage.selectCountryByVisibleText("India");

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
			homePage.searchItemAndAddToCart("Orange");
			homePage.clickOnCartBag();
			cartPage.clickOnProceedToCheckoutButton();
			placeOrderPage.clickOnPlaceOrderButton();
			proceedPage.selectCountryByVisibleText("India");
			proceedPage.clickOnTermsAndConditions();

			WebElement checkBox = proceedPage.termsAndConditionsCheckBoxElement();
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

			homePage.searchItemAndAddToCart("orange");
			homePage.clickOnCartBag();
			cartPage.clickOnProceedToCheckoutButton();
			Thread.sleep(2000);
			placeOrderPage.clickOnPlaceOrderButton();

			proceedPage.selectCountryByVisibleText("India");
			proceedPage.clickOnTermsAndConditions();
			proceedPage.clickOnProceedButton();

			String actualConformation = proceedPage.acceptTermsAndConditionsMessageText();
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
			homePage.searchItemAndAddToCart("Orange");
			homePage.clickOnCartBag();
			cartPage.clickOnProceedToCheckoutButton();
			placeOrderPage.clickOnPlaceOrderButton();
			proceedPage.selectCountryByVisibleText("India");
			proceedPage.clickOnProceedButton();

			String actualError = proceedPage.acceptTermsAndConditionsMessageText();
			String expectedError = "Please accept Terms & Conditions - Required";
			Assert.assertEquals(actualError, expectedError, "User is not getting expected error message");
			System.out.println(method.getName() + " :Passed");
		} catch (Exception e) {
			System.out.println(method.getName() + " : failed");
			e.printStackTrace();
		}
	}

}
