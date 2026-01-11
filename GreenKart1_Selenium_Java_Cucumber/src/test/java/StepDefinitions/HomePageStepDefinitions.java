package StepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import basePage.BasePage;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.HomePage;
import io.cucumber.java.en.Then;

public class HomePageStepDefinitions {
	
	  	WebDriver driver ;
	   HomePage homePage;
	    CartPage cartPage;	
	    

	    @Given("user is on GreenKart home page")
	    public void user_is_on_home_page() {
	    	BasePage base= new BasePage();
	        driver = base.getDriver();
	        homePage = new HomePage(driver);
	        cartPage = new CartPage(driver);
	    }

	    @When("user searches and adds {string} to cart")
	    public void user_searches_and_adds_product(String productName) {
	        homePage.searchItemAndAddToCart(productName);
	    }

	    @Then("cart item count should be {int}")
	    public void cart_item_count_should_be(int expectedCount) {
	        int actualCount = homePage.getItemNumberValue();
	        Assert.assertEquals(actualCount, expectedCount);
	    }

	    @When("user adds {string} and {string} to cart")
	    public void user_adds_multiple_products(String product1, String product2) {
	        homePage.searchItemAndAddToCart(product1);
	        homePage.searchItemAndAddToCart(product2);
	    }

	    @When("user adds {string} twice to cart")
	    public void user_adds_same_product_twice(String product) {
	    	
	        homePage.searchItem(product);
	        homePage.clickOnAddToCart(product);
	        homePage.clickOnAddToCart(product);
	    }

	    @When("user adds {string} to cart and opens cart")
	    public void user_adds_item_and_opens_cart(String product) {
	        homePage.searchItemAndAddToCart(product);
	        homePage.clickOnCartBag();
	    }

	    @Then("cart price should be {int}")
	    public void cart_price_should_be(int expectedPrice) {
	        int actualPrice = homePage.getItemPriceValue();
	        Assert.assertEquals(actualPrice, expectedPrice);
	    }

	    @When("user tries to decrease quantity for {string}")
	    public void user_tries_to_decrease_quantity(String product) throws InterruptedException {
	        homePage.searchItem(product);
	        Thread.sleep(1000);
	        homePage.clickMinusButtonAddToCart();
	    }

	    @Then("item quantity should remain {int}")
	    public void item_quantity_should_remain(int expectedQty) {
	        int actualQty = homePage.getItemQuantityInBox();
	        Assert.assertEquals(actualQty, expectedQty);
	    }

	    @When("user searches for {string}")
	    public void user_searches_for_item(String product) {
	        homePage.searchItem(product);
	    }

	    @Then("product name should be {string}")
	    public void product_name_should_be(String expectedName) {
	        String actualName = homePage.getProductName(expectedName);
	        Assert.assertEquals(actualName, expectedName);
	    }
}
