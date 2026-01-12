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
import pageObjects.PlaceOrderPage;
import io.cucumber.java.en.Then;

public class HomePageStepDefinitions {
	
	  	WebDriver driver ;
	   HomePage homePage;
	    CartPage cartPage;
	    PlaceOrderPage placeOrderPage;
	    

	    @Given("user is on GreenKart home page")
	    public void user_is_on_cart_page() {
	        driver = BasePage.getDriver();  
	        if (driver == null) {
	            throw new IllegalStateException("Driver is not initialized!");
	        }
	        homePage = new HomePage(driver);
	        cartPage = new CartPage(driver);
	        placeOrderPage = new PlaceOrderPage(driver);
	    }

	       

	        @When("user searches and adds {string} to cart")
	        public void user_searches_and_adds_to_cart(String productName) {
	            homePage.searchItemAndAddToCart(productName);
	        }

	        @Then("cart item number should be {int}")
	        public void cart_item_number_should_be(Integer expectedValue) {
	            int actualItemNumber = homePage.getItemNumberValue();
	            Assert.assertEquals(actualItemNumber, expectedValue.intValue(), "Item number mismatch");
	       
	        }

	        @When("user searches {string}")
	        public void user_searches_item(String productName) {
	            homePage.searchItem(productName);
	        }

	        @When("user adds {string} to cart")
	        public void user_adds_item_to_cart(String productName) {
	            homePage.clickOnAddToCart(productName);
	        }

	        @When("user adds {string} to cart again")
	        public void user_adds_item_to_cart_again(String productName) {
	            homePage.clickOnAddToCart(productName);
	        }

	        @When("user opens cart bag")
	        public void user_opens_cart_bag() {
	            homePage.clickOnCartBag();
	        }

	        @Then("cart price should be {int}")
	        public void cart_price_should_be(Integer expectedValue) {
	            int actualPrice = homePage.getItemPriceValue();
	            Assert.assertEquals(actualPrice, expectedValue.intValue(), "Cart price mismatch");
	      
	        }

	        @When("user clicks minus button in quantity box")
	        public void user_clicks_minus_button_in_quantity_box() throws InterruptedException {
	            Thread.sleep(1000);
	            homePage.clickMinusButtonAddToCart();
	        }

	        @Then("default quantity should remain {int}")
	        public void default_quantity_should_remain(Integer expectedValue) {
	            int actualQuantity = homePage.getItemQuantityInBox();
	            Assert.assertEquals(actualQuantity, expectedValue.intValue(), "Default quantity decreased incorrectly");
	           
	        }

	        @Then("product name should be {string}")
	        public void product_name_should_be(String expectedName) {
	            String actualName = homePage.getProductName(expectedName);
	            Assert.assertEquals(actualName, expectedName, "Product name mismatch");
	        
	        }
	    }


