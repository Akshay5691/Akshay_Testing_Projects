package TestPackage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BasePage.BasePage;
import PageObjects.HomePageObjects;
import io.appium.java_client.AppiumBy;;

public class HomePageTestCases extends BasePage {

	HomePageObjects objHome;
	
	
	  @BeforeClass(alwaysRun = true)
	    public void setUpHomePageObjects() {
	        objHome = HomePageObjects.getHomePageObject(driver);
	    }

	
		

	@Test
	public void verifyUserCanSelectCountryAndStartShopping(Method Method) {
		try {
			objHome.selectCountry("India");
			objHome.enterName("Akshay");
			objHome.selectGenderMale();
			objHome.clickShopButton();
            
			System.out.println(Method.getName() + ": passed");
		} catch (Exception e) {
			System.out.println(Method.getName() + ": failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyUserCannotProceedWithoutEnteringName(Method Method) {
		try {
			objHome.selectGenderMale();
			objHome.clickShopButton();

			String actualAlertText = objHome.getAlertText();
			String alertText = "Please enter your name";
			Assert.assertEquals(actualAlertText, alertText, "Alert text does not match expected value");
			System.out.println(Method.getName() + " : passed");
		} catch (Exception e) {
			System.out.println(Method.getName() + ": failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyUserIsAbleToSelectOnlyOneGenderOption(Method Method) {
		try {

			objHome.enterName("Akshay");
			objHome.selectGenderFemale();

			boolean isMaleSelected = objHome.isGenderMaleSelected();
			Assert.assertFalse(isMaleSelected, "male gender option is selected");
			System.out.println(Method.getName() + ": passed");
		} catch (Exception e) {
			System.out.println(Method.getName() + ": failed");
			e.printStackTrace();
		}
	}

	@Test
	public void verifyUserIsAbleToSelectGenderFemaleOption(Method Method) {
		try {
			objHome.enterName("Akshay");
			objHome.selectGenderFemale();

			boolean isFeMaleSelected = objHome.isGenderFemaleSelected();
			Assert.assertTrue(isFeMaleSelected, "Gender female option is not selected");
			System.out.println(Method.getName() + ": passed");
		} catch (Exception e) {
			System.out.println(Method.getName() + ": failed");
			e.printStackTrace();
		}

	}
}
