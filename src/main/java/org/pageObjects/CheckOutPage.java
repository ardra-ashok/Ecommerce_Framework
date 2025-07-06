package org.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.Helpers;

public class CheckOutPage extends Helpers{
    WebDriver webDriver;

    public CheckOutPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css="[placeholder='Select Country']")
    WebElement countrySelect;

    @FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
    WebElement countryBtn;

    @FindBy(css=".action__submit")
    WebElement submitOrderBtn;

    By countryResult =  By.cssSelector(".ta-results");

    public void enterAddressDetails(String country){
        sendKeysAction(countrySelect,country);
        selectCountry();
    }
    public void selectCountry(){
        waitForElement(countryResult,2);
        countryBtn.click();
    }

    public ConfirmOrderPage submitOrder() {
        submitOrderBtn.click();
        return new ConfirmOrderPage(webDriver);
    }
}
