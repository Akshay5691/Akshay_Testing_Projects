import { test, expect, BrowserContext, Page } from '@playwright/test';
import { POManager } from '../pageobjects_ts/POManager';

let poManager: POManager;
let page: Page;
let webContext: BrowserContext;

test.describe('Complete Checkout Flow - End to End Tests - Page Object Model', () => {
    
    test.beforeEach(async ({ browser }) => {
        // Fresh context for each test without pre-saved session
        page = await browser.newPage();
        poManager = new POManager(page);
    });

    test.afterEach(async () => {
        await page.close();
    });

    test('@E2E TC001 - Complete Purchase Flow from Login to Order Confirmation', async () => {
        // Arrange
        const email = 'rahulshetty@gmail.com';
        const password = 'Iamking@000';
        const productName = 'iphone 13 pro';

        // Act & Assert - Step 1: Login
        await poManager.getLoginPage().goTo();
        expect(await poManager.getLoginPage().isUserNameFieldVisible()).toBeTruthy();
        await poManager.getLoginPage().validLogin(email, password);
        expect(await poManager.getDashboardPage().getProductCount()).toBeGreaterThan(0);

        // Step 2: Search and Add Product
        await poManager.getDashboardPage().searchProductAddCart(productName);
        console.log('Product added to cart');

        // Step 3: Navigate to Cart
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().verifyProductIsDisplayed(productName);
        console.log('Product verified in cart');

        // Step 4: Proceed to Checkout
        await poManager.getCartPage().Checkout();
        expect(await poManager.getOrdersReviewPage().isCountryFieldVisible()).toBeTruthy();

        // Step 5: Fill Checkout Details
        await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
        await poManager.getOrdersReviewPage().VerifyEmailId(email);
        console.log('Checkout details filled');

        // Step 6: Submit Order
        const orderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();
        expect(orderId).toBeTruthy();
        console.log('Order ID:', orderId);

        // Step 7: Verify Order Confirmation
        const confirmationMessage = await poManager.getOrdersReviewPage().getOrderConfirmationMessage();
        expect(confirmationMessage).toContain('Thankyou for the order');
    });

    test('@E2E TC002 - Purchase and Verify in Order History', async () => {
        // Arrange
        const email = 'rahulshetty@gmail.com';
        const password = 'Iamking@000';
        const productName = 'iphone 13 pro';

        // Act - Complete Purchase
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().Checkout();
        await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
        const orderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();

        // Navigate to Orders
        await poManager.getDashboardPage().navigateToOrders();
        await poManager.getOrdersHistoryPage().waitForOrdersTableToLoad();

        // Assert
        expect(orderId).toBeTruthy();
        if (orderId) {
            const orderExists = await poManager.getOrdersHistoryPage().verifyOrderIdExists(orderId);
            expect(orderExists).toBeTruthy();
            console.log('Order verified in history:', orderId);
        }
    });

    test('@E2E TC003 - Add Multiple Products and Checkout', async () => {
        // Arrange
        const email = 'rahulshetty@gmail.com';
        const password = 'Iamking@000';

        // Act - Login
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);

        // Add multiple products
        const buttonCount = await poManager.getDashboardPage().getAddToCartButtonCount();
        console.log('Available products:', buttonCount);

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
        console.log('Items in cart:', itemCount);
    });

    test('@E2E TC004 - Verify Product in Cart After Adding', async () => {
        // Arrange
        const email = 'rahulshetty@gmail.com';
        const password = 'Iamking@000';
        const productName = 'iphone 13 pro';

        // Act
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);
        await poManager.getDashboardPage().searchProductAddCart(productName);

        // Assert - Check dashboard still shows products
        const productCount = await poManager.getDashboardPage().getProductCount();
        expect(productCount).toBeGreaterThan(0);

        // Navigate and verify in cart
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().verifyProductIsDisplayed(productName);
    });

    test('@E2E TC005 - Complete Flow with Order Details Verification', async () => {
        // Arrange
        const email = 'rahulshetty@gmail.com';
        const password = 'Iamking@000';
        const productName = 'iphone 13 pro';

        // Act & Assert
        // Step 1: Login and verify
        await poManager.getLoginPage().goTo();
        const loginUrl = await poManager.getLoginPage().getLoginPageURL();
        expect(loginUrl).toContain('client');

        await poManager.getLoginPage().validLogin(email, password);
        const dashboardProducts = await poManager.getDashboardPage().getAllProductNames();
        expect(dashboardProducts.length).toBeGreaterThan(0);
        console.log('Available Products:', dashboardProducts);

        // Step 2: Add product
        await poManager.getDashboardPage().searchProductAddCart(productName);

        // Step 3: Verify in cart
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().verifyProductIsDisplayed(productName);
        const cartUrl = await poManager.getCartPage().getCartPageURL();
        expect(cartUrl).toContain('cart');

        // Step 4: Proceed and verify checkout
        await poManager.getCartPage().Checkout();
        const checkoutUrl = await page.url();
        expect(checkoutUrl).toContain('checkout');

        // Step 5: Fill and verify checkout
        await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
        const cartEmail = await poManager.getOrdersReviewPage().getEmailId();
        expect(cartEmail).toContain(email);

        // Step 6: Place order
        const orderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();
        expect(orderId).toBeTruthy();
        expect(await poManager.getOrdersReviewPage().isOrderConfirmationDisplayed()).toBeTruthy();
    });

    test('@E2E TC006 - Verify Navigation Between All Pages', async () => {
        // Arrange
        const email = 'rahulshetty@gmail.com';
        const password = 'Iamking@000';

        // Act
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);

        // Navigate to Cart
        let url = await page.url();
        expect(url).toContain('client');

        await poManager.getDashboardPage().navigateToCart();
        url = await page.url();
        expect(url).toContain('cart');

        // Navigate to Orders
        await poManager.getDashboardPage().navigateToOrders();
        url = await page.url();
        expect(url).toContain('myorders');

        // Back to Dashboard
        await page.goto('https://rahulshettyacademy.com/client');
        url = await page.url();
        expect(url).toContain('client');

        // Assert
        console.log('Navigation verified across all pages');
        expect(true).toBeTruthy();
    });

    test('@E2E TC007 - Test Login Persistence Across Navigation', async () => {
        // Arrange
        const email = 'rahulshetty@gmail.com';
        const password = 'Iamking@000';

        // Act
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);

        // Perform actions and navigate
        const productCount1 = await poManager.getDashboardPage().getProductCount();
        expect(productCount1).toBeGreaterThan(0);

        await poManager.getDashboardPage().navigateToCart();
        await page.waitForLoadState('networkidle');

        // Navigate back
        await poManager.getDashboardPage().navigateToOrders();
        await page.waitForLoadState('networkidle');

        // Check if still authenticated
        const ordersUrl = await page.url();
        expect(ordersUrl).toContain('myorders');

        // Assert
        console.log('Session persistence verified');
    });

    test('@E2E TC008 - Verify Order Confirmation Details', async () => {
        // Arrange
        const email = 'rahulshetty@gmail.com';
        const password = 'Iamking@000';
        const productName = 'iphone 13 pro';

        // Act
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().Checkout();
        await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');

        // Assert confirmation details
        const emailBefore = await poManager.getOrdersReviewPage().getEmailId();
        expect(emailBefore).toContain(email);

        const orderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();
        expect(orderId).toBeTruthy();

        const confirmationMsg = await poManager.getOrdersReviewPage().getOrderConfirmationMessage();
        expect(confirmationMsg).toContain('Thankyou');
    });

    test('@E2E TC009 - Verify All Dashboard Functionality', async () => {
        // Arrange
        const email = 'rahulshetty@gmail.com';
        const password = 'Iamking@000';

        // Act
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);

        // Dashboard verification
        await poManager.getDashboardPage().waitForDashboardToLoad();
        const productCount = await poManager.getDashboardPage().getProductCount();
        const productNames = await poManager.getDashboardPage().getAllProductNames();
        const isAddToCartVisible = await poManager.getDashboardPage().isAddToCartButtonVisible();

        // Assert
        expect(productCount).toBeGreaterThan(0);
        expect(productNames.length).toBe(productCount);
        expect(isAddToCartVisible).toBeTruthy();
        console.log('Dashboard verification complete');
    });

    test('@E2E TC010 - Complete Purchase to Order Details Page', async () => {
        // Arrange
        const email = 'rahulshetty@gmail.com';
        const password = 'Iamking@000';
        const productName = 'iphone 13 pro';

        // Act
        await poManager.getLoginPage().goTo();
        await poManager.getLoginPage().validLogin(email, password);
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().Checkout();
        await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
        const orderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();

        // Navigate to orders and view details
        await poManager.getDashboardPage().navigateToOrders();
        await poManager.getOrdersHistoryPage().waitForOrdersTableToLoad();

        if (orderId) {
            await poManager.getOrdersHistoryPage().searchOrderAndSelect(orderId);
        }

        // Assert
        expect(orderId).toBeTruthy();
        console.log('Complete purchase flow verified, Order ID:', orderId);
    });
});
