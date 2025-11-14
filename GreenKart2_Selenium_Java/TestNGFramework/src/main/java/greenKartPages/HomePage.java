package greenKartPages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.basepage;

public class HomePage {
	public WebDriver driver;
	basepage basepage = new basepage();

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void greenKartLogo() {
		Assert.assertTrue(basepage.verifyVisibilityOfElement(greenkartlogo));
	}

	public void topDealLink() {
		Assert.assertTrue(basepage.checkElementIsClickable(topdeals));
	}

	public void flightBookingLink() {
		Assert.assertTrue(basepage.checkElementIsClickable(flightbooking));
	}

	public void itemsAndPriceSection() {
		Assert.assertTrue(basepage.verifyVisibilityOfElement(items));
		Assert.assertTrue(basepage.verifyVisibilityOfElement(price));
	}

	public void cartIcon() {
		Assert.assertTrue(basepage.checkElementIsClickable(carticon));
	}

	public void searchvegetable(String vegetablename) {
		basepage.cleartextboxvalue(search);
		basepage.enterData(search, vegetablename);
		basepage.sleep(2000);
		String vegname = basepage.getTextValue(searchvegname);
		String names[] = vegname.split(" ");
		Assert.assertEquals(names[0], vegetablename);
	}

	public void searchvegetableisnotpresent(String vegetablename) {
		basepage.cleartextboxvalue(search);
		basepage.sleep(2000);
		basepage.enterData(search, vegetablename);
		basepage.sleep(2000);
		String msg = basepage.getTextValue(noresulterrormsg);
		System.out.println(msg);
		Assert.assertEquals(msg, "Sorry, no products matched your search!");

	}

	public void detailsPresentOnSearhVegetable(String vegetablename, String price) {
		basepage.cleartextboxvalue(search);
		basepage.enterData(search, vegetablename);
		basepage.sleep(3000);
		String vegname = basepage.getTextValue(searchvegname);
		String actualprice = basepage.getTextValue(productprice);

		Assert.assertEquals(vegname, vegetablename);
		Assert.assertEquals(actualprice, price);
		basepage.checkElementIsClickable(addtocartbutton);

	}

	public void add_n_Kg_vegetable(String veg, int kg) {
		basepage.cleartextboxvalue(search);
		basepage.enterData(search, veg);
		basepage.sleep(3000);

		for (int i = 1; i < kg; i++)
			basepage.clickElement(addquantity);

		String quantity = basepage.getAttributeValue(addedquantity, "value");
		System.out.println("Added Quantity = " + quantity);

		Assert.assertEquals(Integer.parseInt(quantity), kg);

	}

	public void addMultipleVegetables(String vegetables) {
		basepage.reloadbrowser(driver);
		basepage.sleep(3000);
		String[] vegetable = vegetables.split(",");
		for (String veg : vegetable) {
			String[] vegdetails = veg.split(" ");
			searchvegetable(vegdetails[0]);
			int quant = Integer.parseInt(vegdetails[2]);
			if (quant != 1) {
				for (int i = 1; i < quant; i++)
					basepage.clickElement(addquantity);
			}
			addToCart(veg);
			basepage.sleep(5000);
		}
	}

	public void addedVegetablesInCart(String vegetables) {
		boolean status = false;
		basepage.clickElement(carticon);
		String[] vegetable = vegetables.split(",");

		for (int i = 0; i < vegetable.length; i++) {
			status = false;
			String[] veg = vegetable[i].split(" ");
			for (WebElement product : cartproducts) {
				if (basepage.getTextValue(product).contains(veg[0]))
					status = true;
			}
		}

		Assert.assertTrue(status);

	}

	public void addToCart(String veg) {
		basepage.clickElement(addtocartbutton);
	}

	public void navigateToTopDeals() {
		basepage.clickElement(topdeals);
	}

	public void navigate_to_flight_booking_page() {
		basepage.clickElement(flightbooking);
	}
	// Locators

	@FindBy(xpath = "//div[@class='brand greenLogo']")
	WebElement greenkartlogo;

	@FindBy(xpath = "//a[text()='Top Deals']")
	WebElement topdeals;

	@FindBy(xpath = "//a[text()='Flight Booking']")
	WebElement flightbooking;

	@FindBy(xpath = "//td[text()='Items']")
	WebElement items;

	@FindBy(xpath = "//td[text()='Price']")
	WebElement price;

	@FindBy(className = "cart-icon")
	WebElement carticon;

	@FindBy(xpath = "//input[@type='search']")
	WebElement search;

	@FindBy(xpath = "//h4[@class='product-name']")
	WebElement searchvegname;

	@FindBy(xpath = "//div[@class='no-results']//h2")
	WebElement noresulterrormsg;

	@FindBy(xpath = "//p[@class='product-price']")
	WebElement productprice;

	@FindBy(xpath = "//div[@class='product-action']//button")
	WebElement addtocartbutton;

	@FindBy(xpath = "//a[@class='increment']")
	WebElement addquantity;

	@FindBy(xpath = "//input[@type='number']")
	WebElement addedquantity;

	@FindBy(xpath = "//div[@class='cart-preview active']//p[@class='product-name']")
	List<WebElement> cartproducts;

}
