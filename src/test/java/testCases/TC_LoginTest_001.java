package testCases;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.LoginPage;
import utilities.Log;



public class TC_LoginTest_001 extends BaseClass
	{
	
		@Test
		public void loginTest() throws IOException 
		{
			
			driver.get(baseURL);
			Log.info("URL is opened");
			LoginPage lp=new LoginPage(driver);
			
			lp.setUserName(username);
			Log.info("entered username");
			lp.setPassword(password);
			Log.info("entered password");
			
			
			lp.clickSubmit();
			Log.info("clicked submit button");
			
			if(driver.getTitle().equals(" Bank Manager HomePage"))
			{				
				Assert.assertTrue(true);
				Log.info("login test passed");
			}
			else
			{
				captureScreen(driver, "loginTest");
				Assert.assertTrue(false);
				Log.info("login test failed");
				
			}
			
		}
	}