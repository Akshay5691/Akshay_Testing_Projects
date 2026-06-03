import { test, Page, expect } from '@playwright/test';
import { POManager } from '../pageobjects_ts/POManager';
import { ActionsUtility } from '../utils_ts/BrowserActions';

// ============================================
// CONFIGURATION
// ============================================
const BASE_URL = 'https://rahulshettyacademy.com/client';

// ============================================
// TEST CREDENTIALS
// ============================================
const TEST_EMAIL = 'akshaythummanapally08@gmail.com';
const TEST_PASSWORD = 'akshay123';

// ============================================
// EXTEND PAGE WITH POManager
// ============================================
declare global {
    namespace PlaywrightTest {
        interface Page {
            poManager?: POManager;
        }
    }
}

// ============================================
// BEFORE EACH HOOK - Setup for every test
// ============================================
test.beforeEach(async ({ page }) => {
    // Navigate to the application
    await page.goto(BASE_URL);
    await page.waitForLoadState('domcontentloaded');

    

    // Initialize POManager
    const poManager = new POManager(page);
    (page as any).poManager = poManager;

    // Perform login with credentials
    const loginPage = poManager.getLoginPage();
    await loginPage.validLogin(TEST_EMAIL, TEST_PASSWORD);
    
    // Wait for navigation after login
    await page.waitForLoadState('domcontentloaded');
});

// ============================================
// EXPORTS
// ============================================
export { test, expect };




