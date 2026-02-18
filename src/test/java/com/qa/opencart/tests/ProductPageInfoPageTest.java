package com.qa.opencart.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.bases.BaseTest;

public class ProductPageInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productsetup() {
		accPage=lp.loginIcon(prop.getProperty("username"),prop.getProperty("password"));
	}
	@DataProvider
	public Object[][] dataProviderForProductHeader() {
		return new Object[][] {
		{"macbook","MacBook Pro" },
		{"imac", "iMac"},
		{"samsung", "Samsung SyncMaster 941BW"}, 
		{"samsung", "Samsung Galaxy Tab 10.1"},
		
		}; 
	}
	@Test(dataProvider="dataProviderForProductHeader")
	public void productHeaderTest(String productKey, String selectProduct) {
		searchResultPage= 	accPage.doSearch(productKey); 
		productInfoPage= searchResultPage.selectProduct(selectProduct);
		Assert.assertEquals(productInfoPage.selectProductHeader() , selectProduct);
		
	}
//	@Test
//	public void productHeaderTest() {
//		searchResultPage= 	accPage.doSearch("macbook"); 
//		productInfoPage= searchResultPage.selectProduct("MacBook Pro");
//		Assert.assertEquals(productInfoPage.selectProductHeader() , "MacBook Pro");
//		
//	}
	@DataProvider
	public Object[][] dataProviderForProductImage() {
		return new Object[][] {
		{"macbook","MacBook Pro", 4 },
		{"imac", "iMac", 3},
		{"samsung", "Samsung SyncMaster 941BW", 1}, 
		{"samsung", "Samsung Galaxy Tab 10.1",7},
		
		}; 
	}
	@Test(dataProvider="dataProviderForProductImage")
	public void getProductImageCount(String productKey, String selectProduct, int imageCount) {
		searchResultPage= 	accPage.doSearch(productKey); 
		productInfoPage= searchResultPage.selectProduct(selectProduct);
		Assert.assertEquals(productInfoPage.totalNumnerOfImage(), imageCount);
	}
//	@Test
//	public void getProductImageCount() {
//		searchResultPage= 	accPage.doSearch("iMac"); 
//		productInfoPage= searchResultPage.selectProduct("iMac");
//		Assert.assertEquals(productInfoPage.totalNumnerOfImage(), 3);
//	}
	@Test
	public void getProductDetails() {

	    

	    searchResultPage = accPage.doSearch("macbook"); 
	    productInfoPage = searchResultPage.selectProduct("MacBook Pro");

	    Map<String, String> actualProductDetail = productInfoPage.getProductDetailsMap(); 

	    softAssert.assertEquals(actualProductDetail.get("Brand"), "Apple");
	    softAssert.assertEquals(actualProductDetail.get("Product Code"), "Product 18");
	    softAssert.assertEquals(actualProductDetail.get("Reward Points"), "800");
	    softAssert.assertEquals(actualProductDetail.get("Availability"), "Out Of Stock");
	    softAssert.assertEquals(actualProductDetail.get("productPrice"), "$2,000.00");
	    softAssert.assertEquals(actualProductDetail.get("productPriceExPrice"), "$2,000.00");

	    softAssert.assertAll();
	}

}
