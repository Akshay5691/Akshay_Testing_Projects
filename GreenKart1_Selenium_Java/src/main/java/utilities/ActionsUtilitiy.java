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

	public WebElement element(By locator) {
		return driver.findElement(locator);
	}

	public void click(By locator) {
		driver.findElement(locator).click();
	}

	public void type(By locator, String text) {
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(text);
	}

	public void clear(By locator) {
		driver.findElement(locator).clear();
	}

	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}

	public String getAttribute(By locator, String attributeName) {
		return driver.findElement(locator).getAttribute(attributeName);
	}

	public int getIntValue(By locator) {
		return Integer.parseInt(driver.findElement(locator).getText());
	}

	public int getIntValueByAttribute(By locator, String attributeName) {
		return Integer.parseInt(driver.findElement(locator).getAttribute(attributeName));
	}

	// ---------------------------------------------------------
	// 🔹 WAIT METHODS
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
	// 🔹 MOUSE ACTIONS
	// ---------------------------------------------------------

	public void hoverOverElement(By locator) {
		actions.moveToElement(driver.findElement(locator)).perform();
	}

	public void doubleClick(By locator) {
		actions.doubleClick(driver.findElement(locator)).perform();
	}

	public void rightClick(By locator) {
		actions.contextClick(driver.findElement(locator)).perform();
	}

	public void dragAndDrop(By source, By target) {
		actions.dragAndDrop(driver.findElement(source), driver.findElement(target)).perform();
	}

	// ---------------------------------------------------------
	// 🔹 DROPDOWN HANDLING
	// ---------------------------------------------------------

	public void selectByVisibleText(By locator, String text) {
		select = new Select(driver.findElement(locator));
		select.selectByVisibleText(text);
	}

	public void selectByIndex(By locator, int index) {
		select = new Select(driver.findElement(locator));
		select.selectByIndex(index);
	}

	public void selectByValue(By locator, String value) {
		select = new Select(driver.findElement(locator));
		select.selectByValue(value);
	}

	// ---------------------------------------------------------
	// 🔹 SCROLLING
	// ---------------------------------------------------------

	public void scrollToElement(By locator) {
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollBy(int x, int y) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
	}

	// ---------------------------------------------------------
	// 🔹 JAVASCRIPT ACTIONS
	// ---------------------------------------------------------

	public void clickUsingJS(By locator) {
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void typeUsingJS(By locator, String text) {
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + text + "';", element);
	}

	// ---------------------------------------------------------
	// 🔹 ELEMENT STATE CHECKS
	// ---------------------------------------------------------

	public boolean isElementDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementEnabled(By locator) {
		try {
			return driver.findElement(locator).isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementSelected(By locator) {
		try {
			return driver.findElement(locator).isSelected();
		} catch (Exception e) {
			return false;
		}
	}
}
