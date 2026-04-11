
import {LoginPage} from './LoginPage';
import {DashboardPage} from './DashboardPage';
import { OrdersHistoryPage } from './OrdersHistoryPage';
import { CartPage } from './CartPage';
import { CheckoutPage } from './CheckoutPage';
import { PlaceOrderPage } from './PlaceOrderPage';
import {Page} from '@playwright/test';

export class POManager
{
    loginPage: LoginPage;
    dashboardPage: DashboardPage;
    ordersHistoryPage : OrdersHistoryPage;

    cartPage : CartPage;

    checkoutPage: CheckoutPage;
    placeOrderPage: PlaceOrderPage;
    page : Page;


constructor(page:Page)
{
    this.page = page;
    this.loginPage = new LoginPage(this.page);
    this.dashboardPage = new DashboardPage(this.page);
    this.ordersHistoryPage = new OrdersHistoryPage(this.page);

    this.cartPage = new CartPage(this.page);
   
    this.checkoutPage = new CheckoutPage(this.page);
    this.placeOrderPage = new PlaceOrderPage(this.page);


}

getLoginPage()
{
    return this.loginPage;
}

getCartPage()
{
    return this.cartPage;
}

getDashboardPage()
{
    return this.dashboardPage;
}

getOrdersHistoryPage()
{
    return this.ordersHistoryPage;
}





getCheckoutPage()
{
    return this.checkoutPage;
}

getPlaceOrderPage()
{
    return this.placeOrderPage;
}
}