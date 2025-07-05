package eCommerce;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pageObjects.*;
import org.testng.annotations.Test;

import java.time.Duration;

public class StandAlone {

    public void main(String[] args)  {
        String productName = "zara coat";
        String orderSuccessMsg = "Thankyou for the order.";
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        LandingPage landingPage = new LandingPage(webDriver);
        landingPage.navigateToWebPage();
        ProductCatalogue productCatalogue = landingPage.loginApplication("piyaasok@gmail.com","test123!");

        productCatalogue.getProductList();
        CartPage cartPage = productCatalogue.addProductToCart(productName);
        cartPage.goToCart();

        CheckOutPage checkOutPage = cartPage.verifyAndContinueToCheckOut(productName);
        checkOutPage.enterAddressDetails("india");

        ConfirmOrderPage confirmOrderPage = checkOutPage.submitOrder();
        confirmOrderPage.verifyConfirmMessage(orderSuccessMsg);
        webDriver.close();
    }

}
