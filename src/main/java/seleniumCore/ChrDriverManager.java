package seleniumCore;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;



public class ChrDriverManager {
	public WebDriver driver;
	WebDriverManager webDriverManager; 
	private static final ThreadLocal<WebDriver> cdriver = new ThreadLocal<>();
	
	 public void setChroDriver(boolean isHeadless, boolean isIncognito) {
	    //setup the chromedriver using WebDriverManager
		 WebDriverManager.chromedriver().setup();
		
	    //Create Chrome Options
	    ChromeOptions option = new ChromeOptions();
	    option.addArguments("--test-type");
	    option.addArguments("--disable-popup-blocking");
	    
	    if(isHeadless) {
	    	option.addArguments("--headless");
	    }
	    if(isIncognito) {
	    	option.addArguments("--incognito");
	    }
	    option.addArguments("--start-maximized");
	    option.setAcceptInsecureCerts(true);
	    DesiredCapabilities chrome = new DesiredCapabilities();
	    chrome.setJavascriptEnabled(true);
	    option.setCapability(ChromeOptions.CAPABILITY, option);
	    //Create driver object for Chrome
	    //webDriverManager.chromedriver().setup();
	   // driver = new ChromeDriver(option);
	    driver = WebDriverManager.chromedriver().capabilities(option).create();//chromedriver().capabilities(option).create();
	    cdriver.set(Objects.requireNonNull(driver));
	    
	   // return driver;
	}
	
	 public WebDriver getChroDriver() {
		return Objects.requireNonNull(cdriver.get());
	 }
	
}
