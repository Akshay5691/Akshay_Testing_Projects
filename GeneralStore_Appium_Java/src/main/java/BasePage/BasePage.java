package BasePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import utilityClasses.screenshortUtility;

public class BasePage {
   public static ExtentTest test ;  
   
   public static Properties prop;
   public static ExtentReports extent;
   public static AndroidDriver driver;
   public static AppiumDriverLocalService service;
   
   
	public BasePage() {
   try {        
	   
	    prop =new Properties();
	   FileInputStream file =new  FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\GlobalTestProperties" );
	   prop.load(file);   
	  // String actualDriver = prop.getProperty("browser");		

   
       service = new AppiumServiceBuilder()
               .withAppiumJS(new File("C:\\Users\\Akshay\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
               .withIPAddress("127.0.0.1")
               .usingPort(4723)
               .withTimeout(Duration.ofSeconds(60))
               .build();

       service.start();

   
       UiAutomator2Options options = new UiAutomator2Options();
       options.setDeviceName("AkshayEmulator2");  
       options.setApp(System.getProperty("user.dir") + "\\apps\\General-Store.apk");
       options.setAutomationName("UiAutomator2");
       options.setPlatformName("Android");
       options.setAppPackage("com.androidsample.generalstore");
       options.setAppActivity("com.androidsample.generalstore.SplashActivity");
       options.setNewCommandTimeout(Duration.ofSeconds(60));
       
       
       driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
       System.out.println("✅ App launched successfully using UiAutomator2!");
   }
		 catch (Exception e) {
	            System.out.println("Error launching the browser: " + e.getMessage());
	            e.printStackTrace();      
		}
	}
   
    
    @BeforeSuite
    public void setUpExtentReport() {	 
    
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