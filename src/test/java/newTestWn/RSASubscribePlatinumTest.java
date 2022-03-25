package newTestWn;

import org.testng.annotations.Test;

import MyFramework.BaseTest;
import pages.RSAPage;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;

public class RSASubscribePlatinumTest extends BaseTest{
  @Test
  public void subscrbePlatinumTest() {
	  log.info("subscrbePlatinumTest()");
	  driver= getDriverWrapper();
	  log.debug("Navigate to RSA Page");
	  RSAPage rsaPage = new RSAPage(driver).openRSAPage();
	  rsaPage
	  	.clickMentorshipBox()
	  	.clickPlatinumJoinButton();
	
	  assertTrue(rsaPage.isPlatinumPage());
	  
	  rsaPage.clickSelectMembershipLink();
	  sleepSecondsBecauseReasons(1);
  }
  @AfterMethod
  public void afterMethod() {
	  if(driver!=null) {
		  driver.quit();
	  }
	  log.info("TEST PASSED!");
  }

}
