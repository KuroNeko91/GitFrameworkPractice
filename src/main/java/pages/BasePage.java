package pages;

import java.io.IOException;
import java.time.Duration;

import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.PropertiesReader;

public class BasePage {

	public WebDriver driver;
	WebDriverWait wait;
	int waitSeconds = 5;
	public static Properties properties = new Properties();
	
	/*
	 * Base Page Constructor
	 * Args WebDriver, WebDriverWait
	 */
	public BasePage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * 
	 */
	public void waitForElementToBeVisible (WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/*
	 * 
	 */
	public void waitForElementToBeClickable (WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/*
	 * 
	 */
	public boolean isElementPresent(WebElement element) {
		try{
			if(element.isDisplayed());
			return true;
		}catch(NoSuchElementException e) {
		} return false;
	}
	
	public boolean isElementEnabled(WebElement element) {
		try{
			if(element.isEnabled());
			return true;
		}catch(NoSuchElementException e) {
		} return false;
	}
	
	/*
	 * 
	 */
	public String getText(WebElement element) {
		waitForElementToBeVisible(element);
		String text = element.getText();
		return text;
	}
	
	/*
	 * 
	 */
	public void scrollToWebElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	/**
	 * 
	 */
	public void assertEquals(WebElement element, String expectedText) {
		waitForElementToBeVisible(element);
		String actualElementText = element.getText();
		Assert.assertEquals(actualElementText, expectedText, "Actual and Expected values are not equal!");
	}
	
	public static String getHomePageUrlFromProperties(){
		String url = null;
		try {
			url = PropertiesReader.getHomePageURL(properties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	};
	
	public static String getAutomationPageUrlFromProperties() {
		String url = null;
		try {
			url = PropertiesReader.getAutomationPageUrl(properties);
		}catch(IOException o) {
			o.printStackTrace();
		}
		return url;
	}
	
	public void navigatetoHome() {
		driver.get(getHomePageUrlFromProperties());
	}
	
	public void click(WebElement element) {
		waitForElementToBeVisible(element);
		waitForElementToBeClickable(element);
		
		String browserName = PropertiesReader.getBrowserName(properties);
		if(browserName.equals("firefox")) {
			scrollToWebElement(element);
		}
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();

		element.click();
		
	}
}
