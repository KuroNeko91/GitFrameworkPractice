package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage extends BasePage{
	
@FindBy(partialLinkText = "Login")
WebElement loginLocator;

@FindBy(xpath= "//div[contains(@class,'listbuilder-popup-scale')]")
WebElement nastyPopUpLocator;

@FindBy(xpath = "//input[@placeholder='Enter your Email']")
WebElement nastyPopUpLocatorEmailInput;

@FindBy(partialLinkText = "PRACTICE")
WebElement practiceLinkLocator;
	
	public WebDriver driver;
	public WebDriverWait wait;
	 
	public static String url = getHomePageUrlFromProperties();
		
	public HomePage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isLoginLinkPresent() {
		boolean loginFormPresence = isElementPresent(loginLocator); 
		return loginFormPresence;
	}
	
	public boolean isPracticeLinkPresent() {
		boolean practiceLinkPresence = isElementPresent(practiceLinkLocator);
		return practiceLinkPresence;
	}
	
	public HomePage openHomePage() {
		System.out.println("Opening Home Page URL: "+url);
		driver.get(url);
		return new HomePage(driver);
	}
	
	public LoginPage clickOnLoginLink() {
		Assert.assertTrue(isLoginLinkPresent(), "Login Link is not present!");
		click(loginLocator);
		return new LoginPage(driver);
	}
	
	public boolean isPopUpPresent() {
		return isElementPresent(nastyPopUpLocator);
	}
	
	public void dissmisDamnPopUpifPresent() {
		if(isPopUpPresent()) {
			nastyPopUpLocatorEmailInput.sendKeys(Keys.ESCAPE);
		}
	}	
	public AutomationPracticePage clickOnPracticeLink() {
		assertTrue(isPracticeLinkPresent());
		click(practiceLinkLocator);
		return new AutomationPracticePage(driver);
	}
	
}
