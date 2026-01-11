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

	    public static WebDriver initializeDriver() throws IOException {

	        if (driver == null) {
	            prop = new Properties();
	            FileInputStream file = new FileInputStream(
	                    System.getProperty("user.dir") + "/src/test/resources/GlobalTestProperties");
	            prop.load(file);

	            String browser = prop.getProperty("browser");

	            switch (browser.toLowerCase()) {
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
	                    throw new IllegalArgumentException("Invalid browser");
	            }

	            driver.manage().window().maximize();
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	        }
	        return driver;
	    }

	
}