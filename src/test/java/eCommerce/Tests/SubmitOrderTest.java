package eCommerce.Tests;

import eCommerce.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pageObjects.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class SubmitOrderTest extends BaseTest {

    @Test
    public void submitOrder() throws IOException {
        String productName = "zara coat";
        String orderSuccessMsg = "Thankyou for the order.";

        LandingPage landingPage = launchApplication();
        ProductCatalogue productCatalogue = landingPage.loginApplication("piyaasok@gmail.com","test123!");

        productCatalogue.getProductList();
        CartPage cartPage = productCatalogue.addProductToCart(productName);
        cartPage.goToCart();

        CheckOutPage checkOutPage = cartPage.verifyAndContinueToCheckOut(productName);
        checkOutPage.enterAddressDetails("india");

        ConfirmOrderPage confirmOrderPage = checkOutPage.submitOrder();
        confirmOrderPage.verifyConfirmMessage(orderSuccessMsg);
    }

}
