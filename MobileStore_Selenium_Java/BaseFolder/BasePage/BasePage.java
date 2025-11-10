package BasePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import utilityClasses.screenshortUtility;

public class BasePage {
   public static ExtentTest test ;  
   public static WebDriver driver;
   public static Properties prop;
   public static ExtentReports extent;
   
   
	public BasePage() {
   try {        
	   
	    prop =new Properties();
	   FileInputStream file =new  FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\GlobalTestProperties" );
	   prop.load(file);   
	   String actualDriver = prop.getProperty("browser");		
			switch (actualDriver.toLowerCase()) { 
         case "chrome":      
             driver = new ChromeDriver();
             break;
         case "edge":      
             driver = new EdgeDriver();
             break;
         case "firefox":           
             driver = new FirefoxDriver();
             break;
         case "safari":
             driver = new SafariDriver();  
             break;
         default:
             throw new IllegalArgumentException("Invalid browser name: " + actualDriver);
     }
		}
		 catch (Exception e) {
	            System.out.println("Error launching the browser: " + e.getMessage());
	            e.printStackTrace();       // This will print the exact error details in the console
        }		
	}
   
    
    @BeforeSuite
    public void setUpExtentReport() {	 
    	 driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
         
       String path = System.getProperty("user.dir") + "\\reports\\index.html";	
  	   ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Result");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Akshay", "Tester");     
     }
    
    @AfterSuite
    public void CloseTheBrowser(){
  		// driver.quit();	
    	 extent.flush();
     }
    
    @BeforeMethod(alwaysRun =true)
    public void createTestForExtentReport(Method method) {  	
         driver.manage().deleteAllCookies();
    	 driver.get("https://tutorialsninja.com/demo/");		
        test = extent.createTest(method.getName());
    }
    
    @AfterMethod(alwaysRun =true)
    public void logResult(ITestResult result) throws IOException {	
        if (result.getStatus() == ITestResult.FAILURE) {
        	String path= screenshortUtility.takeScreenshort(result.getName());
        	test.addScreenCaptureFromPath(path, result.getName());
            test.fail(result.getThrowable());        
        } else if (result.getStatus() == ITestResult.SUCCESS) {     	
            test.pass("Test passed");
        } else {
            test.skip(result.getThrowable());
        }
        //driver.close();
    }
	
	
  

  
   



}
