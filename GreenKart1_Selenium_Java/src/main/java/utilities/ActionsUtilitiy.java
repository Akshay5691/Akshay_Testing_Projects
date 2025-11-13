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

	   public void click(By locator) {
	        waitUntilElementClickable(locator);
	        driver.findElement(locator).click();
	    }

	    public void type(By locator, String text) {
	        waitUntilElementVisible(locator);
	        WebElement element = driver.findElement(locator);
	        element.clear();
	        element.sendKeys(text);
	    }

	    public String getText(By locator) {
	        waitUntilElementVisible(locator);
	        return driver.findElement(locator).getText();
	    }
	    
	    @SuppressWarnings("deprecation")
		public String getAttribute(By locator, String attributeName) {
	        waitUntilElementVisible(locator);
	        return driver.findElement(locator).getAttribute(attributeName);
	    }
	    
	    public int getIntValue(By locator) {
	        waitUntilElementVisible(locator);
	        return Integer.parseInt(driver.findElement(locator).getText());
	    }
	    @SuppressWarnings("deprecation")
		public int getIntValueByAttribute(By locator,String attributeName) {
	        waitUntilElementVisible(locator);
	        return Integer.parseInt(driver.findElement(locator).getAttribute(attributeName));
	    }
		
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
     public void hoverOverElement(WebElement element, By loactor) {
    	 waitUntilElementVisible(loactor);
         actions.moveToElement(element).perform();
     }
     public void doubleClick(WebElement element, By loactor) {
    	 waitUntilElementClickable(loactor);
         actions.doubleClick(element).perform();
     }
     public void rightClick(WebElement element, By loactor) {
    	 waitUntilElementVisible(loactor);
         actions.contextClick(element).perform();
     }
     public void dragAndDrop(WebElement source, WebElement target, By sourceLocator, By targetLocator) {
         waitUntilElementVisible(sourceLocator);
         waitUntilElementVisible(targetLocator);
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

     public void scrollBy(int x, int y) {
         ((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
     }

     public void clickUsingJS(WebElement element) {
         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
     }

     public void typeUsingJS(WebElement element, String text) {
         ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + text + "';", element);
     }

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
	
	
	
	
	
	

