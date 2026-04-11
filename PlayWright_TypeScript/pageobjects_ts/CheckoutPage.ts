import {test, expect, Locator, Page} from '@playwright/test';

export class CheckoutPage
{
    // Locators
    page: Page;
    checkoutContainer: Locator;
    checkoutTitle: Locator;
    productsList: Locator;
    productName: Locator;
    productPrice: Locator;
    productQuantity: Locator;
    subtotal: Locator;
    shippingCost: Locator;
    discountCode: Locator;
    discountCodeInput: Locator;
    applyDiscountButton: Locator;
    totalPrice: Locator;
    proceedButton: Locator;
    continueShoppingButton: Locator;
    removeButton: Locator;
    cartSummary: Locator;
    emptyMessage: Locator;
    quantityField: Locator;
    increaseQuantityButton: Locator;
    decreaseQuantityButton: Locator;
    checkoutMessage: Locator;
    paymentMethodSection: Locator;

    constructor(page: Page)
    {
        this.page = page;
        this.checkoutContainer = page.locator('app-checkout, .checkout-container, [class*="checkout"]');
        this.checkoutTitle = page.locator('h1:has-text("Checkout"), h2:has-text("Checkout"), text=Checkout');
        this.productsList = page.locator('div li, .product-item, [class*="product"]');
        this.productName = page.locator('h3, .product-name, [class*="name"]');
        this.productPrice = page.locator('.price, [class*="price"]');
        this.productQuantity = page.locator('.product-quantity, [class*="quantity"]');
        this.subtotal = page.locator('text=" Subtotal :", text=" Subtotal", [class*="subtotal"]');
        this.shippingCost = page.locator('text=Shipping, text=Delivery, [class*="shipping"]');
        this.discountCode = page.locator('[class*="discount"], [class*="coupon"]');
        this.discountCodeInput = page.locator('input[placeholder*="discount"], input[placeholder*="coupon"], input[class*="discount"]');
        this.applyDiscountButton = page.locator('button:has-text("Apply"), button:has-text("Redeem")');
        this.totalPrice = page.locator('[class*="total"] b, text=" Total Amount", [class*="order-total"]');
        this.proceedButton = page.locator('button:has-text("Place Order"), text=Place Order');
        this.continueShoppingButton = page.locator('button:has-text("Continue Shopping")');
        this.removeButton = page.locator('button:has-text("Delete"), [class*="remove"], [class*="delete"]');
        this.cartSummary = page.locator('[class*="summary"], [class*="order-summary"]');
        this.emptyMessage = page.locator('text=Empty, text=No Products, text=Your cart is empty');
        this.quantityField = page.locator('input[type="number"]');
        this.increaseQuantityButton = page.locator('button:has(span:has-text("+"))');
        this.decreaseQuantityButton = page.locator('button:has(span:has-text("-"))');
        this.checkoutMessage = page.locator('[class*="message"], [class*="alert"]');
        this.paymentMethodSection = page.locator('[class*="payment"], [class*="select-type"]');
    }

    async waitForCheckoutPageToLoad()
    {
        await this.page.waitForLoadState('networkidle');
        try {
            await this.checkoutTitle.waitFor({ timeout: 5000 });
        } catch {
            // Checkout title might not always be visible
        }
        await this.page.waitForTimeout(1000);
    }

    async isCheckoutPageLoaded(): Promise<boolean>
    {
        try {
            const url = this.page.url();
            return url.includes('checkout');
        } catch {
            return false;
        }
    }

    async getCheckoutPageURL(): Promise<string>
    {
        return this.page.url();
    }

    async getProductCount(): Promise<number>
    {
        return await this.productsList.count();
    }

    async getAllProductNames(): Promise<string[]>
    {
        const count = await this.productsList.count();
        const names = [];
        
        for (let i = 0; i < count; i++) {
            const name = await this.productsList.nth(i).locator('h3').textContent();
            if (name) {
                names.push(name.trim());
            }
        }
        
        return names;
    }

    async verifyProductInCheckout(productName: string): Promise<boolean>
    {
        const names = await this.getAllProductNames();
        return names.some(name => name.includes(productName));
    }

    async getProductPrice(productName: string): Promise<string | null>
    {
        const products = this.productsList;
        const count = await products.count();
        
        for (let i = 0; i < count; i++) {
            const name = await products.nth(i).locator('h3').textContent();
            if (name?.includes(productName)) {
                return await products.nth(i).locator('[class*="price"]').textContent();
            }
        }
        
        return null;
    }

    async getTotalPrice(): Promise<string | null>
    {
        try {
            const totalText = await this.totalPrice.textContent();
            return totalText;
        } catch {
            return null;
        }
    }

    async getSubtotal(): Promise<string | null>
    {
        try {
            const subtotalText = await this.subtotal.textContent();
            return subtotalText;
        } catch {
            return null;
        }
    }

    async applyDiscountCode(code: string): Promise<boolean>
    {
        try {
            await this.discountCodeInput.fill(code);
            await this.applyDiscountButton.click();
            await this.page.waitForLoadState('networkidle');
            return true;
        } catch {
            return false;
        }
    }

    async removeProductFromCheckout(productName: string): Promise<boolean>
    {
        try {
            const products = this.productsList;
            const count = await products.count();
            
            for (let i = 0; i < count; i++) {
                const name = await products.nth(i).locator('h3').textContent();
                if (name?.includes(productName)) {
                    const deleteBtn = products.nth(i).locator(this.removeButton);
                    await deleteBtn.click();
                    await this.page.waitForLoadState('networkidle');
                    return true;
                }
            }
            
            return false;
        } catch {
            return false;
        }
    }

    async updateProductQuantity(productName: string, quantity: number): Promise<boolean>
    {
        try {
            const products = this.productsList;
            const count = await products.count();
            
            for (let i = 0; i < count; i++) {
                const name = await products.nth(i).locator('h3').textContent();
                if (name?.includes(productName)) {
                    const quantityInput = products.nth(i).locator(this.quantityField);
                    await quantityInput.fill(quantity.toString());
                    await this.page.waitForLoadState('networkidle');
                    return true;
                }
            }
            
            return false;
        } catch {
            return false;
        }
    }

    async isPlaceOrderButtonVisible(): Promise<boolean>
    {
        try {
            return await this.proceedButton.isVisible();
        } catch {
            return false;
        }
    }

    async isPlaceOrderButtonEnabled(): Promise<boolean>
    {
        try {
            return await this.proceedButton.isEnabled();
        } catch {
            return false;
        }
    }

    async proceedToPlaceOrder()
    {
        await this.proceedButton.click();
        await this.page.waitForLoadState('networkidle');
    }

    async continueShopping()
    {
        await this.continueShoppingButton.click();
        await this.page.waitForLoadState('networkidle');
    }

    async isCheckoutEmpty(): Promise<boolean>
    {
        try {
            return await this.emptyMessage.isVisible();
        } catch {
            return false;
        }
    }

    async getCheckoutItemDetails(index: number): Promise<any>
    {
        try {
            const item = this.productsList.nth(index);
            const name = await item.locator('h3').textContent();
            const price = await item.locator('[class*="price"]').textContent();
            const quantity = await item.locator(this.quantityField).inputValue();
            
            return {
                name: name?.trim() || '',
                price: price?.trim() || '',
                quantity: quantity || ''
            };
        } catch {
            return null;
        }
    }

    async waitForDiscountToBeApplied()
    {
        await this.page.waitForLoadState('networkidle');
        await this.page.waitForTimeout(1500);
    }
}
