package homePageValidation;


import java.io.IOException;
import java.net.MalformedURLException;

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

public class LinkValidation extends BaseTest{

	public WebDriver driver;
	/*
	 @BeforeTest
	 (alwaysRun = true)
	 public void getDriver() {
	  	driver = getDriverWrapper();
	 }
	 */
	@Test	
	(groups = {Groups.SOME_TEST_GROUP})
	public void homePageValidation() throws MalformedURLException, IOException{
			driver = getDriverWrapper();
			HomePage homePage = new HomePage(driver).openHomePage();
			homePage.dissmisDamnPopUpifPresent();
			AutomationPracticePage app = homePage.clickOnPracticeLink();
			int linkCount = app.getLinkCount();
			System.out.println("Number of link on Automation Practice Page is: [" + linkCount + "]");
			app.validateAllLinks();
	}
	
	@AfterMethod
	(alwaysRun = true)
	public void closeDriver() {
		if(driver!=null) {
			driver.quit();
		}
	}
}
