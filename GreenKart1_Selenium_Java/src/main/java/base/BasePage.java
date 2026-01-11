package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import utilities.screenshortUtility;

public class BasePage {
	public static ExtentTest test;
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports extent;

	
	@BeforeSuite
	public void setUpExtentReport() {
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Result");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Akshay");
		

	}

	@BeforeMethod(alwaysRun = true)
	public void launchBrowser(Method method) {
		
		
		try {
			prop = new Properties();
			FileInputStream file = new FileInputStream(

					System.getProperty("user.dir") + "\\src\\test\\resources\\GlobalTestProperties");

			prop.load(file);
			
			   String actualDriver = prop.getProperty("browser");
			    String executionMode = prop.getProperty("execution"); 

			    if (executionMode.equalsIgnoreCase("grid")) {

			        switch (actualDriver.toLowerCase()) {

			        case "chrome":
			            ChromeOptions chromeOptions = new ChromeOptions();
			            driver = new RemoteWebDriver(
			                    new URL("http://localhost:4444/wd/hub"), chromeOptions);
			            break;

			        case "firefox":
			            FirefoxOptions firefoxOptions = new FirefoxOptions();
			            driver = new RemoteWebDriver(
			                    new URL("http://localhost:4444/wd/hub"), firefoxOptions);
			            break;

			        case "edge":
			            EdgeOptions edgeOptions = new EdgeOptions();
			            driver = new RemoteWebDriver(
			                    new URL("http://localhost:4444/wd/hub"), edgeOptions);
			            break;

			        default:
			            throw new IllegalArgumentException("Invalid browser name: " + actualDriver);
			        }

			    } else {

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
			    }

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			test = extent.createTest(method.getName());		
					

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		
		

	@AfterMethod(alwaysRun = true)
	public void closeBrowser(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String path = screenshortUtility.takeScreenshort(result.getName());
			test.addScreenCaptureFromPath(path);
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test Passed");
		} else {
			test.skip(result.getThrowable());
		}
		driver.quit();
     
	}

	@AfterSuite
	public void flushReport() {
	
		extent.flush();
		
	}

}
