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
	WebDriverWait wait ;
	 Actions actions;
	 Select select ;
	
	public ActionsUtilitiy(AndroidDriver driver) {
		this.driver = driver;
			
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));		
		}
	                        //  Basic Actions
	
    public void click(WebElement element) {
        element.click();
    }
    
    public void type(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public String getAttribute(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    public int getIntValue(WebElement element) {
        return Integer.parseInt(element.getText());
    }

    public int getIntValueByAttribute(WebElement element, String attributeName) {
        return Integer.parseInt(element.getAttribute(attributeName));
    }
    
    private void executeGesture(String command, Map<String, Object> params) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(command, params);
    }
    public void tapElement(WebElement element) {	
       int x= element.getLocation().getX() ;
       int y=  element.getLocation().getY();     
       Map<String, Object> params = new HashMap<>();
       params.put("x", x);
       params.put("y", y);
       executeGesture("mobile: tap", params);
    }
    public void longPressElement(WebElement element, int durationMs) {
        int x = element.getLocation().getX(); 
        int y =  element.getLocation().getY();
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
    public void scrollElement(WebElement element, String direction, double percent) {
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
        driver.findElement(AppiumBy.androidUIAutomator(
          "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + Product + "\"));"));
    }
    public void scrollUp(double percent) {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "up");
        params.put("percent", percent);
        executeGesture("mobile: scrollGesture", params);
    }
    public void dragAndDrop(WebElement source, WebElement target) {
        Map<String, Object> params = new HashMap<>();
        params.put("sourceId", ((RemoteWebElement) source).getId());
        params.put("destinationId", ((RemoteWebElement) target).getId());
        executeGesture("mobile: dragGesture", params);
    }
    public void dragAndDropByCoordinates(WebElement source, int endX, int endY) {
        Rectangle rect = source.getRect();
        Map<String, Object> params = new HashMap<>();
        params.put("startX", rect.getX() + rect.getWidth() / 2);
        params.put("startY", rect.getY() + rect.getHeight() / 2);
        params.put("endX", endX);
        params.put("endY", endY);
        executeGesture("mobile: dragGesture", params);
    }
    
    public void safeClick(WebElement element) {
        try {
            click(element);
        } catch (Exception e) {
            tapElement(element);
        }
    }
                      //  Wait Methods 
    
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
                     //   Verification Methods
    
    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isElementSelected(WebElement element) {
        try {
            return element.isSelected();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    

    // Hide keyboard for Android/iOS
    public void hideKeyboard() {
        try {
            driver.hideKeyboard();
        } catch (Exception ignored) {}
    }
    
    

	
}
	
	
	
	
	
	

