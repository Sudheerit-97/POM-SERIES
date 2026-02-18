package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.bases.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginpageTest extends BaseTest {
	
	@Test(priority = 1)
	public void loginPageTittleTest() {
		String acttittle= lp.getTitle();
		Assert.assertEquals(acttittle, AppConstants.LOGIN_PAGE_TITLE);
		
	}
	
	@Test (priority = 2)
	public void LoginpageUrlTest() {
		String acturl =lp.getUrl(); 
		Assert.assertTrue(acturl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	@Test (priority = 3)
	public void forgotLinkTest()  {
	Assert.assertTrue(lp.isForgotlinkdisplayed());
	}
	@Test (priority = 4)
	public void Login()  {
		accPage= lp.loginIcon(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountTittle(), "My Account");
	}
}