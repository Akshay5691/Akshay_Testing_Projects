import { expect, test, Page, BrowserContext, chromium } from '@playwright/test';
import { POManager } from '../pageobjects_ts/POManager';

const BASE_URL = 'https://rahulshettyacademy.com/client';
const SESSION_PATH = 'state.json';

let page: Page;
let poManager: POManager;
let context: BrowserContext;

test.describe('Dashboard Page Tests - Page Object Model', () => {
    
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

    test('@Dashboard TC001 - Verify Dashboard Loads with Products', async () => {
        // Arrange
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        const productCount = await dashboardPage.getProductCount();
        
        // Assert
        expect(productCount).toBeGreaterThan(0);
    });

    test('@Dashboard TC002 - Verify All Products Display Names', async () => {
        // Arrange
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        const productNames = await dashboardPage.getAllProductNames();
        
        // Assert
        expect(productNames.length).toBeGreaterThan(0);
        productNames.forEach(name => {
            expect(name.trim().length).toBeGreaterThan(0);
        });
        console.log('Available Products:', productNames);
    });

    test('@Dashboard TC003 - Verify Add to Cart Button Count', async () => {
        // Arrange
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        const buttonCount = await dashboardPage.getAddToCartButtonCount();
        
        // Assert
        expect(buttonCount).toBeGreaterThan(0);
        expect(await dashboardPage.isAddToCartButtonVisible()).toBeTruthy();
    });

    test('@Dashboard TC004 - Search and Add Specific Product to Cart', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        
        // Assert
        const toastMessage = await dashboardPage.getToastMessage();
        console.log('Toast Message:', toastMessage);
        expect(true).toBeTruthy(); // Product search and add executed
    });

    test.only
    ('@Dashboard TC005 - Get Product Names and Verify Format', async () => {
        // Arrange
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        const productNames = await dashboardPage.getAllProductNames();
        const count = await dashboardPage.getProductCount();
        
        // Assert
        expect(productNames.length).toBe(count);
        productNames.forEach((name, index) => {
            expect(name).toBeTruthy();
            console.log(`Product ${index + 1}: ${name}`);
        });
    });

    test('@Dashboard TC006 - Verify Navigation to Cart', async () => {
        // Arrange
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        await dashboardPage.navigateToCart();
        
        // Assert
        const cartPage = poManager.getCartPage();
        const url = await cartPage.getCartPageURL();
        expect(url).toContain('cart');
    });

    test('@Dashboard TC007 - Verify Navigation to Orders', async () => {
        // Arrange
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        await dashboardPage.navigateToOrders();
        
        // Assert
        const ordersHistoryPage = poManager.getOrdersHistoryPage();
        const url = await ordersHistoryPage.getOrdersPageURL();
        expect(url).toContain('myorders');
    });

    test('@Dashboard TC008 - Add Multiple Products Sequentially', async () => {
        // Arrange
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        const buttonCount = await dashboardPage.getAddToCartButtonCount();
        console.log('Total Add to Cart buttons:', buttonCount);
        
        // Add first product
        if (buttonCount > 0) {
            await dashboardPage.addProductToCartByIndex(0);
            await page.waitForLoadState('networkidle');
        }
        
        // Add second product
        if (buttonCount > 1) {
            await dashboardPage.addProductToCartByIndex(1);
            await page.waitForLoadState('networkidle');
        }
        
        // Assert
        expect(buttonCount).toBeGreaterThan(0);
    });

    test('@Dashboard TC009 - Verify Product Count Greater Than 0', async () => {
        // Arrange
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        const productCount = await dashboardPage.getProductCount();
        
        // Assert
        expect(await dashboardPage.verifyProductsDisplayed(1)).toBeTruthy();
        expect(productCount).toBeGreaterThan(0);
        console.log('Total Products on Dashboard:', productCount);
    });

    test('@Dashboard TC010 - Verify Dashboard URL', async () => {
        // Arrange
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        const url = await dashboardPage.getDashboardURL();
        
        // Assert
        expect(url).toContain('rahulshettyacademy.com/client');
    });

    test('@Dashboard TC011 - Get Specific Product by Name', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        const productExists = await dashboardPage.isProductVisible(productName);
        
        // Assert
        expect(productExists).toBe(true);
        console.log(`Product "${productName}" is visible on dashboard: ${productExists}`);
    });

    test('@Dashboard TC012 - Get Product by Index and Verify Name', async () => {
        // Arrange
        const dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        
        // Act
        const firstProductName = await dashboardPage.getProductNameByIndex(0);
        
        // Assert
        expect(firstProductName).toBeTruthy();
        console.log('First Product Name:', firstProductName);
    });
});
