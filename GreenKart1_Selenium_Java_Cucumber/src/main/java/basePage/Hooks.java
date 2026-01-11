package basePage;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.screenshortUtility;

public class Hooks {
	
	 @Before(order = 0)
	    public void setupExtent() {
	        if (BasePage.extent == null) {
	            String path = System.getProperty("user.dir") + "/reports/index.html";
	            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	            reporter.config().setReportName("Web Automation Results");
	            reporter.config().setDocumentTitle("Test Result");

	            BasePage.extent = new ExtentReports();
	            BasePage.extent.attachReporter(reporter);
	            BasePage.extent.setSystemInfo("Tester", "Akshay");
	        }
	    }

	    @Before(order = 1)
	    public void launchBrowser(Scenario scenario) throws IOException {
	        BasePage.initializeDriver();
	        BasePage.driver.manage().deleteAllCookies();
	        BasePage.driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	        BasePage.test = BasePage.extent.createTest(scenario.getName());
	    }

	    @After
	    public void tearDown(Scenario scenario) {
	        try {
	            if (scenario.isFailed()) {
	                String path = screenshortUtility.takeScreenshort(
	                        BasePage.driver, scenario.getName());
	                BasePage.test.fail("Scenario Failed");
	                BasePage.test.addScreenCaptureFromPath(path);
	            } else {
	                BasePage.test.pass("Scenario Passed");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @After(order = 0)
	    public void quitDriver() {
	        if (BasePage.extent != null) {
	            BasePage.extent.flush();
	        }
	        if (BasePage.driver != null) {
	            BasePage.driver.quit();
	            BasePage.driver = null;
	        }
	    }

}
