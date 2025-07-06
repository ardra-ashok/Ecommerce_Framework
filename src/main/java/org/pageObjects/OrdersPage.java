package org.pageObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.Helpers;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OrdersPage extends Helpers {
    WebDriver webDriver;

    public OrdersPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css="tr td:nth-child(3)")
    List<WebElement> productNames;

    @FindBy(css ="[routerlink*='/dashboard/myorders']")
    WebElement orderLink;



    public Boolean VerifyOrderDisplay(String productName){
        return productNames.stream().anyMatch(product->product.getText().toLowerCase().contains(productName));
    }

    public void goToOrdersPage() {
        orderLink.click();
    }



}
