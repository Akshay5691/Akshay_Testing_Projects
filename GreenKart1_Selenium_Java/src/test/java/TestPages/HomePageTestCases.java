package TestPages;





import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;


import basePage.BasePage;
import pageObjects.CartPageMethods;
import pageObjects.HomePageMethods;;

public class HomePageTestCases extends BasePage {

	
            

    HomePageMethods homePage = new HomePageMethods(driver);
    CartPageMethods cartPage = new CartPageMethods(driver);

    @Test
    public void verifyUserIsAbleToIncreaseItemNumber(Method method) {
        try {
            homePage.searchItemAndAddToCart("orange");
            int actualItemNumber = homePage.getItemNumber();
            int expectedValue = 1;
            Assert.assertEquals(actualItemNumber, expectedValue, "Item not added to cart");
            System.out.println(method.getName() + " : passed");
        } catch (Exception e) {
            System.out.println(method.getName() + " : failed");
            e.printStackTrace();
        }
    }
  /*
    @Test
    public void cartGrid(ITestResult method) {
        try {
            WebElement cartGrid = driver.findElement(By.xpath("//table[@id='productCartTables']"));
            List<WebElement> cartTableRows = driver.findElements(By.xpath("//table[@id='productCartTables']//tbody//tr"));
            int countOfRows = cartTableRows.size();

            for (int i = 1; i < countOfRows; i++) {
                List<WebElement> cartTableRowColumns = cartTableRows.get(i).findElements(By.xpath("td"));
                String actualProductName = cartTableRowColumns.get(1).getText();
                if (actualProductName.contains("Onion")) {
                    String itemQty = cartTableRowColumns.get(2).getText();
                    Assert.assertEquals(Integer.parseInt(itemQty), 3,
                            "Added item quantity in search page and cart page doesn't match");
                    break;
                }
            }

            homePage.searchItemAndAddToCart("orange");
            int actualItemNumber = homePage.getItemNumber();
            int expectedValue = 1;
            Assert.assertEquals(actualItemNumber, expectedValue, "Item not added to cart");
            System.out.println(method.getName() + " : passed");

        } catch (Exception e) {
            System.out.println(method.getName() + " : failed");
            e.printStackTrace();
        }
    }*/

    @Test
    public void verifyItemNumberIsIncreasingWhileAddingMultipleItems(Method method) {
        try {
            homePage.searchItemAndAddToCart("apple");
            homePage.searchItemAndAddToCart("orange");

            int actualItemNumber = homePage.getItemNumber();
            int expectedValue = 2;
            Assert.assertEquals(actualItemNumber, expectedValue, "Multiple items are not added to cart");
            System.out.println(method.getName() + " : passed");
            
        } catch (Exception e) {
            System.out.println(method.getName() + " : failed");
            e.printStackTrace();
        }
    }

    @Test
    public void verifyItemNumberIsNotIncreasingWhileAddingSameItem(Method method) {
        try {
            homePage.searchItem("orange");
            Thread.sleep(3000);
            homePage.clickOnAddToCart();
            Thread.sleep(6000);
            homePage.clickOnAddToCart();

            int itemNumber = homePage.getItemNumber();
            int expectedValue = 1;
            Assert.assertEquals(itemNumber, expectedValue, "Item number increased after adding same item twice");
            System.out.println(method.getName() + " : passed");
            
        } catch (Exception e) {
            System.out.println(method.getName() + " : failed");
            e.printStackTrace();
        }
    }

    @Test
    public void verifyUserIsAbleToIncreaseCartPrice(Method method) {
        try {
            homePage.searchItemAndAddToCart("apple");
            homePage.clickOnCartBag();

            int itemPrice = homePage.getItemPrice();
            int expectedValue = 72;
            Assert.assertEquals(itemPrice, expectedValue, "Item price is not increased correctly");
            System.out.println(method.getName() + " : passed");
        } catch (Exception e) {
            System.out.println(method.getName() + " : failed");
            e.printStackTrace();
        }
    }

    @Test
    public void verifyUserIsNotAbleToDecreaseDefaultQuantityInQuantityBox(Method method) {
        try {
            homePage.searchItem("orange");
            Thread.sleep(3000);
            homePage.clickMinusButtonAddToCart();

            int defaultQuantity = homePage.getItemQuantityInQuantityBox();
            int expectedValue = 1;
            Assert.assertEquals(defaultQuantity, expectedValue, "Default quantity decreased incorrectly");
            System.out.println(method.getName() + " : passed");
        } catch (Exception e) {
            System.out.println(method.getName() + " : failed");
            e.printStackTrace();
        }
    }

    @Test
    public void verifyUserIsAbleToSearchItem(Method method) {
        try {
            homePage.searchItem("orange");
          
            String itemName = homePage.getItemOrange();
            String expectedName = "Orange";
            Assert.assertEquals(itemName, expectedName, "Item is not searched correctly");
            System.out.println(method.getName() + " : passed");
           } 
        catch (Exception e) {
            System.out.println(method.getName() + " : failed");
            e.printStackTrace();
        }
    }
	
	
	
    	
	}
	

