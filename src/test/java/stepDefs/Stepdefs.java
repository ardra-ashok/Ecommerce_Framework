package stepDefs;

import eCommerce.TestComponents.BaseTest;
import io.cucumber.java.en.*;
import org.pageObjects.LandingPage;

import java.io.IOException;

public class Stepdefs extends BaseTest {
        public LandingPage landingPage;

    @Given("I landed on Ecommerce Page")
    public void i_landed_on_EcommercePage() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void test(String username, String password){
        System.out.println(username);
        System.out.println(password);
    }

    @When("^I add the product (.+) to cart$")
    public void iAddTheProductProductNameToCart(String productName) {
        System.out.println(productName);
    }

    @Then("{string} message is displayed")
    public void test_1(String message){
        System.out.println(message);
    }
}
