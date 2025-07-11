package cucumber;


import io.cucumber.testng.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(features="src/test/java/cucumber",glue ={ "stepDefs"},tags="@Regression", monochrome = true, plugin={"html:target/cucumber.html"})
public class testNGRunner extends AbstractTestNGCucumberTests {
}
