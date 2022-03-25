package newTestWn;

import org.testng.annotations.Test;

import MyFramework.BaseTest;
import pages.RSAPage;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

public class RSASubscribeTest extends BaseTest{
  
  WebDriver driver;
	
  @Test
  public void subscribeRSATest() {
	  log.info("Starting subscribeRSATest()");
	  driver= getDriverWrapper();
	  log.debug("Navigate to RSA Page");
	  RSAPage rsaPage = new RSAPage(driver).openRSAPage();
	  rsaPage
	  	.clickMentorshipBox()
	  	.clickJoinBronzeButton();
	
	  assertTrue(rsaPage.isSubscribeModalOpen());
	  
	  rsaPage.clickCloseModalButton();
  }
  
  @AfterMethod
  public void afterMethod() {
	  if(driver!=null) {
		  driver.quit();
	  }
	  log.info("TEST PASSED!");
  }

}
