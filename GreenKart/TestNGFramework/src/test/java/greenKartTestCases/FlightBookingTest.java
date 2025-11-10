package greenKartTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import greenKartPages.FlightBookingPage;
import greenKartPages.HomePage;
import greenKartPages.TopDealsPage;
import utilities.basepage;

public class FlightBookingTest extends basepage {

	public WebDriver driver;

	@BeforeClass
	public void landOnHomePage() {
		driver = initializeDriver();
		verifyTitle("GrrenKart - veg and fruits Kart");

	}

	@Test(priority = 1)
	public void verify_user_is_able_to_navigate_to_Top_Deals_page() {
		HomePage hp = new HomePage(driver);
		FlightBookingPage fp = new FlightBookingPage(driver);
		hp.navigate_to_flight_booking_page();
		switch_to_new_window();
		fp.explicitwait();

	}

	@Test(priority = 2)
	public void verify_user_is_able_to_select_from_to_details_for_one_wat_trip() {
		FlightBookingPage fp = new FlightBookingPage(driver);
		fp.select_from_to_details();

	}

	@Test(priority = 3)
	public void verify_user_is_able_to_select_passanger_details() {
		FlightBookingPage fp = new FlightBookingPage(driver);
		fp.select_passangers(4, 2, 3);
	}

	@Test(priority = 4)
	public void verify_popup_is_displayed_if_passanger_limit_exceed() {
		FlightBookingPage fp = new FlightBookingPage(driver);
		reloadbrowser(driver);
		fp.select_passangers(8, 2, 0);
		fp.verifyalertmessage();
		acceptalertpopup(driver);
	}

	@Test(priority = 5)
	public void verify_user_is_able_to_select_currency() {
		FlightBookingPage fp = new FlightBookingPage(driver);
		reloadbrowser(driver);
		fp.selectcurrency("USD");
	}

	@Test(priority = 6)
	public void verify_user_is_able_to_select_checkbox() {
		FlightBookingPage fp = new FlightBookingPage(driver);
		reloadbrowser(driver);
		fp.select_checkbox("Student");
	}

	@Test(priority = 7)
	public void verify_tooltip_for_student() {
		FlightBookingPage fp = new FlightBookingPage(driver);
		fp.tooltip_for_student();
	}

	@Test(priority = 8)
	public void verify_special_assistance_popup() {
		FlightBookingPage fp = new FlightBookingPage(driver);
		fp.special_assistance();
	}

	@Test(priority = 9)
	public void verify_multicity_popup() {
		FlightBookingPage fp = new FlightBookingPage(driver);
		fp.multicity_popup();
	}

	@AfterClass
	public void CloseBrowser() {
		quit();
	}

}
