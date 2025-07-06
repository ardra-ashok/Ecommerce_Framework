package eCommerce.Tests;

import eCommerce.TestComponents.BaseTest;
import org.pageObjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SubmitOrderTest extends BaseTest {

    String productName = "zara coat";
    @Test
    public void submitOrder() throws IOException {

        String orderSuccessMsg = "Thankyou for the order.";

        ProductCatalogue productCatalogue = landingPage.loginApplication("piyaasok@gmail.com","test123!");
        productCatalogue.getProductList();
        CartPage cartPage = productCatalogue.addProductToCart(productName);
        cartPage.goToCart();
        Boolean match = cartPage.verifyAndContinueToCheckOut(productName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckout(productName);
        checkOutPage.enterAddressDetails("india");
        ConfirmOrderPage confirmOrderPage = checkOutPage.submitOrder();
        confirmOrderPage.verifyConfirmMessage(orderSuccessMsg);
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest(){
        landingPage.loginApplication("piyaasok@gmail.com","test123!");
        OrdersPage ordersPage = new OrdersPage(webDriver);
        ordersPage.goToOrdersPage();
        Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
    }


}
