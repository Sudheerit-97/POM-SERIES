package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
    
	private By logoutLink= By.linkText("Logout");
	private By myAccountLink= By.linkText("My Account"); 
	private By serachFunc= By.name("search"); 
	private By searchButton = By.xpath("//div[@id='search']//button[@type='button']"); 
	private By headerCheck = By.xpath("//div[@id='content']/h2"); 

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
		
	}  
	public String getAccountTittle() {
		String tittle = eleutil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, 5); 
		System.out.println("account page tittle   :: "+tittle);
		return tittle;
	}
	
	public String getUrl() {
		String url = eleutil.waitForURLContains(AppConstants.ACCOUNT_PAGE_URL_FRACTION, 5);
	  System.out.println("account page url:: "+url);
	  return url;
	}
	
	public boolean isLogoutLinkEXist() {
		return eleutil.waitForElementVisible(logoutLink, 5).isDisplayed(); 
	}
	public boolean isMyAccountExist() {
		return eleutil.waitForElementVisible(myAccountLink, 5).isDisplayed();
	}
	
	public List<String>  headersChcek() {
		List<WebElement> headerlist= eleutil.waitForElementsVisible(headerCheck, 10);
		List<String> allHeaders = new ArrayList<String>();
		System.out.println(allHeaders);
		
		for (WebElement e :headerlist) {
			String text= e.getText(); 
		allHeaders.add(text.trim()); 
		}
		return allHeaders;
	}
	public SearchResultPage doSearch(String searchKey) {
		System.out.println("Search the item ::"+searchKey);
		eleutil.doSendKeys(serachFunc, searchKey);;
		eleutil.doClick(searchButton, 5);
		return new SearchResultPage(driver);
	}
		
	}


