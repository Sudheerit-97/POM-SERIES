package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	private WebDriver driver;
	private ElementUtil eleutil;
    
	
	private By images = By.cssSelector("div.product-thumb") ;
	//private By product= By.linkText("productName"); 

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
		
	}  
	
	public int productCount() {
		return eleutil.waitForElementsPresence(images, 6).size();
	
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Searching for the product: "+productName);
		eleutil.waitForElementVisible(By.linkText(productName), 10).click();
        return new ProductInfoPage(driver);
		
		
	}

}
