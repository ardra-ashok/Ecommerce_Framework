package eCommerce;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pageObjects.*;
import org.testng.annotations.Test;

import java.time.Duration;

public class StandAlone {

    @Test
    public void eCommmerce() throws InterruptedException {
        String productName = "zara coat";
        String orderSuccessMsg = "Thankyou for the order.";
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        LandingPage landingPage = new LandingPage(webDriver);
        landingPage.navigateToWebPage();
        landingPage.loginApplication("piyaasok@gmail.com","test123!");

        ProductCatalogue productCatalogue = new ProductCatalogue(webDriver);
        productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);


        CartPage cartPage = new CartPage(webDriver);
        cartPage.goToCart();
        cartPage.verifyAndContinueToCheckOut(productName);

        CheckOutPage checkOutPage = new CheckOutPage(webDriver);
        checkOutPage.enterAddressDetails("india");
        checkOutPage.submitOrder();

        ConfirmOrderPage  confirmOrderPage= new ConfirmOrderPage(webDriver);
        confirmOrderPage.verifyConfirmMessage(orderSuccessMsg);
        webDriver.close();
    }

}
