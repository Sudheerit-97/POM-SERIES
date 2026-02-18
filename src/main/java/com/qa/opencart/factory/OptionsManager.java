package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.opencart.logger.Log;

public class OptionsManager {
	
	private Properties prop; 
	 
	
	private ChromeOptions co; 
	private EdgeOptions eo;
	private FirefoxOptions fo;
	
	
	public OptionsManager(Properties prop) {
		this.prop= prop; 
	}
	
	public ChromeOptions getChromeOptions() {
		co= new ChromeOptions(); 
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			//System.out.println("running chrome in Headless Mode ");
			Log.info("running chrome in Headless Mode");
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			
			co.addArguments("--incognito");
		}
		return co;
	}
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions(); 
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			//System.out.println("running Edge in Headless Mode ");
			Log.info("running Edge in Headless Mode");
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			co.addArguments("--inprivate");
		}
		return eo;
		
	}
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions(); 
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			//System.out.println("running Firefox in Headless Mode ");
			Log.info("running Firefox in Headless Mode");
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			co.addArguments("--incognito");
		}
		return fo;
	}
}
