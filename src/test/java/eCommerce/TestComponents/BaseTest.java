package eCommerce.TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.pageObjects.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver webDriver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream("/Users/aashok/IdeaProjects/Ecommerce_Framework/src/main/java/org/resources/GlobalData.properties");
        prop.load(fileInputStream);
        String browserName = prop.getProperty("browser");


        if(browserName.equalsIgnoreCase("chrome")) {
            if(webDriver==null) {
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                webDriver.manage().window().maximize();
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                return webDriver;
            }else{
                return getDriver();
            }
           
        }
        else if(browserName.equalsIgnoreCase("firefox")){
//            firefox
        }
        return webDriver;
    }

    @BeforeMethod(alwaysRun=true)
    public LandingPage launchApplication() throws IOException {
        webDriver = initializeDriver();
        landingPage = new LandingPage(webDriver);
        landingPage.navigateToWebPage();
        return landingPage;
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown(){
        if (webDriver != null)
            webDriver.quit();
        webDriver = null;
    }

    public WebDriver getDriver(){
        return webDriver;
    }

}
