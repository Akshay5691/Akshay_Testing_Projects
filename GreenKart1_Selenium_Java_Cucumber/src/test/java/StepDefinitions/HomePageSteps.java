package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class HomePageSteps {

    @Given("I navigate to the home page")
    public void iNavigateToTheHomePage() {
      
        System.out.println("Navigating to the home page");
    }

    @When("I check the title of the page")
    public void iCheckTheTitleOfThePage() {
        
        System.out.println("Checking the title of the page");
    }

    @Then("the title should be Home - GreenKart")
    public void theTitleShouldBeHomeGreenKart() {
       
        System.out.println("Verifying the title is 'Home - GreenKart'");
    }
}