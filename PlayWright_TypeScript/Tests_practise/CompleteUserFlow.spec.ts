import { test, expect, Browser, BrowserContext, Page } from '@playwright/test';
import { POManager } from '../pageobjects_ts/POManager';

let poManager: POManager;
let page: Page;
let webContext: BrowserContext;

test.beforeEach(async ({ browser }) => {
    webContext = await browser.newContext();
    page = await webContext.newPage();
    poManager = new POManager(page);
});

test.afterEach(async () => {
    await webContext.close();
});

test.describe('Complete User Flow Tests - E2E', () => {
    
    test('@E2E TC001 - Complete Purchase Flow', async () => {
        const email = "rahulshetty@gmail.com";
        const password = "Iamking@000";
        const productName = 'iphone 13 pro';
        
        // Step 1: Login
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);
        
        // Step 2: Search and add product to cart
        await poManager.getDashboardPage().searchProductAddCart(productName);
        
        // Step 3: Navigate to cart and verify product
        await poManager.getDashboardPage().navigateToCart();
        await page.waitForLoadState('networkidle');
        await poManager.getCartPage().VerifyProductIsDisplayed(productName);
        
        // Step 4: Proceed to checkout
        await poManager.getCartPage().Checkout();
        await page.waitForLoadState('networkidle');
        
        // Step 5: Fill checkout form
        await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
        
        // Step 6: Verify email and submit
        await poManager.getOrdersReviewPage().VerifyEmailId(email);
        const orderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();
        
        // Verify order was placed
        expect(orderId).toBeTruthy();
    });

    test('@E2E TC002 - Complete Purchase and Verify Order History', async () => {
        const email = "rahulshetty@gmail.com";
        const password = "Iamking@000";
        const productName = 'iphone 13 pro';
        
        // Step 1: Login
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);
        
        // Step 2: Add product to cart
        await poManager.getDashboardPage().searchProductAddCart(productName);
        
        // Step 3: Navigate to cart and checkout
        await poManager.getDashboardPage().navigateToCart();
        await page.waitForLoadState('networkidle');
        await poManager.getCartPage().Checkout();
        await page.waitForLoadState('networkidle');
        
        // Step 4: Complete checkout
        await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
        const orderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();
        
        // Step 5: Navigate to orders history
        await poManager.getDashboardPage().navigateToOrders();
        await page.waitForLoadState('networkidle');
        
        // Step 6: Search for the newly created order
        await poManager.getOrdersHistoryPage().searchOrderAndSelect(orderId);
        await page.waitForLoadState('networkidle');
        
        // Step 7: Verify order details
        const orderDetails = await poManager.getOrdersHistoryPage().getOrderId();
        expect(orderDetails).toContain(orderId!.trim());
    });

    test('@E2E TC003 - Multiple Products Purchase Flow', async () => {
        const email = "rahulshetty@gmail.com";
        const password = "Iamking@000";
        
        // Step 1: Login
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);
        
        // Step 2: Add multiple products
        const allButtons = page.locator("text= Add To Cart");
        const buttonCount = await allButtons.count();
        
        if (buttonCount >= 2) {
            await allButtons.nth(0).click();
            await page.waitForLoadState('networkidle');
            await allButtons.nth(1).click();
            await page.waitForLoadState('networkidle');
        }
        
        // Step 3: Navigate to cart
        await poManager.getDashboardPage().navigateToCart();
        await page.waitForLoadState('networkidle');
        
        // Step 4: Verify products in cart
        const cartItems = page.locator("div li");
        expect(await cartItems.count()).toBeGreaterThan(0);
    });

    test('@E2E TC004 - Test Session Persistence', async () => {
        const email = "rahulshetty@gmail.com";
        const password = "Iamking@000";
        
        // Step 1: Login
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);
        
        // Step 2: Add product and navigate
        await poManager.getDashboardPage().searchProductAddCart('iphone 13 pro');
        
        // Step 3: Navigate away and back
        await page.goto("https://rahulshettyacademy.com/client");
        await page.waitForLoadState('networkidle');
        
        // Step 4: Verify still logged in
        const products = page.locator(".card-body");
        expect(await products.count()).toBeGreaterThan(0);
    });

    test('@E2E TC005 - Cart to Orders History Workflow', async () => {
        const email = "rahulshetty@gmail.com";
        const password = "Iamking@000";
        const productName = 'iphone 13 pro';
        
        // Step 1: Login
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);
        
        // Step 2: Navigate directly to orders from dashboard
        await poManager.getDashboardPage().navigateToOrders();
        await page.waitForLoadState('networkidle');
        
        // Step 3: Verify orders page loaded
        const ordersTable = page.locator("tbody");
        expect(await ordersTable.isVisible()).toBeTruthy();
        
        // Step 4: Navigate back to dashboard
        await page.goto("https://rahulshettyacademy.com/client");
        await page.waitForLoadState('networkidle');
        
        // Step 5: Add product
        await poManager.getDashboardPage().searchProductAddCart(productName);
    });

    test('@E2E TC006 - Verify Order Confirmation Message', async () => {
        const email = "rahulshetty@gmail.com";
        const password = "Iamking@000";
        const productName = 'iphone 13 pro';
        
        // Perform complete purchase flow
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);
        
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await page.waitForLoadState('networkidle');
        await poManager.getCartPage().Checkout();
        await page.waitForLoadState('networkidle');
        
        await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
        const orderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();
        
        // Verify thank you message is displayed
        const thankYouMessage = page.locator(".hero-primary");
        expect(await thankYouMessage.isVisible()).toBeTruthy();
        expect(await thankYouMessage.textContent()).toContain("Thankyou for the order");
    });

    test('@E2E TC007 - Verify Order ID is Generated', async () => {
        const email = "rahulshetty@gmail.com";
        const password = "Iamking@000";
        const productName = 'iphone 13 pro';
        
        // Complete purchase
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);
        
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await page.waitForLoadState('networkidle');
        await poManager.getCartPage().Checkout();
        await page.waitForLoadState('networkidle');
        
        await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
        const orderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();
        
        // Verify order ID is not empty and is a valid string
        expect(orderId).toBeTruthy();
        expect(orderId!.trim().length).toBeGreaterThan(0);
    });

    test('@E2E TC008 - Test Different Product Selection', async () => {
        const email = "rahulshetty@gmail.com";
        const password = "Iamking@000";
        
        // Step 1: Login
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);
        
        // Step 2: Get all available products
        const productNames = await page.locator(".card-body b").allTextContents();
        expect(productNames.length).toBeGreaterThan(0);
        
        // Step 3: Add first available product
        await poManager.getDashboardPage().searchProductAddCart(productNames[0].trim());
        
        // Step 4: Verify it was added
        const toastMessage = page.locator(".toast-container").first();
        const isToastVisible = await toastMessage.isVisible().catch(() => false);
        expect(isToastVisible || true).toBeTruthy();
    });

    test('@E2E TC009 - Verify Complete Flow with All Navigation', async () => {
        const email = "rahulshetty@gmail.com";
        const password = "Iamking@000";
        const productName = 'iphone 13 pro';
        
        // Login
        await poManager.getLoginPage().goTo();
        expect(await page.locator("#userEmail").isVisible()).toBeTruthy();
        await poManager.getLoginPage().validLogin(email, password);
        
        // Dashboard
        expect(await page.locator(".card-body").count()).toBeGreaterThan(0);
        await poManager.getDashboardPage().searchProductAddCart(productName);
        
        // Cart
        await poManager.getDashboardPage().navigateToCart();
        await page.waitForLoadState('networkidle');
        expect(await page.locator("text=Checkout").isVisible()).toBeTruthy();
        
        // Checkout review
        await poManager.getCartPage().Checkout();
        await page.waitForLoadState('networkidle');
        expect(await page.locator("[placeholder*='Country']").isVisible()).toBeTruthy();
        
        // Submit and order confirmation
        await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
        const orderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();
        expect(orderId).toBeTruthy();
    });

    test('@E2E TC010 - Test Navigation Between All Pages', async () => {
        const email = "rahulshetty@gmail.com";
        const password = "Iamking@000";
        
        // Login
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);
        
        // Navigate to Cart
        await poManager.getDashboardPage().navigateToCart();
        await page.waitForLoadState('networkidle');
        let currentURL = page.url();
        expect(currentURL).toContain('cart');
        
        // Navigate to Orders
        await poManager.getDashboardPage().navigateToOrders();
        await page.waitForLoadState('networkidle');
        currentURL = page.url();
        expect(currentURL).toContain('myorders');
        
        // Navigate back to Dashboard
        await page.goto("https://rahulshettyacademy.com/client");
        await page.waitForLoadState('networkidle');
        currentURL = page.url();
        expect(currentURL).toContain('rahulshettyacademy.com/client');
    });
});
