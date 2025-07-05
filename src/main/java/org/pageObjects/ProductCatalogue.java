package org.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.Helpers;

import java.util.List;

public class ProductCatalogue extends Helpers {

    WebDriver webDriver;

    public ProductCatalogue(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css=".mb-3")
    List<WebElement> products;

    @FindBy(css=".ng-animating")
    WebElement loadingComponent;

    By productsBy = By.cssSelector(".mb-3");
    By addToCartBtn = By.cssSelector(".card-body button:last-of-type");
    By successToast = By.cssSelector("#toast-container");

    public List<WebElement> getProductList(){
        waitForElement(productsBy,5);
        return products;
    }

    public WebElement getProductByName(String productName){
        return getProductList().stream().filter(prod->prod.findElement(By.cssSelector(".card-body b")).getText().
                toLowerCase().contains(productName)).findFirst().orElse(null);

    }

    public void addProductToCart(String productName) {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCartBtn).click();
        waitForElementToDisapper(loadingComponent,5);
        waitForElementPresent(successToast,0);
    }
}
