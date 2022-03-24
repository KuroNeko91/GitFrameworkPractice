package homePageValidation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import MyFramework.BaseTest;
import pages.AutomationPracticePage;
import pages.HomePage;
import utils.Groups;

public class HideShowButtonTest extends BaseTest{
  public WebDriver driver;
  public String sTypeText = "Some text";

  
  /*
 @BeforeTest
 (alwaysRun = true)
 public void getDriver() {
  	driver = getDriverWrapper();
 }
  */
  @Test
  (groups = {Groups.SOME_OTHER_TEST_GROUP})
  public void hideShowButtonTest() {
	  driver = getDriverWrapper();
	  HomePage homePage = new HomePage(driver).openHomePage();
	  sleepSecondsBecauseReasons(7);
	  homePage.dissmisDamnPopUpifPresent();
	  AutomationPracticePage app = homePage.clickOnPracticeLink();
	  app 
			.typeTextInDisplayTextBox(sTypeText)
			.clickHideButton()
			.clickShowButton()
			.clickHideButton();
	  assertFalse(app.isTextBoxInteractable());
	  app.clickShowButton();
	  assertTrue(app.isTextBoxInteractable());
	  String sTextInTheBox = app.getTypedTextFromDisplayTextBox();
	  assertEquals(sTextInTheBox, sTypeText, "Texts are not matching!");
		
	}

  @AfterMethod
	(alwaysRun = true)
	public void closeDriver() {
		if(driver!=null) {
			driver.quit();
		}
	}
}
