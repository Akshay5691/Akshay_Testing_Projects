import { chromium, FullConfig } from '@playwright/test';

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
        console.log('Global Setup: Performing login...');
        
        // Perform login
        await page.goto('https://rahulshettyacademy.com/client');
        await page.locator('#userEmail').fill('rahulshetty@gmail.com');
        await page.locator('#userPassword').fill('Iamking@000');
        await page.locator("[value='Login']").click();
        await page.waitForLoadState('networkidle');
        
        console.log('Global Setup: Saving session state...');
        // Save session state
        await context.storageState({ path: 'state.json' });
        
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
