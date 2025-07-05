package org.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.Helpers;

import static org.testng.AssertJUnit.assertTrue;

public class ConfirmOrderPage extends Helpers {

    WebDriver webDriver;

    public ConfirmOrderPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css=".hero-primary")
    WebElement confirmMsg;

    public void verifyConfirmMessage(String orderSuccessMsg) {
        assertTrue(confirmMsg.getText().equalsIgnoreCase(orderSuccessMsg));
    }
}
