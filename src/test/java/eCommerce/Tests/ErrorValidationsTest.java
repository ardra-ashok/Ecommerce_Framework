package eCommerce.Tests;

import eCommerce.TestComponents.BaseTest;
import eCommerce.TestComponents.Retry;
import org.pageObjects.CartPage;
import org.pageObjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups={"errorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation()  {
        String productName = "zara coat";
        landingPage.loginApplication("piyaasok@gmail.co","test12");
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
    }

    @Test
    public void productErrorValidation(){
        String productName = "zara coat";

        ProductCatalogue productCatalogue = landingPage.loginApplication("ardraasok@yahoo.in","Test123!");
        productCatalogue.getProductList();
        CartPage cartPage = productCatalogue.addProductToCart(productName);
        cartPage.goToCart();
        Boolean match = cartPage.verifyAndContinueToCheckOut("zar coat");
        Assert.assertFalse(match);
    }

}
