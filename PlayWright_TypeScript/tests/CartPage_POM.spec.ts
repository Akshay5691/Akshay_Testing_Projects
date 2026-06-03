import { test, expect } from '../base/test-base';
import { POManager } from '../pageobjects_ts/POManager';
import { CartPage } from '../pageobjects_ts/CartPage';

test.describe('Cart Page Tests - Page Object Model', () => {

    let poManager: POManager;
    let dashboardPage: any;
    let cartPage: CartPage;

    test.beforeEach(async ({ page }) => {
        poManager = (page as any).poManager;
        if (!poManager) throw new Error('poManager not initialized in test-base beforeEach');
        dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        cartPage = poManager.getCartPage();
    });

    test('@Cart TC001 - Navigate to Cart and Verify Page Loads', async () => {
        // Act
        await dashboardPage.navigateToCart();
        
        // Assert
        expect(await cartPage.isCartPageLoaded()).toBeTruthy();
        const url = await cartPage.getCartPageURL();
        expect(url).toContain('cart');
    });

    test('@Cart TC002 - Add Product to Cart and Verify it Appears', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act - Add product
        await dashboardPage.searchProductAddCart(productName);
        
        // Navigate to cart
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        
        // Assert
        await cartPage.verifyProductIsDisplayed(productName);
    });

    test('@Cart TC003 - Verify Checkout Button is Visible and Enabled', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        
        // Assert
        expect(await cartPage.isCheckoutButtonVisible()).toBeTruthy();
        expect(await cartPage.isCheckoutButtonEnabled()).toBeTruthy();
    });

    test('@Cart TC004 - Proceed to Checkout', async ({ page }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCheckoutButtonToAppear();
        await cartPage.checkout();
        
        // Assert
        const url = await page.url();
        expect(url).toContain('checkout');
    });

    test('@Cart TC005 - Get Cart Item Count', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        const itemCount = await cartPage.getCartItemCount();
        
        // Assert
        expect(itemCount).toBeGreaterThan(0);
        console.log('Cart Item Count:', itemCount);
    });

    test('@Cart TC006 - Verify Multiple Products in Cart', async ({ page }) => {
        // Act - Add multiple products
        const buttonCount = await dashboardPage.getAddToCartButtonCount();
        
        if (buttonCount > 0) {
            await dashboardPage.addProductToCartByIndex(0);
            await page.waitForLoadState('networkidle');
        }
        
        if (buttonCount > 1) {
            await dashboardPage.addProductToCartByIndex(1);
            await page.waitForLoadState('networkidle');
        }
        
        // Navigate to cart
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        
        // Assert
        const itemCount = await cartPage.getCartItemCount();
        expect(itemCount).toBeGreaterThan(0);
    });

    test('@Cart TC007 - Verify Cart Page URL Contains Cart', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        const url = await cartPage.getCartPageURL();
        
        // Assert
        expect(url).toContain('cart');
        console.log('Cart Page URL:', url);
    });

    test('@Cart TC008 - Verify Cart Items Names', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        const cartItemNames = await cartPage.getCartItemsNames();
        
        // Assert
        expect(cartItemNames.length).toBeGreaterThan(0);
        console.log('Cart Items:', cartItemNames);
    });

    test('@Cart TC009 - Verify Cart is Not Empty', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        
        // Assert
        const isEmpty = await cartPage.isCartEmpty();
        expect(isEmpty).toBeFalsy();
    });

    test('@Cart TC010 - Verify Checkout Flow Till Order Review Page', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.Checkout();
    });
});
