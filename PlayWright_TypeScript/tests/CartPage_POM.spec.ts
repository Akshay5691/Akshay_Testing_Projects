import { expect } from '@playwright/test';
import { customTest as test } from '../utils_ts/test-base';

test.describe('Cart Page Tests - Page Object Model', () => {
    
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

    test('@Cart TC001 - Navigate to Cart and Verify Page Loads', async ({ page, poManager }) => {
        // Arrange
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().navigateToCart();
        
        // Assert
        expect(await poManager.getCartPage().isCartPageLoaded()).toBeTruthy();
        const url = await poManager.getCartPage().getCartPageURL();
        expect(url).toContain('cart');
    });

    test('@Cart TC002 - Add Product to Cart and Verify it Appears', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act - Add product
        await poManager.getDashboardPage().searchProductAddCart(productName);
        
        // Navigate to cart
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        
        // Assert
        await poManager.getCartPage().verifyProductIsDisplayed(productName);
    });

    test('@Cart TC003 - Verify Checkout Button is Visible and Enabled', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        
        // Assert
        expect(await poManager.getCartPage().isCheckoutButtonVisible()).toBeTruthy();
        expect(await poManager.getCartPage().isCheckoutButtonEnabled()).toBeTruthy();
    });

    test('@Cart TC004 - Proceed to Checkout', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCheckoutButtonToAppear();
        await poManager.getCartPage().checkout();
        
        // Assert
        const url = await page.url();
        expect(url).toContain('checkout');
    });

    test('@Cart TC005 - Get Cart Item Count', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        const itemCount = await poManager.getCartPage().getCartItemCount();
        
        // Assert
        expect(itemCount).toBeGreaterThan(0);
        console.log('Cart Item Count:', itemCount);
    });

    test('@Cart TC006 - Verify Multiple Products in Cart', async ({ page, poManager }) => {
        // Arrange
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act - Add multiple products
        const buttonCount = await poManager.getDashboardPage().getAddToCartButtonCount();
        
        if (buttonCount > 0) {
            await poManager.getDashboardPage().addProductToCartByIndex(0);
            await page.waitForLoadState('networkidle');
        }
        
        if (buttonCount > 1) {
            await poManager.getDashboardPage().addProductToCartByIndex(1);
            await page.waitForLoadState('networkidle');
        }
        
        // Navigate to cart
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        
        // Assert
        const itemCount = await poManager.getCartPage().getCartItemCount();
        expect(itemCount).toBeGreaterThan(0);
    });

    test.only('@Cart TC007 - Verify Cart Page URL Contains Cart', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        const url = await poManager.getCartPage().getCartPageURL();
        
        // Assert
        expect(url).toContain('cart');
        console.log('Cart Page URL:', url);
    });

    test('@Cart TC008 - Verify Cart Items Names', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        const cartItemNames = await poManager.getCartPage().getCartItemsNames();
        
        // Assert
        expect(cartItemNames.length).toBeGreaterThan(0);
        console.log('Cart Items:', cartItemNames);
    });

    test('@Cart TC009 - Verify Cart is Not Empty', async ({page,poManager}) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await page.goto('https://rahulshettyacademy.com/client');
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        
        // Assert
        const isEmpty = await poManager.getCartPage().isCartEmpty();
        expect(isEmpty).toBeFalsy();
    });

    test('@Cart TC010 - Verify Checkout Flow Till Order Review Page', async ({page,poManager}) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await page.goto('https://rahulshettyacademy.com/client');
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().Checkout();
        
       
       
    });
});
