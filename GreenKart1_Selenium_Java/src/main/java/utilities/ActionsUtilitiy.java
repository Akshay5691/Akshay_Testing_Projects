package utilities;

import java.time.Duration;
import java.util.NoSuchElementException;

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
	
    public void waitUntilElementVisible(WebElement element) {
	
	wait.until(ExpectedConditions.visibilityOf(element));	
	}
    
    public void waitUntilElementClickable(WebElement element) {
    wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
     public void waitUntilElementInvisible(WebElement element) {
    wait.until(ExpectedConditions.invisibilityOf(element));
     }
     public void waitUntilElementPresent(org.openqa.selenium.By locator) {
         wait.until(ExpectedConditions.presenceOfElementLocated(locator));
     }
     public void waitUntilTextPresent(WebElement element, String text) {
         wait.until(ExpectedConditions.textToBePresentInElement(element, text));
     }
     public void hoverOverElement(WebElement element) {
    	 waitUntilElementVisible(element);
         actions.moveToElement(element).perform();
     }
     public void doubleClick(WebElement element) {
    	 waitUntilElementClickable(element);
         actions.doubleClick(element).perform();
     }
     public void rightClick(WebElement element) {
    	 waitUntilElementVisible(element);
         actions.contextClick(element).perform();
     }
     public void dragAndDrop(WebElement source, WebElement target) {
         waitUntilElementVisible(source);
         waitUntilElementVisible(target);
         actions.dragAndDrop(source, target).perform();
     }

   
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

     // 11️⃣ Scroll to element
     public void scrollToElement(WebElement element) {
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
     }

     // 12️⃣ Scroll by pixels
     public void scrollBy(int x, int y) {
         ((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
     }

     // 13️⃣ Click using JavaScript
     public void clickUsingJS(WebElement element) {
         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
     }

     // 14️⃣ Enter text using JavaScript
     public void typeUsingJS(WebElement element, String text) {
         ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + text + "';", element);
     }

     // 15️⃣ Is element displayed
     public boolean isElementDisplayed(WebElement element) {
         try {
             return element.isDisplayed();
         } catch (NoSuchElementException e) {
             return false;
         }
     }

     // 16️⃣ Is element enabled
     public boolean isElementEnabled(WebElement element) {
         try {
             return element.isEnabled();
         } catch (Exception e) {
             return false;
         }
     }

     // 17️⃣ Is element selected
     public boolean isElementSelected(WebElement element) {
         try {
             return element.isSelected();
         } catch (Exception e) {
             return false;
         }
     }

     // 18️⃣ Wait and click
     public void waitAndClick(WebElement element) {
         waitUntilElementClickable(element);
         element.click();
     }
	
	
}
	
	
	
	
	
	

