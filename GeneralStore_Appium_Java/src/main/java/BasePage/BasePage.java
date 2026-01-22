package BasePage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
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
   
   
	
    
   @BeforeSuite
   public void setUpExtentReport() {

       try {
           service = new AppiumServiceBuilder()
                   .withAppiumJS(new File(System.getProperty("user.home")
                           + "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                   .withIPAddress("127.0.0.1")
                   .usingPort(4723)
                   .withTimeout(Duration.ofSeconds(60))
                   .build();
           service.start();
       } catch (Exception e) {
           System.out.println("Error launching Appium server: " + e.getMessage());
       }

       String path = System.getProperty("user.dir") + "\\reports\\index.html";
       ExtentSparkReporter reporter = new ExtentSparkReporter(path);
       reporter.config().setReportName("Mobile Automation Results");
       reporter.config().setDocumentTitle("Test Results");

       extent = new ExtentReports();
       extent.attachReporter(reporter);
       extent.setSystemInfo("Tester", "Akshay");
   }

   @BeforeMethod(alwaysRun = true)
   public void createTestForExtentReport(Method method) {

       test = extent.createTest(method.getName());

       try {
           UiAutomator2Options options = new UiAutomator2Options();
           options.setDeviceName("AkshayEmulator2");
           options.setApp(System.getProperty("user.dir") + "\\apps\\General-Store.apk");
           options.setAutomationName("UiAutomator2");
           options.setPlatformName("Android");
           options.setAppPackage("com.androidsample.generalstore");
           options.setAppActivity("com.androidsample.generalstore.SplashActivity");
           options.setNewCommandTimeout(Duration.ofSeconds(60));

           driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
           System.out.println("App launched successfully!");
        

       }  catch (Exception e) {
           test.fail("Failed to launch the app: " + e.getMessage());
           throw new RuntimeException(e);
       }
   }

   @AfterMethod(alwaysRun = true)
   public void logResult(ITestResult result) throws IOException {

       if (result.getStatus() == ITestResult.FAILURE) {
           String path = screenshortUtility.takeScreenshort(result.getName());
           test.addScreenCaptureFromPath(path);
           test.fail(result.getThrowable());
       } else if (result.getStatus() == ITestResult.SUCCESS) {
           test.pass("Test Passed");
       } else {
           test.skip(result.getThrowable());
       }

       if (driver != null) {
           driver.quit();
       }
   }

   @AfterSuite
   public void tearDown() {
       extent.flush();
       if (service != null) {
           service.stop();
       }
   }
    


}