package com.qa.opencart.tests;

import java.util.List;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.bases.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPagetest extends BaseTest {
	
	@BeforeClass
	public void accSetup() {
		accPage=lp.loginIcon(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test(priority =1)
	public void getTittle() {
		String actTitle= accPage.getAccountTittle(); 
		Assert.assertEquals(actTitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	@Test(priority =2)
	public void getUrlLink() {
		String actURL = accPage.getUrl();
		Assert.assertTrue(actURL.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION));
	}
	@Test(priority =3)
    public void getLogoutLink() {
     Assert.assertTrue(accPage.isLogoutLinkEXist());
    }
	@Test(priority =4)
    public void getMyaccountLink() {
    	Assert.assertTrue(accPage.isMyAccountExist());
    }
	@Test(priority =5)
    public void getHeadersTexList() {
    	List<String> actHeaders= accPage.headersChcek(); 
    	System.out.println(actHeaders);
    	
    }
	@Test(priority =6)
    public void getSearchResult() {
    	 accPage.doSearch("macbook");
    	
    }
}
