package org.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.Helpers;

public class LandingPage extends Helpers {
    WebDriver webDriver;

    public LandingPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

//    WebElement userEmail = webDriver.findElement(By.id("userEmail"));
//    password

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement passwordEle;

    @FindBy(id="login")
    WebElement submit;

    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatalogue loginApplication(String username, String password){
        userEmail.sendKeys(username);
        passwordEle.sendKeys(password);
        submit.click();
        return new ProductCatalogue(webDriver);
    }

    public String getErrorMessage(){
        waitForElement(errorMessage,5);
        return errorMessage.getText();
    }
    public void navigateToWebPage() {
        webDriver.get("https://rahulshettyacademy.com/client");
    }

}
