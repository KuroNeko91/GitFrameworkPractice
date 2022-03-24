package MyFramework;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.AutomationPracticePage;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;
import utils.Groups;
import utils.stringsUS;

public class NavigateToLoginPage extends BaseTest {

	String sInvalidEmail = stringsUS.INVALID_USER_EMAIL;
	String sInvalidPassword = stringsUS.INVALID_USER_PASS;
	String sExpectedLoginPageHeaderText = stringsUS.EXPECTED_LOGIN_PAGE_HEADER_TEXT;
	String sExpectedAlertText = stringsUS.ALERT_TEXT_WRONG_CREDENTIALS_LOGIN_PAGE;
	public WebDriver driver;

	
	public WebDriver driver2;
	

	

	
	@Test(groups = {Groups.SOME_TEST_GROUP})
	public void navigateToLoginPage() {
		//System.setProperty("webdriver.chrome.driver", "C://Users/Pavle/OneDrive/Documents/chromedriver.exe");
		driver = getDriverWrapper();
		log.debug("[Debug] Opening Home Page");
		HomePage homePage = new HomePage(driver).openHomePage();
		//driver.get("http://qaclickacademy.com");
		homePage.dissmisDamnPopUpifPresent();
		log.debug("[Debug] Clicking on Login Link");
		LoginPage loginPage = homePage.clickOnLoginLink();
		String sActualLoginHeaderText = loginPage.getHeaderText();
		assertEquals(sActualLoginHeaderText, sExpectedLoginPageHeaderText, "Expected and Actual texts are not matching!");

		loginPage.tryLoginWithWrongCreds("HUAS", sInvalidPassword);
		String sActualAlertText = loginPage.getAlertText();
		assertEquals(sActualAlertText, sExpectedAlertText, "Expected and Actual ALERT texts are not matching!");
		ForgotPasswordPage fpp = loginPage.clickForgotPassword();
		
		
		System.out.println(fpp.getHaderText());
		fpp.typeEmail(sInvalidEmail);
		fpp.clickOnSendMeInstructions();
		log.info("Test Passed!");
		
	}
	
	@Test
	(groups = {Groups.SOME_OTHER_TEST_GROUP})
	public void automationPageFrameNavigation() {
		//System.setProperty("webdriver.chrome.driver", "C://Users/Pavle/OneDrive/Documents/chromedriver.exe");
		driver2 = getDriverWrapper();
			AutomationPracticePage app = new AutomationPracticePage(driver2).openAutomationPracticePage();
			log.debug("[Debug] Opening Automation Page");
			app.switchToFrame();
			app.clickOnConsultingLink();
			app.enterNameInFrame("Some Name");
			sleepSecondsBecauseReasons(5);
			app.switchBackToParentFrame();
			app.clickHideButton();
			log.debug("Daj vamo");
			sleepSecondsBecauseReasons(2);
			assertTrue(app.isTextBoxDisplayed());
			assertTrue(app.isTextBoxEnabled());
			assertFalse(app.isTextBoxInteractable());
			app.clickShowButton();
			assertTrue(app.isTextBoxInteractable());
			log.info("Brt moj test prosao!!!");
			log.info("Da vidimo dal ce na gitu prodje sad!");
		
	}
	
	@AfterTest
	(alwaysRun = true)
	public void closeDriver() {
		if(driver!=null) {
			driver.quit();
		}
		if(driver2!=null) {
			driver2.quit();
		}
	}
	
}
