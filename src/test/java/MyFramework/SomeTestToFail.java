package MyFramework;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.AutomationPracticePage;
import utils.Groups;

public class SomeTestToFail extends BaseTest{
	public WebDriver driver;
	/*
	@BeforeTest
	(alwaysRun = true)
	public void getDriver() {
		driver = getDriverWrapper();
	}
	*/
	@Test
	(groups = {Groups.SOME_OTHER_TEST_GROUP})
	public void testToFail() {
		driver = getDriverWrapper();
		AutomationPracticePage app = new AutomationPracticePage(driver).openAutomationPracticePage();
		app.clickHideButton();
		assertTrue(app.isTextBoxDisplayed());
		assertTrue(app.isTextBoxEnabled());
			//System.out.println("Why is driver closing?!");
		assertTrue(app.isTextBoxInteractable());
	}
		
	
	@AfterMethod
	(alwaysRun = true)
	public void closeDriver(){
		System.out.println("Closing driver!");
	//	takenewScreenshot(driver);
		driver.quit();
		System.out.println("Driver is now closed!");
	}
}
	/*
	public void takenewScreenshot(WebDriver webDriver) {
		File source;
	    try {
	        source = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);


	    FileUtils.copyFile(source, new File (System.getProperty("user.dir")+"\\reports\\SampleScrenShot7.png"));
	    System.out.println("Screenshot Taken!!!!");

	    } catch (IOException e) {
	        e.printStackTrace();
	    } 
	}/**/


