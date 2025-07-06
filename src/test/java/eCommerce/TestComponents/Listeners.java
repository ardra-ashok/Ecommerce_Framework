package eCommerce.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.resources.ExtentReporterNG;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        try {
            webDriver = (WebDriver) result.getTestClass().getRealClass().getField("webDriver")
                    .get(result.getInstance());

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        String filePath = null;
        try {

            filePath = getScreenshot(result.getMethod().getMethodName(),webDriver);
            System.out.println(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentTest.get()
                .fail("Screenshot of failure: " + result.getMethod().getMethodName())
                .addScreenCaptureFromPath(filePath);

//        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
