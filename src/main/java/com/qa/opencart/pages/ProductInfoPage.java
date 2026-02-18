package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	
    private By productHeader= By.tagName("h1"); 
    private By images = By.xpath("//ul[@class='thumbnails']//img");
	private By productMetadata= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li"); 
	private By productPricedata= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String, String> ProductData = new LinkedHashMap<String, String>();

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
		
	}  
	
	public String selectProductHeader() {
		String  Headr= eleutil.doGetElementText(productHeader); 
		System.out.println(Headr);
		return Headr; 
		
	}
	
	public int totalNumnerOfImage() {
		int totalImages = eleutil.waitForElementsVisible(images, 10).size();
				System.out.println("total Images of "+selectProductHeader()+"::"+totalImages);
		return totalImages; 
				
	}
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	public void selectProductMetaData() {
		List<WebElement> metaList= eleutil.getElements(productMetadata);
		for(WebElement e : metaList) {
			String text = e.getText(); 
			String metaKey= text.split(":")[0].trim(); 
			String metaValue= text.split(":")[1].trim();
			ProductData.put(metaKey, metaValue); 
		}
	}
//	$2,000.00
//	Ex Tax: $2,000.00
   public void priceData() {
	   List<WebElement> priceList=  eleutil.getElements(productPricedata); 
	    
	  String price=  priceList.get(0).getText();
	  String ExPrice = priceList.get(1).getText().split(":")[1].trim();
	  ProductData.put("productPrice", price); 
	  ProductData.put("productPriceExPrice", ExPrice);
	   
	   
   }
   public Map<String, String> getProductDetailsMap() {
	   ProductData.put("productHeader::", selectProductHeader()); 
	   ProductData.put("Images Count", String.valueOf(totalNumnerOfImage()));
	   selectProductMetaData();
	   priceData(); 
	   System.out.println("product details: \n"+ProductData);
	   return ProductData;
   }
   
   
}
