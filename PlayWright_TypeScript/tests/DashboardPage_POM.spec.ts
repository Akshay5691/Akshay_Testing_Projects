import { test, expect } from '../base/test-base';
import { POManager } from '../pageobjects_ts/POManager';
import { DashboardPage } from '../pageobjects_ts/DashboardPage';

test.describe('Dashboard Page Tests - Page Object Model', () => {

    let poManager: POManager;
    let dashboardPage: DashboardPage;

    test.beforeEach(async ({ page }) => {
        poManager = (page as any).poManager;
        if (!poManager) throw new Error('poManager not initialized in test-base beforeEach');
        dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
    });

    test('@Dashboard TC001 - Verify Dashboard Loads with Products', async () => {
        // Act
        const productCount = await dashboardPage.getProductCount();
        
        // Assert
        expect(productCount).toBeGreaterThan(0);
    });

    test('@Dashboard TC002 - Verify All Products Display Names', async () => {
        // Act
        const productNames = await dashboardPage.getAllProductNames();
        
        // Assert
        expect(productNames.length).toBeGreaterThan(0);
        productNames.forEach(name => {
            expect(name.trim().length).toBeGreaterThan(0);
        });
        console.log('Available Products:', productNames);
        await dashboardPage.printAdidasShoeDetails();
    });

    test('@Dashboard TC003 - Verify Add to Cart Button Count', async () => {
        // Act
        const buttonCount = await dashboardPage.getAddToCartButtonCount();
        
        // Assert
        expect(buttonCount).toBeGreaterThan(0);
        expect(await dashboardPage.isAddToCartButtonVisible()).toBeTruthy();
    });

    test('@Dashboard TC004 - Search and Add Specific Product to Cart', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        
        // Assert
        const toastMessage = await dashboardPage.getToastMessage();
        console.log('Toast Message:', toastMessage);
        await dashboardPage.waitForToastToDisappear();
    });

    test('@Dashboard TC005 - Get Product Names and Verify Format', async () => {
        // Act
        const productNames = await dashboardPage.getAllProductNames();
        const count = await dashboardPage.getProductCount();
        
        // Assert
        expect(productNames.length).toBe(count);
       for (let i = 0; i < productNames.length; i++) {
        expect(productNames[i]).toBeTruthy();
        console.log(`Product ${i + 1}: ${productNames[i]}`);
    }
    });

    test.only('@Dashboard TC006 - Verify Navigation to Cart', async () => {
        // Act
        await dashboardPage.navigateToCart();
        
        // Assert
        const cartPage = poManager.getCartPage();
        const url = await cartPage.getCartPageURL();
        expect(url).toContain('cart');
    });

    test.only('@Dashboard TC007 - Verify Navigation to Orders', async () => {
        // Act
        await dashboardPage.navigateToOrders();
        
        // Assert
        const ordersHistoryPage = poManager.getOrdersHistoryPage();
        const url = await ordersHistoryPage.getOrdersPageURL();
        expect(url).toContain('myorders');
    });

    test('@Dashboard TC008 - Add Multiple Products Sequentially', async ({ page }) => {
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
        // Act
        const productCount = await dashboardPage.getProductCount();
        
        // Assert
        expect(await dashboardPage.verifyProductsDisplayed(1)).toBeTruthy();
        expect(productCount).toBeGreaterThan(0);
        console.log('Total Products on Dashboard:', productCount);
    });

    test('@Dashboard TC010 - Verify Dashboard URL', async () => {
        // Act
        const url = await dashboardPage.getDashboardURL();
        
        // Assert
        expect(url).toContain('rahulshettyacademy.com/client');
    });

    test('@Dashboard TC011 - Get Specific Product by Name', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        const productExists = await dashboardPage.isProductVisible(productName);
        
        // Assert
        expect(productExists).toBe(true);
        console.log(`Product "${productName}" is visible on dashboard: ${productExists}`);
    });

    test('@Dashboard TC012 - Get Product by Index and Verify Name', async () => {
        // Act
        const firstProductName = await dashboardPage.getProductNameByIndex(0);
        
        // Assert
        expect(firstProductName).toBeTruthy();
        console.log('First Product Name:', firstProductName);
    });

    test.only('@Dashboard TC013 - Verify Search Product', async () => {
        // Act
        await dashboardPage.searchProduct('ZARA');
        const zaraCoatName = await dashboardPage.getZaraCoat();
        
        // Assert
        console.log('Searched Product Name:', zaraCoatName);
        expect(zaraCoatName).toContain('ZARA COAT 3');
    });

});
