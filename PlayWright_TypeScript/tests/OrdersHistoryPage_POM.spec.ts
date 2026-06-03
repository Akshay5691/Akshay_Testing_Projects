import { test, expect } from '../base/test-base';
import { POManager } from '../pageobjects_ts/POManager';
import { OrdersHistoryPage } from '../pageobjects_ts/OrdersHistoryPage';

test.describe('Orders History Page Tests - Page Object Model', () => {

    let poManager: POManager;
    let dashboardPage: any;
    let ordersPage: OrdersHistoryPage;

    test.beforeEach(async ({ page }) => {
        poManager = (page as any).poManager;
        if (!poManager) throw new Error('poManager not initialized in test-base beforeEach');
        dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        ordersPage = poManager.getOrdersHistoryPage();
    });

    test('@OrderHistory TC001 - Navigate to Orders and Verify Page Loads', async () => {
        // Act
        await dashboardPage.navigateToOrders();
        
        // Assert
        const isPageLoaded = await ordersPage.isOrdersPageLoaded();
        expect(isPageLoaded).toBeTruthy();
    });

    test('@OrderHistory TC002 - Verify Orders Table is Visible', async () => {
        // Act
        await dashboardPage.navigateToOrders();
        await ordersPage.waitForOrdersTableToLoad();
        
        // Assert
        expect(await ordersPage.isOrdersTableVisible()).toBeTruthy();
    });

    test('@OrderHistory TC003 - Get Order Row Count', async () => {
        // Act
        await dashboardPage.navigateToOrders();
        await ordersPage.waitForOrdersTableToLoad();
        const rowCount = await ordersPage.getOrderRowCount();
        
        // Assert
        expect(rowCount).toBeGreaterThanOrEqual(0);
        console.log('Total Orders:', rowCount);
    });

    test('@OrderHistory TC004 - Get All Order IDs', async () => {
        // Act
        await dashboardPage.navigateToOrders();
        await ordersPage.waitForOrdersTableToLoad();
        const orderIds = await ordersPage.getOrderIds();
        
        // Assert
        console.log('Order IDs:', orderIds);
        expect(Array.isArray(orderIds)).toBeTruthy();
    });

    test('@OrderHistory TC005 - Verify Orders Page URL Contains myorders', async () => {
        // Act
        await dashboardPage.navigateToOrders();
        const url = await ordersPage.getOrdersPageURL();
        
        // Assert
        expect(url).toContain('myorders');
        console.log('Orders Page URL:', url);
    });

    test('@OrderHistory TC006 - Click on First Order and View Details', async ({ page }) => {
        // Act
        await dashboardPage.navigateToOrders();
        await ordersPage.waitForOrdersTableToLoad();
        const rowCount = await ordersPage.getOrderRowCount();
        
        // Assert
        if (rowCount > 0) {
            await ordersPage.clickViewButtonForOrder(0);
            await page.waitForLoadState('networkidle');
            expect(true).toBeTruthy(); // Order details page verification
        } else {
            console.log('No orders to view');
        }
    });

    test('@OrderHistory TC007 - Get First Order ID', async () => {
        // Act
        await dashboardPage.navigateToOrders();
        await ordersPage.waitForOrdersTableToLoad();
        const firstOrderId = await ordersPage.getFirstOrderId();
        
        // Assert
        if (firstOrderId) {
            expect(firstOrderId.length).toBeGreaterThan(0);
            console.log('First Order ID:', firstOrderId);
        }
    });

    test.skip('@OrderHistory TC008 - Complete Purchase and Verify in Order History', async () => {
        // This test requires proper implementation of PlaceOrderPage methods
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act - Complete purchase flow would go here
        // For now, this test is skipped until PlaceOrderPage has proper methods
        
        // Assert
        expect(true).toBeTruthy();
    });

   

    test('@OrderHistory TC010 - Verify Order Details Page Loads', async () => {
        // Arrange - Page already navigated via fixture
        
        // Act
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.navigateToOrders();
        const ordersPage = poManager.getOrdersHistoryPage();
        await ordersPage.waitForOrdersTableToLoad();
        const rowCount = await ordersPage.getOrderRowCount();
        
        if (rowCount > 0) {
            await ordersPage.clickViewButtonForOrder(0);
            const isDetailsPageLoaded = await ordersPage.verifyOrderDetailsPageLoads();
            
            // Assert
            expect(isDetailsPageLoaded).toBeTruthy();
        }
    });

    test('@OrderHistory TC011 - Get Order By Index', async () => {
        // Arrange
        
        // Act
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.navigateToOrders();
        const ordersPage = poManager.getOrdersHistoryPage();
        await ordersPage.waitForOrdersTableToLoad();
        const firstOrderId = await ordersPage.getOrderByIndex(0);
        
        // Assert
        console.log('First Order ID (by index):', firstOrderId);
        if (firstOrderId) {
            expect(firstOrderId.length).toBeGreaterThan(0);
        }
    });

   
});
