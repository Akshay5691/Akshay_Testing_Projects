package TestPages;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BasePage;
import pageObjects.CartPage;
import pageObjects.HomePage;;

public class HomePageTestCases extends BasePage {

	private static final Logger log = LogManager.getLogger(HomePageTestCases.class);
	
	HomePage homePage;
	CartPage cartPage;
	@BeforeMethod(alwaysRun = true)
    public void driverUsage() {
	 homePage = new HomePage(driver);
	 cartPage = new CartPage(driver);
       }

	String orange = "Orange";
	String apple = "Apple";

	@Test(dataProvider = "productsNamesFromExcel", dataProviderClass = dataProvider.DataProviders.class)
	public void verifyItemNumberIsUpdatingWhileAddingDifferentProductsToCart(Method method, String productName) {
		try {
			homePage.searchItemAndAddToCart(productName);

			int actualItemNumber = homePage.getItemNumberValue();
			int expectedValue = 1;
			Assert.assertEquals(actualItemNumber, expectedValue, "Item not added to cart");
			log.info(method.getName() + " : passed");

		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
			log.error(method.getName() + " : failed");

		}
	}

	@Test()
	public void verifyItemNumberIsIncreasingWhileAddingMultipleItems(Method method) {
		try {
			homePage.searchItemAndAddToCart(orange);
			homePage.searchItemAndAddToCart(apple);

			int actualItemNumber = homePage.getItemNumberValue();
			int expectedValue = 2;
			Assert.assertEquals(actualItemNumber, expectedValue, "Multiple items are not added to cart");
			log.info(method.getName() + " : passed");

		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
			log.error(method.getName() + " : failed");

		}
	}

	@Test(groups= {"smoke"})
	public void verifyItemNumberIsNotIncreasingWhileAddingSameItem(Method method) {
		try {
			homePage.searchItem(orange);
			homePage.clickOnAddToCart(orange);
			homePage.clickOnAddToCart(orange);

			int itemNumber = homePage.getItemNumberValue();
			int expectedValue = 1;
			Assert.assertEquals(itemNumber, expectedValue, "Item number increased after adding same item twice");
			log.info(method.getName() + " : passed");

		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
			log.error(method.getName() + " : failed");

		}
	}

	@Test(groups= {"smoke"})
	public void verifyUserIsAbleToIncreaseCartPrice(Method method) {
		try {
			homePage.searchItemAndAddToCart(apple);
			homePage.clickOnCartBag();

			int itemPrice = homePage.getItemPriceValue();
			int expectedValue = 72;
			Assert.assertEquals(itemPrice, expectedValue, "Item price is not increased correctly");
			log.info(method.getName() + " : passed");

		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
			log.error(method.getName() + " : failed");

		}
	}

	@Test(groups= {"smoke"},retryAnalyzer=retryFailedTests.reExecuteFailedTestCases.class)
	public void verifyUserIsNotAbleToDecreaseDefaultQuantityInQuantityBox(Method method) {
		try {
			homePage.searchItem(orange);
			Thread.sleep(1000);
			homePage.clickMinusButtonAddToCart();

			int defaultQuantity = homePage.getItemQuantityInBox();
			int expectedValue = 1;
			Assert.assertEquals(defaultQuantity, expectedValue, "Default quantity decreased incorrectly");
			log.info(method.getName() + " : passed");

		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
			log.error(method.getName() + " : failed");

		}
	}

	@Test()
	public void verifyUserIsAbleToSearchItem(Method method) {
		try {
			homePage.searchItem(orange);

			String itemName = homePage.getProductName(orange);
			String expectedName = orange;
			Assert.assertEquals(itemName, expectedName, "Item is not searched correctly");
			log.info(method.getName() + " : passed");

		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
			log.error(method.getName() + " : failed");

		}
	}
	/*
	 * @Test public void cartGrid(ITestResult method) { try { WebElement cartGrid =
	 * driver.findElement(By.xpath("//table[@id='productCartTables']"));
	 * List<WebElement> cartTableRows =
	 * driver.findElements(By.xpath("//table[@id='productCartTables']//tbody//tr"));
	 * int countOfRows = cartTableRows.size();
	 * 
	 * for (int i = 1; i < countOfRows; i++) { List<WebElement> cartTableRowColumns
	 * = cartTableRows.get(i).findElements(By.xpath("td")); String actualProductName
	 * = cartTableRowColumns.get(1).getText(); if
	 * (actualProductName.contains("Onion")) { String itemQty =
	 * cartTableRowColumns.get(2).getText();
	 * Assert.assertEquals(Integer.parseInt(itemQty), 3,
	 * "Added item quantity in search page and cart page doesn't match"); break; } }
	 * 
	 * homePage.searchItemAndAddToCart("orange"); int actualItemNumber =
	 * homePage.getItemNumber(); int expectedValue = 1;
	 * Assert.assertEquals(actualItemNumber, expectedValue,
	 * "Item not added to cart"); System.out.println(method.getName() +
	 * " : passed");
	 * 
	 * } catch (Exception e) { System.out.println(method.getName() + " : failed");
	 * e.printStackTrace(); } }
	 */

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
