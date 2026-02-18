package com.qa.opencart.bases;

import java.util.Properties;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;


import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df; 
	protected Properties prop;
	protected LoginPage lp;  
	protected AccountPage accPage;
	protected SearchResultPage searchResultPage;
	protected ProductInfoPage productInfoPage; 
	protected SoftAssert softAssert;
	protected RegistrationPage registrationPage; 
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df= new  DriverFactory(); 
		prop= df.initProperties();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
	 driver= df.initBrowser(prop);
	 
	 lp = new LoginPage(driver); 
	 softAssert = new SoftAssert();
		
	}
	@AfterTest
	public void tearDown () {
		driver.quit();
	}

}
