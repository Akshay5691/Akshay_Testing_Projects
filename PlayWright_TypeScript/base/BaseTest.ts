import { Page, BrowserContext } from '@playwright/test';

export class BaseTest {
    page: Page;
    context?: BrowserContext;

    // Configuration
    protected baseURL = 'https://rahulshettyacademy.com/client';
    protected loginURL = 'https://rahulshettyacademy.com/client';
    protected defaultUsername = 'rahulshetty@gmail.com';
    protected defaultPassword = 'Iamking@000';
    protected sessionStoragePath = 'state.json';

    constructor(page: Page, context?: BrowserContext) {
        this.page = page;
        this.context = context;
    }

    /**
     * Navigate to the base URL
     */
    async navigateToBaseURL(): Promise<void> {
        await this.page.goto(this.baseURL);
        await this.page.waitForLoadState('domcontentloaded');
    }

    /**
     * Navigate to login page
     */
    async navigateToLoginPage(): Promise<void> {
        await this.page.goto(this.loginURL);
        await this.page.waitForLoadState('domcontentloaded');
    }

    /**
     * Perform login with credentials
     */
    async login(username: string, password: string): Promise<void> {
        await this.navigateToLoginPage();
        
        // Fill username
        await this.page.locator('#userEmail').fill(username);
        
        // Fill password
        await this.page.locator('#userPassword').fill(password);
        
        // Click sign in button
        await this.page.locator("[value='Login']").click();
        
        // Wait for navigation to complete
        await this.page.waitForLoadState('networkidle');
    }

    /**
     * Perform login with default credentials
     */
    async loginWithDefaultCredentials(): Promise<void> {
        await this.login(this.defaultUsername, this.defaultPassword);
    }

    /**
     * Save session state to file
     */
    async saveSessionState(filePath: string = this.sessionStoragePath): Promise<void> {
        if (this.context) {
            await this.context.storageState({ path: filePath });
        }
    }

    /**
     * Save session state from context
     */
    async saveSessionStateFromPage(filePath: string = this.sessionStoragePath): Promise<void> {
        await this.page.context().storageState({ path: filePath });
    }

    /**
     * Perform complete login and save session
     */
    async loginAndSaveSession(
        username: string = this.defaultUsername,
        password: string = this.defaultPassword,
        filePath: string = this.sessionStoragePath
    ): Promise<void> {
        await this.login(username, password);
        await this.saveSessionStateFromPage(filePath);
    }

    /**
     * Verify user is logged in by checking for dashboard elements
     */
    async verifyLoggedIn(): Promise<boolean> {
        try {
            // Wait for dashboard elements to be visible
            await this.page.waitForTimeout(2000);
            const dashboardElement = await this.page.locator('[routerlink*="cart"], .sidebar, .navbar').isVisible().catch(() => false);
            return dashboardElement;
        } catch {
            return false;
        }
    }

    /**
     * Wait for dashboard to load
     */
    async waitForDashboardToLoad(): Promise<void> {
        await this.page.waitForLoadState('networkidle');
        await this.page.locator(".card-body").first().waitFor({ timeout: 10000 }).catch(() => {});
    }

    /**
     * Get current page URL
     */
    async getCurrentURL(): Promise<string> {
        return this.page.url();
    }

    /**
     * Get page title
     */
    async getPageTitle(): Promise<string> {
        return await this.page.title();
    }

    /**
     * Logout functionality (if needed)
     */
    async logout(): Promise<void> {
        // Look for logout button - adjust selector based on your app
        const logoutButton = this.page.locator('button:has-text("Logout"), a:has-text("Logout"), [class*="logout"]');
        
        if (await logoutButton.isVisible().catch(() => false)) {
            await logoutButton.click();
            await this.page.waitForLoadState('networkidle');
        }
    }

    /**
     * Close the page
     */
    async closePage(): Promise<void> {
        await this.page.close();
    }

    /**
     * Close browser context
     */
    async closeContext(): Promise<void> {
        if (this.context) {
            await this.context.close();
        }
    }

    /**
     * Take screenshot
     */
    async takeScreenshot(filePath: string): Promise<void> {
        await this.page.screenshot({ path: filePath });
    }

    /**
     * Handle alert/dialog
     */
    async acceptAlert(): Promise<void> {
        this.page.on('dialog', dialog => dialog.accept());
    }

    /**
     * Dismiss alert/dialog
     */
    async dismissAlert(): Promise<void> {
        this.page.on('dialog', dialog => dialog.dismiss());
    }

    /**
     * Wait for specific time
     */
    async waitForTime(milliseconds: number): Promise<void> {
        await this.page.waitForTimeout(milliseconds);
    }

    /**
     * Check if element is visible
     */
    async isElementVisible(selector: string): Promise<boolean> {
        try {
            return await this.page.locator(selector).isVisible();
        } catch {
            return false;
        }
    }

    /**
     * Get element text content
     */
    async getElementText(selector: string): Promise<string | null> {
        try {
            return await this.page.locator(selector).textContent();
        } catch {
            return null;
        }
    }

    /**
     * Click element
     */
    async clickElement(selector: string): Promise<void> {
        await this.page.locator(selector).click();
    }

    /**
     * Fill input field
     */
    async fillInput(selector: string, value: string): Promise<void> {
        await this.page.locator(selector).fill(value);
    }

    /**
     * Get attribute value
     */
    async getAttributeValue(selector: string, attribute: string): Promise<string | null> {
        return await this.page.locator(selector).getAttribute(attribute);
    }

    /**
     * Clear input field
     */
    async clearInput(selector: string): Promise<void> {
        await this.page.locator(selector).clear();
    }

    /**
     * Scroll to element
     */
    async scrollToElement(selector: string): Promise<void> {
        await this.page.locator(selector).scrollIntoViewIfNeeded();
    }

    /**
     * Set viewport size
     */
    async setViewportSize(width: number, height: number): Promise<void> {
        await this.page.setViewportSize({ width, height });
    }

    /**
     * Stop navigation
     */
    async stopNavigation(): Promise<void> {
        await this.page.context().browser()?.close().catch(() => {});
    }

    /**
     * Reload page
     */
    async reloadPage(): Promise<void> {
        await this.page.reload();
        await this.page.waitForLoadState('networkidle');
    }
}

export default BaseTest;
