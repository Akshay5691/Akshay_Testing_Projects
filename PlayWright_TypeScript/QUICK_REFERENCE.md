# Checkout & Place Order - Quick Reference Guide

## Quick Start

### Basic Checkout Test
```typescript
import { expect } from '@playwright/test';
import { customTest as test } from '../utils_ts/test-base';

test('Quick Checkout Test', async ({ page, poManager }) => {
    // Setup
    const productName = 'iphone 13 pro';
    await poManager.getDashboardPage().waitForDashboardToLoad();
    
    // Add to cart
    await poManager.getDashboardPage().searchProductAddCart(productName);
    
    // Go to checkout
    await poManager.getDashboardPage().navigateToCart();
    await poManager.getCartPage().waitForCartToLoad();
    await poManager.getCartPage().checkout();
    
    // Verify checkout page
    expect(await poManager.getCheckoutPage().isCheckoutPageLoaded()).toBeTruthy();
});
```

### Basic Place Order Test
```typescript
test('Quick Place Order Test', async ({ page, poManager }) => {
    // ... [previous checkout code] ...
    
    // Proceed to place order
    await poManager.getCheckoutPage().proceedToPlaceOrder();
    await poManager.getPlaceOrderPage().waitForPlaceOrderPageToLoad();
    
    // Fill form
    await poManager.getPlaceOrderPage().fillCompleteOrderForm(
        'John Doe',           // name
        'john@test.com',      // email
        '1234567890',         // phone
        '123 Main St',        // street
        'New York',           // city
        'NY',                 // state
        '10001',              // postal code
        'United States',      // country
        'John Doe',           // card name
        '123'                 // CVV
    );
    
    // Complete order
    await poManager.getPlaceOrderPage().agreeToTerms();
    await poManager.getPlaceOrderPage().placeOrder();
    
    // Verify success
    expect(await poManager.getPlaceOrderPage().isOrderPlacedSuccessfully()).toBeTruthy();
});
```

---

## CheckoutPage Common Methods

| Method | Purpose | Returns |
|--------|---------|---------|
| `waitForCheckoutPageToLoad()` | Wait for page load | void |
| `isCheckoutPageLoaded()` | Check if loaded | boolean |
| `getProductCount()` | Get cart item count | number |
| `getAllProductNames()` | Get all products | string[] |
| `verifyProductInCheckout(name)` | Check product exists | boolean |
| `getTotalPrice()` | Get order total | string \| null |
| `getSubtotal()` | Get subtotal | string \| null |
| `applyDiscountCode(code)` | Apply coupon | boolean |
| `removeProductFromCheckout(name)` | Remove product | boolean |
| `updateProductQuantity(name, qty)` | Update qty | boolean |
| `isPlaceOrderButtonVisible()` | Button visible | boolean |
| `isPlaceOrderButtonEnabled()` | Button enabled | boolean |
| `proceedToPlaceOrder()` | Go to order page | void |

---

## PlaceOrderPage Common Methods

| Method | Purpose | Returns |
|--------|---------|---------|
| `waitForPlaceOrderPageToLoad()` | Wait for page load | void |
| `isPlaceOrderPageLoaded()` | Check if loaded | boolean |
| `fillPersonalDetails(...)` | Fill name/email | void |
| `fillShippingAddress(...)` | Fill address | void |
| `selectCountry(name)` | Select country | void |
| `fillPaymentDetails(...)` | Fill card info | void |
| `agreeToTerms()` | Accept terms | boolean |
| `placeOrder()` | Submit order | void |
| `isOrderPlacedSuccessfully()` | Check success | boolean |
| `getOrderConfirmationMessage()` | Get message | string \| null |
| `getOrderNumber()` | Extract order # | string \| null |
| `getFormValidationErrors()` | Get errors | string[] |
| `fillCompleteOrderForm(...)` | Fill all fields | void |

---

## Common Assertion Examples

```typescript
// Checkout assertions
expect(await poManager.getCheckoutPage().getProductCount()).toBeGreaterThan(0);
expect(await poManager.getCheckoutPage().verifyProductInCheckout('iPhone')).toBeTruthy();
expect(await poManager.getCheckoutPage().getTotalPrice()).not.toBeNull();
expect(await poManager.getCheckoutPage().isPlaceOrderButtonEnabled()).toBeTruthy();

// Place Order assertions
expect(await poManager.getPlaceOrderPage().isOrderPlacedSuccessfully()).toBeTruthy();
expect(await poManager.getPlaceOrderPage().getOrderConfirmationMessage()).toBeTruthy();
expect(await poManager.getPlaceOrderPage().getFormValidationErrors()).toHaveLength(0);
```

---

## Common Form Data

### Test User
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "1234567890"
}
```

### Test Address
```json
{
  "street": "123 Main Street",
  "city": "New York",
  "state": "NY",
  "postalCode": "10001",
  "country": "United States"
}
```

### Test Payment
```json
{
  "cardName": "John Doe",
  "cvv": "123"
}
```

### Test Discount Codes
- `rahulshettyacademy` - Popular test discount code

---

## Test Execution Commands

```bash
# Run all tests
npx playwright test

# Run specific test file
npx playwright test CheckoutPage_POM.spec.ts
npx playwright test PlaceOrderPage_POM.spec.ts

# Run specific test by name
npx playwright test -g "TC001"
npx playwright test -g "Checkout"

# Run in debug mode
npx playwright test --debug

# Run headed (see browser)
npx playwright test --headed

# Run specific browser
npx playwright test --project=chromium
npx playwright test --project=firefox

# Single test
npx playwright test CheckoutPage_POM.spec.ts -g "TC001"

# View report
npx playwright show-report

# Record new test
npx playwright codegen https://rahulshettyacademy.com/client
```

---

## Debugging Tips

### 1. Add Logging
```typescript
console.log('Product count:', await poManager.getCheckoutPage().getProductCount());
console.log('Total price:', await poManager.getCheckoutPage().getTotalPrice());
```

### 2. Add Screenshots
```typescript
await page.screenshot({ path: 'checkout.png' });
```

### 3. Use Debug Mode
```bash
npx playwright test --debug
```

### 4. Pause Execution
```typescript
await page.pause();
```

### 5. Wait for Element
```typescript
await page.waitForSelector('button:has-text("Place Order")', { timeout: 5000 });
```

---

## File Structure

```
PlayWright_TypeScript/
├── pageobjects_ts/
│   ├── CheckoutPage.ts          ✅ NEW
│   ├── PlaceOrderPage.ts        ✅ NEW
│   ├── POManager.ts             ✅ UPDATED
│   ├── LoginPage.ts
│   ├── DashboardPage.ts
│   ├── CartPage.ts
│   └── ...
├── tests/
│   ├── CheckoutPage_POM.spec.ts     ✅ NEW
│   ├── PlaceOrderPage_POM.spec.ts   ✅ NEW
│   ├── CartPage_POM.spec.ts
│   └── ...
└── CHECKOUT_PLACEORDER_DOCUMENTATION.md  ✅ NEW
```

---

## Test Tags

### Checkout Tests
- `@Checkout` - All checkout tests
- `@Checkout TC001` - Specific test case

### Place Order Tests
- `@PlaceOrder` - All place order tests
- `@PlaceOrder TC010` - Specific test case

### Usage
```bash
npx playwright test --grep "@Checkout"
npx playwright test --grep "@PlaceOrder TC010"
```

---

## Expected Test Data

### Product Names in System
- ADIDAS ORIGINAL
- iphone 13 pro
- SAMSUNG 360
- ZX2 Color

### Test Credentials
- Email: `rahulshetty@gmail.com`
- Password: `Iamking@000`

---

## Common Errors & Solutions

| Error | Solution |
|-------|----------|
| "Element not found" | Check if selectors match current UI |
| "Timeout waiting for" | Increase timeout or check network |
| "Button not enabled" | Verify form is valid before clicking |
| "Country not selected" | Try using country code instead of name |
| "Discount not applied" | Verify discount code is valid |

---

## Next Steps

1. ✅ Copy test data to your testing framework
2. ✅ Run single test to verify setup: `npx playwright test CheckoutPage_POM.spec.ts -g "TC001"`
3. ✅ Run all tests: `npx playwright test`
4. ✅ View report: `npx playwright show-report`
5. ✅ Integrate into CI/CD pipeline

---

## Support Resources

- Full Documentation: `CHECKOUT_PLACEORDER_DOCUMENTATION.md`
- Playwright Docs: https://playwright.dev
- Page Object Model Pattern: https://playwright.dev/docs/pom
- Test Best Practices: Study existing test files in `/tests` folder

---

Last Updated: April 11, 2026
