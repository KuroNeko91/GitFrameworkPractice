package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage extends LoginPage{

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void typeEmailLoginPage(String email) {
		typeEmail(email);
	}
	
	public void clickOnSendMeInstructions() {
		clickLogInButton();
	}
	
	public String getHaderText() {
		return getHeaderText();
	}

}
