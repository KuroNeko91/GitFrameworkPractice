package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	protected static Properties properties;
	protected static FileInputStream fis;
	protected static String userDirPath = System.getProperty("user.dir");
	protected static String propertiesPath = "/src/main/java/resources/data.properties";
	protected static String propertiesRelativePath = userDirPath+propertiesPath;
	
	public static String getBrowserName(Properties properties) {
		properties= new Properties();
		try {
			fis = new FileInputStream(propertiesRelativePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			properties.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String browser = properties.getProperty("browser").toLowerCase();
		return browser;
			
	}
	
	public static String getHomePageURL(Properties properties) throws IOException {
		properties= new Properties();
		fis = new FileInputStream(propertiesRelativePath);
		
		properties.load(fis);
		String url = properties.getProperty("url");
		return url;
	}
	
	public static String getAutomationPageUrl(Properties properties) throws IOException{
		properties = new Properties();
		fis = new FileInputStream(propertiesRelativePath);
		properties.load(fis);
		String automationPageUrl = properties.getProperty("practiceUrl");
		return automationPageUrl;
	}
	
	public static String getRSAPageUrl(Properties properties) throws IOException{
		properties = new Properties();
		fis = new FileInputStream(propertiesRelativePath);
		properties.load(fis);
		String automationPageUrl = properties.getProperty("rsaUrl");
		return automationPageUrl;
	}
	
	public static boolean isHeadless(Properties properties){
		properties = new Properties();
		try {
			fis = new FileInputStream(propertiesRelativePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			properties.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String headless = properties.getProperty("headless");
		if(headless.equals("true")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isIncognito(Properties properties) {
		properties = new Properties();
		try {
			fis = new FileInputStream(propertiesRelativePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			properties.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String incognito = properties.getProperty("incognito");
		if(incognito.equals("true")) {
			return true;
		} else {
			return false;
		}
	}
}
