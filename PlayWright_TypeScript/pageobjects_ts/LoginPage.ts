import {test, expect,Locator,Page} from '@playwright/test';

export class LoginPage {
    // Locators
    signInbutton : Locator;
    userName :Locator;
    password : Locator;
    page : Page;
    pageTitle: Locator;
    errorMessage: Locator;
    termsCheckbox: Locator;
    forgotPasswordLink: Locator;

    constructor(page:Page)
    {
        this.page = page;
        this.signInbutton = page.locator("[value='Login']");
        this.userName = page.locator("#userEmail");
        this.password = page.locator("#userPassword");
        this.errorMessage = page.locator("[role='alert']");
        this.termsCheckbox = page.locator("input[type='checkbox']");
        this.forgotPasswordLink = page.locator("text=Forgot password");
        this.pageTitle = page.locator("h1, .login-title");
    }

    async goTo()
    {
        await this.page.goto("https://rahulshettyacademy.com/client");
        await this.page.waitForLoadState('domcontentloaded');
    }

    async validLogin(username:string, password:string)
    {
        await this.userName.fill(username);
        await this.password.fill(password);
        await this.signInbutton.click();
        await this.page.waitForLoadState('networkidle');
    }

    async getLoginPageTitle(): Promise<string>
    {
        return await this.page.title();
    }

    async isUserNameFieldVisible(): Promise<boolean>
    {
        return await this.userName.isVisible();
    }

    async isPasswordFieldVisible(): Promise<boolean>
    {
        return await this.password.isVisible();
    }

    async isSignInButtonVisible(): Promise<boolean>
    {
        return await this.signInbutton.isVisible();
    }

    async isSignInButtonEnabled(): Promise<boolean>
    {
        return await this.signInbutton.isEnabled();
    }

    async enterEmail(email: string)
    {
        await this.userName.fill(email);
    }

    async enterPassword(pwd: string)
    {
        await this.password.fill(pwd);
    }

    async clickSignInButton()
    {
        await this.signInbutton.click();
    }

    async getErrorMessage(): Promise<string | null>
    {
        try {
            await this.errorMessage.waitFor({ timeout: 5000 });
            return await this.errorMessage.textContent();
        } catch {
            return null;
        }
    }

    async isErrorDisplayed(): Promise<boolean>
    {
        try {
            return await this.errorMessage.isVisible();
        } catch {
            return false;
        }
    }

    async getPasswordFieldType(): Promise<string | null>
    {
        return await this.password.getAttribute('type');
    }

    async getLoginPageURL(): Promise<string>
    {
        return this.page.url();
    }

    async waitForLoginPageToLoad()
    {
        await this.page.waitForLoadState('networkidle');
        await this.userName.waitFor();
     
    }
}