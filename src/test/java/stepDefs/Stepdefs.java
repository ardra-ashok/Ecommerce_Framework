package stepDefs;

import eCommerce.TestComponents.BaseTest;
import io.cucumber.java.en.*;
import org.pageObjects.*;
import org.testng.Assert;

import java.io.IOException;

public class Stepdefs extends BaseTest {
        public LandingPage landingPage;
        public ProductCatalogue productCatalogue;
        public CartPage cartPage;
        public CheckOutPage checkOutPage;
        public ConfirmOrderPage confirmOrderPage;


    @Given("I landed on Ecommerce Page")
    public void i_landed_on_EcommercePage() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void test(String username, String password){
        productCatalogue = landingPage.loginApplication(username,password);
    }

    @When("^I add the product (.+) to cart$")
    public void iAddTheProductProductNameToCart(String productName) {
        productCatalogue.getProductList();

    }

    @Then("{string} message is displayed for order confirmation")
    public void Verify_Success_message_is_displayed(String successMsg){
        confirmOrderPage.verifyConfirmMessage(successMsg.toLowerCase());

    }

    @Then("{string} message is displayed")
    public void Verify_Error_message_is_displayed(String successMsg){
        Assert.assertEquals(successMsg.toLowerCase(),landingPage.getErrorMessage().toLowerCase());
    }

    @Then ("^I checkout (.+) and submit the order$")
    public void I_checkout_and_submit_the_order(String productName){
        cartPage = productCatalogue.addProductToCart(productName);
        cartPage.goToCart();
        Boolean match = cartPage.verifyAndContinueToCheckOut(productName);
        Assert.assertTrue(match);
        checkOutPage = cartPage.goToCheckout(productName);
        checkOutPage.enterAddressDetails("india");
        confirmOrderPage = checkOutPage.submitOrder();

    }
}
