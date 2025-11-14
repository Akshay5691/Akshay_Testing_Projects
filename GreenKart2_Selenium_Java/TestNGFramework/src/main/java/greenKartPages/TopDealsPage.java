package greenKartPages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

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

public class TopDealsPage {
	public WebDriver driver;
	basepage basepage = new basepage();

	public TopDealsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void descending_sorting_of_veg_fruit_column() {
		basepage.clickElement(veg_fruit_name);
		ArrayList<String> actualvalue = new ArrayList<String>();
		ArrayList<String> sortedvalue = new ArrayList<String>();

		for (WebElement name : veg_fruit_name_column_list)
			actualvalue.add(basepage.getTextValue(name));

		System.out.println(actualvalue);

		for (String value : actualvalue)
			sortedvalue.add(value);

		Collections.sort(sortedvalue);
		Collections.reverse(sortedvalue);

		System.out.println("Sorted List = " + sortedvalue);
		Assert.assertEquals(actualvalue, sortedvalue);

	}

	public void ascending_sorting_of_veg_fruit_column() {
		basepage.clickElement(veg_fruit_name);
		ArrayList<String> actualvalue = new ArrayList<String>();
		ArrayList<String> sortedvalue = new ArrayList<String>();

		for (WebElement name : veg_fruit_name_column_list)
			actualvalue.add(basepage.getTextValue(name));

		System.out.println(actualvalue);

		for (String value : actualvalue)
			sortedvalue.add(value);

		Collections.sort(sortedvalue);

		System.out.println("Sorted List = " + sortedvalue);
		Assert.assertEquals(actualvalue, sortedvalue);

	}

	public void descending_sorting_of_price_column() {
		basepage.clickElement(price_column);
		ArrayList<Integer> actualvalue = new ArrayList<Integer>();
		ArrayList<Integer> sortedvalue = new ArrayList<Integer>();

		for (WebElement price : price_column_list)
			actualvalue.add(Integer.parseInt(basepage.getTextValue(price)));

		System.out.println(actualvalue);

		for (Integer value : actualvalue)
			sortedvalue.add(value);

		Collections.sort(sortedvalue);
		Collections.reverse(sortedvalue);

		System.out.println("Sorted List = " + sortedvalue);
		Assert.assertEquals(actualvalue, sortedvalue);

	}

	public void ascending_sorting_of_price_column() {
		basepage.clickElement(price_column);
		ArrayList<Integer> actualvalue = new ArrayList<Integer>();
		ArrayList<Integer> sortedvalue = new ArrayList<Integer>();

		for (WebElement price : price_column_list)
			actualvalue.add(Integer.parseInt(basepage.getTextValue(price)));

		System.out.println(actualvalue);

		for (Integer value : actualvalue)
			sortedvalue.add(value);

		Collections.sort(sortedvalue);

		System.out.println("Sorted List = " + sortedvalue);
		Assert.assertEquals(actualvalue, sortedvalue);

	}

	public void descending_sorting_of_discount_price_column() {
		basepage.clickElement(discount_price_column);
		ArrayList<Integer> actualvalue = new ArrayList<Integer>();
		ArrayList<Integer> sortedvalue = new ArrayList<Integer>();

		for (WebElement price : discount_price_column_list)
			actualvalue.add(Integer.parseInt(basepage.getTextValue(price)));

		System.out.println(actualvalue);

		for (Integer value : actualvalue)
			sortedvalue.add(value);

		Collections.sort(sortedvalue);
		Collections.reverse(sortedvalue);

		System.out.println("Sorted List = " + sortedvalue);
		Assert.assertEquals(actualvalue, sortedvalue);

	}

	public void ascending_sorting_of_discount_price_column() {
		basepage.clickElement(discount_price_column);
		ArrayList<Integer> actualvalue = new ArrayList<Integer>();
		ArrayList<Integer> sortedvalue = new ArrayList<Integer>();

		for (WebElement price : discount_price_column_list)
			actualvalue.add(Integer.parseInt(basepage.getTextValue(price)));

		System.out.println(actualvalue);

		for (Integer value : actualvalue)
			sortedvalue.add(value);

		Collections.sort(sortedvalue);

		System.out.println("Sorted List = " + sortedvalue);
		Assert.assertEquals(actualvalue, sortedvalue);

	}

	// Locators

	@FindBy(xpath = "//div[@class='brand greenLogo']")
	WebElement greenkartlogo;

	@FindBy(xpath = "//tbody//td[2]")
	List<WebElement> veg_fruit_name_column_list;

	@FindBy(xpath = "//*[text()='Veg/fruit name']")
	WebElement veg_fruit_name;

	@FindBy(xpath = "//*[text()='Price']")
	WebElement price_column;

	@FindBy(xpath = "//tbody//td[3]")
	List<WebElement> price_column_list;

	@FindBy(xpath = "//*[text()='Discount price']")
	WebElement discount_price_column;

	@FindBy(xpath = "//tbody//td[4]")
	List<WebElement> discount_price_column_list;
}
