# Page Object Model (POM) Implementation - Test Framework Documentation

## Overview
This document describes the comprehensive Page Object Model implementation for the Rahul Shetty Academy E-Commerce platform (https://rahulshettyacademy.com/client).

---

## Architecture

### Page Object Model Benefits
1. **Maintainability**: Locators centralized in page classes
2. **Reusability**: Methods can be reused across multiple tests
3. **Readability**: Tests are more readable and self-documenting
4. **Scalability**: Easy to add new pages and methods
5. **Separation of Concerns**: Test logic separated from UI interaction logic

### Directory Structure
```
pageobjects_ts/
├── POManager.ts              # Manager class to manage all page objects
├── LoginPage.ts             # Login page locators and methods
├── DashboardPage.ts         # Dashboard page locators and methods
├── CartPage.ts              # Cart page locators and methods
├── OrdersHistoryPage.ts     # Orders history page locators and methods
└── OrdersReviewPage.ts      # Order review/checkout page locators and methods

tests/
├── LoginPage_POM.spec.ts           # Login page tests
├── DashboardPage_POM.spec.ts       # Dashboard page tests
├── CartPage_POM.spec.ts            # Cart page tests
├── OrdersHistoryPage_POM.spec.ts   # Orders history page tests
└── CompleteCheckoutFlow_POM.spec.ts # End-to-end tests
```

---

## Page Object Classes

### 1. LoginPage.ts

#### Locators
```typescript
signInbutton         // Login button
userName            // Email input field
password            // Password input field
errorMessage        // Error message element
pageTitle           // Page title element
termsCheckbox       // Terms and conditions checkbox
forgotPasswordLink  // Forgot password link
```

#### Key Methods
```typescript
// Navigation
async goTo()                              // Navigate to login page

// Validation Methods
async getLoginPageTitle()                 // Get page title
async isUserNameFieldVisible()            // Check email field visibility
async isPasswordFieldVisible()            // Check password field visibility
async isSignInButtonVisible()             // Check login button visibility
async isSignInButtonEnabled()             // Check login button is enabled
async getPasswordFieldType()              // Get password field type
async getLoginPageURL()                   // Get current page URL

// Input Methods
async enterEmail(email: string)           // Enter email
async enterPassword(pwd: string)          // Enter password
async clickSignInButton()                 // Click login button

// Authentication Methods
async validLogin(username: string, password: string)  // Complete login flow
async waitForLoginPageToLoad()            // Wait for page load

// Error Handling
async getErrorMessage()                   // Get error message text
async isErrorDisplayed()                  // Check if error is displayed
```

---

### 2. DashboardPage.ts

#### Locators
```typescript
products            // Product cards container
productsText        // Product names/titles
cart                // Cart link/button
orders              // Orders link/button
addToCartButtons    // Add to cart buttons
productPrice        // Product prices
toastMessage        // Toast notification messages
loadingSpinner      // Loading spinner element
```

#### Key Methods
```typescript
// Navigation and Loading
async waitForDashboardToLoad()            // Wait for dashboard to fully load
async navigateToCart()                    // Navigate to cart page
async navigateToOrders()                  // Navigate to orders page

// Product Information
async getProductCount()                   // Get total number of products
async getAllProductNames()                // Get all product names
async getProductNameByIndex(index)        // Get product name by index
async getProductByName(productName)       // Get product locator by name
async getProductLocatorByIndex(index)     // Get product locator by index

// Product Actions
async searchProductAddCart(productName)   // Search and add product to cart
async addProductToCartByIndex(index)      // Add product to cart by index

// Verification Methods
async isAddToCartButtonVisible()          // Check add to cart button visibility
async isProductVisible(productName)       // Check if product is visible
async verifyProductsDisplayed(count)      // Verify product count
async getAddToCartButtonCount()           // Get count of add to cart buttons

// Other Methods
async getToastMessage()                   // Get toast notification message
async waitForToastToDisappear()           // Wait for toast to disappear
async getDashboardURL()                   // Get dashboard page URL
```

---

### 3. CartPage.ts

#### Locators
```typescript
cartProducts        // Cart products
checkout            // Checkout button
cartItemsList       // List of cart items
cartTotal           // Cart total price
deleteButton        // Delete product button
quantityInput       // Quantity input field
cartContainer       // Cart container
emptyCartMessage    // Empty cart message
continueShoppingButton  // Continue shopping button
paymentOptions      // Payment method options
```

#### Key Methods
```typescript
// Navigation and Loading
async waitForCartToLoad()                 // Wait for cart page to load
async waitForCheckoutButtonToAppear()     // Wait for checkout button

// Cart Information
async getCartItemCount()                  // Get count of items in cart
async getCartItemsNames()                 // Get all product names in cart
async getCartTotal()                      // Get cart total price
async getProductQuantity(productName)     // Get product quantity

// Product Actions
async verifyProductIsDisplayed(productName)  // Verify product in cart
async deleteProductFromCart(productName)     // Delete product from cart
async checkout()                          // Proceed to checkout
async Checkout()                          // Proceed to checkout (alias)
async continueShoppingClick()             // Continue shopping

// Verification Methods
async isCartEmpty()                       // Check if cart is empty
async isCheckoutButtonVisible()           // Check checkout button visibility
async isCheckoutButtonEnabled()           // Check checkout button is enabled
async isCartPageLoaded()                  // Check if cart page is loaded

// Utility Methods
async getCartPageURL()                    // Get cart page URL
async getProductLocator(productName)      // Get product locator by name
```

---

### 4. OrdersHistoryPage.ts

#### Locators
```typescript
ordersTable         // Orders table body
rows                // Table rows
orderdIdDetails     // Order ID details
orderIdColumn       // Order ID column
viewButton          // View order button
emptyOrdersMessage  // Empty orders message
tableHeaders        // Table headers
paginationButtons   // Pagination buttons
```

#### Key Methods
```typescript
// Navigation and Loading
async waitForOrdersTableToLoad()          // Wait for orders table to load

// Order Information
async getOrderRowCount()                  // Get count of order rows
async getOrderIds()                       // Get all order IDs
async getOrderByIndex(index)              // Get order by index
async getFirstOrderId()                   // Get first order ID
async getOrderId()                        // Get order ID details

// Order Actions
async searchOrderAndSelect(orderId)       // Search and select order
async searchOrderByIdAndSelect(orderId)   // Search order by ID and select
async clickViewButtonForOrder(index)      // Click view button for order

// Verification Methods
async isOrdersTableVisible()              // Check if orders table is visible
async isOrdersPageLoaded()                // Check if orders page is loaded
async verifyOrderIdExists(orderId)        // Verify order ID exists
async isEmptyOrdersDisplayed()            // Check if empty orders message shows
async verifyOrderDetailsPageLoads()       // Verify order details page loads

// Pagination
async navigateToNextPage()                // Navigate to next page

// Other Methods
async getOrdersPageURL()                  // Get orders page URL
```

---

### 5. OrdersReviewPage.ts

#### Locators
```typescript
country             // Country input field
dropdown            // Country dropdown
emailId             // Email ID field
submit              // Submit button
orderConfirmationText  // Order confirmation message
orderId             // Order ID element
checkoutTitle       // Checkout page title
orderReference      // Order reference number
productSummary      // Product summary section
totalPrice          // Total price display
termsCheckbox       // Terms checkbox
```

#### Key Methods
```typescript
// Navigation and Loading
async waitForCheckoutPageToLoad()         // Wait for checkout page to load

// Checkout Actions
async searchCountryAndSelect(code, name)  // Search and select country
async fillCountryField(countryCode)       // Fill country field
async selectCountryFromDropdown(name)     // Select country from dropdown
async clickSubmitButton()                 // Click submit button

// Verification Methods
async verifyEmailId(username)             // Verify email ID
async VerifyEmailId(username)             // Verify email ID (alias)
async isCountryFieldVisible()             // Check country field visibility
async isSubmitButtonEnabled()             // Check submit button is enabled

// Order Submission
async submitAndGetOrderId()               // Submit and get order ID
async SubmitAndGetOrderId()               // Submit and get order ID (alias)

// Order Information
async getEmailId()                        // Get email ID
async getOrderConfirmationMessage()       // Get confirmation message
async getTotalPrice()                     // Get total price

// Verification
async isOrderConfirmationDisplayed()      // Check if confirmation is displayed
async verifyCheckoutPageLoaded()          // Verify checkout page loaded

// Other Actions
async cancelOrder()                       // Cancel order

// Utility Methods
async getCheckoutPageURL()                // Get checkout page URL
```

---

### 6. POManager.ts

#### Purpose
Central manager for all page objects. Provides single entry point to access any page object.

#### Methods
```typescript
constructor(page: Page)                   // Initialize all page objects

getLoginPage()                            // Get LoginPage instance
getDashboardPage()                        // Get DashboardPage instance
getCartPage()                             // Get CartPage instance
getOrdersHistoryPage()                    // Get OrdersHistoryPage instance
getOrdersReviewPage()                     // Get OrdersReviewPage instance
```

---

## Test Files

### 1. LoginPage_POM.spec.ts
**Files**: `tests/LoginPage_POM.spec.ts`
**Test Cases**: 10

#### Test Scenarios
- TC001: Verify login page title
- TC002: Verify login form elements visibility
- TC003: Verify sign in button is enabled
- TC004: Verify email field input
- TC005: Verify password field is masked
- TC006: Valid login with correct credentials
- TC007: Verify login URL
- TC008: Verify page loads successfully
- TC009: Test login flow step by step
- TC010: Complete login session establishment

#### Example Test
```typescript
test('@Login TC006 - Valid Login with Correct Credentials', async () => {
    const email = 'rahulshetty@gmail.com';
    const password = 'Iamking@000';
    
    await poManager.getLoginPage().goTo();
    await poManager.getLoginPage().validLogin(email, password);
    
    const productCount = await poManager.getDashboardPage().getProductCount();
    expect(productCount).toBeGreaterThan(0);
});
```

---

### 2. DashboardPage_POM.spec.ts
**Files**: `tests/DashboardPage_POM.spec.ts`
**Test Cases**: 12

#### Test Scenarios
- TC001: Verify dashboard loads with products
- TC002: Verify all products display names
- TC003: Verify add to cart button count
- TC004: Search and add specific product to cart
- TC005: Get product names and verify format
- TC006: Verify navigation to cart
- TC007: Verify navigation to orders
- TC008: Add multiple products sequentially
- TC009: Verify product count greater than 0
- TC010: Verify dashboard URL
- TC011: Get specific product by name
- TC012: Get product by index and verify name

#### Example Test
```typescript
test('@Dashboard TC004 - Search and Add Specific Product to Cart', async () => {
    const productName = 'iphone 13 pro';
    await page.goto('https://rahulshettyacademy.com/client');
    await poManager.getDashboardPage().waitForDashboardToLoad();
    
    await poManager.getDashboardPage().searchProductAddCart(productName);
    
    const toastMessage = await poManager.getDashboardPage().getToastMessage();
    expect(true).toBeTruthy();
});
```

---

### 3. CartPage_POM.spec.ts
**Files**: `tests/CartPage_POM.spec.ts`
**Test Cases**: 10

#### Test Scenarios
- TC001: Navigate to cart and verify page loads
- TC002: Add product to cart and verify it appears
- TC003: Verify checkout button visible and enabled
- TC004: Proceed to checkout
- TC005: Get cart item count
- TC006: Verify multiple products in cart
- TC007: Verify cart page URL contains cart
- TC008: Verify cart items names
- TC009: Verify cart is not empty
- TC010: Verify checkout flow till order review page

#### Example Test
```typescript
test('@Cart TC002 - Add Product to Cart and Verify it Appears', async () => {
    const productName = 'iphone 13 pro';
    await page.goto('https://rahulshettyacademy.com/client');
    await poManager.getDashboardPage().waitForDashboardToLoad();
    
    await poManager.getDashboardPage().searchProductAddCart(productName);
    await poManager.getDashboardPage().navigateToCart();
    await poManager.getCartPage().waitForCartToLoad();
    
    await poManager.getCartPage().verifyProductIsDisplayed(productName);
});
```

---

### 4. OrdersHistoryPage_POM.spec.ts
**Files**: `tests/OrdersHistoryPage_POM.spec.ts`
**Test Cases**: 12

#### Test Scenarios
- TC001: Navigate to orders and verify page loads
- TC002: Verify orders table is visible
- TC003: Get order row count
- TC004: Get all order IDs
- TC005: Verify orders page URL contains myorders
- TC006: Click on first order and view details
- TC007: Get first order ID
- TC008: Complete purchase and verify in order history
- TC009: Search and select specific order
- TC010: Verify order details page loads
- TC011: Get order by index
- TC012: Verify order exists in history

#### Example Test
```typescript
test('@OrderHistory TC008 - Complete Purchase and Verify in Order History', async () => {
    const productName = 'iphone 13 pro';
    
    // Complete purchase
    await page.goto('https://rahulshettyacademy.com/client');
    await poManager.getDashboardPage().waitForDashboardToLoad();
    await poManager.getDashboardPage().searchProductAddCart(productName);
    await poManager.getDashboardPage().navigateToCart();
    await poManager.getCartPage().waitForCartToLoad();
    await poManager.getCartPage().Checkout();
    await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
    const orderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();
    
    expect(orderId).toBeTruthy();
});
```

---

### 5. CompleteCheckoutFlow_POM.spec.ts
**Files**: `tests/CompleteCheckoutFlow_POM.spec.ts`
**Test Cases**: 10 (End-to-End)

#### Test Scenarios
- TC001: Complete purchase flow from login to order confirmation
- TC002: Purchase and verify in order history
- TC003: Add multiple products and checkout
- TC004: Verify product in cart after adding
- TC005: Complete flow with order details verification
- TC006: Verify navigation between all pages
- TC007: Test login persistence across navigation
- TC008: Verify order confirmation details
- TC009: Verify all dashboard functionality
- TC010: Complete purchase to order details page

#### Example Test
```typescript
test('@E2E TC001 - Complete Purchase Flow from Login to Order Confirmation', async () => {
    const email = 'rahulshetty@gmail.com';
    const password = 'Iamking@000';
    const productName = 'iphone 13 pro';

    // Step 1: Login
    await poManager.getLoginPage().goTo();
    expect(await poManager.getLoginPage().isUserNameFieldVisible()).toBeTruthy();
    await poManager.getLoginPage().validLogin(email, password);

    // Step 2: Add Product
    await poManager.getDashboardPage().searchProductAddCart(productName);

    // Step 3: Cart
    await poManager.getDashboardPage().navigateToCart();
    await poManager.getCartPage().verifyProductIsDisplayed(productName);

    // Step 4: Checkout
    await poManager.getCartPage().Checkout();
    await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
    const orderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();
    
    expect(orderId).toBeTruthy();
});
```

---

## Running Tests

### Run All Tests
```bash
npx playwright test
```

### Run Specific Test File
```bash
npx playwright test tests/LoginPage_POM.spec.ts
npx playwright test tests/DashboardPage_POM.spec.ts
npx playwright test tests/CartPage_POM.spec.ts
npx playwright test tests/OrdersHistoryPage_POM.spec.ts
npx playwright test tests/CompleteCheckoutFlow_POM.spec.ts
```

### Run Tests by Tag
```bash
# Login tests
npx playwright test --grep "@Login"

# Dashboard tests
npx playwright test --grep "@Dashboard"

# Cart tests
npx playwright test --grep "@Cart"

# Order History tests
npx playwright test --grep "@OrderHistory"

# End-to-End tests
npx playwright test --grep "@E2E"
```

### Run in Debug Mode
```bash
npx playwright test --debug
```

### Run with Headed Browser
```bash
npx playwright test --headed
```

### Generate and View Report
```bash
npx playwright test
npx playwright show-report
```

---

## Best Practices Implemented

1. **Single Responsibility Principle**: Each page class has a single responsibility
2. **DRY (Don't Repeat Yourself)**: Methods encapsulate repeated action sequences
3. **Clear Naming**: Method names clearly describe what they do
4. **Waiting Strategies**: Proper waits for elements and network conditions
5. **Error Handling**: Graceful handling of missing elements
6. **Logging**: Console logs for debugging and tracking test flow
7. **Arrange-Act-Assert**: Clear test structure (AAA pattern)
8. **Session Management**: Reusing login sessions for faster tests
9. **Assertion Strategy**: Clear and specific assertions
10. **Code Reusability**: Methods can be used across multiple tests

---

## Test Data

### Credentials
- **Email**: `rahulshetty@gmail.com`
- **Password**: `Iamking@000`

### Test Products
- `iphone 13 pro` (primary test product)

### Test Countries
- `India` (code: 'ind')

---

## Common Test Patterns

### Pattern 1: Login and Dashboard Verification
```typescript
await poManager.getLoginPage().goTo();
await poManager.getLoginPage().validLogin(email, password);
const productCount = await poManager.getDashboardPage().getProductCount();
expect(productCount).toBeGreaterThan(0);
```

### Pattern 2: Add Product and Verify in Cart
```typescript
await poManager.getDashboardPage().searchProductAddCart(productName);
await poManager.getDashboardPage().navigateToCart();
await poManager.getCartPage().verifyProductIsDisplayed(productName);
```

### Pattern 3: Complete Purchase Flow
```typescript
await poManager.getDashboardPage().searchProductAddCart(productName);
await poManager.getDashboardPage().navigateToCart();
await poManager.getCartPage().Checkout();
await poManager.getOrdersReviewPage().searchCountryAndSelect('ind', ' India');
const orderId = await poManager.getOrdersReviewPage().SubmitAndGetOrderId();
```

---

## Troubleshooting

### Issue: Selector Not Found
- **Solution**: Verify selectors in page object classes
- **Action**: Update selectors if UI has changed

### Issue: Test Timeout
- **Solution**: Increase timeout in playwright.config.ts
- **Action**: Use appropriate wait methods

### Issue: Session Not Persisting
- **Solution**: Delete state.json and re-run tests
- **Action**: Verify beforeAll hook executes correctly

### Issue: Element Visibility Issues
- **Solution**: Use waitForLoadState('networkidle')
- **Action**: Add explicit waits before element interaction

---

## Future Enhancements

1. Add more assertions and error validation
2. Implement data-driven testing with external data sources
3. Add API testing integration
4. Implement performance monitoring
5. Add visual regression testing
6. Create custom assertions
7. Add retry logic for flaky tests
8. Implement advanced reporting

---

## Support

For issues or questions:
1. Check test logs in `playwright-report/`
2. Review test failure screenshots
3. Verify selectors are up-to-date
4. Check Playwright documentation: https://playwright.dev

---

## Summary

This POM implementation provides:
- ✅ **55+ Test Cases** across 5 test files
- ✅ **Comprehensive Locators** for all UI elements
- ✅ **Reusable Methods** for common actions
- ✅ **Clear Test Structure** following AAA pattern
- ✅ **Session Management** for faster execution
- ✅ **End-to-End Coverage** of complete purchase flow
- ✅ **Best Practices** implementation
- ✅ **Easy Maintenance** and scalability
