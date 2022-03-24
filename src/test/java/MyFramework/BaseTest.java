package MyFramework;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import seleniumCore.DriverManagerFactory;

public class BaseTest {

	public WebDriver driver;
	public static Logger log=LogManager.getLogger("");
	
	
	DriverManagerFactory driverManagerFactory = new DriverManagerFactory();;
	String currentTimeInMs = String.valueOf(System.currentTimeMillis());
	
	public WebDriver getDriverWrapper() {
		driver = driverManagerFactory.driverWrapper();
		return driver;
	}
	
	public void sleepSecondsBecauseReasons(int secondsToSleep) {
		try {
			Thread.sleep(secondsToSleep*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
	//	System.out.println(ts.getClass());
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String destinationFile = System.getProperty("user.dir")+"/reports/"+testCaseName+currentTimeInMs+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;
	}
	
	public void takeScreenshot(WebDriver webDriver) {
		File source;
	    try {
	        source = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);


	    FileUtils.copyFile(source, new File (System.getProperty("user.dir")+"\\reports\\SampleScrenShot12.png"));
	    System.out.println("Screenshot Taken!!!!");

	    } catch (IOException e) {
	        e.printStackTrace();
	    } 
	}
}
