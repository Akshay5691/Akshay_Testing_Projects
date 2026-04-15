import { expect, test, Page, BrowserContext, chromium } from '@playwright/test';
import { POManager } from '../pageobjects_ts/POManager';

const BASE_URL = 'https://rahulshettyacademy.com/client';
const SESSION_PATH = 'state.json';

let page: Page;
let poManager: POManager;
let context: BrowserContext;

test.describe('Cart Page Tests - Page Object Model', () => {
    
    test.beforeEach(async () => {
        const browser = await chromium.launch();
        context = await browser.newContext({ storageState: SESSION_PATH });
        page = await context.newPage();
        await page.goto(BASE_URL);
        await page.waitForLoadState('domcontentloaded');
        poManager = new POManager(page);
    });
    
    test.afterEach(async () => {
        if (page) await page.close();
        if (context) await context.close();
    });

    test('@Cart TC001 - Navigate to Cart and Verify Page Loads', async () => {
        // Arrange
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        await dashboardPage.navigateToCart();
        
        // Assert
        const cartPage = poManager.getCartPage();
        expect(await cartPage.isCartPageLoaded()).toBeTruthy();
        const url = await cartPage.getCartPageURL();
        expect(url).toContain('cart');
    });

    test('@Cart TC002 - Add Product to Cart and Verify it Appears', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act - Add product
        await dashboardPage.searchProductAddCart(productName);
        
        // Navigate to cart
        await dashboardPage.navigateToCart();
        const cartPage = poManager.getCartPage();
        await cartPage.waitForCartToLoad();
        
        // Assert
        await cartPage.verifyProductIsDisplayed(productName);
    });

    test('@Cart TC003 - Verify Checkout Button is Visible and Enabled', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        const cartPage = poManager.getCartPage();
        await cartPage.waitForCartToLoad();
        
        // Assert
        expect(await cartPage.isCheckoutButtonVisible()).toBeTruthy();
        expect(await cartPage.isCheckoutButtonEnabled()).toBeTruthy();
    });

    test('@Cart TC004 - Proceed to Checkout', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        const cartPage = poManager.getCartPage();
        await cartPage.waitForCheckoutButtonToAppear();
        await cartPage.checkout();
        
        // Assert
        const url = await page.url();
        expect(url).toContain('checkout');
    });

    test('@Cart TC005 - Get Cart Item Count', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        const cartPage = poManager.getCartPage();
        await cartPage.waitForCartToLoad();
        const itemCount = await cartPage.getCartItemCount();
        
        // Assert
        expect(itemCount).toBeGreaterThan(0);
        console.log('Cart Item Count:', itemCount);
    });

    test('@Cart TC006 - Verify Multiple Products in Cart', async () => {
        // Arrange
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
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
        const cartPage = poManager.getCartPage();
        await cartPage.waitForCartToLoad();
        
        // Assert
        const itemCount = await cartPage.getCartItemCount();
        expect(itemCount).toBeGreaterThan(0);
    });

    test('@Cart TC007 - Verify Cart Page URL Contains Cart', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        const cartPage = poManager.getCartPage();
        const url = await cartPage.getCartPageURL();
        
        // Assert
        expect(url).toContain('cart');
        console.log('Cart Page URL:', url);
    });

    test('@Cart TC008 - Verify Cart Items Names', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        const cartPage = poManager.getCartPage();
        await cartPage.waitForCartToLoad();
        const cartItemNames = await cartPage.getCartItemsNames();
        
        // Assert
        expect(cartItemNames.length).toBeGreaterThan(0);
        console.log('Cart Items:', cartItemNames);
    });

    test('@Cart TC009 - Verify Cart is Not Empty', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        await page.goto('https://rahulshettyacademy.com/client');
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        const cartPage = poManager.getCartPage();
        await cartPage.waitForCartToLoad();
        
        // Assert
        const isEmpty = await cartPage.isCartEmpty();
        expect(isEmpty).toBeFalsy();
    });

    test('@Cart TC010 - Verify Checkout Flow Till Order Review Page', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        await page.goto('https://rahulshettyacademy.com/client');
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        const cartPage = poManager.getCartPage();
        await cartPage.waitForCartToLoad();
        await cartPage.Checkout();
        
       
       
    });
});
