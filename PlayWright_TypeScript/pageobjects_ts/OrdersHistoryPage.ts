import {test, expect,Locator,Page} from '@playwright/test';

export class OrdersHistoryPage
{
    // Locators
    orderdIdDetails : Locator;
    rows : Locator;
    ordersTable : Locator;
    page : Page;
    orderIdColumn: Locator;
    viewButton: Locator;
    ordersHeader: Locator;
    emptyOrdersMessage: Locator;
    tableHeaders: Locator;
    paginationButtons: Locator;
    nextPageButton: Locator;
    previousPageButton: Locator;

    constructor(page: Page)
    {
        this.page = page;
        this.ordersTable = page.locator("tbody");
        this.rows = page.locator("tbody tr");
        this.orderdIdDetails = page.locator(".col-text");
        this.orderIdColumn = page.locator("tbody th");
        this.viewButton = page.locator("button[class*='view'], button.view-btn");
        this.ordersHeader = page.locator("text=My Orders, Orders");
        this.emptyOrdersMessage = page.locator("text=No orders found");
        this.tableHeaders = page.locator("thead th");
        this.paginationButtons = page.locator("[class*='pagination'] button");
        this.nextPageButton = page.locator("button[aria-label*='next']");
        this.previousPageButton = page.locator("button[aria-label*='previous']");
    }

    async waitForOrdersTableToLoad()
    {
        await this.page.waitForLoadState('networkidle');
        try {
            await this.ordersTable.waitFor({ timeout: 10000 });
        } catch {
            console.log("Orders table not found - may be empty");
        }
    }

    async searchOrderAndSelect(orderId: any)
    {
        await this.ordersTable.waitFor();
        for(let i = 0; i < await this.rows.count(); ++i)
        {
            const rowOrderId = await this.rows.nth(i).locator("th").textContent();
            if (orderId && orderId.includes(rowOrderId))
            {
                await this.rows.nth(i).locator("button").first().click();
                await this.page.waitForLoadState('networkidle');
                break;
            }
        }
    }

    async getOrderId(): Promise<string | null>
    {
        return await this.orderdIdDetails.textContent();
    }

    async getOrderRowCount(): Promise<number>
    {
        return await this.rows.count();
    }

    async getOrderIds(): Promise<(string | null)[]>
    {
        const orderIds: (string | null)[] = [];
        const count = await this.getOrderRowCount();
        for(let i = 0; i < count; i++)
        {
            const orderId = await this.rows.nth(i).locator("th").textContent();
            orderIds.push(orderId);
        }
        return orderIds;
    }

    async clickViewButtonForOrder(index: number)
    {
        await this.rows.nth(index).locator("button").first().click();
        await this.page.waitForLoadState('networkidle');
    }

    async getOrderByIndex(index: number): Promise<string | null>
    {
        if(index < await this.getOrderRowCount())
        {
            return await this.rows.nth(index).locator("th").textContent();
        }
        return null;
    }

    async isOrdersTableVisible(): Promise<boolean>
    {
        return await this.ordersTable.isVisible().catch(() => false);
    }

    async isOrdersPageLoaded(): Promise<boolean>
    {
        const url = this.page.url();
        return url.includes('myorders');
    }

    async verifyOrderIdExists(orderId: string): Promise<boolean>
    {
        const orderIds = await this.getOrderIds();
        return orderIds.some(id => id && id.includes(orderId));
    }

    async getOrdersPageURL(): Promise<string>
    {
        return this.page.url();
    }

    async searchOrderByIdAndSelect(orderId: string)
    {
        await this.searchOrderAndSelect(orderId);
    }

    async getFirstOrderId(): Promise<string | null>
    {
        if(await this.getOrderRowCount() > 0)
        {
            return await this.getOrderByIndex(0);
        }
        return null;
    }

    async navigateToNextPage()
    {
        try {
            await this.nextPageButton.click();
            await this.page.waitForLoadState('networkidle');
        } catch {
            console.log("Next page button not found");
        }
    }

    async isEmptyOrdersDisplayed(): Promise<boolean>
    {
        return await this.emptyOrdersMessage.isVisible().catch(() => false);
    }

    async verifyOrderDetailsPageLoads()
    {
        await this.page.waitForLoadState('networkidle');
        const pageUrl = this.page.url();
        return pageUrl.includes('order-details') || pageUrl.includes('order');
    }
}
