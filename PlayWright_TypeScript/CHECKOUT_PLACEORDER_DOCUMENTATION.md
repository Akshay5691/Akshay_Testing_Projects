# Checkout and Place Order Page - Page Object Model Documentation

## Overview
This documentation covers the new Page Object Models (POMs) created for the Checkout Page and Place Order Page of the Rahul Shetty Academy e-commerce application, along with comprehensive test scripts following the POM pattern.

---

## 1. CheckoutPage.ts - Page Object Model

### Purpose
The `CheckoutPage` class encapsulates all locators and methods related to the checkout page of the application. This page displays the items in the user's cart, allows modification of quantities, application of discount codes, and navigation to the place order page.

### Key Locators
- **checkoutContainer**: Main checkout page container
- **productsList**: List of products displayed in checkout
- **productName, productPrice, productQuantity**: Product-related information
- **subtotal, totalPrice**: Order totals
- **discountCodeInput, applyDiscountButton**: Discount functionality
- **removeButton, deleteButton**: Remove product functionality
- **proceedButton**: "Place Order" button to proceed to order page
- **cartSummary, emptyMessage**: Cart status messages

### Key Methods

#### Navigation & Page Status
- `waitForCheckoutPageToLoad()` - Waits for the checkout page to fully load with network idle
- `isCheckoutPageLoaded()` - Verifies if checkout page is loaded by checking URL
- `getCheckoutPageURL()` - Returns the current page URL

#### Product Operations
- `getProductCount()` - Returns the total number of products in checkout
- `getAllProductNames()` - Retrieves all product names in the cart
- `verifyProductInCheckout(productName)` - Checks if a specific product exists in checkout
- `getProductPrice(productName)` - Gets the price of a specific product
- `removeProductFromCheckout(productName)` - Removes a product from the cart
- `updateProductQuantity(productName, quantity)` - Updates the quantity of a product

#### Order Summary
- `getTotalPrice()` - Gets the total order amount
- `getSubtotal()` - Gets the subtotal (before taxes/shipping)
- `getCartItemCount()` - Returns count of items in cart
- `getCheckoutItemDetails(index)` - Gets detailed information about an item at specified index

#### Discount Operations
- `applyDiscountCode(code)` - Applies a discount/coupon code
- `waitForDiscountToBeApplied()` - Waits for discount to be processed

#### Button & Navigation
- `isPlaceOrderButtonVisible()` - Checks if "Place Order" button is visible
- `isPlaceOrderButtonEnabled()` - Checks if "Place Order" button is clickable
- `proceedToPlaceOrder()` - Clicks the "Place Order" button and navigates to order page
- `continueShopping()` - Navigates back to dashboard to continue shopping

#### Cart Status
- `isCheckoutEmpty()` - Determines if checkout cart is empty

---

## 2. PlaceOrderPage.ts - Page Object Model

### Purpose
The `PlaceOrderPage` class manages all interactions and verifications on the place order page, where users fill in personal, shipping, and payment details before finalizing their order.

### Key Locators
- **pageTitle, pageContainer**: Page identification elements
- **customerNameInput, customerEmailInput, customerPhoneInput**: Personal details fields
- **streetAddressInput, cityInput, stateInput, postalCodeInput, countryInput**: Shipping address fields
- **cvvInput, cardNameInput**: Payment details fields
- **userReviewsCheckbox**: Terms and conditions acceptance
- **placeOrderFinalButton**: Final "Place Order" button
- **orderConfirmationMessage, orderSuccessMessage**: Order confirmation elements
- **formErrorMessages**: Validation error displays
- **personDetailsSection, shippingAddressSection, paymentDetailsSection, orderSummarySection**: Form sections

### Key Methods

#### Page Status & Navigation
- `waitForPlaceOrderPageToLoad()` - Waits for page to fully load
- `isPlaceOrderPageLoaded()` - Verifies page is loaded by checking URL
- `getPlaceOrderPageURL()` - Returns current page URL
- `goBack()` - Navigates back to previous page

#### Form Filling Methods
- `fillPersonalDetails(name, email, phone)` - Fills customer personal information
- `fillShippingAddress(street, city, state, postalCode)` - Fills shipping address details
- `selectCountry(countryName)` - Selects country from dropdown with search
- `fillPaymentDetails(cardName, cvv)` - Fills card payment details
- `agreeToTerms()` - Checks the terms and conditions checkbox
- `fillCompleteOrderForm(...)` - Convenience method to fill all form fields at once

#### Order Placement
- `placeOrder()` - Clicks the final "Place Order" button
- `isOrderPlacedSuccessfully()` - Verifies order placement success message
- `getOrderConfirmationMessage()` - Retrieves order confirmation message
- `getOrderNumber()` - Extracts order number from confirmation

#### Form Validation
- `getFormValidationErrors()` - Returns array of validation error messages
- `isPlaceOrderButtonVisible()` - Checks if Place Order button is visible
- `isPlaceOrderButtonEnabled()` - Checks if Place Order button is enabled

#### Section Verification
- `verifyPersonDetailsSection()` - Checks if personal details section exists
- `verifyShippingAddressSection()` - Checks if shipping address section exists
- `verifyPaymentDetailsSection()` - Checks if payment section exists
- `verifyOrderSummarySection()` - Checks if order summary section exists

#### Order Summary
- `getProductSummary()` - Returns list of products in order
- `getTotalAmount()` - Returns total order amount

---

## 3. Test Scripts

### CheckoutPage_POM.spec.ts

Complete test suite for checkout page functionality:

#### Test Cases

| Test ID | Title | Description |
|---------|-------|-------------|
| @Checkout TC001 | Navigate and Verify Page Loads | Verifies checkout page loads correctly |
| @Checkout TC002 | Verify Products Appear | Checks all added products display in checkout |
| @Checkout TC003 | Verify Product Price | Validates product pricing is displayed |
| @Checkout TC004 | Verify Calculations | Tests subtotal and total amount calculations |
| @Checkout TC005 | Apply Discount Code | Tests discount code application and price updates |
| @Checkout TC006 | Update Quantity | Tests modifying product quantities |
| @Checkout TC007 | Remove Product | Tests removing products from checkout |
| @Checkout TC008 | Place Order Button | Verifies Place Order button visibility and state |
| @Checkout TC009 | Get Item Details | Retrieves and validates individual item details |
| @Checkout TC010 | Navigate to Place Order | Tests navigation from checkout to place order page |

**Usage:**
```bash
npx playwright test CheckoutPage_POM.spec.ts
npx playwright test CheckoutPage_POM.spec.ts --grep "@Checkout TC005"  # Run specific test
```

---

### PlaceOrderPage_POM.spec.ts

Complete test suite for place order page functionality:

#### Test Cases

| Test ID | Title | Description |
|---------|-------|-------------|
| @PlaceOrder TC001 | Navigate and Verify Page Loads | Verifies place order page loads |
| @PlaceOrder TC002 | Verify Form Sections | Checks all form sections are visible |
| @PlaceOrder TC003 | Order Summary | Validates products and total in order summary |
| @PlaceOrder TC004 | Fill Personal Details | Tests personal information form filling |
| @PlaceOrder TC005 | Fill Shipping Address | Tests shipping address form filling |
| @PlaceOrder TC006 | Select Country | Tests country dropdown selection |
| @PlaceOrder TC007 | Fill Payment Details | Tests payment information form filling |
| @PlaceOrder TC008 | Accept Terms | Tests terms and conditions acceptance |
| @PlaceOrder TC009 | Place Order Button | Verifies Place Order button state |
| @PlaceOrder TC010 | Complete Order Flow | End-to-end test with valid order placement |
| @PlaceOrder TC011 | Form Validations | Tests all validations pass with valid data |
| @PlaceOrder TC012 | Order Summary Verification | Verifies complete order summary display |
| @PlaceOrder TC013 | Empty Field Validations | Tests validation with empty form fields |

**Usage:**
```bash
npx playwright test PlaceOrderPage_POM.spec.ts
npx playwright test PlaceOrderPage_POM.spec.ts --grep "@PlaceOrder TC010"  # Run specific test
```

---

## 4. Updated POManager.ts

The POManager has been updated with new page object instances:

### New Methods
- `getCheckoutPage()` - Returns CheckoutPage instance
- `getPlaceOrderPage()` - Returns PlaceOrderPage instance

### Usage
```typescript
// In your tests
const checkoutPage = poManager.getCheckoutPage();
const placeOrderPage = poManager.getPlaceOrderPage();
```

---

## 5. Complete E-to-E Test Flow Example

```typescript
test('E2E: Complete Purchase Flow', async ({ page, poManager }) => {
    const productName = 'iphone 13 pro';
    
    // Dashboard - Add product to cart
    await poManager.getDashboardPage().waitForDashboardToLoad();
    await poManager.getDashboardPage().searchProductAddCart(productName);
    
    // Cart - Verify and proceed
    await poManager.getDashboardPage().navigateToCart();
    await poManager.getCartPage().waitForCartToLoad();
    await poManager.getCartPage().verifyProductIsDisplayed(productName);
    
    // Checkout - Apply discount and verify totals
    await poManager.getCartPage().checkout();
    await poManager.getCheckoutPage().waitForCheckoutPageToLoad();
    await poManager.getCheckoutPage().applyDiscountCode('rahulshettyacademy');
    const totalAmount = await poManager.getCheckoutPage().getTotalPrice();
    
    // Place Order - Fill details and complete
    await poManager.getCheckoutPage().proceedToPlaceOrder();
    await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
    
    await poManager.getPlaceOrderPage().fillCompleteOrderForm(
        'John Doe', 'john@example.com', '1234567890',
        '123 Main St', 'New York', 'NY', '10001',
        'United States', 'John Doe', '123'
    );
    
    await poManager.getPlaceOrderPage().agreeToTerms();
    await poManager.getPlaceOrderPage().placeOrder();
    
    // Verify order success
    expect(await poManager.getPlaceOrderPage().isOrderPlacedSuccessfully()).toBeTruthy();
    const orderNum = await poManager.getPlaceOrderPage().getOrderNumber();
    console.log('Order placed successfully:', orderNum);
});
```

---

## 6. Key Features & Best Practices Implemented

### ✅ Robustness
- Multiple selector strategies for cross-browser compatibility
- Comprehensive error handling and fallbacks
- Smart waits for dynamic content loading
- Network idle waits for async operations

### ✅ Reusability
- Page objects encapsulate complex interactions
- Methods are composable and chainable
- POManager provides centralized access
- Easy to extend with new functionality

### ✅ Maintainability
- Clear method names describing actions
- Comprehensive javadoc-style comments
- Logical grouping of related locators
- Separation of concerns between pages

### ✅ Testability
- Independent test cases
- Clear Arrange-Act-Assert pattern
- Meaningful assertions and logging
- Easy to run specific or grouped tests

---

## 7. Running the Tests

### Run all checkout tests
```bash
npx playwright test CheckoutPage_POM.spec.ts
```

### Run all place order tests
```bash
npx playwright test PlaceOrderPage_POM.spec.ts
```

### Run specific test
```bash
npx playwright test CheckoutPage_POM.spec.ts -g "TC001"
```

### Run with specific browser
```bash
npx playwright test CheckoutPage_POM.spec.ts --project=chromium
```

### Run in headed mode (see browser window)
```bash
npx playwright test CheckoutPage_POM.spec.ts --headed
```

### Generate test report
```bash
npx playwright test CheckoutPage_POM.spec.ts
npx playwright show-report
```

---

## 8. Troubleshooting

### Issue: "Element not found" errors
**Solution**: Update selectors in the page object if the application UI changes.

### Issue: Timeouts in fillCompleteOrderForm
**Solution**: Check if country dropdown requires a specific format or has changed selectors.

### Issue: Form validation errors not clearing
**Solution**: Add explicit wait times between field fills using `await page.waitForTimeout(500)`.

### Issue: Discount code not applying
**Solution**: Verify the discount code is valid and the button selector is correct for the current version.

---

## 9. Future Enhancements

- Add support for multiple shipping methods
- Implement gift card/loyalty points functionality
- Add payment method selection (Credit Card, PayPal, etc.)
- Implement order tracking page object
- Add invoice/receipt generation verification
- Support for different tax calculations by region

---

## 10. Files Updated/Created

| File | Type | Status |
|------|------|--------|
| pageobjects_ts/CheckoutPage.ts | New POM | ✅ Created |
| pageobjects_ts/PlaceOrderPage.ts | New POM | ✅ Created |
| pageobjects_ts/POManager.ts | Updated | ✅ Modified |
| tests/CheckoutPage_POM.spec.ts | New Tests | ✅ Created |
| tests/PlaceOrderPage_POM.spec.ts | New Tests | ✅ Created |

---

## 11. Test Execution Tips

### For CI/CD Pipeline
```bash
# Run all tests in JSON reporter format for integration
npx playwright test --reporter=json > test-results.json

# Run with timeout management for flaky networks
npx playwright test --timeout=60000
```

### For Debugging
```bash
# Debug mode with inspector
npx playwright test --debug

# Soft assertion to continue on failure
expect.soft(condition).toBeTruthy();
```

---

This comprehensive documentation ensures that QA engineers can effectively use the Checkout and Place Order page objects for testing all major e-commerce functionalities in the Rahul Shetty Academy application.
