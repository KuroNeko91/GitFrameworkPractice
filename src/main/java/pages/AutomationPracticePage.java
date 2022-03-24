package pages;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class AutomationPracticePage extends BasePage{

@FindBy(id = "gf-BIG")
WebElement footerElementLocator;

@FindBy(css = "li[class='gf-li'] a")
List <WebElement> allAutomationPagePracticelinks;

@FindBy(id = "courses-iframe")
WebElement iFrameLocator;

@FindBy(xpath = "//a[normalize-space()='Consulting']")
WebElement consultingFrameLinkLocator;

@FindBy(id = "username")
WebElement yourInputNameLocator;

@FindBy(id = "hide-textbox")
WebElement hideButtonLocator;

@FindBy(id = "displayed-text")
WebElement displayedTextBox;

@FindBy(id = "show-textbox")
WebElement showButtonLocator;

WebDriver driver;

	public AutomationPracticePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public int getLinkCount() {
		int linkCount = allAutomationPagePracticelinks.size();
		return linkCount;
	}
	
	public void validateAllLinks() throws MalformedURLException, IOException {
	    //  List<WebElement> links=   driver.findElements(By.cssSelector("li[class='gf-li'] a"));

		  List<WebElement> links= allAutomationPagePracticelinks;
 	      SoftAssert a =new SoftAssert();

	     

	      for(WebElement link : links)
	      {
	          String url= link.getAttribute("href");
	          HttpURLConnection   conn= (HttpURLConnection)new URL(url).openConnection();
	          conn.setRequestMethod("HEAD");
	          conn.connect();
	          int respCode = conn.getResponseCode();
	          System.out.println(respCode);
	          a.assertTrue(respCode<500, "The link with Text"+link.getText()+" is broken with code" +respCode);
	      }
	      a.assertAll();

	}
	
	public void switchToFrame() {
		assertTrue(isElementPresent(iFrameLocator));
		driver.switchTo().frame(iFrameLocator);
	}
	
	public void switchBackToParentFrame() {
		driver.switchTo().parentFrame();
	}
	
	public void enterNameInFrame(String name) {
		assertTrue(isElementPresent(yourInputNameLocator));
		scrollToWebElement(yourInputNameLocator);
		yourInputNameLocator.sendKeys(name);
	}
	
	public void clickOnConsultingLink() {
		assertTrue(isElementPresent(consultingFrameLinkLocator));
		scrollToWebElement(consultingFrameLinkLocator);
		click(consultingFrameLinkLocator);
	}
	
	public AutomationPracticePage clickHideButton() {
		assertTrue(isElementPresent(hideButtonLocator));
		click(hideButtonLocator);
		return new AutomationPracticePage(driver);
	}
	
	public AutomationPracticePage clickShowButton() {
		assertTrue(isElementPresent(showButtonLocator));
		click(showButtonLocator);
		return new AutomationPracticePage(driver);
	}
	
	public boolean isTextBoxDisplayed() {
		boolean isIt = isElementPresent(displayedTextBox);
		return isIt;
	}
	
	public boolean isTextBoxEnabled() {
		boolean isIt = isElementEnabled(displayedTextBox);
		return isIt;
	}
	
	public boolean isTextBoxInteractable() {
		try {
			waitForElementToBeClickable(displayedTextBox);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public AutomationPracticePage typeTextInDisplayTextBox(String text) {
		assertTrue(isTextBoxInteractable());
		displayedTextBox.sendKeys(text);
		return new AutomationPracticePage(driver);
	}
	
	public String getTypedTextFromDisplayTextBox() {
		assertTrue(isTextBoxInteractable());
		String displText = displayedTextBox.getAttribute("value");
		return displText;
	}
	
	public AutomationPracticePage openAutomationPracticePage() {
		String url = getAutomationPageUrlFromProperties();
		System.out.println("Opening Automation Page URL: "+url);
		driver.get(url);
		return new AutomationPracticePage(driver);
	}
}

