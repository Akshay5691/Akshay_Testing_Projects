package greenKartTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import greenKartPages.HomePage;
import greenKartPages.TopDealsPage;
import utilities.basepage;

public class TopDealsTest extends basepage {

	public WebDriver driver;

	@BeforeClass
	public void landOnHomePage() {
		driver = initializeDriver();
		verifyTitle("GrrenKart - veg and fruits Kart");

	}

	@Test(priority = 1)
	public void verify_user_is_able_to_navigate_to_Top_Deals_page() {
		HomePage hp = new HomePage(driver);
		hp.navigateToTopDeals();
		switch_to_new_window();
	}

	@Test(priority = 2)
	public void verify_descending_sorting_on_veg_fruit_column() {
		TopDealsPage tp = new TopDealsPage(driver);
		tp.descending_sorting_of_veg_fruit_column();
	}

	@Test(priority = 3)
	public void verify_ascending_sorting_on_veg_fruit_column() {
		TopDealsPage tp = new TopDealsPage(driver);
		tp.ascending_sorting_of_veg_fruit_column();
	}

	@Test(priority = 4)
	public void verify_descending_sorting_on_price_column() {
		TopDealsPage tp = new TopDealsPage(driver);
		tp.descending_sorting_of_price_column();
	}

	@Test(priority = 5)
	public void verify_ascending_sorting_on_price_column() {
		TopDealsPage tp = new TopDealsPage(driver);
		tp.ascending_sorting_of_price_column();
	}

	@Test(priority = 6)
	public void verify_descending_sorting_on_discount_price_column() {
		TopDealsPage tp = new TopDealsPage(driver);
		tp.descending_sorting_of_discount_price_column();
	}

	@Test(priority = 7)
	public void verify_ascending_sorting_on_discount_price_column() {
		TopDealsPage tp = new TopDealsPage(driver);
		tp.ascending_sorting_of_discount_price_column();
	}

	@AfterClass
	public void CloseBrowser() {
		quit();
	}

}
