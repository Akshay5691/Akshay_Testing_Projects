# Base Test Fixture Implementation Guide

## Overview
The base test fixture has been created to eliminate the need for `page.goto()` in every test case. This fixture automatically handles page navigation and POManager initialization for all test cases.

## What Changed

### 1. **Updated Base Test File** - `utils_ts/test-base.ts`
The custom test fixture now provides:
- **Automatic page navigation** to `https://rahulshettyacademy.com/client` before each test
- **POManager initialization** - automatically instantiated and injected into tests
- **Browser context management** - handles session storage and cleanup

### 2. **Test Fixtures Available**
When using `customTest`, the following fixtures are available in your test functions:

| Fixture | Type | Purpose |
|---------|------|---------|
| `page` | Page | Playwright Page object (auto-navigated to base URL) |
| `poManager` | POManager | Pre-initialized POManager instance |
| `testDataForOrder` | TestDataForOrder | Test data fixture (username, password, productName) |
| `webContext` | BrowserContext | Browser context for session management |

## How to Use

### Before (Old Pattern)
```typescript
import { test, expect } from '@playwright/test';
import { POManager } from '../pageobjects_ts/POManager';

let poManager: POManager;
let page: Page;
let webContext: BrowserContext;

test.describe('Dashboard Page Tests', () => {
    test.beforeEach(async ({ browser }) => {
        webContext = await browser.newContext({ storageState: 'state.json' });
        page = await webContext.newPage();
        poManager = new POManager(page);
    });

    test.afterEach(async () => {
        await webContext.close();
    });

    test('TC001', async () => {
        await page.goto('https://rahulshettyacademy.com/client');
        // test code...
    });
});
```

### After (New Pattern)
```typescript
import { expect } from '@playwright/test';
import { customTest as test } from '../utils_ts/test-base';

test.describe('Dashboard Page Tests', () => {
    test.beforeAll(async ({ browser }) => {
        // Only needed for login/session setup
        const context = await browser.newContext();
        const loginPage = await context.newPage();
        
        await loginPage.goto('https://rahulshettyacademy.com/client');
        await loginPage.locator('#userEmail').fill('rahulshetty@gmail.com');
        await loginPage.locator('#userPassword').fill('Iamking@000');
        await loginPage.locator("[value='Login']").click();
        await loginPage.waitForLoadState('networkidle');
        await loginPage.context().storageState({ path: 'state.json' });
        
        await context.close();
    });

    test('TC001', async ({ page, poManager }) => {
        // page is already navigated, poManager is initialized
        await poManager.getDashboardPage().waitForDashboardToLoad();
        // test code...
    });
});
```

## Updated Test Files

The following test files have been updated to use the new base test fixture:

1. ✅ `tests/CartPage_POM.spec.ts` - 8 test cases updated
2. ✅ `tests/DashboardPage_POM.spec.ts` - 12 test cases updated
3. ✅ `tests/LoginPage_POM.spec.ts` - 3 test cases updated
4. ✅ `tests/OrdersHistoryPage_POM.spec.ts` - 10 test cases updated
5. ✅ `tests/ClientAppPO.spec.ts` - Dynamic and custom tests updated

## Benefits

1. **Reduced Code Redundancy**: No need to repeat `page.goto()` in every test
2. **Cleaner Test Code**: Focus on test logic instead of setup
3. **Consistency**: All tests follow the same initialization pattern
4. **Maintainability**: Centralized page navigation logic
5. **Automatic Cleanup**: Browser context and page cleanup handled automatically
6. **Session Management**: Automatically reuses saved session state

## Key Files Modified

- `utils_ts/test-base.ts` - Enhanced with page and POManager fixtures
- `tests/*.spec.ts` - All test files updated to use the new fixture pattern

## Running Tests

Tests can be run as before:
```bash
npx playwright test
```

The fixture will automatically:
1. Restore session from `state.json`
2. Navigate to the base URL
3. Initialize POManager
4. Cleanup resources after each test

## Notes

- The `test.beforeAll()` hook is still used for initial login and session setup
- Individual `beforeEach()` and `afterEach()` hooks are no longer needed
- Session state is automatically managed through the fixture
