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
	WebDriverWait wait ;
	 Actions actions;
	 Select select ;
	
	public ActionsUtilitiy(WebDriver driver) {
		this.driver = driver;
		 actions = new Actions(driver);
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));		
		}
	                        // 🔹 Basic Actions
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

                           // 🔹 Wait Methods 
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

                         // 🔹 Mouse Actions
    public void hoverOverElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
    }

    public void rightClick(WebElement element) {
        actions.contextClick(element).perform();
    }

    public void dragAndDrop(WebElement source, WebElement target) {
        actions.dragAndDrop(source, target).perform();
    }

    // 🔹 Dropdown Handling
    public void selectByVisibleText(WebElement dropdown, String text) {
        select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    public void selectByIndex(WebElement dropdown, int index) {
        select = new Select(dropdown);
        select.selectByIndex(index);
    }

    public void selectByValue(WebElement dropdown, String value) {
        select = new Select(dropdown);
        select.selectByValue(value);
    }

                                      // 🔹 Scrolling
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollBy(int x, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

                                 // 🔹 JavaScript Actions
    public void clickUsingJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void typeUsingJS(WebElement element, String text) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + text + "';", element);
    }

                               // 🔹 Element State Checks
    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
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

    public boolean isElementSelected(WebElement element) {
        try {
            return element.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

	
}
	
	
	
	
	
	

