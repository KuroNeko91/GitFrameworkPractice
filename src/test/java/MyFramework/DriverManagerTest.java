package MyFramework;


 
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import org.testng.annotations.Test;



public class DriverManagerTest extends BaseTest{
public WebDriver driver;
/*
@BeforeTest
(alwaysRun = true)
public void getDriver() {
	driver = getDriverWrapper();
	
}
	*/
@Test 
public void testDriverManager(){
	driver = getDriverWrapper();
	log.info(getClass().getName() + ".testDriverManager()");
	log.debug("Getting driver wrapper");
	log.debug("Getting google.com");
		log.error("ARHG");
		driver.get("http://google.com");
		
}

@AfterMethod
(alwaysRun = true)
public void closeDriver() {
	if(driver!=null) {
		driver.quit();
	}
}

}
