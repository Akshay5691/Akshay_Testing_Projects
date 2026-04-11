import {test, expect,Locator,Page} from '@playwright/test';

export class DashboardPage
{
    // Locators
    products : Locator;
    productsText : Locator;
    cart : Locator;
    orders : Locator;
    page : Page;
    addToCartButtons: Locator;
    productPrice: Locator;
    productDescription: Locator;
    dashboardTitle: Locator;
    searchInput: Locator;
    toastMessage: Locator;
    loadingSpinner: Locator;

    constructor(page:Page)
    {
        this.page = page;
        this.products = page.locator(".card-body");
        this.productsText = page.locator(".card-body b");
        this.cart = page.locator("[routerlink*='cart']");
        this.orders = page.locator("button[routerlink*='myorders']");
        this.addToCartButtons = page.locator("text= Add To Cart");
        this.productPrice = page.locator(".card-body .price");
        this.productDescription = page.locator(".card-body p");
        this.dashboardTitle = page.locator("text=Dashboard, Orders");
        this.searchInput = page.locator(".search input, [placeholder*='search']");
        this.toastMessage = page.locator(".toast-container, .toastr");
        this.loadingSpinner = page.locator(".spinner, [class*='loader']");
    }

    async waitForDashboardToLoad()
    {
        await this.page.waitForLoadState('networkidle');
        await this.products.first().waitFor({ timeout: 10000 }).catch(() => {});
    }

    async getProductCount(): Promise<number>
    {
        return await this.products.count();
    }

    async getAllProductNames(): Promise<string[]>
    {
        return await this.productsText.allTextContents();
    }

    async searchProductAddCart(productName: string)
    {
        const titles = await this.productsText.allTextContents();
        console.log("Available products:", titles);
        const count = await this.products.count();
        
        for(let i = 0; i < count; ++i)
        {
            const productNameText = await this.products.nth(i).locator("b").textContent();
            if(productNameText === productName)
            {
                await this.products.nth(i).locator("text= Add To Cart").click();
                await this.page.waitForLoadState('networkidle');
                break;
            }
        }
    }

    async addProductToCartByIndex(index: number)
    {
        await this.addToCartButtons.nth(index).click();
        await this.page.waitForLoadState('networkidle');
    }

    async navigateToOrders()
    {
        await this.orders.click();
        await this.page.waitForLoadState('networkidle');
    }

    async navigateToCart()
    {
        await this.cart.click();
        await this.page.waitForLoadState('networkidle');
    }

    async getProductByName(productName: string): Promise<Locator>
    {
        return this.page.locator(`h3:has-text('${productName}')`);
    }

    async getProductLocatorByIndex(index: number): Promise<Locator>
    {
        return this.products.nth(index);
    }

    async getProductNameByIndex(index: number): Promise<string | null>
    {
        return await this.products.nth(index).locator("b").textContent();
    }

    async isAddToCartButtonVisible(): Promise<boolean>
    {
        return await this.addToCartButtons.first().isVisible();
    }

    async getAddToCartButtonCount(): Promise<number>
    {
        return await this.addToCartButtons.count();
    }

    async getToastMessage(): Promise<string | null>
    {
        try {
            await this.toastMessage.waitFor({ timeout: 3000 });
            return await this.toastMessage.textContent();
        } catch {
            return null;
        }
    }

    async isProductVisible(productName: string): Promise<boolean>
    {
        const productLocator = await this.getProductByName(productName);
        try {
            return await productLocator.isVisible();
        } catch {
            return false;
        }
    }

    async waitForToastToDisappear()
    {
        try {
            await this.toastMessage.waitFor({ state: 'hidden', timeout: 5000 });
        } catch {
            // Toast may not appear
        }
    }

    async getDashboardURL(): Promise<string>
    {
        return this.page.url();
    }

    async verifyProductsDisplayed(expectedCount: number): Promise<boolean>
    {
        const actualCount = await this.getProductCount();
        return actualCount >= expectedCount;
    }

}