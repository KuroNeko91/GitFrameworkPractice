package testNGListeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import MyFramework.BaseTest;
import extentReporter.ExentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentReports extent = ExentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal <ExtentTest>extentTest = new ThreadLocal<ExtentTest>();
	
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String testMethodName =result.getMethod().getMethodName();
		test= extent.createTest(testMethodName);
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String testMethodName =result.getMethod().getMethodName();
		log.info(testMethodName + ": PASSED WOOOHOOO!");
		extentTest.get().log(Status.PASS, "Test passed!");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());
		WebDriver driver = null;
		String testMethodName =result.getMethod().getMethodName();
		
			try {
				driver =(WebDriver)result.getTestClass().
						getRealClass()
						.getDeclaredField("driver")
						.get(result.getInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	//	}
	//	try {
			try {
				String screenshotPath =getScreenShotPath(testMethodName, driver);
				extentTest.get().addScreenCaptureFromPath(screenshotPath, testMethodName);
				log.info("Screenshot on failure saved: [" + screenshotPath + "].");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//, result.getMethod().getMethodName());
			/*
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}
	

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
