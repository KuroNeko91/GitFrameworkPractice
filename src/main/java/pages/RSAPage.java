package pages;

import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RSAPage extends BasePage{

@FindBy (xpath = "//a[@href='#/mentorship']/div")
WebElement mentorshipBoxLocator;

@FindBy (xpath = "//button[contains(@class,'mentorshipJoinBtn')]")
WebElement menthorshipBronzeJoinNowButtonLocator;

@FindBy (xpath = "//button[contains(@class,'mentorshipJoinPlatinumBtn')]")
WebElement menthorshipPlatinumJoinNowButtonLocator;

@FindBy (id = "subscribeModal")
WebElement subscribeModalLocator;

@FindBy (xpath ="//div[@id='subscribeModal']//button[@class='close']")
WebElement closeModalButtonLocator;

@FindBy (id = "enroll-button-top")
WebElement selectMemberShipPackageLocator;

//"//a[@href='#/mentorship']/div"
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	public static String url = getRSAPageUrlFromProperties();
	
	public static Logger log=LogManager.getLogger("RSA Page Logger");
	
	public RSAPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isMentorshipBoxPresent () {
		boolean isPresent = isElementPresent(mentorshipBoxLocator);
		log.debug("isMentorshipBoxPresent() - result: " +isPresent);
		return isPresent;
	}
	
	public RSAPage clickMentorshipBox() {
		assertTrue(isMentorshipBoxPresent());
		click(mentorshipBoxLocator);
		log.debug("clickMentorshipBox()");
		return new RSAPage(driver);
	}
	
	public boolean isBronzeJoinButtonPresent() {
		boolean isPresent = isElementPresent(menthorshipBronzeJoinNowButtonLocator);
		log.debug("isBronzeJoinButtonPresent() - result: " +isPresent);
		return isPresent;
	}
	
	public RSAPage clickJoinBronzeButton() {
		assertTrue(isBronzeJoinButtonPresent());
		click(menthorshipBronzeJoinNowButtonLocator);
		log.debug("clickJoinBronzeButton()");
		return new RSAPage(driver);
	}
	
	public boolean isSubscribeModalOpen() {
		boolean isPresent = isElementPresent(subscribeModalLocator);
		log.debug("isSubscribeModalOpen() - result: " +isPresent);
		return isPresent;
	}
	
	public RSAPage clickCloseModalButton() {
		assertTrue(isSubscribeModalOpen());
		click(closeModalButtonLocator);
		log.debug("clickCloseModalButton()");
		return new RSAPage(driver);
	}
	
	public RSAPage openRSAPage() {
		log.debug("Opening Rahul Shetty Academy URL: "+url);
		driver.get(url);
		return new RSAPage(driver);
	}
	
	public boolean isPlatinumJoinButtonPresent() {
		boolean isPresent = isElementPresent(menthorshipPlatinumJoinNowButtonLocator);
		log.debug("isPlatinumJoinButtonPresent() - result: " +isPresent);
		return isPresent;
	}
	
	public RSAPage clickPlatinumJoinButton() {
		assertTrue(isPlatinumJoinButtonPresent());
		click(menthorshipPlatinumJoinNowButtonLocator);
		log.debug("clickPlatinumJoinButton()");
		return new RSAPage(driver);
	}
	
	public boolean isPlatinumPage() {
		boolean isPresent = isElementPresent(selectMemberShipPackageLocator);
		log.debug("isPlatinumPage() - result: " +isPresent);
		return isPresent;
	}
	
	public void clickSelectMembershipLink() {
		assertTrue(isPlatinumPage());
		click(selectMemberShipPackageLocator);
		log.debug("clickSelectMembershipLink()");
	}
}
	

