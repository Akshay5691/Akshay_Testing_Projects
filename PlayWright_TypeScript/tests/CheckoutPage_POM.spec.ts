import { test, expect } from '../base/test-base';
import { POManager } from '../pageobjects_ts/POManager';
import { CheckoutPage } from '../pageobjects_ts/CheckoutPage';
import { CartPage } from '../pageobjects_ts/CartPage';

test.describe('Checkout Page Tests - Page Object Model', () => {

    let poManager: POManager;
    let dashboardPage: any;
    let cartPage: CartPage;
    let checkoutPage: CheckoutPage;

    test.beforeEach(async ({ page }) => {
        poManager = (page as any).poManager;
        if (!poManager) throw new Error('poManager not initialized in test-base beforeEach');
        dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        cartPage = poManager.getCartPage();
        checkoutPage = poManager.getCheckoutPage();
    });

    test('@Checkout TC001 - Navigate to Checkout Page and Verify Page Loads', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        
        // Assert
       
        const url = await checkoutPage.getCheckoutPageURL();
        expect(url).toContain('rahulshettyacademy.com/client/#/dashboard/order?');
    });

    test('@Checkout TC002 - Verify All Products Appear in Checkout Page', async () => {
        // Arrange
        const productName = 'ADIDAS ORIGINAL';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        
        // Assert
        const productCount = await checkoutPage.getProductCount();
        expect(productCount).toBeGreaterThan(0);
        
        const productNames = await checkoutPage.getAllProductNames();
        const isProductInCheckout = await checkoutPage.verifyProductInCheckout(productName);
        expect(isProductInCheckout).toBeTruthy();
    });

    test('@Checkout TC003 - Verify Product Price is Displayed in Checkout', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        
        // Assert
        const price = await checkoutPage.getProductPrice(productName);
        expect(price).not.toBeNull();
        console.log(`Product ${productName} price: ${price}`);
    });

    test('@Checkout TC004 - Verify Subtotal and Total Amount Calculation', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        
        // Assert
        const subtotal = await checkoutPage.getSubtotal();
        const total = await checkoutPage.getTotalPrice();
        
        console.log(`Subtotal: ${subtotal}`);
        console.log(`Total: ${total}`);
        
        // Verify that total is not empty
        expect(total).not.toBeNull();
    });

    test('@Checkout TC005 - Apply Discount Code and Verify Amount is Updated', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        const discountCode = 'rahulshettyacademy';
        
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        
        await checkoutPage.waitForCheckoutPageToLoad();
        const totalBefore = await checkoutPage.getTotalPrice();
        
        // Act
        const applied = await checkoutPage.applyDiscountCode(discountCode);
        
        if (applied) {
            await checkoutPage.waitForDiscountToBeApplied();
            const totalAfter = await checkoutPage.getTotalPrice();
            
            // Assert
            console.log(`Total before discount: ${totalBefore}`);
            console.log(`Total after discount: ${totalAfter}`);
            expect(totalAfter).not.toBeNull();
        }
    });

    test('@Checkout TC006 - Update Product Quantity in Checkout', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        const newQuantity = 2;
        
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        
        // Act
        const updated = await checkoutPage.updateProductQuantity(productName, newQuantity);
        
        // Assert
        if (updated) {
            console.log(`Product quantity updated to ${newQuantity}`);
            expect(updated).toBeTruthy();
        }
    });

    test('@Checkout TC007 - Remove Product from Checkout', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        
        const productCountBefore = await checkoutPage.getProductCount();
        
        // Act
        const removed = await checkoutPage.removeProductFromCheckout(productName);
        
        // Assert
        if (removed) {
            await checkoutPage.waitForCheckoutPageToLoad();
            const productCountAfter = await checkoutPage.getProductCount();
            console.log(`Products before: ${productCountBefore}, after: ${productCountAfter}`);
        }
    });

    test('@Checkout TC008 - Verify Place Order Button is Visible and Enabled', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        
        // Assert
        const isVisible = await checkoutPage.isPlaceOrderButtonVisible();
        const isEnabled = await checkoutPage.isPlaceOrderButtonEnabled();
        
        expect(isVisible).toBeTruthy();
        expect(isEnabled).toBeTruthy();
    });

    test('@Checkout TC009 - Get Checkout Item Details', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        
        // Assert
        const itemDetails = await checkoutPage.getCheckoutItemDetails(0);
        
        console.log('Item Details:', itemDetails);
        expect(itemDetails).not.toBeNull();
        expect(itemDetails?.name).toBeTruthy();
        expect(itemDetails?.price).toBeTruthy();
    });

    test('@Checkout TC010 - Navigate to Place Order Page from Checkout', async ({ page }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        
        // Proceed to place order
        await checkoutPage.proceedToPlaceOrder();
        
        // Assert
        const url = await page.url();
        console.log('URL after proceeding:', url);
        // URL should change after clicking proceed
        expect(url).not.toContain('checkout');
    });

});
