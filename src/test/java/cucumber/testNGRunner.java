package cucumber;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue = "stepDefs.StepDefs")
public class testNGRunner extends AbstractTestNGCucumberTests {


    public void TestNGRunner(){
    }
}
