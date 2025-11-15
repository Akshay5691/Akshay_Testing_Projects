package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import utilityClasses.ActionsUtilitiy;

public class CartPageObjects extends ActionsUtilitiy {

	AndroidDriver driver;
	private static CartPageObjects instance;

	public CartPageObjects(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		
	}
	
	public static CartPageObjects getCartPageObject(AndroidDriver driver) {
	
		  if (instance == null) {
		        instance = new CartPageObjects(driver);
		    }
		    return instance;
	}
	
	 // =================== 🔹 Locators ===================
	 

	 
	 
	 
     	// =================== 🔹 WebElements ===================
	 

	
	
	
	    // =================== 🔹 Actions ===================
	
	
	
	
	
	
	

}
