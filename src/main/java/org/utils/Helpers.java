package org.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Abstract components
public class Helpers {

    WebDriver webDriver;

    public Helpers(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void waitForElement(By by,long waitTime){
        WebDriverWait waitForElement = new WebDriverWait(webDriver, Duration.ofSeconds(waitTime));
        waitForElement.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementToDisapper(WebElement element,long waitTime){
        WebDriverWait waitForElement = new WebDriverWait(webDriver, Duration.ofSeconds(waitTime));
        waitForElement.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementPresent(By by,long waitTime){
        WebDriverWait waitForElement = new WebDriverWait(webDriver, Duration.ofSeconds(waitTime));
        waitForElement.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void sendKeysAction(WebElement elem, String country){
        Actions a = new Actions(webDriver);
        a.sendKeys(elem, country).build().perform();
    }
}
