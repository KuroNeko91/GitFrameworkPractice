package seleniumCore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager {

	public WebDriver driver;
	
	public WebDriver getFirefoxGeckoDriver() {
		WebDriverManager.firefoxdriver().setup();
		
		FirefoxOptions option = new FirefoxOptions();
		option.addArguments("--test-type");
	    option.addArguments("--disable-popup-bloacking");
	    option.addArguments("start-maximized");
	    option.setAcceptInsecureCerts(true);
	    DesiredCapabilities firefox = new DesiredCapabilities();
	    firefox.setJavascriptEnabled(true);
	    option.setCapability(FirefoxOptions.FIREFOX_OPTIONS, option);
	    //Create driver object for Firefox
	    this.driver = new FirefoxDriver(option);
		return this.driver;
	}
	
}
