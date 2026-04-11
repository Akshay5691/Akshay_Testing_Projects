# Test Cases Documentation - Rahul Shetty Academy E-Commerce Application

## Overview
These test suites validate major functionalities of the e-commerce application at https://rahulshettyacademy.com/client using Playwright and Page Object Model design pattern.

---

## Test Files Created

### 1. **LoginPage.spec.ts** - Login Functionality Tests
Located in: `tests/LoginPage.spec.ts`

#### Test Cases:
- **TC001**: Verify Login Page Loads Successfully
  - Validates page title and visibility of login form elements
  - Checks email input, password input, and login button

- **TC002**: Valid Login with Correct Credentials
  - Tests successful login with valid credentials
  - Verifies dashboard products are displayed after login

- **TC003**: Verify Error Message with Invalid Credentials
  - Tests login with incorrect credentials
  - Validates error message display

- **TC004**: Email Field Accepts Valid Email Format
  - Verifies email input accepts valid format
  - Checks filled value matches input

- **TC005**: Password Field Masks Input
  - Verifies password field type is 'password'
  - Ensures special password masking

- **TC006**: Login Button is Clickable
  - Validates login button is enabled
  - Checks button clickability

- **TC007**: Verify Login Page URL
  - Verifies current URL contains expected domain

---

### 2. **DashboardPage.spec.ts** - Dashboard & Product Search Tests
Located in: `tests/DashboardPage.spec.ts`

#### Test Cases:
- **TC001**: Verify Dashboard Displays Products
  - Validates dashboard loads with product list
  - Checks product count > 0

- **TC002**: Verify All Products Have Product Names
  - Verifies each product has a name
  - Validates product names are not empty

- **TC003**: Search and Add Product to Cart
  - Tests search functionality for specific product
  - Validates add-to-cart action

- **TC004**: Verify Add to Cart Button Functionality
  - Checks all add-to-cart buttons are present
  - Validates buttons are enabled

- **TC005**: Navigate to Cart Page
  - Tests navigation from dashboard to cart
  - Verifies cart page loads successfully

- **TC006**: Navigate to Orders Page
  - Tests navigation to order history
  - Verifies orders page elements

- **TC007**: Verify Product Card Layout
  - Validates structure of product cards
  - Checks presence of required elements

- **TC008**: Search for Non-Existent Product
  - Tests behavior when searching for unavailable product
  - Validates product doesn't exist in list

- **TC009**: Add Multiple Products to Cart
  - Tests adding more than one product
  - Validates cart functionality with multiple items

- **TC010**: Verify Page Displays Correctly
  - Validates page height and width
  - Checks responsive layout

---

### 3. **CartPage.spec.ts** - Cart Operations Tests
Located in: `tests/CartPage.spec.ts`

#### Test Cases:
- **TC001**: Navigate to Cart Page
  - Validates navigation to cart
  - Checks checkout button visibility

- **TC002**: Add Product and Verify in Cart
  - Tests complete flow: add product → verify in cart
  - Validates product display

- **TC003**: Verify Checkout Button is Visible
  - Checks checkout button visibility and availability
  - Validates button is enabled

- **TC004**: Proceed to Checkout
  - Tests clicking checkout button
  - Verifies checkout page loads with form

- **TC005**: Verify Empty Cart Handling
  - Tests navigation to cart without products
  - Validates page loads properly

- **TC006**: Verify Product Information in Cart
  - Validates product details display in cart
  - Checks product name visibility

- **TC007**: Verify Cart Product Quantity
  - Checks number of items in cart
  - Validates cart item list

- **TC008**: Verify Cart Page URL
  - Validates URL contains 'cart' path
  - Checks routing

- **TC009**: Verify Checkout Form Fields
  - Validates all form fields are present
  - Checks country field, email field, submit button

- **TC010**: Verify Cart Page Layout and Elements
  - Validates overall cart page structure
  - Checks key elements presence

---

### 4. **OrdersHistoryPage.spec.ts** - Order History Tests
Located in: `tests/OrdersHistoryPage.spec.ts`

#### Test Cases:
- **TC001**: Navigate to Orders Page
  - Tests navigation to order history
  - Validates orders table loads

- **TC002**: Verify Orders Table is Displayed
  - Checks orders table visibility
  - Validates table rows

- **TC003**: Verify Order Table Headers
  - Validates table structure
  - Checks table presence

- **TC004**: Verify Order ID Column is Present
  - Validates order ID data in table
  - Checks first row order ID

- **TC005**: Verify Action Buttons in Order Rows
  - Tests action buttons in each row
  - Validates buttons are clickable

- **TC006**: Click on Order and Verify Details Page
  - Tests clicking order details button
  - Verifies order details page loads

- **TC007**: Verify Page URL Contains Orders Path
  - Validates URL contains 'myorders'
  - Checks routing

- **TC008**: Verify Orders Load with Network Idle
  - Tests page loads with network idle wait
  - Validates data is fully loaded

- **TC009**: Search for Specific Order ID
  - Tests finding specific orders
  - Validates order search functionality

- **TC010**: Verify Order History Page Responsiveness
  - Tests responsive page layout
  - Checks page dimensions

- **TC011**: Verify Order Table Pagination (if applicable)
  - Tests pagination controls if present
  - Validates table navigation

---

### 5. **CompleteUserFlow.spec.ts** - End-to-End Tests
Located in: `tests/CompleteUserFlow.spec.ts`

#### Test Cases:
- **TC001**: Complete Purchase Flow
  - Full workflow: Login → Search Product → Add to Cart → Checkout → Place Order
  - Validates entire purchase process

- **TC002**: Complete Purchase and Verify Order History
  - Extended flow: Complete purchase + verify in order history
  - Tests data persistence across pages

- **TC003**: Multiple Products Purchase Flow
  - Tests adding and purchasing multiple products
  - Validates multi-product cart handling

- **TC004**: Test Session Persistence
  - Tests session maintenance across navigation
  - Validates login state persistence

- **TC005**: Cart to Orders History Workflow
  - Tests navigation between cart and orders
  - Validates page transitions

- **TC006**: Verify Order Confirmation Message
  - Tests confirmation message after order
  - Validates success message display

- **TC007**: Verify Order ID is Generated
  - Tests order ID generation
  - Validates ID format and content

- **TC008**: Test Different Product Selection
  - Tests with various products
  - Validates product selection flexibility

- **TC009**: Verify Complete Flow with All Navigation
  - Comprehensive test of all pages
  - Tests all navigation paths

- **TC010**: Test Navigation Between All Pages
  - Tests cart → orders → dashboard navigation
  - Validates all routing

---

## Running the Tests

### Run All Tests
```bash
npx playwright test
```

### Run Specific Test File
```bash
npx playwright test tests/LoginPage.spec.ts
npx playwright test tests/DashboardPage.spec.ts
npx playwright test tests/CartPage.spec.ts
npx playwright test tests/OrdersHistoryPage.spec.ts
npx playwright test tests/CompleteUserFlow.spec.ts
```

### Run Specific Test with Tag
```bash
# Run all Login tests
npx playwright test --grep "@Login"

# Run all E2E tests
npx playwright test --grep "@E2E"

# Run all Dashboard tests
npx playwright test --grep "@Dashboard"

# Run all Cart tests
npx playwright test --grep "@Cart"

# Run all Order History tests
npx playwright test --grep "@OrderHistory"
```

### Run Tests in Debug Mode
```bash
npx playwright test --debug
```

### Run Tests with Headed Mode (see browser)
```bash
npx playwright test --headed
```

### Run Tests with Specific Browser
```bash
npx playwright test --project=chromium
npx playwright test --project=firefox
npx playwright test --project=webkit
```

### Run Tests and View Report
```bash
npx playwright test
npx playwright show-report
```

---

## Test Configuration

### Important Notes:
1. **Login Credentials**: Tests use the following credentials by default
   - Email: `rahulshetty@gmail.com`
   - Password: `Iamking@000`

2. **Session Storage**: E2E and other tests use stored session state (`state.json`) for faster execution
   - Initial login is performed in `test.beforeAll()`
   - Session is saved to `state.json`
   - Subsequent tests use this saved session

3. **Network Wait**: Tests use `waitForLoadState('networkidle')` to ensure all data is loaded

4. **Page Objects**: All tests use Page Object Model from `pageobjects_ts/` folder:
   - `LoginPage.ts`
   - `DashboardPage.ts`
   - `CartPage.ts`
   - `OrdersHistoryPage.ts`
   - `OrdersReviewPage.ts`
   - `POManager.ts`

---

## Functionalities Covered

### ✅ Login Module
- Valid login
- Invalid login handling
- Form field validation
- Password masking

### ✅ Dashboard Module
- Product listing
- Product search
- Add to cart functionality
- Navigate to cart
- Navigate to orders

### ✅ Cart Module
- Display cart items
- Verify product in cart
- Checkout button
- Empty cart handling
- Cart page navigation

### ✅ Checkout & Order Review
- Country selection
- Email verification
- Order submission
- Order confirmation
- Order ID generation

### ✅ Order History
- View orders list
- Search orders
- Order details
- Order tracking

### ✅ End-to-End Flows
- Complete purchase process
- Multiple product purchase
- Order verification
- Session persistence
- Navigation between all pages

---

## Test Data

### Products Available for Testing:
- `iphone 13 pro` (primary test product)
- Other products available on dashboard

### Countries for Testing:
- `India` (tested in checkout)

---

## Troubleshooting

### Common Issues:

1. **Test Fails Due to Login**
   - Verify credentials are correct in test file
   - Check website is accessible
   - Ensure browser can access the URL

2. **Timeout Errors**
   - Increase timeout in `playwright.config.ts`
   - Check network connectivity
   - Verify website is responsive

3. **Element Not Found**
   - Verify selectors in page objects
   - Check website structure hasn't changed
   - Update selectors if UI changed

4. **Session Not Persisting**
   - Delete `state.json` file and re-run
   - Verify `beforeAll` hook executes correctly
   - Check browser context is properly created

---

## Best Practices Used

1. **Page Object Model**: Centralized selectors and methods
2. **Session Reuse**: Faster test execution using stored session
3. **Proper Waits**: Network idle and element waits
4. **Assertions**: Clear and specific assertions
5. **Test Tags**: Organization with @Tag naming
6. **Test Isolation**: Each test is independent
7. **Error Handling**: Graceful handling of missing elements

---

## Next Steps

1. Run tests to validate all functionalities
2. Review test reports in `playwright-report/`
3. Customize tests as needed for your requirements
4. Add more test cases for edge cases
5. Integrate with CI/CD pipeline

---

## Support & Documentation

- Playwright Documentation: https://playwright.dev
- Page Objects Used: `pageobjects_ts/` folder
- Test Reports: `playwright-report/` folder
- Allure Reports: `allure-results/` folder
