import {test, expect,Locator,Page} from '@playwright/test';

export class CartPage
{
    // Locators
    cartProducts : Locator;
    productsText : Locator;
    cart : Locator;
    orders : Locator;
    checkoutButton: Locator;
    page : Page;
    cartItemsList: Locator;
    cartTotal: Locator;
    deleteButton: Locator;
    quantityInput: Locator;
    cartContainer: Locator;
    emptyCartMessage: Locator;
    continueShoppingButton: Locator;
    cartHeader: Locator;

    constructor(page: Page)
    {
        this.page = page;
        this.cartProducts = page.locator("div li").first();
        this.productsText = page.locator(".card-body b");
        this.cart = page.locator("[routerlink*='cart']");
        this.orders = page.locator("button[routerlink*='myorders']");
        this.checkoutButton = page.locator("text=Checkout");
        this.cartItemsList = page.locator("div li");
        this.cartTotal = page.locator(".subtotal, .total, .price");
        this.deleteButton = page.locator("text=Delete, .delete-btn");
        this.quantityInput = page.locator(".quantity input, [type='number']");
        this.cartContainer = page.locator(".cart, [class*='cart-container']");
        this.emptyCartMessage = page.locator("text=Add product to the cart, Your cart is empty");
        this.continueShoppingButton = page.locator("text=Continue Shopping");
        this.cartHeader = page.locator("h2, .cart-title");
    }

    async waitForCartToLoad()
    {
        await this.page.waitForLoadState('networkidle');
        try {
            await this.cartProducts.waitFor({ timeout: 5000 });
        } catch {
            // Cart might be empty
        }
    }

    async verifyProductIsDisplayed(productName: string)
    {
        try {
            await this.cartProducts.waitFor();
            const bool = await this.getProductLocator(productName).isVisible();
            expect(bool).toBeTruthy();
        } catch {
            throw new Error(`Product ${productName} not found in cart`);
        }
    }

    async VerifyProductIsDisplayed(productName: string)
    {
        await this.verifyProductIsDisplayed(productName);
    }

    async checkoutMethod()
    {
        await this.checkoutButton.click();
        await this.page.waitForLoadState('networkidle');
    }

    async checkout()
    {
        await this.checkoutButton.click();
        await this.page.waitForLoadState('networkidle');
    }

    async Checkout()
    {
        await this.checkoutButton.click();
        await this.page.waitForLoadState('networkidle');
    }

    getProductLocator(productName: string): Locator
    {
        return this.page.locator(`h3:has-text('${productName}')`);
    }

    async getCartItemCount(): Promise<number>
    {
        return await this.cartItemsList.count();
    }

    async getCartTotal(): Promise<string | null>
    {
        try {
            return await this.cartTotal.textContent();
        } catch {
            return null;
        }
    }

    async deleteProductFromCart(productName: string)
    {
        const productElement = this.getProductLocator(productName);
        const parentElement = productElement.locator("..");
        await parentElement.locator(this.deleteButton).click();
        await this.page.waitForLoadState('networkidle');
    }

    async isCheckoutButtonVisible(): Promise<boolean>
    {
        return await this.checkoutButton.isVisible();
    }

    async isCheckoutButtonEnabled(): Promise<boolean>
    {
        return await this.checkoutButton.isEnabled();
    }

    async isCartEmpty(): Promise<boolean>
    {
        const count = await this.getCartItemCount();
        return count === 0;
    }

    async getCartItemsNames(): Promise<string[]>
    {
        const items: string[] = [];
        const count = await this.getCartItemCount();
        for(let i = 0; i < count; i++)
        {
            const name = await this.cartItemsList.nth(i).locator("h3").textContent();
            if(name) items.push(name.trim());
        }
        return items;
    }

    async continueShoppingClick()
    {
        await this.continueShoppingButton.click();
        await this.page.waitForLoadState('networkidle');
    }

    async getCartPageURL(): Promise<string>
    {
        return this.page.url();
    }

    async isCartPageLoaded(): Promise<boolean>
    {
        return this.getCartPageURL().then(url => url.includes('cart'));
    }

    async waitForCheckoutButtonToAppear()
    {
        await this.checkoutButton.waitFor({ timeout: 10000 });
    }

    async getProductQuantity(productName: string): Promise<string | null>
    {
        const productElement = this.getProductLocator(productName);
        return await productElement.locator(".quantity, .qty").textContent();
    }
}
