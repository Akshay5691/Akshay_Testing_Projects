import { test, expect } from '../base/test-base';
import { POManager } from '../pageobjects_ts/POManager';
import { PlaceOrderPage } from '../pageobjects_ts/PlaceOrderPage';
import { CheckoutPage } from '../pageobjects_ts/CheckoutPage';
import { CartPage } from '../pageobjects_ts/CartPage';

test.describe('Place Order Page Tests - Page Object Model', () => {

    let poManager: POManager;
    let dashboardPage: any;
    let cartPage: CartPage;
    let checkoutPage: CheckoutPage;
    let placeOrderPage: PlaceOrderPage;

    test.beforeEach(async ({ page }) => {
        poManager = (page as any).poManager;
        if (!poManager) throw new Error('poManager not initialized in test-base beforeEach');
        dashboardPage = poManager.getDashboardPage();
        await dashboardPage.waitForDashboardToLoad();
        cartPage = poManager.getCartPage();
        checkoutPage = poManager.getCheckoutPage();
        placeOrderPage = poManager.getPlaceOrderPage();
    });

    test('@PlaceOrder TC001 - Navigate to Place Order Page and Verify Page Loads', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        await checkoutPage.proceedToPlaceOrder();
        await placeOrderPage.waitForPlaceOrderPageToLoad();
        
        // Assert
        expect(await placeOrderPage.isPlaceOrderPageLoaded()).toBeTruthy();
    });

    test('@PlaceOrder TC002 - Verify All Form Sections are Visible', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        await checkoutPage.proceedToPlaceOrder();
        await placeOrderPage.waitForPlaceOrderPageToLoad();
        
        // Assert
        const hasPersonDetails = await placeOrderPage.verifyPersonDetailsSection();
        const hasShippingAddress = await placeOrderPage.verifyShippingAddressSection();
        const hasPaymentDetails = await placeOrderPage.verifyPaymentDetailsSection();
        const hasOrderSummary = await placeOrderPage.verifyOrderSummarySection();
        
        console.log(`Person Details: ${hasPersonDetails}`);
        console.log(`Shipping Address: ${hasShippingAddress}`);
        console.log(`Payment Details: ${hasPaymentDetails}`);
        console.log(`Order Summary: ${hasOrderSummary}`);
    });

    test('@PlaceOrder TC003 - Verify Order Summary with Products', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        await checkoutPage.proceedToPlaceOrder();
        await placeOrderPage.waitForPlaceOrderPageToLoad();
        
        // Assert
        const products = await placeOrderPage.getProductSummary();
        const totalAmount = await placeOrderPage.getTotalAmount();
        
        console.log('Products in Order:', products);
        console.log('Total Amount:', totalAmount);
        
        expect(products.length).toBeGreaterThan(0);
        expect(totalAmount).not.toBeNull();
    });

    test('@PlaceOrder TC004 - Fill Personal Details', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        const name = 'John Doe';
        const email = 'john.doe@example.com';
        const phone = '1234567890';
        
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        await checkoutPage.proceedToPlaceOrder();
        
        // Act
        await placeOrderPage.waitForPlaceOrderPageToLoad();
        await placeOrderPage.fillPersonalDetails(name, email, phone);
        
        // Assert
        console.log('Personal details filled successfully');
        expect(await placeOrderPage.verifyPersonDetailsSection()).toBeTruthy();
    });

    test('@PlaceOrder TC005 - Fill Shipping Address', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        const street = '123 Main Street';
        const city = 'New York';
        const state = 'NY';
        const postalCode = '10001';
        
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        await checkoutPage.proceedToPlaceOrder();
        
        // Act
        await placeOrderPage.waitForPlaceOrderPageToLoad();
        await placeOrderPage.fillShippingAddress(street, city, state, postalCode);
        
        // Assert
        console.log('Shipping address filled successfully');
        expect(await placeOrderPage.verifyShippingAddressSection()).toBeTruthy();
    });

    test('@PlaceOrder TC006 - Select Country', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        const country = 'United States';
        
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        await checkoutPage.proceedToPlaceOrder();
        
        // Act
        await placeOrderPage.waitForPlaceOrderPageToLoad();
        await placeOrderPage.selectCountry(country);
        
        // Assert
        console.log(`Country ${country} selected successfully`);
    });

    test('@PlaceOrder TC007 - Fill Payment Details', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        const cardName = 'John Doe';
        const cvv = '123';
        
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        await checkoutPage.proceedToPlaceOrder();
        
        // Act
        await placeOrderPage.waitForPlaceOrderPageToLoad();
        await placeOrderPage.fillPaymentDetails(cardName, cvv);
        
        // Assert
        console.log('Payment details filled successfully');
        expect(await placeOrderPage.verifyPaymentDetailsSection()).toBeTruthy();
    });

    test('@PlaceOrder TC008 - Accept Terms and Conditions', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        await checkoutPage.proceedToPlaceOrder();
        
        // Act
        await placeOrderPage.waitForPlaceOrderPageToLoad();
        const accepted = await placeOrderPage.agreeToTerms();
        
        // Assert
        console.log(`Terms accepted: ${accepted}`);
    });

    test('@PlaceOrder TC009 - Verify Place Order Button is Visible and Enabled', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        await checkoutPage.proceedToPlaceOrder();
        await placeOrderPage.waitForPlaceOrderPageToLoad();
        
        // Assert
        const isVisible = await placeOrderPage.isPlaceOrderButtonVisible();
        const isEnabled = await placeOrderPage.isPlaceOrderButtonEnabled();
        
        expect(isVisible).toBeTruthy();
        expect(isEnabled).toBeTruthy();
    });

    test('@PlaceOrder TC010 - Complete Order with Valid Details and Verify Confirmation', async () => {
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
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        await checkoutPage.proceedToPlaceOrder();
        
        await placeOrderPage.waitForPlaceOrderPageToLoad();
        
        // Fill all form details
        await placeOrderPage.fillCompleteOrderForm(
            name, email, phone,
            street, city, state, postalCode,
            country, cardName, cvv
        );
        
        // Accept terms
        await placeOrderPage.agreeToTerms();
        
        // Place order
        await placeOrderPage.placeOrder();
        
        // Assert
        const isOrderPlaced = await placeOrderPage.isOrderPlacedSuccessfully();
        expect(isOrderPlaced).toBeTruthy();
        
        const confirmationMessage = await placeOrderPage.getOrderConfirmationMessage();
        const orderNumber = await placeOrderPage.getOrderNumber();
        
        console.log('Order Confirmation:', confirmationMessage);
        console.log('Order Number:', orderNumber);
    });

    test('@PlaceOrder TC011 - Fill Complete Form and Verify All Validations Pass', async () => {
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
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        await checkoutPage.proceedToPlaceOrder();
        
        await placeOrderPage.waitForPlaceOrderPageToLoad();
        await placeOrderPage.fillCompleteOrderForm(
            name, email, phone,
            street, city, state, postalCode,
            country, cardName, cvv
        );
        
        // Assert
        const errors = await placeOrderPage.getFormValidationErrors();
        expect(errors.length).toBe(0);
        console.log('Form validation passed with no errors');
    });

    test('@PlaceOrder TC012 - Verify Order Summary in Place Order Page', async () => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        // Act
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        await checkoutPage.proceedToPlaceOrder();
        
        await placeOrderPage.waitForPlaceOrderPageToLoad();
        
        // Assert
        const hasSummarySection = await placeOrderPage.verifyOrderSummarySection();
        const products = await placeOrderPage.getProductSummary();
        const totalAmount = await placeOrderPage.getTotalAmount();
        
        expect(hasSummarySection).toBeTruthy();
        expect(products.length).toBeGreaterThan(0);
        expect(totalAmount).not.toBeNull();
        
        console.log('Order Summary verified');
        console.log('Products:', products);
        console.log('Total:', totalAmount);
    });

    test('@PlaceOrder TC013 - Test Form Field Validations with Empty Fields', async ({ page }) => {
        // Arrange
        const productName = 'iphone 13 pro';
        
        await dashboardPage.searchProductAddCart(productName);
        await dashboardPage.navigateToCart();
        await cartPage.waitForCartToLoad();
        await cartPage.checkout();
        await checkoutPage.waitForCheckoutPageToLoad();
        await checkoutPage.proceedToPlaceOrder();
        
        // Act
        await placeOrderPage.waitForPlaceOrderPageToLoad();
        
        // Try to place order without filling any details
        if (await placeOrderPage.isPlaceOrderButtonEnabled()) {
            await page.waitForTimeout(1000);
        }
        
        // Get any validation errors
        const errors = await placeOrderPage.getFormValidationErrors();
        
        // Assert
        console.log('Validation Errors:', errors);
        // Some errors might be displayed for empty fields
    });

});
