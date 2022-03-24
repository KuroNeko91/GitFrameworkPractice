package pages;

import static org.testng.Assert.assertTrue;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
	
	
@FindBy(tagName = "h1")
WebElement headerTextLocator;
	
@FindBy(id = "user_email")
WebElement emailTextFieldLocator;

@FindBy(id = "user_password")
WebElement passwordTextFieldLocator;

@FindBy(css = "input[type='submit']")
WebElement submitButtonLocator;

@FindBy(css = "div[role='alert']")
WebElement alertTextLocator;

@FindBy(css = "a[class=link-below-button]")
WebElement forgotPasswordLocator;

	WebDriver driver;
	public LoginPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getHeaderText() {
		String headerText = getText(headerTextLocator);
		return headerText;
	}
	
	public void typeEmail(String email) {
		assertTrue(isElementPresent(emailTextFieldLocator));
		emailTextFieldLocator.sendKeys(email);
	}
	
	public void typePassword(String password) {
		assertTrue(isElementPresent(passwordTextFieldLocator));
		passwordTextFieldLocator.sendKeys(password);
	}
	
	public void clickLogInButton() {
		assertTrue(isElementPresent(submitButtonLocator));
		click(submitButtonLocator);
	}
	
	public void getEmailTextLabel() {
		WebElement labelEmail = driver.findElement(with(By.tagName("input")).above(emailTextFieldLocator));
		System.out.println(labelEmail.getText());
	}
	
	public String getAlertText() {
		String alertText = getText(alertTextLocator);
		return alertText;
	}
	
	public void tryLoginWithWrongCreds(String wrongEmail, String wrongPassword) {
		typeEmail(wrongEmail);
		typePassword(wrongPassword);
		clickLogInButton();
	}
	
	public ForgotPasswordPage clickForgotPassword() {
		assertTrue(isElementPresent(forgotPasswordLocator));
		forgotPasswordLocator.click();
		return new ForgotPasswordPage(driver);
	}

}
