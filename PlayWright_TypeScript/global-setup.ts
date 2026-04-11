import { chromium, FullConfig } from '@playwright/test';
import { BaseTest } from './base/BaseTest';

/**
 * Global setup - runs once before all tests
 * This ensures session state is created and available for all tests
 */
async function globalSetup(config: FullConfig) {
    console.log('Global Setup: Starting login and session creation...');
    
    const browser = await chromium.launch();
    const context = await browser.newContext();
    const page = await context.newPage();
    
    try {
        const baseTest = new BaseTest(page, context);
        
        console.log('Global Setup: Performing login...');
        // Perform login with default credentials
        await baseTest.loginWithDefaultCredentials();
        
        console.log('Global Setup: Saving session state...');
        // Save session state
        await baseTest.saveSessionStateFromPage('state.json');
        
        console.log('Global Setup: Login successful and session saved!');
    } catch (error) {
        console.error('Global Setup: Login failed!', error);
        throw error;
    } finally {
        await page.close();
        await context.close();
        await browser.close();
    }
}

export default globalSetup;
