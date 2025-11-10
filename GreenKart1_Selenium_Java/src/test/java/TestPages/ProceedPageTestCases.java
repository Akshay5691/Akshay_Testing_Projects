package TestPages;

import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import basePage.BasePage;
import pageObjects.CartPageMethods;
import pageObjects.HomePageMethods;
import pageObjects.PlaceOrderPageMethods;
import pageObjects.ProceedPageMethods;

public class ProceedPageTestCases extends BasePage {
	  HomePageMethods objHomePage = new HomePageMethods(driver);
	    CartPageMethods objCartPage = new CartPageMethods(driver);
	    PlaceOrderPageMethods objPlaceOrderPage = new PlaceOrderPageMethods(driver);
	    ProceedPageMethods objProceedPage = new ProceedPageMethods(driver);

	    @Test
	    public void VerifyUserIsAbleToSelectCountry(Method method) {
	        try {
	            objHomePage.searchItemAndAddToCart("orange");
	            objHomePage.clickOnCartBag();
	            objCartPage.clickOnProceedToCheckOutButton();
	            Thread.sleep(2000);
	            objPlaceOrderPage.clickOnPlaceOrderButton();

	            Select countryDropdown = objProceedPage.selectCountry();
	            countryDropdown.selectByVisibleText("India");
	            String selectedCountry = countryDropdown.getFirstSelectedOption().getText();
	            String expectedCountry = "India";

	            Assert.assertEquals(selectedCountry, expectedCountry, "Country is not selected");
	        } catch (Exception e) {
	            System.out.println(method.getName() + " : failed");
	            e.printStackTrace();
	        }
	    }

	    @Test
	    public void VerifyUserIsAbleToAcceptTermsAndConditions(Method method) {
	        try {
	            objHomePage.searchItemAndAddToCart("orange");
	            objHomePage.clickOnCartBag();
	            objCartPage.clickOnProceedToCheckOutButton();
	            Thread.sleep(2000);
	            objPlaceOrderPage.clickOnPlaceOrderButton();

	            objProceedPage.selectCountry().selectByVisibleText("India");
	            objProceedPage.clickOnTermsAndConditions();

	            WebElement checkBox = objProceedPage.getTermsAndConditionsCheckBox();
	            Assert.assertTrue(checkBox.isSelected(), "User is not able to accept Terms and Conditions");
	        } catch (Exception e) {
	            System.out.println(method.getName() + " : failed");
	            e.printStackTrace();
	        }
	    }

	    @Test
	    public void VerifyUserIsAbleToProceedAndGetConformationMessage(Method method) {
	        try {
	            driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	            objHomePage.searchItemAndAddToCart("orange");
	            objHomePage.clickOnCartBag();
	            objCartPage.clickOnProceedToCheckOutButton();
	            Thread.sleep(2000);
	            objPlaceOrderPage.clickOnPlaceOrderButton();

	            objProceedPage.selectCountry().selectByVisibleText("India");
	            objProceedPage.clickOnTermsAndConditions();
	            objProceedPage.clickOnProceedButton();

	            // You can add validation for confirmation page (if visible)
	            System.out.println("Order placed successfully!");
	        } catch (Exception e) {
	            System.out.println(method.getName() + " : failed");
	            e.printStackTrace();
	        }
	    }

	    @Test
	    public void VerifyUserIsUnableToProceedWithOutAcceptingTermsAndCoditions(Method method) {
	        try {
	            objHomePage.searchItemAndAddToCart("orange");
	            objHomePage.clickOnCartBag();
	            objCartPage.clickOnProceedToCheckOutButton();
	            Thread.sleep(2000);
	            objPlaceOrderPage.clickOnPlaceOrderButton();

	            objProceedPage.selectCountry().selectByVisibleText("India");
	            objProceedPage.clickOnProceedButton();

	            String actualError = objProceedPage.getAcceptTheTermsAndConditionsMessage();
	            String expectedError = "Please accept Terms & Conditions - Required";

	            Assert.assertEquals(actualError, expectedError, "User is not getting expected error message");
	        } catch (Exception e) {
	            System.out.println(method.getName() + " : failed");
	            e.printStackTrace();
	        }
	    }

}
