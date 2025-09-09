package com.opencart.base;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.opencart.utilities.ExtentManager;
import com.opencart.utilities.DriverFactory;   // ✅ import DriverFactory

public class Base {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setup(Method method) {
        test = extent.createTest(method.getName());
        System.out.println("🚀 Starting Test: " + method.getName());

        // ✅ Get driver from DriverFactory
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/index.php");

        waitForPageLoad(20);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                test.pass("✅ Test Passed: " + result.getName());
            } else if (result.getStatus() == ITestResult.FAILURE) {
                // Screenshot on failure
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    String screenshotPath = "screenshots/" + result.getName() + ".png";
                    Files.createDirectories(new File("screenshots").toPath());
                    Files.copy(src.toPath(), new File(screenshotPath).toPath());
                    test.addScreenCaptureFromPath(screenshotPath);
                } catch (Exception e) {
                    test.warning("⚠️ Could not attach screenshot: " + e.getMessage());
                }
                test.fail("❌ Test Failed: " + result.getName());
                test.fail(result.getThrowable());
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.skip("⚠️ Test Skipped: " + result.getName());
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @AfterSuite
    public void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    public void waitForPageLoad(int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
            .until(webDriver -> ((JavascriptExecutor) webDriver)
            .executeScript("return document.readyState").equals("complete"));
    }
}