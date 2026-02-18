package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.bases.BaseTest;

public class SearchResuktPageTest extends BaseTest {
	
	
	@BeforeClass
	public void accSetup() {
		accPage=lp.loginIcon(prop.getProperty("username"),prop.getProperty("password"));
		
	}
	@DataProvider
	public Object[][] getDataProvider() {
		return new Object[][] {
			{"macbook",3},
			{"samsung",2},
			{"imac",1}
		};
	}
	@Test(dataProvider="getDataProvider")
	public void searchResultTest(String productKey, int productCount) {
		searchResultPage= accPage.doSearch(productKey);
		Assert.assertEquals(searchResultPage.productCount(), productCount);
	}
//	@Test
//	public void searchResultTest() {
//		searchResultPage= accPage.doSearch("macbook");
//		Assert.assertEquals(searchResultPage.productCount(), 3);
//	}
	
	
}
