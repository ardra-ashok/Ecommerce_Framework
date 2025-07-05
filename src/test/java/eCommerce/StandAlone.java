package eCommerce;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.List;

public class StandAlone {


    public static void main(String[] args) {
        String productName = "zara coat";
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriver.get("https://rahulshettyacademy.com/client");
        WebDriverWait waitForElement = new WebDriverWait(webDriver,Duration.ofSeconds(5));

        webDriver.findElement(By.id("userEmail")).sendKeys("piyaasok@gmail.com");
        webDriver.findElement(By.id("userPassword")).sendKeys("test123!");
        webDriver.findElement(By.cssSelector("[id=login]")).click();
        waitForElement.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = webDriver.findElements(By.cssSelector(".mb-3"));
        WebElement product = products.stream().filter(prod->prod.findElement(By.cssSelector(".card-body b")).getText().
                toLowerCase().contains("zara coat")).findFirst().orElse(null);
        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        waitForElement.until(ExpectedConditions.invisibilityOf(webDriver.findElement(By.cssSelector(".ng-animating"))));
        waitForElement.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        webDriver.findElement(By.cssSelector("[routerlink*='/cart']")).click();


        webDriver.close();

    }

}
