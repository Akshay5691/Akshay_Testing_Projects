package TestPackage;





import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;


import BasePage.BasePage;
import PageObjects.HomePageObjects;;

public class HomePageTestCases extends BasePage {

	
            

	HomePageObjects ObjHome =new HomePageObjects(driver);
	
	@Test(priority=1, groups={"sanity"})
	public void verifyUserIsAbleToSearchItemWithValidData(Method method) throws IOException 
	{	
		try {	
		ObjHome.clickOnSearchBox();	
		ObjHome.enterTextInSearchBox("Iphone");
		ObjHome.clickOnSearchButton();
		String actualProduct =ObjHome.getProductText();
		String expectedProduct= "iPhone";
		Assert.assertEquals(expectedProduct,actualProduct );	
	  
		}
		
		catch(AssertionError ae) {		
	       System.out.println(method.getName() + " : failed"+ ae.getMessage());
	       throw ae;
		}
		catch(Exception e){ 	
	    System.out.println(method.getName() + " : failed"+ e.getMessage());
		}
		
		   System.out.println(method.getName() + " : passed");
	}
	
	
	
	@Test(priority=1, groups={"sanity"},dataProvider = "excelData", dataProviderClass = dataProvider.DataProviders.class)
	public void verifyUserIsAbleToSearchItemsWithValidData(Method method, String expectedMobile) throws IOException 
	{	
		try {	
		ObjHome.clickOnSearchBox();	
		ObjHome.enterTextInSearchBox(expectedMobile);
		ObjHome.clickOnSearchButton();
		
		String actualProduct =ObjHome.getProductText();
		String expectedProduct= expectedMobile;
		Assert.assertEquals(expectedProduct,actualProduct );	
	  
		}
		
		catch(AssertionError ae) {		
	       System.out.println(method.getName() + " : failed"+ ae.getMessage());
	       throw ae;
		}
		catch(Exception e){ 	
	    System.out.println(method.getName() + " : failed"+ e.getMessage());
		}
		
		   System.out.println( expectedMobile + " : passed");
	}
		
	
	
	
	
	
	@Test(priority=1,groups={"sanity"})
	public void verifyUserIsAbleToSearchSpecificProductsWithIphoneBrandWithDescription(Method method) {
		
	try {	
		ObjHome.clickOnSearchBox();
		ObjHome.enterTextInSearchBox("Iphone");
		ObjHome.clickOnSearchButton();
		ObjHome.selectTheCheckBoxToSearchProductInDescription();
		ObjHome.clickSearchButtonToCheckProductInDescription();
		List<String> expectedProducts = new ArrayList<String>(Arrays.asList("iphone", "iPod Nano"));
		expectedProducts.add("iPod Touch"); 
		 List<String> allSearchedProducts = ObjHome.allSearchedProductsWithDescription();
		if(expectedProducts.size()== allSearchedProducts.size()) {	
		for(int i=0; i < allSearchedProducts.size() ;i++) {
			
			 String expected = expectedProducts.get(i);
		       String actual = allSearchedProducts.get(i);
		        if (expected.equalsIgnoreCase(actual)) {
		            System.out.println("Matched: " + expected);
		        } else {
		            System.out.println("Mismatch! Expected: " + expected + ", but found: " + actual);
		        }
		      }	    		
	    	}	
		 }
		catch(AssertionError ae) {
		        System.out.println(method.getName() + " : failed");
		        throw ae;
		}
		catch(Exception e){ 
      System.out.println(method.getName() + " : failed");
		
		  }
		  System.out.println(method.getName() + " : passed");
    	}
	 
	
	
	

	
	
	
	
    	
	}
	

