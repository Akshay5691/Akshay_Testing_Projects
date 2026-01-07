package TestPackage;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BasePage.BasePage;
import PageObjects.HomePage;
import PageObjects.ProductPage;


public class HomePageTestCases extends BasePage {

	HomePage objHome;
	ProductPage objProduct;
	
	private static final Logger log = LogManager.getLogger(HomePageTestCases.class);

	
	  @BeforeClass(alwaysRun = true)
	    public void setUpHomePageObjects() {
	        objHome = HomePage.getHomePageObject(driver);
	        objProduct = ProductPage.getProductPageObject(driver);
	    }
     @Test
	public void verifyUserIsAbleToEnterName(Method Method) {
		try {
			String name = "Akshay";
			objHome.enterName(name);

			String actualName = objHome.getEnteredName();
			Assert.assertEquals(actualName, name, "Entered name does not match expected value");
			log.info(Method.getName() + " : passed");
		} catch (Exception e) {
			log.error(Method.getName() + " : failed");
			e.printStackTrace();
		}
	}
		
    	@Test
	public void verifyUserIsAbleToSelectCountryDropdown(Method Method) {
		try {
			String country = "India";
			objHome.selectCountry(country);

			String actualSelectedCountry = objHome.getSelectedCountry();
			Assert.assertEquals(actualSelectedCountry, country, "Selected country does not match expected value");
			log.info(Method.getName() + " : passed");
		} catch (Exception e) {
			log.error(Method.getName() + " : failed");
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
			log.info(Method.getName() + " : passed");
		} catch (Exception e) {
			log.error(Method.getName() + " : failed");
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
			log.info(Method.getName() + " : passed");
		} catch (Exception e) {
			log.error(Method.getName() + " : failed");
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
			log.info(Method.getName() + " : passed");
		} catch (Exception e) {
			log.error(Method.getName() + " : failed");
			e.printStackTrace();
		}

	}
	@Test
	public void verifyUserCanSelectCountryAndStartShopping(Method Method) {
		try {		
			objHome.enterName("Akshay");
			objHome.selectGenderMale();
			objHome.clickShopButton();
           	String actualTitle = objProduct.getProductPageTitle();
           	Assert.assertEquals(actualTitle, "Products", "User is not navigated to Products page");
			log.info(Method.getName() + " : passed");
		} catch (Exception e) {
			log.error(Method.getName() + " : failed");
			e.printStackTrace();
		}
	}
	
	
	
	
}
