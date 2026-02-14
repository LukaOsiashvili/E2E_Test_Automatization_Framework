package listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();

        if (driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);

                Allure.addAttachment(
                        "Screenshot on Failure - " + result.getName(),
                        "image/png",
                        new ByteArrayInputStream(screenshot),
                        ".png"
                );

                // Also attach the error message
                if (result.getThrowable() != null) {
                    Allure.addAttachment(
                            "Failure Reason",
                            "text/plain",
                            result.getThrowable().getMessage()
                    );
                }

                System.out.println("✓ Screenshot captured for failed test: " + result.getName());

            } catch (Exception e) {
                System.err.println("✗ Failed to capture screenshot: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("▶ Starting test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✓ Test passed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⊘ Test skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("\n========================================");
        System.out.println("Starting test suite: " + context.getName());
        System.out.println("========================================\n");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\n========================================");
        System.out.println("Test suite finished: " + context.getName());
        System.out.println("Passed: " + context.getPassedTests().size());
        System.out.println("Failed: " + context.getFailedTests().size());
        System.out.println("Skipped: " + context.getSkippedTests().size());
        System.out.println("========================================\n");
    }
}