package basePage;

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

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utilities.screenshortUtility;

public class BasePage {
	   public static WebDriver driver;
	    public static Properties prop;
	    public static ExtentReports extent;
	    public static ExtentTest test;

	    // Runs once before all scenarios
	    @Before(order = 0)
	    public void setUpExtentReport() {
	        if (extent == null) {
	            String path = System.getProperty("user.dir") + "/reports/index.html";
	            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	            reporter.config().setReportName("Web Automation Results");
	            reporter.config().setDocumentTitle("Test Result");

	            extent = new ExtentReports();
	            extent.attachReporter(reporter);
	            extent.setSystemInfo("Tester", "Akshay");
	        }
	    }

	    // Runs before each scenario
	    @Before
	    public void launchBrowser(Scenario scenario) {

	        try {
	            
	                prop = new Properties();
	                FileInputStream file = new FileInputStream(
	                        System.getProperty("user.dir") + "/src/test/resources/GlobalTestProperties");
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
	                    default:
	                        throw new IllegalArgumentException("Invalid browser name: " + actualDriver);
	                }
	                driver.manage().window().maximize();
	                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	            driver.manage().deleteAllCookies();
	            driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

	            test = extent.createTest(scenario.getName());

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // Runs after each scenario
	    @After
	    public void tearDown(Scenario scenario) {

	        try {
	            if (scenario.isFailed()) {
	                String path = screenshortUtility.takeScreenshort(driver, scenario.getName());
	                test.fail("Scenario Failed");
	                test.addScreenCaptureFromPath(path);
	            } else {
	                test.pass("Scenario Passed");   
	            }
	            if (driver != null) {
		            driver.quit();
		        }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // Runs once after all scenarios (flush report & quit driver)
	    @After(order = 0)
	    public void flushReportAndQuit() {
	        if (extent != null) {
	            extent.flush();
	        }
	       
	    }

	    public static WebDriver getDriver() {
	        return driver;
	    }
	
}