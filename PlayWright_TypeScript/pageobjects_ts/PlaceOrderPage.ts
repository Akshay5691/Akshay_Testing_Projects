import {test, expect, Locator, Page} from '@playwright/test';

export class PlaceOrderPage
{
    // Locators
    page: Page;
    pageTitle: Locator;
    pageContainer: Locator;
    customerNameInput: Locator;
    customerEmailInput: Locator;
    customerPhoneInput: Locator;
    streetAddressInput: Locator;
    cityInput: Locator;
    stateInput: Locator;
    postalCodeInput: Locator;
    countryInput: Locator;
    countrySearchInput: Locator;
    countryOptions: Locator;
    selectCountryButton: Locator;
    cvvInput: Locator;
    cardNameInput: Locator;
    applyButtonForCountry: Locator;
    userReviewsCheckbox: Locator;
    placeOrderFinalButton: Locator;
    orderConfirmationMessage: Locator;
    orderNumber: Locator;
    orderSuccessMessage: Locator;
    formErrorMessages: Locator;
    requiredFieldIndicators: Locator;
    personDetailsSection: Locator;
    shippingAddressSection: Locator;
    paymentDetailsSection: Locator;
    orderSummarySection: Locator;
    productSummaryInOrder: Locator;
    totalAmountInOrder: Locator;
    editOrderButton: Locator;
    cancelOrderButton: Locator;
    backButton: Locator;

    constructor(page: Page)
    {
        this.page = page;
        this.pageTitle = page.locator('h1:has-text("Order"), h2:has-text("Order"), text=/Order|Checkout/i');
        this.pageContainer = page.locator('[class*="order"], [class*="checkout"], app-order-review');
        this.customerNameInput = page.locator('input[placeholder*="name"], input[placeholder*="Name"], input#firstName, input#lastName');
        this.customerEmailInput = page.locator('input[placeholder*="email"], input[placeholder*="Email"], input#email, input[type="email"]');
        this.customerPhoneInput = page.locator('input[placeholder*="phone"], input[placeholder*="Phone"], input#phone');
        this.streetAddressInput = page.locator('input[placeholder*="street"], input[placeholder*="address"], input[placeholder*="Address"], input#address');
        this.cityInput = page.locator('input[placeholder*="city"], input[placeholder*="City"], input#city');
        this.stateInput = page.locator('input[placeholder*="state"], input[placeholder*="State"], input[placeholder*="province"], input#state');
        this.postalCodeInput = page.locator('input[placeholder*="postal"], input[placeholder*="zip"], input[placeholder*="Postal"], input#postalCode');
        this.countryInput = page.locator('input[placeholder*="country"], input[placeholder*="Country"], input#country');
        this.countrySearchInput = page.locator('.input-group input:visible, [class*="country-search"] input');
        this.countryOptions = page.locator('[class*="country-list"] li, .options li, [role="option"]');
        this.selectCountryButton = page.locator('button:has-text("Select"), button:has-text("Apply")');
        this.cvvInput = page.locator('input[placeholder*="CVV"], input[placeholder*="cvv"], input#cvv');
        this.cardNameInput = page.locator('input[placeholder*="card"], input[placeholder*="Card"], input#cardName');
        this.applyButtonForCountry = page.locator('button:has-text("Apply")');
        this.userReviewsCheckbox = page.locator('input[type="checkbox"]');
        this.placeOrderFinalButton = page.locator('button:has-text("Place Order"), button:has-text("Submit Order"), button:has-text("Confirm")');
        this.orderConfirmationMessage = page.locator('[class*="success"], [class*="confirmation"], text=/Order Confirmed|Thank you/i');
        this.orderNumber = page.locator('text=/Order.*[0-9]+/, [class*="order-number"]');
        this.orderSuccessMessage = page.locator('text=Your order has been placed, Thank you for your order, Order placed successfully');
        this.formErrorMessages = page.locator('[class*="error"], [role="alert"], .invalid-feedback');
        this.requiredFieldIndicators = page.locator('.required, [aria-required="true"]');
        this.personDetailsSection = page.locator('[class*="person"], [class*="customer"]');
        this.shippingAddressSection = page.locator('[class*="address"], [class*="shipping"]');
        this.paymentDetailsSection = page.locator('[class*="payment"], [class*="card"]');
        this.orderSummarySection = page.locator('[class*="summary"], [class*="order-summary"]');
        this.productSummaryInOrder = page.locator('[class*="summary"] h3, [class*="order-summary"] h3, .product-summary');
        this.totalAmountInOrder = page.locator('[class*="summary"] b, [class*="order-total"] b, text=" Total Amount"');
        this.editOrderButton = page.locator('button:has-text("Edit"), button:has-text("Update")');
        this.cancelOrderButton = page.locator('button:has-text("Cancel")');
        this.backButton = page.locator('button:has-text("Back"), [class*="back"]');
    }

    async waitForPlaceOrderPageToLoad()
    {
        await this.page.waitForLoadState('networkidle');
        try {
            await this.pageTitle.waitFor({ timeout: 5000 });
        } catch {
            // Title might not exist
        }
        await this.page.waitForTimeout(1000);
    }

    async isPlaceOrderPageLoaded(): Promise<boolean>
    {
        try {
            const url = this.page.url();
            // Check for typical checkout/order URLs
            return url.includes('checkout') || url.includes('order') || url.includes('review');
        } catch {
            return false;
        }
    }

    async getPlaceOrderPageURL(): Promise<string>
    {
        return this.page.url();
    }

    async fillPersonalDetails(name: string, email: string, phone: string)
    {
        // Try multiple selectors for name field
        const nameFields = this.page.locator('input[placeholder*="name"], input#firstName, input[class*="name"]');
        const nameCount = await nameFields.count();
        
        if (nameCount > 0) {
            await nameFields.first().fill(name);
        }

        // Fill email
        const emailFields = this.page.locator('input[placeholder*="email"], input[type="email"]');
        const emailCount = await emailFields.count();
        
        if (emailCount > 0) {
            await emailFields.first().fill(email);
        }

        // Fill phone
        const phoneFields = this.page.locator('input[placeholder*="phone"], input#phone');
        const phoneCount = await phoneFields.count();
        
        if (phoneCount > 0) {
            await phoneFields.first().fill(phone);
        }

        await this.page.waitForTimeout(500);
    }

    async fillShippingAddress(street: string, city: string, state: string, postalCode: string)
    {
        // Fill street address
        const streetFields = this.page.locator('input[placeholder*="street"], input[placeholder*="address"]');
        const streetCount = await streetFields.count();
        
        if (streetCount > 0) {
            await streetFields.first().fill(street);
        }

        // Fill city
        const cityFields = this.page.locator('input[placeholder*="city"]');
        const cityCount = await cityFields.count();
        
        if (cityCount > 0) {
            await cityFields.first().fill(city);
        }

        // Fill state
        const stateFields = this.page.locator('input[placeholder*="state"], input[placeholder*="province"]');
        const stateCount = await stateFields.count();
        
        if (stateCount > 0) {
            await stateFields.first().fill(state);
        }

        // Fill postal code
        const postalFields = this.page.locator('input[placeholder*="postal"], input[placeholder*="zip"]');
        const postalCount = await postalFields.count();
        
        if (postalCount > 0) {
            await postalFields.first().fill(postalCode);
        }

        await this.page.waitForTimeout(500);
    }

    async selectCountry(countryName: string)
    {
        try {
            // Click on country input to open dropdown
            const countryInput = this.page.locator('input[placeholder*="country"]');
            await countryInput.click();
            
            await this.page.waitForTimeout(500);

            // Type country name
            await countryInput.fill(countryName);
            
            await this.page.waitForTimeout(800);

            // Get country options
            const options = this.page.locator('[class*="country"] li, .options li, [role="option"]');
            const count = await options.count();
            
            // Look for matching option
            for (let i = 0; i < count; i++) {
                const optionText = await options.nth(i).textContent();
                if (optionText?.includes(countryName)) {
                    await options.nth(i).click();
                    break;
                }
            }

            // Click apply if available
            try {
                const applyBtn = this.page.locator('button:has-text("Apply")');
                if (await applyBtn.isVisible({ timeout: 1000 })) {
                    await applyBtn.click();
                }
            } catch {
                // No apply button needed
            }

            await this.page.waitForLoadState('networkidle');
        } catch (error) {
            console.error('Error selecting country:', error);
        }
    }

    async fillPaymentDetails(cardName: string, cvv: string)
    {
        try {
            // Fill card name
            const cardNameFields = this.page.locator('input[placeholder*="card"], input[placeholder*="name"]');
            const cardNameCount = await cardNameFields.count();
            
            if (cardNameCount > 0) {
                await cardNameFields.last().fill(cardName);
            }

            // Fill CVV
            const cvvFields = this.page.locator('input[placeholder*="CVV"], input[placeholder*="cvv"]');
            const cvvCount = await cvvFields.count();
            
            if (cvvCount > 0) {
                await cvvFields.last().fill(cvv);
            }

            await this.page.waitForTimeout(500);
        } catch (error) {
            console.error('Error filling payment details:', error);
        }
    }

    async agreeToTerms(): Promise<boolean>
    {
        try {
            const checkbox = this.page.locator('input[type="checkbox"]');
            if (await checkbox.isVisible()) {
                await checkbox.check();
                await this.page.waitForTimeout(500);
                return true;
            }
            return false;
        } catch {
            return false;
        }
    }

    async placeOrder()
    {
        await this.placeOrderFinalButton.click();
        await this.page.waitForLoadState('networkidle');
        await this.page.waitForTimeout(2000);
    }

    async isOrderPlacedSuccessfully(): Promise<boolean>
    {
        try {
            const isSuccessMessageVisible = await this.orderSuccessMessage.isVisible({ timeout: 5000 });
            return isSuccessMessageVisible;
        } catch {
            return false;
        }
    }

    async getOrderConfirmationMessage(): Promise<string | null>
    {
        try {
            return await this.orderSuccessMessage.textContent();
        } catch {
            return null;
        }
    }

    async getOrderNumber(): Promise<string | null>
    {
        try {
            return await this.orderNumber.textContent();
        } catch {
            return null;
        }
    }

    async getFormValidationErrors(): Promise<string[]>
    {
        try {
            const errors = await this.formErrorMessages.allTextContents();
            return errors.filter(e => e.trim().length > 0);
        } catch {
            return [];
        }
    }

    async verifyPersonDetailsSection(): Promise<boolean>
    {
        try {
            return await this.personDetailsSection.isVisible({ timeout: 2000 });
        } catch {
            return false;
        }
    }

    async verifyShippingAddressSection(): Promise<boolean>
    {
        try {
            return await this.shippingAddressSection.isVisible({ timeout: 2000 });
        } catch {
            return false;
        }
    }

    async verifyPaymentDetailsSection(): Promise<boolean>
    {
        try {
            return await this.paymentDetailsSection.isVisible({ timeout: 2000 });
        } catch {
            return false;
        }
    }

    async verifyOrderSummarySection(): Promise<boolean>
    {
        try {
            return await this.orderSummarySection.isVisible({ timeout: 2000 });
        } catch {
            return false;
        }
    }

    async getProductSummary(): Promise<string[]>
    {
        try {
            return await this.productSummaryInOrder.allTextContents();
        } catch {
            return [];
        }
    }

    async getTotalAmount(): Promise<string | null>
    {
        try {
            return await this.totalAmountInOrder.textContent();
        } catch {
            return null;
        }
    }

    async isPlaceOrderButtonVisible(): Promise<boolean>
    {
        try {
            return await this.placeOrderFinalButton.isVisible();
        } catch {
            return false;
        }
    }

    async isPlaceOrderButtonEnabled(): Promise<boolean>
    {
        try {
            return await this.placeOrderFinalButton.isEnabled();
        } catch {
            return false;
        }
    }

    async fillCompleteOrderForm(
        name: string,
        email: string,
        phone: string,
        street: string,
        city: string,
        state: string,
        postalCode: string,
        country: string,
        cardName: string,
        cvv: string
    )
    {
        await this.fillPersonalDetails(name, email, phone);
        await this.fillShippingAddress(street, city, state, postalCode);
        await this.selectCountry(country);
        await this.fillPaymentDetails(cardName, cvv);
        await this.page.waitForTimeout(500);
    }

    async goBack()
    {
        await this.backButton.click();
        await this.page.waitForLoadState('networkidle');
    }
}
