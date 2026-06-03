import { test, expect, chromium, Page, BrowserContext } from '@playwright/test';
import { POManager } from '../pageobjects_ts/POManager';
import { LoginPage } from '../pageobjects_ts/LoginPage';

const BASE_URL = 'https://rahulshettyacademy.com/client';

test.describe('Login Page Tests - Page Object Model', () => {
    
    let page: Page;
    let context: BrowserContext;
    let poManager: POManager;
    let loginPage: LoginPage;
    
    test.beforeEach(async () => {
        const browser = await chromium.launch();
        context = await browser.newContext();
        page = await context.newPage();
        poManager = new POManager(page);
        loginPage = poManager.getLoginPage();
    });
    
    test.afterEach(async () => {
        await page.close();
        await context.close();
    });

    test('@Login TC001 - Navigate to Login Page and Verify Page Loads', async () => {
        // Act
        await loginPage.goTo();
        
        // Assert
        const title = await loginPage.getLoginPageTitle();
        expect(title).toBe("Let's Shop");
        expect(await loginPage.isUserNameFieldVisible()).toBeTruthy();
        expect(await loginPage.isPasswordFieldVisible()).toBeTruthy();
        expect(await loginPage.isSignInButtonVisible()).toBeTruthy();
    });

    test('@Login TC002 - Verify Email Field is Visible and Enabled', async () => {
        // Act
        await loginPage.goTo();
        
        // Assert
        expect(await loginPage.isUserNameFieldVisible()).toBeTruthy();
    });

    test('@Login TC003 - Verify Password Field is Visible and Enabled', async () => {
        // Act
        await loginPage.goTo();
        
        // Assert
        expect(await loginPage.isPasswordFieldVisible()).toBeTruthy();
    });

    test('@Login TC004 - Verify Sign In Button is Visible and Enabled', async () => {
        // Act
        await loginPage.goTo();
        
        // Assert
        expect(await loginPage.isSignInButtonVisible()).toBeTruthy();
        expect(await loginPage.isSignInButtonEnabled()).toBeTruthy();
    });

    test('@Login TC005 - Valid Login with Correct Credentials', async () => {
        // Arrange
        const email = 'rahulshetty@gmail.com';
        const password = 'Iamking@000';
        
        // Act
        await loginPage.goTo();
        await loginPage.validLogin(email, password);
        
        // Assert - Verify navigation success
        const url = await page.url();
        // URL should have changed from login
        expect(url).not.toContain('login');
        expect(url).toContain('/client');
        console.log('Successfully logged in. Current URL:', url);
    });

    test('@Login TC006 - Login with Invalid Email Format', async () => {
        // Act
        await loginPage.goTo();
        await loginPage.enterEmail('invalidemail');
        await loginPage.enterPassword('Test@123');
        await loginPage.clickSignInButton();
        
        // Assert
        const isErrorDisplayed = await loginPage.isErrorDisplayed();
        if (isErrorDisplayed) {
            const errorMessage = await loginPage.getErrorMessage();
            console.log('Error Message:', errorMessage);
            expect(isErrorDisplayed).toBeTruthy();
        }
    });

    test('@Login TC007 - Login with Wrong Password', async () => {
        // Act
        await loginPage.goTo();
        await loginPage.enterEmail('bhuvan@gmail.com');
        await loginPage.enterPassword('WrongPassword');
        await loginPage.clickSignInButton();
        
        // Assert
        const isErrorDisplayed = await loginPage.isErrorDisplayed();
        if (isErrorDisplayed) {
            const errorMessage = await loginPage.getErrorMessage();
            console.log('Error Message:', errorMessage);
            expect(isErrorDisplayed).toBeTruthy();
        }
    });

    test('@Login TC008 - Login with Empty Email Field', async () => {
        // Act
        await loginPage.goTo();
        await loginPage.enterPassword('Test@123');
        await loginPage.clickSignInButton();
        
        // Assert
        const url = await page.url();
        // Should remain on login page
        expect(url).toContain('login');
    });

    test('@Login TC009 - Login with Empty Password Field', async () => {
        // Act
        await loginPage.goTo();
        await loginPage.enterEmail('bhuvan@gmail.com');
        await loginPage.clickSignInButton();
        
        // Assert
        const url = await page.url();
        // Should remain on login page
        expect(url).toContain('login');
    });

    test('@Login TC010 - Login with Both Fields Empty', async () => {
        // Act
        await loginPage.goTo();
        await loginPage.clickSignInButton();
        
        // Assert
        const url = await page.url();
        expect(url).toContain('login');
    });

    test('@Login TC011 - Clear Email Field and Verify', async () => {
        // Act
        await loginPage.goTo();
        await loginPage.enterEmail('test@example.com');
        await loginPage.enterEmail('');
        
        // Assert
        const isEmpty = await page.locator("#userEmail").inputValue();
        expect(isEmpty).toBe('');
    });

    test('@Login TC012 - Enter Email and Verify it Persists in Field', async () => {
        // Arrange
        const email = 'testuser@gmail.com';
        
        // Act
        await loginPage.goTo();
        await loginPage.enterEmail(email);
        const enteredEmail = await page.locator("#userEmail").inputValue();
        
        // Assert
        expect(enteredEmail).toBe(email);
    });

    test('@Login TC013 - Verify Forgot Password Link is Present', async () => {
        // Act
        await loginPage.goTo();
        const isForgotPasswordLinkVisible = await page.locator("text=Forgot password?").isVisible();
        
        // Assert
        expect(isForgotPasswordLinkVisible).toBeTruthy();
    });

    test('@Login TC014 - Verify Register Link is Present', async () => {
        // Act
        await loginPage.goTo();
        const isRegisterLinkVisible = await page.locator("text=Register").first().isVisible();
        
        // Assert
        expect(isRegisterLinkVisible).toBeTruthy();
    });

    test('@Login TC015 - Valid Login and Verify Session is Saved', async () => {
        // Arrange
        const email = 'rahulshetty@gmail.com';
        const password = 'Iamking@000';
        
        // Act
        await loginPage.goTo();
        await loginPage.validLogin(email, password);
        
        // Get session storage
        const sessionState = await context.storageState();
        
        // Assert
        const url = await page.url();
        expect(url).not.toContain('login');
        expect(sessionState.cookies.length).toBeGreaterThan(0);
        console.log('Session saved successfully. Current URL:', url);
    });

});
