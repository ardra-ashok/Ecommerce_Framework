package eCommerce.Tests;

import eCommerce.TestComponents.BaseTest;
import org.pageObjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

   String productName = "zara coat";

    @Test(dataProvider="getData", groups = {"purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException {

        String orderSuccessMsg = "Thankyou for the order.";

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("username"),input.get("password"));
        productCatalogue.getProductList();
        CartPage cartPage = productCatalogue.addProductToCart(input.get("productName"));
        cartPage.goToCart();
        Boolean match = cartPage.verifyAndContinueToCheckOut(input.get("productName"));
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckout(input.get("productName"));
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

    @DataProvider
    public Object[][] getData() throws IOException {
        HashMap<Object,Object> dataMap = new HashMap<>();
//        dataMap.put("username","piyaasok@gmail.com");
//        dataMap.put("password","test123!");
//        dataMap.put("productName","zara coat");
//        return new Object[][]{{"piyaasok@gmail.com","test123!","zara coat"},{"ardraasok@yahoo.in","Test123!","iphone 13"}};

//        return new Object[][]{{dataMap}};
        List<HashMap<String,String>> data = getJsonToData(System.getProperty("user.dir")+"//src//test//java/eCommerce//data//PurchaseOrder.json");
        return new Object[][] {{data.get(0)}};
    }

}
