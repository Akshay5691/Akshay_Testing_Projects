package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	
	
	
	 private WebDriver driver;

	    private HomePage homePage;
	    private CartPage cartPage;
	    private  PlaceOrderPage placeOrderPage;
	    private ProceedPage procedPage;
	    

	    public PageObjectManager(WebDriver driver) {
	        this.driver = driver;
	    }

	    public HomePage getHomePage() {
	        if (homePage == null) {
	            homePage = new HomePage(driver);
	        }
	        return homePage;
	    }

	    public CartPage getCartPage() {
	        if (cartPage == null) {
	            cartPage = new CartPage(driver);
	        }
	        return cartPage;
	    }	
	
	    public PlaceOrderPage getPlaceOrderPage() {
	        if (placeOrderPage == null) {
	        	placeOrderPage = new PlaceOrderPage(driver);
	        }
	        return placeOrderPage;
	    }
	    public ProceedPage getProceedPage() {
	        if (procedPage == null) {
	        	procedPage = new ProceedPage(driver);
	        }
	        return procedPage;
	    }
	

}
