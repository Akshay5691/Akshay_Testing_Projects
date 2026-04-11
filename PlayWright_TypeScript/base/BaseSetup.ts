import { Page, BrowserContext, Browser } from '@playwright/test';
import { BaseTest } from './BaseTest';

export class BaseSetup extends BaseTest {
    private static instance: BaseSetup;

    constructor(page: Page, context?: BrowserContext) {
        super(page, context);
    }

    /**
     * Initialize browser context with session state
     */
    static async initializeWithSession(browser: Browser, sessionPath: string = 'state.json'): Promise<BrowserContext> {
        try {
            return await browser.newContext({ storageState: sessionPath });
        } catch {
            // If session file doesn't exist, create new context
            return await browser.newContext();
        }
    }

    /**
     * Setup: Create new context and perform login
     */
    static async setupTestWithLogin(browser: Browser, username?: string, password?: string): Promise<BaseSetup> {
        const context = await browser.newContext();
        const page = await context.newPage();
        
        const baseSetup = new BaseSetup(page, context);
        
        // Perform login
        if (username && password) {
            await baseSetup.login(username, password);
        } else {
            await baseSetup.loginWithDefaultCredentials();
        }
        
        // Save session
        await baseSetup.saveSessionState();
        
        return baseSetup;
    }

    /**
     * Setup: Create new context with existing session
     */
    static async setupTestWithExistingSession(browser: Browser, sessionPath: string = 'state.json'): Promise<BaseSetup> {
        const context = await this.initializeWithSession(browser, sessionPath);
        const page = await context.newPage();
        
        return new BaseSetup(page, context);
    }

    /**
     * Teardown: Close page and context
     */
    async teardown(): Promise<void> {
        await this.closePage();
        await this.closeContext();
    }

    /**
     * Get BaseTest instance
     */
    static getInstance(page: Page, context?: BrowserContext): BaseTest {
        return new BaseTest(page, context);
    }

    /**
     * Compare expected vs actual
     */
    assertExpectedVsActual(expected: any, actual: any): boolean {
        return expected === actual;
    }

    /**
     * Check if response is successful
     */
    isResponseSuccessful(statusCode: number): boolean {
        return statusCode >= 200 && statusCode < 300;
    }

    /**
     * Create test report data
     */
    createTestReport(testName: string, passed: boolean, duration: number, message?: string): any {
        return {
            testName,
            passed,
            duration,
            timestamp: new Date().toISOString(),
            message: message || (passed ? 'Test passed' : 'Test failed')
        };
    }

    /**
     * Verify element is clickable
     */
    async isElementClickable(selector: string): Promise<boolean> {
        try {
            const element = this.page.locator(selector);
            return await element.isVisible() && await element.isEnabled();
        } catch {
            return false;
        }
    }

    /**
     * Wait for element and interact
     */
    async waitAndClick(selector: string, timeout: number = 5000): Promise<void> {
        await this.page.locator(selector).waitFor({ timeout });
        await this.clickElement(selector);
    }

    /**
     * Wait for element and fill
     */
    async waitAndFill(selector: string, value: string, timeout: number = 5000): Promise<void> {
        await this.page.locator(selector).waitFor({ timeout });
        await this.fillInput(selector, value);
    }

    /**
     * Get all matching elements count
     */
    async getElementCount(selector: string): Promise<number> {
        return await this.page.locator(selector).count();
    }

    /**
     * Check if multiple elements are visible
     */
    async areMultipleElementsVisible(selector: string, expectedCount: number): Promise<boolean> {
        const count = await this.getElementCount(selector);
        return count >= expectedCount;
    }

    /**
     * Get all matching elements text
     */
    async getAllElementsText(selector: string): Promise<string[]> {
        return await this.page.locator(selector).allTextContents();
    }

    /**
     * Verify page contains text
     */
    async verifyPageContainsText(text: string): Promise<boolean> {
        try {
            await this.page.getByText(text).waitFor({ timeout: 5000 });
            return true;
        } catch {
            return false;
        }
    }

    /**
     * Get page error logs
     */
    async getPageErrorLogs(): Promise<string[]> {
        const errors: string[] = [];
        this.page.on('console', msg => {
            if (msg.type() === 'error') {
                errors.push(msg.text());
            }
        });
        return errors;
    }

    /**
     * Switch to iframe
     */
    async switchToIframe(iframeSelector: string): Promise<any> {
        const frame = this.page.frameLocator(iframeSelector);
        return frame;
    }

    /**
     * Handle network timeout
     */
    async handleNetworkTimeout(callback: () => Promise<void>, timeout: number = 30000): Promise<void> {
        try {
            await Promise.race([
                callback(),
                new Promise((_, reject) => setTimeout(() => reject(new Error('Network timeout')), timeout))
            ]);
        } catch (error) {
            console.log('Network operation timed out:', error);
        }
    }

    /**
     * Navigate and wait for navigation complete
     */
    async navigateAndWait(url: string): Promise<void> {
        await Promise.all([
            this.page.waitForNavigation(),
            this.page.goto(url)
        ]);
    }

    /**
     * Get response from navigation
     */
    async getResponseFromNavigation(url: string): Promise<any> {
        const responses: any = [];
        
        this.page.on('response', response => {
            responses.push({
                url: response.url(),
                status: response.status(),
                statusText: response.statusText()
            });
        });

        await this.page.goto(url);
        return responses;
    }
}

export default BaseSetup;
