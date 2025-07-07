package eCommerce.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.pageObjects.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public abstract class BaseTest {

    public WebDriver webDriver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/org/resources/GlobalData.properties");
        prop.load(fileInputStream);

        String browserName = System.getProperty("browser")!=null? System.getProperty("browser"):prop.getProperty("browser");


        if(browserName.equalsIgnoreCase("chrome")) {
            if(webDriver==null) {
                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--headless");

                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(options);
                webDriver.manage().window().maximize();
                webDriver.manage().window().setSize(new Dimension(1440,900));
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                return webDriver;
            }else{
                return getDriver();
            }
           
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            webDriver = new FirefoxDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            return webDriver;
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

    public List<HashMap<String, String>> getJsonToData(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});

    }
    public String getScreenshot(String testCaseName, WebDriver webDriver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)webDriver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
    }
}
