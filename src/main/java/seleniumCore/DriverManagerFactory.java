package seleniumCore;

import java.time.Duration;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.PropertiesReader;

public class DriverManagerFactory {
	
	public WebDriver driver;
	public Properties properties = new Properties();
	public PropertiesReader propReader;
	ChrDriverManager chromeDriverManager;
	FirefoxDriverManager firefoxGeckodriverManager;
	boolean isHeadless;
	boolean isIncognito;
	
//	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
/*
    public static void setDriver(String browser) {
        WebDriver rwd;
            switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                rwd = new ChromeDriver();
                break;

            case "fireFox":
                WebDriverManager.firefoxdriver().setup();
                rwd = new FirefoxDriver();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }
        driver.set(Objects.requireNonNull(rwd));
        prepareBrowser();
    }
    private static void prepareBrowser(){
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }
    public static WebDriver getDriver(){
        return Objects.requireNonNull(driver.get());
    }
    public static void closeBrowser() {
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().close();
        getDriver().quit();
    }
}
	*/
	public WebDriver driverWrapper(){
		WebDriver driver;
		propReader = new PropertiesReader();
		String browser = System.getProperty("browser");
		
		//[maven command to invoke browser] mvn test -Dbrowser=
		System.out.println("Browser from maven comand is: " +browser);
		if(browser == null) {
			browser = PropertiesReader.getBrowserName(properties);
			System.out.println("Browser from data.properties is: "+browser);
		}
		
		String headless = System.getProperty("headless");
		System.out.println("Headless value from maven comand: " +headless);
		if(headless ==null) {
			isHeadless = PropertiesReader.isHeadless(properties);
			System.out.println("Headless value from data.properties: "+isHeadless);
		}else if((!(headless.contains("headless")))) {
			if(headless.contains("normal")) {
				isHeadless = false;
			}else {
			isHeadless = PropertiesReader.isHeadless(properties);
			System.out.println("Headless value from data.properties: "+isHeadless);
			}
		} else if(headless.contains("default")){
			isHeadless = PropertiesReader.isHeadless(properties);
			System.out.println("Headless value from data.properties: "+isHeadless);
		} else {
			isHeadless = true;
		}
		
		isIncognito = PropertiesReader.isIncognito(properties);
		switch(browser) {
		case ("chrome"):{
			chromeDriverManager = new ChrDriverManager();
			chromeDriverManager.setChroDriver(isHeadless, isIncognito);
			driver = chromeDriverManager.getChroDriver();
			System.out.println("THREAD_ID: [" + Thread.currentThread().getId() + "]");
			System.out.println("CHROME_ID: [" + ((ChromeDriver) driver).getSessionId()+ "]");
			//driver.manage().window().maximize();
			break;
		} case ("firefox"):{
			firefoxGeckodriverManager = new FirefoxDriverManager();
			driver = firefoxGeckodriverManager.getFirefoxGeckoDriver();
			driver.manage().window().maximize();
			break;
		} default:{
			chromeDriverManager = new ChrDriverManager();
			chromeDriverManager.setChroDriver(isHeadless, isIncognito);
			driver = chromeDriverManager.getChroDriver();
			break;
		}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		return driver;
	}
}
	

