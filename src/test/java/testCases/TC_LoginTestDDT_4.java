package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.LoginPage;
import utilities.ExcelUtilityUdemy;
import utilities.Log;
import utilities.XLUtils;

public class TC_LoginTestDDT_4 extends BaseClass{
	
	@Test
	public void loginDDT() throws Exception 
	{
		
			
	String path="E:\\bindu network\\Wspaces\\Workspace_Bindu\\bank\\src\\test\\java\\testData\\LoginData.xlsx";
		
			//String path=System.getProperty("user.dir")+"/src/test/java/testData/LoginData.xlsx";
		int rowNum=ExcelUtilityUdemy.getRowCount(path,"Sheet1");
		System.out.println(+rowNum);
	//	int colNum=XLUtils.getColumnCount("Sheet1");
	//	int colNum=XLUtils.getCellCount(path,"Sheet1",1);
//	System.out.println(XLUtils.addColumn(path, "Sheet1", "Status"));
		
		for(int i=1;i<=rowNum;i++){
		//	String user=XLUtils.getCellData("Sheet1", 0,i);
			String user=ExcelUtilityUdemy.getCellData(i,0);
			String pwd=ExcelUtilityUdemy.getCellData(i,1);
			//String pwd=XLUtils.getCellData("Sheet1", 1,i);
		
	LoginPage lp=new LoginPage(driver);
	lp.setUserName(user);
	System.out.println(user);
	Log.info("username is provided");
	lp.setPassword(pwd);
	System.out.println(pwd);
	Log.info("password is provided");
	lp.clickSubmit();
	
	Log.info("clicked submit button");

	
	if(isAlertPresent()==true)//means login failed
	{
		Thread.sleep(5000);//add this otherwise 2 set of data is getting passed as alert is not available so fast
		driver.switchTo().alert().accept();//close aert
		driver.switchTo().defaultContent();//after cosing to go to the main page
		System.out.println("switched to default content");
		//Assert.assertTrue(false);//failure clase
		//if I am asserting without data provider then test is stopping after first row. as assertion applies for 1 data without data provider.
		System.out.println("asserted false");
	String	result="Login failed";
	//Assert.assertTrue(false);
		//XLUtils.setCellData(path, "Sheet1", "Status", i, result);
	ExcelUtilityUdemy.setCellData(result, i, 2, path);
		System.out.println("set result as login failed in the excel");
		Log.warn("Login failed");
			}
	else//isalertpresent=false case is success case
	{
		Thread.sleep(3000);
	//	Assert.assertTrue(true);//so click on logout link
		Log.info("login passed");
		
	String result="Login passed";
	//Assert.assertTrue(true);
//	XLUtils.setCellData(path, "Sheet1", "Status", i, result);
	ExcelUtilityUdemy.setCellData(result, i, 2, path);
		lp.clickLogout();
		
		driver.switchTo().alert();//close logout alert
		driver.switchTo().defaultContent();//focus to login page
	}
		
	
	}
		System.out.println("end of for loop");
	}
	//user defined to verify if alert present or not
	public boolean isAlertPresent()//this method can be resued and put it in baseclass--Do that
	{
		try{
			driver.switchTo().alert();
			return true;//if no alert then throw exception so put in try block
		}
		catch(Exception e)//or else (NoAlertPresentException e)
		{
			return false;
		}
	

	
}
		
		
	
	}



	
	

	