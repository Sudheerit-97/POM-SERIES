package com.qa.opencart.pages;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	private By emailId = By.id("input-email");
	private By passId = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotpwdLink = By.linkText("Forgotten Password");
	private By regsiterLink = By.linkText("Register"); 

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
		
	}

	public String getTitle() {
		String tittle= eleutil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, 5);
		//System.out.println("write the title" + tittle);
		Log.info("write the title" + tittle);
		
		return tittle;

	}

	public String getUrl() {
	String url=  eleutil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, 5);
	System.out.println("get the current url"+url);
	return url;
   }

	public boolean isForgotlinkdisplayed() {
		return eleutil.isElementDisplayed(forgotpwdLink);
	}

	public AccountPage loginIcon(String email, String pwd)  {
		System.out.println("Login credentials" + email + "  ::" + pwd);
		eleutil.waitForElementVisible(emailId, 5).sendKeys(email);
		eleutil.doSendKeys(passId, pwd);
		eleutil.doClick(loginButton);
		
		
		return  new AccountPage(driver);
		
	}
	public RegistrationPage navigateToRegistration() {
		eleutil.waitForElementVisible(regsiterLink, 5).click();
		return new RegistrationPage(driver);
	}

}
