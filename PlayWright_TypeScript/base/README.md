# Base Test Framework Documentation

## Overview
The base test framework provides centralized handling for login, website navigation, and common test operations. This eliminates repetitive code and ensures consistency across all test cases.

## Directory Structure

```
base/
├── BaseTest.ts          # Core base class with login and navigation methods
├── BaseSetup.ts         # Setup/teardown and helper methods
└── index.ts             # Export file for base classes
```

## Core Classes

### 1. BaseTest Class
The `BaseTest` class provides core functionality for test execution:

#### Key Methods:

**Login & Navigation:**
- `navigateToBaseURL()` - Navigate to base URL
- `navigateToLoginPage()` - Navigate to login page
- `login(username, password)` - Perform login with credentials
- `loginWithDefaultCredentials()` - Login with pre-configured credentials
- `verifyLoggedIn()` - Verify user is logged in
- `waitForDashboardToLoad()` - Wait for dashboard to be ready

**Session Management:**
- `saveSessionState(filePath)` - Save session to file
- `saveSessionStateFromPage(filePath)` - Save session from page context

**Browser Operations:**
- `getCurrentURL()` - Get current page URL
- `getPageTitle()` - Get page title
- `closePage()` - Close page
- `closeContext()` - Close browser context
- `reloadPage()` - Reload page

**Element Interactions:**
- `isElementVisible(selector)` - Check element visibility
- `getElementText(selector)` - Get element text
- `clickElement(selector)` - Click element
- `fillInput(selector, value)` - Fill input field
- `clearInput(selector)` - Clear input field
- `scrollToElement(selector)` - Scroll to element
- `getElementCount(selector)` - Get matching elements count
- `getAllElementsText(selector)` - Get all matching elements text

**Utility Methods:**
- `takeScreenshot(filePath)` - Take screenshot
- `waitForTime(milliseconds)` - Wait for specified time
- `acceptAlert()` - Accept browser alert
- `dismissAlert()` - Dismiss browser alert
- `setViewportSize(width, height)` - Set viewport size

### 2. BaseSetup Class
The `BaseSetup` class extends `BaseTest` and provides setup/teardown functionality:

#### Key Methods:

**Initialization:**
- `initializeWithSession(browser, sessionPath)` - Create context with existing session
- `setupTestWithLogin(browser, username, password)` - Setup and perform login
- `setupTestWithExistingSession(browser, sessionPath)` - Setup with existing session

**Cleanup:**
- `teardown()` - Close page and context

**Testing Utilities:**
- `assertExpectedVsActual(expected, actual)` - Compare values
- `isResponseSuccessful(statusCode)` - Check if response is successful
- `createTestReport(testName, passed, duration, message)` - Create test report
- `isElementClickable(selector)` - Check if element is clickable
- `waitAndClick(selector, timeout)` - Wait and click element
- `waitAndFill(selector, value, timeout)` - Wait and fill input
- `areMultipleElementsVisible(selector, count)` - Check multiple elements
- `verifyPageContainsText(text)` - Verify page contains text
- `getPageErrorLogs()` - Get page error logs
- `switchToIframe(selector)` - Switch to iframe

## Usage Examples

### Example 1: Using BaseTest Directly
```typescript
import { BaseTest } from '../base/BaseTest';
import { test } from '@playwright/test';

test('Login test', async ({ page }) => {
    const baseTest = new BaseTest(page);
    
    // Perform login
    await baseTest.loginWithDefaultCredentials();
    
    // Verify login
    const isLoggedIn = await baseTest.verifyLoggedIn();
    expect(isLoggedIn).toBeTruthy();
});
```

### Example 2: Using BaseSetup
```typescript
import { BaseSetup } from '../base/BaseSetup';
import { test } from '@playwright/test';

test('Setup with login', async ({ browser }) => {
    const baseSetup = await BaseSetup.setupTestWithLogin(browser);
    
    try {
        // Perform test operations
        const url = await baseSetup.getCurrentURL();
        expect(url).toContain('rahulshettyacademy.com');
    } finally {
        // Cleanup
        await baseSetup.teardown();
    }
});
```

### Example 3: Using Custom Test Fixture
```typescript
import { customTest as test } from '../utils_ts/test-base';

test('Using custom fixture', async ({ page, poManager, baseTest }) => {
    // Page is already navigated and logged in
    // POManager is pre-initialized
    // BaseTest is available for base operations
    
    await poManager.getDashboardPage().waitForDashboardToLoad();
    const productCount = await poManager.getDashboardPage().getProductCount();
    expect(productCount).toBeGreaterThan(0);
});
```

## Test Execution Flow

### Global Setup (global-setup.ts)
1. Runs once before ALL tests
2. Performs login with default credentials
3. Saves session state to `state.json`
4. Ensures all tests have valid session

### Per-Test Execution
1. Fixture creates browser context with saved session
2. Page is automatically navigated to base URL
3. POManager is pre-initialized
4. Test executes with all pre-configured fixtures
5. Fixtures are automatically cleaned up after test

## Configuration

### Current Configuration (playwright.config.js)
- **globalSetup**: `./global-setup.ts` - Runs login before all tests
- **testDir**: `./tests` - Location of test files
- **timeout**: 30 seconds - Max time per test
- **expectTimeout**: 5 seconds - Max wait for assertions
- **screenshot**: `on` - Screenshots on failure
- **trace**: `on` - Tracing enabled for debugging

### Updating Base URL or Credentials
Edit the `BaseTest` class to change default values:

```typescript
protected baseURL = 'https://rahulshettyacademy.com/client';
protected loginURL = 'https://rahulshettyacademy.com/client';
protected defaultUsername = 'rahulshetty@gmail.com';
protected defaultPassword = 'Iamking@000';
protected sessionStoragePath = 'state.json';
```

## Benefits

1. **DRY (Don't Repeat Yourself)** - Login logic written once, used everywhere
2. **Consistency** - All tests follow same setup pattern
3. **Maintainability** - Changes to login flow only need to be made in one place
4. **Efficiency** - Session reuse reduces test execution time
5. **Reliability** - Centralized error handling and logging
6. **Scalability** - Easy to add new base methods or extend functionality

## File Locations

- **Base Classes**: `base/BaseTest.ts`, `base/BaseSetup.ts`
- **Test Fixture**: `utils_ts/test-base.ts`
- **Global Setup**: `global-setup.ts`
- **Config**: `playwright.config.js`

## Running Tests

```bash
# Run all tests
npx playwright test

# Run specific test file
npx playwright test AddToCartPage_POM.spec.ts

# Run with specific browser
npx playwright test --project=chromium

# Debug mode
npx playwright test --debug

# Show report
npx playwright show-report
```

## Troubleshooting

### Session File Not Found
- First test run will create `state.json` via global setup
- Ensure global setup completes successfully
- Check that session path matches in configuration

### Login Fails
- Verify credentials in `BaseTest` class
- Check network connectivity
- Ensure application is accessible
- Review browser console for errors

### Tests Timeout
- Increase timeout in `playwright.config.js`
- Check network performance
- Verify selectors are correct
- Check application performance

## Extensions

You can extend the base classes by creating new methods:

```typescript
export class CustomTest extends BaseTest {
    async customLoginFlow(username: string, password: string): Promise<void> {
        // Add custom login logic
        await this.login(username, password);
        // Additional steps
    }
}
```

## Best Practices

1. Always use base fixtures when available
2. Don't duplicate login logic in test files
3. Use `baseTest` fixture for common operations
4. Extend `BaseTest` class for domain-specific functionality
5. Keep test-specific logic separate from base class
6. Use meaningful test names and descriptions
7. Handle errors gracefully with try-catch
8. Clean up resources in finally blocks
