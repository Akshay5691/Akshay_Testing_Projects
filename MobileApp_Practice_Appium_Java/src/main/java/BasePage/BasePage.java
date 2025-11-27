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
   public static AppiumDriver driver;
   public static AppiumDriverLocalService service;
   
   
   @SuppressWarnings("deprecation")
@BeforeSuite(alwaysRun = true)
   public void setupSuite() {

       try {

           // Load Global Properties
           prop = new Properties();
           FileInputStream file = new FileInputStream(
                   System.getProperty("user.dir") + "\\src\\test\\resources\\GlobalTestProperties");
           prop.load(file);

           // Start Appium Server
           service = new AppiumServiceBuilder()
                   .withAppiumJS(new File("C:\\Users\\Akshay\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                   .withIPAddress("127.0.0.1")
                   .usingPort(4723)
                   .withTimeout(Duration.ofSeconds(60))
                   .build();

           service.start();
           System.out.println(" Appium Server Started");

           // Appium Capabilities
           UiAutomator2Options options = new UiAutomator2Options();
           options.setDeviceName("AkshayEmulator2");
           options.setApp(System.getProperty("user.dir") + "\\apps\\General-Store.apk");
           options.setAutomationName("UiAutomator2");
           options.setPlatformName("Android");
           options.setAppPackage("com.androidsample.generalstore");
           options.setAppActivity("com.androidsample.generalstore.SplashActivity");
           options.setNewCommandTimeout(Duration.ofSeconds(60));

           driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

           System.out.println(" Android App Launched Successfully");

           // Extent Report Setup
           String path = System.getProperty("user.dir") + "\\reports\\index.html";
           ExtentSparkReporter reporter = new ExtentSparkReporter(path);

           reporter.config().setReportName("Appium Automation Results");
           reporter.config().setDocumentTitle("Test Result");

           extent = new ExtentReports();
           extent.attachReporter(reporter);
           extent.setSystemInfo("Tester", "Akshay");

       } catch (Exception e) {
           e.printStackTrace();
           System.out.println(" Error in @BeforeSuite: " + e.getMessage());
       }
   }

   @BeforeMethod(alwaysRun = true)
   public void startTest(Method method) {
       test = extent.createTest(method.getName());
   }

   @AfterMethod(alwaysRun = true)
   public void captureResult(ITestResult result) throws IOException {

       if (result.getStatus() == ITestResult.FAILURE) {
           String path = screenshortUtility.takeScreenshort(result.getName());
           test.addScreenCaptureFromPath(path);
           test.fail(result.getThrowable());

       } else if (result.getStatus() == ITestResult.SUCCESS) {
           test.pass("Test Passed");

       } else {
           test.skip(result.getThrowable());
       }
   }

   @AfterSuite(alwaysRun = true)
   public void tearDownSuite() {

       try {
           if (driver != null) {
               driver.quit();
               System.out.println(" Android Driver Closed");
           }

           extent.flush();
           System.out.println(" Extent Report Generated");

       } catch (Exception e) {
           System.out.println(" Error in @AfterSuite: " + e.getMessage());
       }
   }
   



}