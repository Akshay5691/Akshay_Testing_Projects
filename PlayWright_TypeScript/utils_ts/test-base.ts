
import { test as baseTest, Page, BrowserContext } from '@playwright/test';
import { POManager } from '../pageobjects_ts/POManager';
import { BaseTest } from '../base/BaseTest';
import { BaseSetup } from '../base/BaseSetup';

const BASE_URL = 'https://rahulshettyacademy.com/client';
const SESSION_PATH = 'state.json';

interface TestDataForOrder {
    username: string;
    password: string;
    productName: string;
}

interface TestFixtures {
    testDataForOrder: TestDataForOrder;
    page: Page;
    poManager: POManager;
    webContext: BrowserContext;
    baseTest: BaseTest;
    baseSetup: BaseSetup;
}

export const customTest = baseTest.extend<TestFixtures & { testDataForOrder: TestDataForOrder }>({
    testDataForOrder: {
        username: "anshika@gmail.com",
        password: "Iamking@000",
        productName: "ADIDAS ORIGINAL"
    },

    // Custom fixture for page with automatic navigation and login
    page: async ({ browser }, use) => {
        // Try to use existing session, if not available create new one
        let context: BrowserContext;
        try {
            context = await browser.newContext({ storageState: SESSION_PATH });
        } catch {
            // Session doesn't exist, perform login
            const newContext = await browser.newContext();
            const loginPage = await newContext.newPage();
            
            // Perform login
            const baseTestInstance = new BaseTest(loginPage, newContext);
            await baseTestInstance.loginWithDefaultCredentials();
            await baseTestInstance.saveSessionStateFromPage(SESSION_PATH);
            
            await loginPage.close();
            await newContext.close();
            
            // Create new context with saved session
            context = await browser.newContext({ storageState: SESSION_PATH });
        }
        
        const page = await context.newPage();
        
        // Automatically navigate to base URL before test
        await page.goto(BASE_URL);
        await page.waitForLoadState('domcontentloaded');
        
        await use(page);
        
        await context.close();
    },

    // Custom fixture for POManager with automatic initialization
    poManager: async ({ page }, use) => {
        const poManager = new POManager(page);
        await use(poManager);
    },

    // Custom fixture for browser context
    webContext: async ({ browser }, use) => {
        let context: BrowserContext;
        try {
            context = await browser.newContext({ storageState: SESSION_PATH });
        } catch {
            context = await browser.newContext();
        }
        await use(context);
        await context.close();
    },

    // BaseTest fixture for direct access to base functionality
    baseTest: async ({ page }, use) => {
        const baseTestInstance = new BaseTest(page);
        await use(baseTestInstance);
    },

    // BaseSetup fixture for setup/teardown operations
    baseSetup: async ({ browser }, use) => {
        // Try to use existing session
        let baseSetupInstance: BaseSetup;
        try {
            baseSetupInstance = await BaseSetup.setupTestWithExistingSession(browser, SESSION_PATH);
        } catch {
            // If session doesn't exist, create with login
            baseSetupInstance = await BaseSetup.setupTestWithLogin(browser);
        }
        
        await use(baseSetupInstance);
        
        await baseSetupInstance.teardown();
    }
});




