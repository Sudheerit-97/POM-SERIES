package com.qa.opencart.tests;

import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.bases.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtil;

public class RegistrationTest extends BaseTest {
	
	@BeforeClass
	public void regisTrationSetup() {
		registrationPage= lp.navigateToRegistration();
		
	}
	//String firstName, String lastName, String email, String telephone, String password, String confirmPassword ,String SubsCribe )
	@DataProvider
	public Object[][] getDataProviderforRegistration() {
		return new Object[][] {
			{"ravi", "mishra","9452244889", "P@972555", "P@972555","yes"}, 
			{"narottam", "chahar" ,"9452244889", "P@972555", "P@972555","yes" },
			{"suman","kataria","9452244889", "P@972555", "P@972555","yes"},
			{"Diksha", "singh","9452244889", "P@972555", "P@972555","yes"}
		
			
		};
	}
	
		@DataProvider 
		public Object[][] getDataProviderfromExcel() {
			return ExcelUtil.getTestData(AppConstants.REGISTERATION_SHEET_NAME);
				
			}
	
	@Test(dataProvider="getDataProviderforRegistration")
	public void userRegistration(String firstName, String lastName,  String telephone, String password, String confirmPassword ,String SubsCribe) {
		Assert.assertTrue(registrationPage.userRegister(firstName, lastName, StringUtil.getRandomEmailid(),telephone, password, confirmPassword,SubsCribe));
	}
//	public void userRegistration() {
//		Assert.assertTrue(registrationPage.userRegister("sudheer", "shukla", StringUtil.getRandomEmailid(),"9792712020", "sudheer@92", "sudheer@92","yes"));
//	}
//	

}
