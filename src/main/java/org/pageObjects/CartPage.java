package org.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.Helpers;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class CartPage extends Helpers {

    WebDriver webDriver;

    public CartPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    @FindBy(css="[routerlink*='/cart']")
    WebElement cartBtn;

    @FindBy(css=".cartSection h3")
    List<WebElement> cartProducts;

    public void goToCart() {
        cartBtn.click();    }


    public void verifyAndContinueToCheckOut(String productName) {
        assertTrue(cartProducts.stream().anyMatch(prod->prod.getText().toLowerCase().contains(productName)));
        webDriver.findElement(By.cssSelector(".totalRow button")).click();
    }
}
