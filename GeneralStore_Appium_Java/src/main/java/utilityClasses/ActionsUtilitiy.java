package utilityClasses;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ActionsUtilitiy {
	AndroidDriver driver;
	WebDriverWait wait;
	Actions actions;
	Select select;

	public ActionsUtilitiy(AndroidDriver driver) {
		this.driver = driver;

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	// Basic Actions

//  Basic Actions	

	public void click(By locator) {
		driver.findElement(locator).click();
	}

	public void type(By locator, String text) {
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(text);
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

// --------------------------------------------------------
// Gesture Executor
// --------------------------------------------------------
	private void executeGesture(String command, Map<String, Object> params) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(command, params);
	}

// --------------------------------------------------------
// Gestures using BY locator
// --------------------------------------------------------

	public void tapElement(By locator) {
		WebElement element = driver.findElement(locator);
		int x = element.getLocation().getX();
		int y = element.getLocation().getY();

		Map<String, Object> params = new HashMap<>();
		params.put("x", x);
		params.put("y", y);

		executeGesture("mobile: tap", params);
	}

	public void longPressElement(By locator, int durationMs) {
		WebElement element = driver.findElement(locator);
		int x = element.getLocation().getX();
		int y = element.getLocation().getY();

		Map<String, Object> params = new HashMap<>();
		params.put("x", x);
		params.put("y", y);
		params.put("duration", durationMs);

		executeGesture("mobile: longClickGesture", params);
	}

	public void swipe(int startX, int startY, int endX, int endY, int speed) {
		Map<String, Object> params = new HashMap<>();
		params.put("startX", startX);
		params.put("startY", startY);
		params.put("endX", endX);
		params.put("endY", endY);
		params.put("speed", speed);

		executeGesture("mobile: swipeGesture", params);
	}

	public void scrollElement(By locator, String direction, double percent) {
		WebElement element = driver.findElement(locator);

		Map<String, Object> params = new HashMap<>();
		params.put("elementId", ((RemoteWebElement) element).getId());
		params.put("direction", direction);
		params.put("percent", percent);

		executeGesture("mobile: scrollGesture", params);
	}

	public void scrollDown(double percent) {
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "down");
		params.put("percent", percent);

		executeGesture("mobile: scrollGesture", params);
	}

	public void ScrollToProduct(String Product) {
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + Product + "\"));"));
	}

	public void scrollUp(double percent) {
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "up");
		params.put("percent", percent);

		executeGesture("mobile: scrollGesture", params);
	}

	public void dragAndDrop(By sourceLocator, By targetLocator) {
		WebElement source = driver.findElement(sourceLocator);
		WebElement target = driver.findElement(targetLocator);

		Map<String, Object> params = new HashMap<>();
		params.put("sourceId", ((RemoteWebElement) source).getId());
		params.put("destinationId", ((RemoteWebElement) target).getId());

		executeGesture("mobile: dragGesture", params);
	}

	public void dragAndDropByCoordinates(By locator, int endX, int endY) {
		WebElement source = driver.findElement(locator);
		Rectangle rect = source.getRect();

		Map<String, Object> params = new HashMap<>();
		params.put("startX", rect.getX() + rect.getWidth() / 2);
		params.put("startY", rect.getY() + rect.getHeight() / 2);
		params.put("endX", endX);
		params.put("endY", endY);

		executeGesture("mobile: dragGesture", params);
	}

	public void safeClick(By locator) {
		try {
			click(locator);
		} catch (Exception e) {
			tapElement(locator);
		}
	}

// --------------------------------------------------------
//  Wait Methods 
// --------------------------------------------------------

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

// --------------------------------------------------------
// Verification Methods
// --------------------------------------------------------

	public boolean isElementDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
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

	public boolean isElementEnabled(By locator) {
		try {
			return driver.findElement(locator).isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

// --------------------------------------------------------
// Hide keyboard
// --------------------------------------------------------

	public void hideKeyboard() {
		try {
			driver.hideKeyboard();
		} catch (Exception ignored) {
		}
	}

}
