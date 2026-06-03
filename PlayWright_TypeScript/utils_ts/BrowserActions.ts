
import { Page, Locator, expect } from '@playwright/test';

export class ActionsUtility {
    readonly page: Page;

    constructor(page: Page) {
        this.page = page;
    }

    // ----------------------------------
    // BASIC ACTIONS
    // ----------------------------------

    async click(locator: Locator): Promise<void> {
        await locator.click();
    }

    async type(locator: Locator, text: string): Promise<void> {
        await locator.fill(text);
    }

    async clear(locator: Locator): Promise<void> {
        await locator.clear();
    }

    async getText(locator: Locator): Promise<string> {
        return (await locator.textContent()) ?? '';
    }

    async getAttribute(locator: Locator, attribute: string): Promise<string | null> {
        return await locator.getAttribute(attribute);
    }

    async getCount(locator: Locator): Promise<number> {
        return await locator.count();
    }

    async getInputValue(locator: Locator): Promise<string> {
        return await locator.inputValue();
    }

    // ----------------------------------
    // WAIT METHODS
    // ----------------------------------

    async waitForVisible(locator: Locator): Promise<void> {
        await locator.waitFor({ state: 'visible' });
    }

    async waitForHidden(locator: Locator): Promise<void> {
        await locator.waitFor({ state: 'hidden' });
    }

    async waitForAttached(locator: Locator): Promise<void> {
        await locator.waitFor({ state: 'attached' });
    }

    async waitForPageLoad(): Promise<void> {
        await this.page.waitForLoadState('networkidle');
    }

    // ----------------------------------
    // MOUSE ACTIONS
    // ----------------------------------

    async hover(locator: Locator): Promise<void> {
        await locator.hover();
    }

    async doubleClick(locator: Locator): Promise<void> {
        await locator.dblclick();
    }

    async rightClick(locator: Locator): Promise<void> {
        await locator.click({ button: 'right' });
    }

    async dragAndDrop(source: Locator, target: Locator): Promise<void> {
        await source.dragTo(target);
    }

    // ----------------------------------
    // DROPDOWN ACTIONS
    // ----------------------------------

    async selectByText(locator: Locator, text: string): Promise<void> {
        await locator.selectOption({ label: text });
    }

    async selectByValue(locator: Locator, value: string): Promise<void> {
        await locator.selectOption(value);
    }

    async selectByIndex(locator: Locator, index: number): Promise<void> {
        const options = await locator.locator('option').all();
        const value = await options[index].getAttribute('value');
        if (value) {
            await locator.selectOption(value);
        }
    }

    // ----------------------------------
    // SCROLLING
    // ----------------------------------

    async scrollIntoView(locator: Locator): Promise<void> {
        await locator.scrollIntoViewIfNeeded();
    }

    async scrollBy(x: number, y: number): Promise<void> {
        await this.page.evaluate(
            ({ x, y }) => window.scrollBy(x, y),
            { x, y }
        );
    }

    // ----------------------------------
    // JAVASCRIPT ACTIONS
    // ----------------------------------

    async clickUsingJS(locator: Locator): Promise<void> {
        await locator.evaluate((element: any) => element.click());
    }

    async typeUsingJS(locator: Locator, text: string): Promise<void> {
        await locator.evaluate(
            (element: any, value) => {
                element.value = value;
            },
            text
        );
    }

    // ----------------------------------
    // ELEMENT STATE
    // ----------------------------------

    async isVisible(locator: Locator): Promise<boolean> {
        return await locator.isVisible();
    }

    async isEnabled(locator: Locator): Promise<boolean> {
        return await locator.isEnabled();
    }

    async isChecked(locator: Locator): Promise<boolean> {
        return await locator.isChecked();
    }

    // ----------------------------------
    // ASSERTIONS
    // ----------------------------------

    async verifyText(locator: Locator, expectedText: string): Promise<void> {
        await expect(locator).toHaveText(expectedText);
    }

    async verifyVisible(locator: Locator): Promise<void> {
        await expect(locator).toBeVisible();
    }

    async verifyHidden(locator: Locator): Promise<void> {
        await expect(locator).toBeHidden();
    }
}