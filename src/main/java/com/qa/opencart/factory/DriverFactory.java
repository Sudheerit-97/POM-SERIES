package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;


import com.qa.opencart.erors.AppErrors;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

public class DriverFactory {
	
	protected WebDriver driver; 
	Properties prop; 
	OptionsManager  optionsManager;
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();
	public static String highlight; 
	
	public WebDriver initBrowser(Properties  prop ) {
		String BrowserName= prop.getProperty("browser");
		
		
		System.out.println("broswer name is : "+BrowserName);
		Log.info("broswer name is :"+BrowserName);
		
		highlight= prop.getProperty("highlight"); 
		
		
		
		optionsManager= new OptionsManager(prop);
		
		
		
		
		switch (BrowserName.toLowerCase().trim()) {
		case "chrome":
			//driver= new ChromeDriver(optionsManager.getChromeOptions()); 
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));			
			break;
		case "firefox":
			//driver= new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));	
			break;
		case "edge":
			//driver= new EdgeDriver(optionsManager.getEdgeOptions()); 
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		default:
			
			//System.out.println("pass the correct Browser"+BrowserName);]
			Log.error("pass the correct Browser....."+BrowserName);
			throw new BrowserException("BROWSER IS INCORRECT"); 
			
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.get(prop.getProperty("url"));
		return getDriver();
	}
	
	public static WebDriver  getDriver() {
		return tlDriver.get();
	}
	public Properties initProperties() {
		
		FileInputStream ip =null; 
		prop = new Properties(); 
		String envName= System.getProperty("env"); 
		System.out.println("enter the envirinment name:"+envName);
		try {
		if(envName==null) {
			ip =new FileInputStream("./src/test/resources/config/config.qa.properties");
		}
		
		else {
		switch (envName.toLowerCase().trim()) {
		
		case "qa":
			ip =new FileInputStream("./src/test/resources/config/config.qa.properties");
			break;
     case "dev":
	   ip =new FileInputStream("./src/test/resources/config/config.dev.properties");
	   break;
     case "stage":
    	
	   ip =new FileInputStream("./src/test/resources/config/config.stage.properties");
	   break;
    case "uat":
	     ip =new FileInputStream("./src/test/resources/config/config.uat.properties");
			break;

		default:
			System.out.println("plz pass the right envirionment "+envName);
			throw new FrameworkException(AppErrors.ELEMENT_NOT_FOUND+"::"+envName); 
			
		}
		}
		} catch(FileNotFoundException e) {
		e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
		
		
		
		
//		try {
//			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
//			prop.load(ip);		
//			}
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return prop;
	}
	/**
	 * take screenshot
	 */

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
