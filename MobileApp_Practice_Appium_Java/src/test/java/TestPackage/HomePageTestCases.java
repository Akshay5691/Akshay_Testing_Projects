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
	
	@Test
	public void verifyUserIsAbleToSearchItemWithValidData(Method method) throws IOException 
	{	
		
		
		 
  	  List <String> list =new ArrayList <String> ();
  	  
  	  list.add("Hyderabad");
  	  list.add("Bangalore");
  	  list.add("Chennai");
  	  list.add("Pune");
  	   System.out.println(list);
  	  
  	   
  	   
  	   
  	   
  	   
  	   
  	   
		
		
	}
	
	
	
	
		
	
	
	
	
	
	
}
