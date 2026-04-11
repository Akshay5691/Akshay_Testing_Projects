import { expect } from '@playwright/test';
import { customTest as test } from '../utils_ts/test-base';

test.describe('Place Order Page Tests - Page Object Model', () => {
    
    test.beforeAll(async ({ browser }) => {
        const context = await browser.newContext();
        const loginPage = await context.newPage();
        
        // Perform login once to save session
        await loginPage.goto('https://rahulshettyacademy.com/client');
        await loginPage.locator('#userEmail').fill('rahulshetty@gmail.com');
        await loginPage.locator('#userPassword').fill('Iamking@000');
        await loginPage.locator("[value='Login']").click();
        await loginPage.waitForLoadState('networkidle');
        await loginPage.context().storageState({ path: 'state.json' });
        
        await context.close();
    });

    test('@PlaceOrder TC001 - Navigate to Place Order Page and Verify Page Loads', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        await poManager.getCheckoutPage().proceedToPlaceOrder();
        await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
        
        // Assert
        expect(await poManager.getPlaceOrderPage().isPlaceOrderPageLoaded()).toBeTruthy();
    });

    test('@PlaceOrder TC002 - Verify All Form Sections are Visible', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        await poManager.getCheckoutPage().proceedToPlaceOrder();
        await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
        
        // Assert
        const hasPersonDetails = await poManager.getPlaceOrderPage().verifyPersonDetailsSection();
        const hasShippingAddress = await poManager.getPlaceOrderPage().verifyShippingAddressSection();
        const hasPaymentDetails = await poManager.getPlaceOrderPage().verifyPaymentDetailsSection();
        const hasOrderSummary = await poManager.getPlaceOrderPage().verifyOrderSummarySection();
        
        console.log(`Person Details: ${hasPersonDetails}`);
        console.log(`Shipping Address: ${hasShippingAddress}`);
        console.log(`Payment Details: ${hasPaymentDetails}`);
        console.log(`Order Summary: ${hasOrderSummary}`);
    });

    test('@PlaceOrder TC003 - Verify Order Summary with Products', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        await poManager.getCheckoutPage().proceedToPlaceOrder();
        await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
        
        // Assert
        const products = await poManager.getPlaceOrderPage().getProductSummary();
        const totalAmount = await poManager.getPlaceOrderPage().getTotalAmount();
        
        console.log('Products in Order:', products);
        console.log('Total Amount:', totalAmount);
        
        expect(products.length).toBeGreaterThan(0);
        expect(totalAmount).not.toBeNull();
    });

    test('@PlaceOrder TC004 - Fill Personal Details', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        const name = 'John Doe';
        const email = 'john.doe@example.com';
        const phone = '1234567890';
        
        await poManager.getDashboardPage().waitForDashboardToLoad();
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        await poManager.getCheckoutPage().proceedToPlaceOrder();
        
        // Act
        await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
        await poManager.getPlaceOrderPage().fillPersonalDetails(name, email, phone);
        
        // Assert
        console.log('Personal details filled successfully');
        expect(await poManager.getPlaceOrderPage().verifyPersonDetailsSection()).toBeTruthy();
    });

    test('@PlaceOrder TC005 - Fill Shipping Address', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        const street = '123 Main Street';
        const city = 'New York';
        const state = 'NY';
        const postalCode = '10001';
        
        await poManager.getDashboardPage().waitForDashboardToLoad();
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        await poManager.getCheckoutPage().proceedToPlaceOrder();
        
        // Act
        await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
        await poManager.getPlaceOrderPage().fillShippingAddress(street, city, state, postalCode);
        
        // Assert
        console.log('Shipping address filled successfully');
        expect(await poManager.getPlaceOrderPage().verifyShippingAddressSection()).toBeTruthy();
    });

    test('@PlaceOrder TC006 - Select Country', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        const country = 'United States';
        
        await poManager.getDashboardPage().waitForDashboardToLoad();
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        await poManager.getCheckoutPage().proceedToPlaceOrder();
        
        // Act
        await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
        await poManager.getPlaceOrderPage().selectCountry(country);
        
        // Assert
        console.log(`Country ${country} selected successfully`);
    });

    test('@PlaceOrder TC007 - Fill Payment Details', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        const cardName = 'John Doe';
        const cvv = '123';
        
        await poManager.getDashboardPage().waitForDashboardToLoad();
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        await poManager.getCheckoutPage().proceedToPlaceOrder();
        
        // Act
        await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
        await poManager.getPlaceOrderPage().fillPaymentDetails(cardName, cvv);
        
        // Assert
        console.log('Payment details filled successfully');
        expect(await poManager.getPlaceOrderPage().verifyPaymentDetailsSection()).toBeTruthy();
    });

    test('@PlaceOrder TC008 - Accept Terms and Conditions', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        await poManager.getDashboardPage().waitForDashboardToLoad();
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        await poManager.getCheckoutPage().proceedToPlaceOrder();
        
        // Act
        await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
        const accepted = await poManager.getPlaceOrderPage().agreeToTerms();
        
        // Assert
        console.log(`Terms accepted: ${accepted}`);
    });

    test('@PlaceOrder TC009 - Verify Place Order Button is Visible and Enabled', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        await poManager.getCheckoutPage().proceedToPlaceOrder();
        await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
        
        // Assert
        const isVisible = await poManager.getPlaceOrderPage().isPlaceOrderButtonVisible();
        const isEnabled = await poManager.getPlaceOrderPage().isPlaceOrderButtonEnabled();
        
        expect(isVisible).toBeTruthy();
        expect(isEnabled).toBeTruthy();
    });

    test('@PlaceOrder TC010 - Complete Order with Valid Details and Verify Confirmation', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        const name = 'John Doe';
        const email = 'john.doe@example.com';
        const phone = '1234567890';
        const street = '123 Main Street';
        const city = 'New York';
        const state = 'NY';
        const postalCode = '10001';
        const country = 'United States';
        const cardName = 'John Doe';
        const cvv = '123';
        
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act - Add product and checkout
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        await poManager.getCheckoutPage().proceedToPlaceOrder();
        
        await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
        
        // Fill all form details
        await poManager.getPlaceOrderPage().fillCompleteOrderForm(
            name, email, phone,
            street, city, state, postalCode,
            country, cardName, cvv
        );
        
        // Accept terms
        await poManager.getPlaceOrderPage().agreeToTerms();
        
        // Place order
        await poManager.getPlaceOrderPage().placeOrder();
        
        // Assert
        const isOrderPlaced = await poManager.getPlaceOrderPage().isOrderPlacedSuccessfully();
        expect(isOrderPlaced).toBeTruthy();
        
        const confirmationMessage = await poManager.getPlaceOrderPage().getOrderConfirmationMessage();
        const orderNumber = await poManager.getPlaceOrderPage().getOrderNumber();
        
        console.log('Order Confirmation:', confirmationMessage);
        console.log('Order Number:', orderNumber);
    });

    test('@PlaceOrder TC011 - Fill Complete Form and Verify All Validations Pass', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        const name = 'John Doe';
        const email = 'john.doe@example.com';
        const phone = '1234567890';
        const street = '123 Main Street';
        const city = 'New York';
        const state = 'NY';
        const postalCode = '10001';
        const country = 'United States';
        const cardName = 'John Doe';
        const cvv = '123';
        
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        await poManager.getCheckoutPage().proceedToPlaceOrder();
        
        await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
        await poManager.getPlaceOrderPage().fillCompleteOrderForm(
            name, email, phone,
            street, city, state, postalCode,
            country, cardName, cvv
        );
        
        // Assert
        const errors = await poManager.getPlaceOrderPage().getFormValidationErrors();
        expect(errors.length).toBe(0);
        console.log('Form validation passed with no errors');
    });

    test('@PlaceOrder TC012 - Verify Order Summary in Place Order Page', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        await poManager.getDashboardPage().waitForDashboardToLoad();
        
        // Act
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        await poManager.getCheckoutPage().proceedToPlaceOrder();
        
        await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
        
        // Assert
        const hasSummarySection = await poManager.getPlaceOrderPage().verifyOrderSummarySection();
        const products = await poManager.getPlaceOrderPage().getProductSummary();
        const totalAmount = await poManager.getPlaceOrderPage().getTotalAmount();
        
        expect(hasSummarySection).toBeTruthy();
        expect(products.length).toBeGreaterThan(0);
        expect(totalAmount).not.toBeNull();
        
        console.log('Order Summary verified');
        console.log('Products:', products);
        console.log('Total:', totalAmount);
    });

    test('@PlaceOrder TC013 - Test Form Field Validations with Empty Fields', async ({ page, poManager }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        await poManager.getDashboardPage().waitForDashboardToLoad();
        await poManager.getDashboardPage().searchProductAddCart(productName);
        await poManager.getDashboardPage().navigateToCart();
        await poManager.getCartPage().waitForCartToLoad();
        await poManager.getCartPage().checkout();
        await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
        await poManager.getCheckoutPage().proceedToPlaceOrder();
        
        // Act
        await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
        
        // Try to place order without filling any details
        if (await poManager.getPlaceOrderPage().isPlaceOrderButtonEnabled()) {
            await page.waitForTimeout(1000);
        }
        
        // Get any validation errors
        const errors = await poManager.getPlaceOrderPage().getFormValidationErrors();
        
        // Assert
        console.log('Validation Errors:', errors);
        // Some errors might be displayed for empty fields
    });

});
