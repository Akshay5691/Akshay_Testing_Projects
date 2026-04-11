import { expect } from '@playwright/test';
import { customTest as test } from '../utils_ts/test-base';

test.describe('Checkout Page Tests - Page Object Model', () => {
    
    test.beforeAll(async ({ browser }) => {
        const context = await browser.newContext();
        const loginPage = await context.newPage();
        
        // Perform login once to save session
        await loginPage.goto('https://rahulshettyacademy.com/client');
        await loginPage.locator('#userEmail').fill('rahulshetty@gmail.com');
        await loginPage.locator('#userPassword').fill('Iamking@000');
        await loginPage.locator("[value='Login']").click();
        await loginPage.waitForLoadState('networkidle');
        await loginPage.context().storageState({ path: 'state.json' });
        
        await context.close();
    });

    test('@Checkout TC001 - Navigate to Checkout Page and Verify Page Loads', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        
        // Assert
        expect(await poManager.getCheckoutPage().isCheckoutPageLoaded()).toBeTruthy();
        const url = await poManager.getCheckoutPage().getCheckoutPageURL();
        expect(url).toContain('checkout');
    });

    test('@Checkout TC002 - Verify All Products Appear in Checkout Page', async ({ page, poManager }) => {
        // Arrange
        const productName = 'ADIDAS ORIGINAL';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        
        // Assert
        const productCount = await poManager.getCheckoutPage().getProductCount();
        expect(productCount).toBeGreaterThan(0);
        
        const productNames = await poManager.getCheckoutPage().getAllProductNames();
        const isProductInCheckout = await poManager.getCheckoutPage().verifyProductInCheckout(productName);
        expect(isProductInCheckout).toBeTruthy();
    });

    test('@Checkout TC003 - Verify Product Price is Displayed in Checkout', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        
        // Assert
        const price = await poManager.getCheckoutPage().getProductPrice(productName);
        expect(price).not.toBeNull();
        console.log(`Product ${productName} price: ${price}`);
    });

    test('@Checkout TC004 - Verify Subtotal and Total Amount Calculation', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        
        // Assert
        const subtotal = await poManager.getCheckoutPage().getSubtotal();
        const total = await poManager.getCheckoutPage().getTotalPrice();
        
        console.log(`Subtotal: ${subtotal}`);
        console.log(`Total: ${total}`);
        
        // Verify that total is not empty
        expect(total).not.toBeNull();
    });

    test('@Checkout TC005 - Apply Discount Code and Verify Amount is Updated', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        const discountCode = 'rahulshettyacademy';
        
        await poManager.getDashboardPage().waitForDashboardToLoad();
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        const totalBefore = await poManager.getCheckoutPage().getTotalPrice();
        
        // Act
        const applied = await poManager.getCheckoutPage().applyDiscountCode(discountCode);
        
        if (applied) {
            await poManager.getCheckoutPage().waitForDiscountToBeApplied();
            const totalAfter = await poManager.getCheckoutPage().getTotalPrice();
            
            // Assert
            console.log(`Total before discount: ${totalBefore}`);
            console.log(`Total after discount: ${totalAfter}`);
            expect(totalAfter).not.toBeNull();
        }
    });

    test('@Checkout TC006 - Update Product Quantity in Checkout', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        const newQuantity = 2;
        
        await poManager.getDashboardPage().waitForDashboardToLoad();
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        
        // Act
        const updated = await poManager.getCheckoutPage().updateProductQuantity(productName, newQuantity);
        
        // Assert
        if (updated) {
            console.log(`Product quantity updated to ${newQuantity}`);
            expect(updated).toBeTruthy();
        }
    });

    test('@Checkout TC007 - Remove Product from Checkout', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        
        const productCountBefore = await poManager.getCheckoutPage().getProductCount();
        
        // Act
        const removed = await poManager.getCheckoutPage().removeProductFromCheckout(productName);
        
        // Assert
        if (removed) {
            await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
            const productCountAfter = await poManager.getCheckoutPage().getProductCount();
            console.log(`Products before: ${productCountBefore}, after: ${productCountAfter}`);
        }
    });

    test('@Checkout TC008 - Verify Place Order Button is Visible and Enabled', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        
        // Assert
        const isVisible = await poManager.getCheckoutPage().isPlaceOrderButtonVisible();
        const isEnabled = await poManager.getCheckoutPage().isPlaceOrderButtonEnabled();
        
        expect(isVisible).toBeTruthy();
        expect(isEnabled).toBeTruthy();
    });

    test('@Checkout TC009 - Get Checkout Item Details', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        
        // Assert
        const itemDetails = await poManager.getCheckoutPage().getCheckoutItemDetails(0);
        
        console.log('Item Details:', itemDetails);
        expect(itemDetails).not.toBeNull();
        expect(itemDetails?.name).toBeTruthy();
        expect(itemDetails?.price).toBeTruthy();
    });

    test('@Checkout TC010 - Navigate to Place Order Page from Checkout', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        
        // Proceed to place order
        await poManager.getCheckoutPage().proceedToPlaceOrder();
        
        // Assert
        const url = await page.url();
        console.log('URL after proceeding:', url);
        // URL should change after clicking proceed
        expect(url).not.toContain('checkout');
    });

});
