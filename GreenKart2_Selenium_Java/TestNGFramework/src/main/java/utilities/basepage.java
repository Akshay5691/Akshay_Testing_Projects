package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class basepage {

	public WebDriver driver;
	// public WebDriverWait wait;
	Properties prop = new Properties();
	String browserName = null;
	String url;
	String propertyFileName = System.getProperty("user.dir") + "\\src\\main\\java\\Utilities\\data.properties";
	FileInputStream file;

	public WebDriver initializeDriver() {

		try {
			file = new FileInputStream(propertyFileName);
			try {
				prop.load(file);
				 browserName = prop.getProperty("browser"); //To Read a browser name from property file
//				browserName = System.getProperty("browser"); // To Read a browser name from Variable stored in jenkins
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (browserName.contains("chrome")) {
				ChromeOptions options = new ChromeOptions();
				if (browserName.contains("headless"))
					options.addArguments("headless");
				
				System.out.println("Chrome driver is Opening");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
				driver = new ChromeDriver(options);

			} else if (browserName.equalsIgnoreCase("Firefox")) {
				System.out.println("Firefox driver is Opening");
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\GeckoDriver.exe");
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				driver = new InternetExplorerDriver();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		maximizeBrowser();
		openURL();
		return driver;
	}

	public boolean verifyTitle(String Title) {
		boolean status;

		if (driver.getTitle().equals(Title)) {
			System.out.println("Title Matches");
			status = true;
		} else {
			System.out.println("Title Present:-" + driver.getTitle());
			status = false;
		}
		return status;
	}

	public void openURL() {
		try {
			file = new FileInputStream(propertyFileName);
			try {
				prop.load(file);
				url = prop.getProperty("url");
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		driver.get(url);

	}

	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}

	public boolean verifyVisibilityOfElement(WebElement Locator) {
		return Locator.isDisplayed();
	}

	public boolean checkElementIsClickable(WebElement locator) {
		return locator.isEnabled();
	}

	public void quit() {
		driver.quit();
	}

	public void close() {
		driver.close();
	}

	public void enterData(WebElement locator, String value) {
		locator.sendKeys(value);
	}

	public String getTextValue(WebElement loactor) {
		return loactor.getText();
	}

	public void cleartextboxvalue(WebElement locator) {
		locator.clear();
	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitunilElementisPresent(WebElement loactor, long time, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(loactor));
	}

	public void clickElement(WebElement locator) {
		locator.click();
	}

	public String getAttributeValue(WebElement locator, String attribute) {
		return locator.getAttribute(attribute);
	}

	public void switch_to_new_window() {

		String parent = driver.getWindowHandle();
		Set<String> allwindows = driver.getWindowHandles();
		System.out.println(allwindows.size());

		for (String window : allwindows) {
			if (!window.equals(parent))
				driver.switchTo().window(window);
		}
	}

	public void switch_to_parent_window(String parent) {
		driver.switchTo().window(parent);
	}

	public String get_parent_window_handle() {
		return driver.getWindowHandle();
	}

	public void reloadbrowser(WebDriver driver) {
		driver.navigate().refresh();
	}

	public String getalerttext(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void acceptalertpopup(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public String getScreenShotPath(String testcasename, String destinationfile, WebDriver driver) {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);

		destinationfile = destinationfile + "\\" + testcasename + ".png";
		try {
			FileUtils.copyFile(source, new File(destinationfile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationfile;
	}
	

}