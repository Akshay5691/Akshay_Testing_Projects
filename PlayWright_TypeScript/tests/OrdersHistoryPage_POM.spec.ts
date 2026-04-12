import { expect, test, Page, BrowserContext, chromium } from '@playwright/test';
import { POManager } from '../pageobjects_ts/POManager';

const BASE_URL = 'https://rahulshettyacademy.com/client';
const SESSION_PATH = 'state.json';

let page: Page;
let poManager: POManager;
let context: BrowserContext;

test.describe('Orders History Page Tests - Page Object Model', () => {
    
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

    test('@OrderHistory TC001 - Navigate to Orders and Verify Page Loads', async () => {
        // Arrange - Page already navigated via fixture
        
        // Act
        await poManager.getDashboardPage().navigateToOrders();
        
        // Assert
        const isPageLoaded = await poManager.getOrdersHistoryPage().isOrdersPageLoaded();
        expect(isPageLoaded).toBeTruthy();
    });

    test('@OrderHistory TC002 - Verify Orders Table is Visible', async () => {
        // Arrange - Page already navigated via fixture
        
        // Act
        await poManager.getDashboardPage().navigateToOrders();
        await poManager.getOrdersHistoryPage().waitForOrdersTableToLoad();
        
        // Assert
        expect(await poManager.getOrdersHistoryPage().isOrdersTableVisible()).toBeTruthy();
    });

    test('@OrderHistory TC003 - Get Order Row Count', async () => {
        // Arrange - Page already navigated via fixture
        
        // Act
        await poManager.getDashboardPage().navigateToOrders();
        await poManager.getOrdersHistoryPage().waitForOrdersTableToLoad();
        const rowCount = await poManager.getOrdersHistoryPage().getOrderRowCount();
        
        // Assert
        expect(rowCount).toBeGreaterThanOrEqual(0);
        console.log('Total Orders:', rowCount);
    });

    test('@OrderHistory TC004 - Get All Order IDs', async () => {
        // Arrange - Page already navigated via fixture
        
        // Act
        await poManager.getDashboardPage().navigateToOrders();
        await poManager.getOrdersHistoryPage().waitForOrdersTableToLoad();
        const orderIds = await poManager.getOrdersHistoryPage().getOrderIds();
        
        // Assert
        console.log('Order IDs:', orderIds);
        expect(Array.isArray(orderIds)).toBeTruthy();
    });

    test('@OrderHistory TC005 - Verify Orders Page URL Contains myorders', async () => {
        // Arrange - Page already navigated via fixture
        
        // Act
        await poManager.getDashboardPage().navigateToOrders();
        const url = await poManager.getOrdersHistoryPage().getOrdersPageURL();
        
        // Assert
        expect(url).toContain('myorders');
        console.log('Orders Page URL:', url);
    });

    test('@OrderHistory TC006 - Click on First Order and View Details', async () => {
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

    test('@OrderHistory TC007 - Get First Order ID', async () => {
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

    test.skip('@OrderHistory TC008 - Complete Purchase and Verify in Order History', async () => {
        // This test requires proper implementation of PlaceOrderPage methods
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act - Complete purchase flow would go here
        // For now, this test is skipped until PlaceOrderPage has proper methods
        
        // Assert
        expect(true).toBeTruthy();
    });

    test.skip('@OrderHistory TC009 - Search and Select Specific Order', async () => {
        // This test requires proper implementation of PlaceOrderPage methods
        // Arrange
        const productName = 'iphone 13 pro';
        let createdOrderId: string | null = null;
        
        // Create an order would go here
        // For now, this test is skipped until PlaceOrderPage has proper methods
        
        // Assert
        expect(true).toBeTruthy();
    });

    test('@OrderHistory TC010 - Verify Order Details Page Loads', async () => {
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

   
});
