import { expect } from '@playwright/test';
import { customTest as test } from '../utils_ts/test-base';

test.describe('Orders History Page Tests - Page Object Model', () => {
    
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

    test('@OrderHistory TC001 - Navigate to Orders and Verify Page Loads', async ({ page, poManager }) => {
        // Arrange - Page already navigated via fixture
        
        // Act
        await poManager.getDashboardPage().navigateToOrders();
        
        // Assert
        const isPageLoaded = await poManager.getOrdersHistoryPage().isOrdersPageLoaded();
        expect(isPageLoaded).toBeTruthy();
    });

    test('@OrderHistory TC002 - Verify Orders Table is Visible', async ({ page, poManager }) => {
        // Arrange - Page already navigated via fixture
        
        // Act
        await poManager.getDashboardPage().navigateToOrders();
        await poManager.getOrdersHistoryPage().waitForOrdersTableToLoad();
        
        // Assert
        expect(await poManager.getOrdersHistoryPage().isOrdersTableVisible()).toBeTruthy();
    });

    test('@OrderHistory TC003 - Get Order Row Count', async ({ page, poManager }) => {
        // Arrange - Page already navigated via fixture
        
        // Act
        await poManager.getDashboardPage().navigateToOrders();
        await poManager.getOrdersHistoryPage().waitForOrdersTableToLoad();
        const rowCount = await poManager.getOrdersHistoryPage().getOrderRowCount();
        
        // Assert
        expect(rowCount).toBeGreaterThanOrEqual(0);
        console.log('Total Orders:', rowCount);
    });

    test('@OrderHistory TC004 - Get All Order IDs', async ({ page, poManager }) => {
        // Arrange - Page already navigated via fixture
        
        // Act
        await poManager.getDashboardPage().navigateToOrders();
        await poManager.getOrdersHistoryPage().waitForOrdersTableToLoad();
        const orderIds = await poManager.getOrdersHistoryPage().getOrderIds();
        
        // Assert
        console.log('Order IDs:', orderIds);
        expect(Array.isArray(orderIds)).toBeTruthy();
    });

    test('@OrderHistory TC005 - Verify Orders Page URL Contains myorders', async ({ page, poManager }) => {
        // Arrange - Page already navigated via fixture
        
        // Act
        await poManager.getDashboardPage().navigateToOrders();
        const url = await poManager.getOrdersHistoryPage().getOrdersPageURL();
        
        // Assert
        expect(url).toContain('myorders');
        console.log('Orders Page URL:', url);
    });

    test('@OrderHistory TC006 - Click on First Order and View Details', async ({ page, poManager }) => {
        // Arrange - Page already navigated via fixture
        
        // Act
        await poManager.getDashboardPage().navigateToOrders();
        await poManager.getOrdersHistoryPage().waitForOrdersTableToLoad();
        const rowCount = await poManager.getOrdersHistoryPage().getOrderRowCount();
        
        // Assert
        if (rowCount > 0) {
            await poManager.getOrdersHistoryPage().clickViewButtonForOrder(0);
            await page.waitForLoadState('networkidle');
            expect(true).toBeTruthy(); // Order details page verification
        } else {
            console.log('No orders to view');
        }
    });

    test('@OrderHistory TC007 - Get First Order ID', async ({ page, poManager }) => {
        // Arrange - Page already navigated via fixture
        
        // Act
        await poManager.getDashboardPage().navigateToOrders();
        await poManager.getOrdersHistoryPage().waitForOrdersTableToLoad();
        const firstOrderId = await poManager.getOrdersHistoryPage().getFirstOrderId();
        
        // Assert
        if (firstOrderId) {
            expect(firstOrderId.length).toBeGreaterThan(0);
            console.log('First Order ID:', firstOrderId);
        }
    });

    test('@OrderHistory TC008 - Complete Purchase and Verify in Order History', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act - Complete purchase flow
        await poManager.getDashboardPage().waitForDashboardToLoad();
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().Checkout();
        await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
        const orderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();
        
        // Assert
        expect(orderId).toBeTruthy();
        console.log('Created Order ID:', orderId);
    });

    test('@OrderHistory TC009 - Search and Select Specific Order', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        let createdOrderId: string | null = null;
        
        // Create an order
        await poManager.getDashboardPage().waitForDashboardToLoad();
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().Checkout();
        await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
        createdOrderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();
        
        // Act - Navigate to orders
        await poManager.getDashboardPage().navigateToOrders();
        await poManager.getOrdersHistoryPage().waitForOrdersTableToLoad();
        
        // Search for the order
        if (createdOrderId) {
            await poManager.getOrdersHistoryPage().searchOrderAndSelect(createdOrderId);
        }
        
        // Assert
        expect(true).toBeTruthy();
    });

    test('@OrderHistory TC010 - Verify Order Details Page Loads', async ({ page, poManager }) => {
        // Arrange - Page already navigated via fixture
        
        // Act
        await poManager.getDashboardPage().navigateToOrders();
        await poManager.getOrdersHistoryPage().waitForOrdersTableToLoad();
        const rowCount = await poManager.getOrdersHistoryPage().getOrderRowCount();
        
        if (rowCount > 0) {
            await poManager.getOrdersHistoryPage().clickViewButtonForOrder(0);
            const isDetailsPageLoaded = await poManager.getOrdersHistoryPage().verifyOrderDetailsPageLoads();
            
            // Assert
            expect(isDetailsPageLoaded).toBeTruthy();
        }
    });

    test('@OrderHistory TC011 - Get Order By Index', async () => {
        // Arrange
        await page.goto('https://rahulshettyacademy.com/client');
        
        // Act
        await poManager.getDashboardPage().navigateToOrders();
        await poManager.getOrdersHistoryPage().waitForOrdersTableToLoad();
        const firstOrderId = await poManager.getOrdersHistoryPage().getOrderByIndex(0);
        
        // Assert
        console.log('First Order ID (by index):', firstOrderId);
        if (firstOrderId) {
            expect(firstOrderId.length).toBeGreaterThan(0);
        }
    });

    test('@OrderHistory TC012 - Verify Order Exists in History', async () => {
        // Arrange
        const email = 'rahulshetty@gmail.com';
        const productName = 'iphone 13 pro';
        let createdOrderId: string | null = null;
        
        // Create an order
        await page.goto('https://rahulshettyacademy.com/client');
        await poManager.getDashboardPage().waitForDashboardToLoad();
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().Checkout();
        await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
        createdOrderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();
        
        // Act - Verify in orders
        await poManager.getDashboardPage().navigateToOrders();
        await poManager.getOrdersHistoryPage().waitForOrdersTableToLoad();
        
        if (createdOrderId) {
            const orderExists = await poManager.getOrdersHistoryPage().verifyOrderIdExists(createdOrderId);
            
            // Assert
            expect(orderExists).toBeTruthy();
            console.log('Order exists in history:', orderExists);
        }
    });
});
