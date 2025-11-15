package utilities;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionsUtilitiy {
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;
	Select select;

	public ActionsUtilitiy(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
//  BASIC ACTIONS WITH WAITS
// ---------------------------------------------------------

	public WebElement getElement(By locator) {
		waitUntilElementPresent(locator);
		return driver.findElement(locator);
	}

	public void click(By locator) {
		try {
			waitUntilElementClickable(locator);
			driver.findElement(locator).click();
		} catch (Exception e) {
			System.out.println("Element not found to click: " +e.getMessage()+ locator);
		}
	}

	public void type(By locator, String text) {
		try {
			waitUntilElementVisible(locator);
			WebElement element = driver.findElement(locator);
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			System.out.println("Element not found to type: " +e.getMessage() + locator);
		}
	}

	public void clear(By locator) {
		try {
		waitUntilElementVisible(locator);
		driver.findElement(locator).clear();
	  }	catch (Exception e) {
			System.out.println("Element not found to clear: " +e.getMessage()+ locator);
		}
	}

	public String getText(By locator) {
	    try {
	        waitUntilElementVisible(locator);
	        return driver.findElement(locator).getText();
	    } catch (Exception e) {
	        throw new RuntimeException("❌ Failed to get text from element: " + locator, e);
	    }
	}

	public String getAttribute(By locator, String attributeName) {
		waitUntilElementPresent(locator);
		return driver.findElement(locator).getAttribute(attributeName);
	}

	public int getIntValue(By locator) {
		waitUntilElementVisible(locator);
		return Integer.parseInt(driver.findElement(locator).getText());
	}

	public int getIntValueByAttribute(By locator, String attributeName) {
		waitUntilElementPresent(locator);
		return Integer.parseInt(driver.findElement(locator).getAttribute(attributeName));
	}

// ---------------------------------------------------------
//  WAIT METHODS (already correct)
// ---------------------------------------------------------

	public void waitUntilElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitUntilElementClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitUntilElementInvisible(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitUntilElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitUntilTextPresent(By locator, String text) {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}

// ---------------------------------------------------------
//  MOUSE ACTIONS WITH WAITS
// ---------------------------------------------------------

	public void hoverOverElement(By locator) {
		waitUntilElementVisible(locator);
		actions.moveToElement(driver.findElement(locator)).perform();
	}

	public void doubleClick(By locator) {
		waitUntilElementClickable(locator);
		actions.doubleClick(driver.findElement(locator)).perform();
	}

	public void rightClick(By locator) {
		waitUntilElementClickable(locator);
		actions.contextClick(driver.findElement(locator)).perform();
	}

	public void dragAndDrop(By source, By target) {
		waitUntilElementVisible(source);
		waitUntilElementVisible(target);
		actions.dragAndDrop(driver.findElement(source), driver.findElement(target)).perform();
	}

// ---------------------------------------------------------
//  DROPDOWN HANDLING WITH WAITS
// ---------------------------------------------------------

	public void selectByVisibleText(By locator, String text) {
		waitUntilElementVisible(locator);
		select = new Select(driver.findElement(locator));
		select.selectByVisibleText(text);
	}

	public void selectByIndex(By locator, int index) {
		waitUntilElementVisible(locator);
		select = new Select(driver.findElement(locator));
		select.selectByIndex(index);
	}

	public void selectByValue(By locator, String value) {
		waitUntilElementVisible(locator);
		select = new Select(driver.findElement(locator));
		select.selectByValue(value);
	}

// ---------------------------------------------------------
//  SCROLLING WITH WAITS
// ---------------------------------------------------------

	public void scrollToElement(By locator) {
		waitUntilElementVisible(locator);
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollBy(int x, int y) throws InterruptedException {
		// Scroll does not require wait, but adding slight wait for stability
		Thread.sleep(500); // optional
		((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
	}

// ---------------------------------------------------------
//  JAVASCRIPT ACTIONS WITH WAITS
// ---------------------------------------------------------

	public void clickUsingJS(By locator) {
		waitUntilElementClickable(locator);
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void typeUsingJS(By locator, String text) {
		waitUntilElementVisible(locator);
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + text + "';", element);
	}

// ---------------------------------------------------------
//  ELEMENT STATE CHECKS WITH WAITS
// ---------------------------------------------------------

	public boolean isElementDisplayed(By locator) {
		try {
			waitUntilElementVisible(locator);
			return driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementEnabled(By locator) {
		try {
			waitUntilElementVisible(locator);
			return driver.findElement(locator).isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementSelected(By locator) {
		try {
			waitUntilElementVisible(locator);
			return driver.findElement(locator).isSelected();
		} catch (Exception e) {
			return false;
		}
	}
}
