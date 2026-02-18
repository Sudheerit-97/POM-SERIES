package com.qa.opencart.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;


public class RegistrationPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName= By.id("input-firstname"); 
	private By lastName=  By.id("input-lastname");
	private By email= By.id("input-email"); 
	private By telephone = By.id("input-telephone"); 
	private By password = By.id("input-password"); 
	private By confirmPassword= By.id("input-confirm"); 
	
	private By SubsCribeYes= By.xpath("//label[@class='radio-inline']/input[@type= 'radio' and @value='1']"); 
	private By SubsCribeNo= By.xpath("//label[@class='radio-inline']/input[@type= 'radio' and @value='0']"); 
	private By checkBox= By.xpath("//*[@id=\"content\"]/form/div/div/input[1]"); 
	private By clickContinue = By.cssSelector("#content > form > div > div > input.btn.btn-primary");
	private By successMessage= By.tagName("h1"); 
	
	private By logout = By.linkText("Logout"); 
	private By register = By.linkText("Register"); 
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean userRegister(String firstName, String lastName, String email, String telephone, String password, String confirmPassword ,String SubsCribe ) {
		eleUtil.waitForElementVisible(this.firstName, 10).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, confirmPassword);
		
		if(SubsCribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(SubsCribeYes);
		}
		else {
			eleUtil.doClick(SubsCribeNo);
		}
		eleUtil.doClick(checkBox);
		eleUtil.doClick(clickContinue);
		String registrationSuccess= eleUtil.waitForElementVisible(successMessage, 5).getText(); 
		System.out.println(registrationSuccess);
		if(registrationSuccess.equals(AppConstants.REGISTRATION_SUCCESS_DONE)) {
			eleUtil.doClick(logout);
			eleUtil.doClick(register);
			return true;
		}
		return false;
        		
	
	}
}