import { test as baseTest, Page, BrowserContext } from '@playwright/test';
import { POManager } from '../pageobjects_ts/POManager';
/*
// ============================================
// CONFIGURATION
// ============================================
const BASE_URL = 'https://rahulshettyacademy.com/client';
const SESSION_PATH = 'state.json';

// ============================================
// TEST DATA TYPE - Define what data tests use
// ============================================
interface TestDataForOrder {
    username: string;
    password: string;
    productName: string;
}

// ============================================
// TEST FIXTURES - Custom tools available to tests
// ============================================
interface TestFixtures {
    testDataForOrder: TestDataForOrder;          // Test constants (username, password, etc.)
    page: Page;                                   // Browser page with auto-login
    poManager: POManager;                         // Page Object Manager for interacting with UI
}

// ============================================
// CUSTOM TEST SETUP - Simplified without BaseTest/BaseSetup
// ============================================
export const customTest = baseTest.extend<TestFixtures & { testDataForOrder: TestDataForOrder }>({
    
    // -------- TEST DATA --------
    // Provide default test data (username, password, product name)
    testDataForOrder: {
        username: "anshika@gmail.com",
        password: "Iamking@000",
        productName: "ADIDAS ORIGINAL"
    },

    // -------- PAGE FIXTURE --------
    // Use saved session from global-setup.ts
    page: async ({ browser }, use) => {
        // Use the session state created by global-setup.ts
        const context = await browser.newContext({ storageState: SESSION_PATH });
        const page = await context.newPage();
        
        // Navigate to app
        await page.goto(BASE_URL);
        await page.waitForLoadState('domcontentloaded');
        
        // Run test with this page
        await use(page);
        
        // Cleanup
        await context.close();
    },

    // -------- PO MANAGER FIXTURE --------
    // Provides all Page Object classes for UI interaction
    poManager: async ({ page }, use) => {
        const poManager = new POManager(page);
        await use(poManager);
    }*/
//});




