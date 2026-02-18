package com.qa.opencart.utils;


public class StringUtil {

	public static String getRandomEmailid() {
		String emaiId = "testAutomation" + System.currentTimeMillis() + "@opencart.com";

		return emaiId;
	}

}
