
//had to comment this test case as maven through cli is giving error --this test case utility file is not set --XLUtils but changed parameters there..
/*package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.LoginPage;
import utilities.Log;
import utilities.XLUtils;

public class TC_LoginTestDDT_002 extends BaseClass{
	String result;
	
	//testmethod to login multiple times
	//data provider to provide data
	//data provider method

	@Test(dataProvider="LoginData")//forgot to give this and got Cannot inject @Test annotated Method [loginDDT] with [class java.lang.String, class java.lang.String].
	public void loginDDT(String user,String pwd) throws InterruptedException//here we have to take 2 parameters as obj array stores it in 2 dimensional ie data providing sending in 2 dimensional
	{
		//logout--xpath-- //a[contains(text(),'Log out')] or //a[@attribute=Logout.php  
	
	//data provider method give some name here it is LoginData
	//stores the data from excel and stores it in 2 dimensional array
	
	//create obj of login page
	LoginPage lp=new LoginPage(driver);
	lp.setUserName(user);
	
	Log.info("username is provided");
	lp.setPassword(pwd);
	
	Log.info("password is provided");
	lp.clickSubmit();
	
	Log.info("clicked submit button");
	
	if(isAlertPresent()==true)//means login failed
	{
		Thread.sleep(3000);//add this otherwise 2 set of data is getting passed as alert is not available so fast
		driver.switchTo().alert().accept();//close aert
		driver.switchTo().defaultContent();//after cosing to go to the main page
		Assert.assertTrue(false);//failure clase
		//result="Login failed";
		Log.warn("Login failed");
			}
	else//isalertpresent=false case is success case
	{
		Thread.sleep(3000);
		Assert.assertTrue(true);//so click on logout link
		Log.info("login passed");
	//	result="Login passed";
		lp.clickLogout();
		
		driver.switchTo().alert();//close logout alert
		driver.switchTo().defaultContent();//focus to login page
		
		
		
	}
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
	
	@DataProvider(name="LoginData")

	String[][]	getData() throws Exception
	{
		String path=System.getProperty("user.dir")+"/src/test/java/testData/LoginData.xlsx";
		//count no of rows and columns(cell data)
		//we have to pass the file which is in the path and the sheet name
		
		
		
		int rowNum=XLUtils.getRowCount(path,"Sheet1");
		int colNum=XLUtils.getCellCount(path, "Sheet1",1);//here specify one of the rownumber here as 1 count 
		//create 2 d array
		String loginData[][]=new String[rowNum][colNum];
		//String path1=System.getProperty("user.dir")+"/src/test/java/testData/LoginDataResult.xlsx";
		
		
		
		for(int i=1;i<=rowNum;i++)//rowcount is 5(1,2,3,4,5)
		{
			for (int j=0;j<colNum;j++)//if col index stars from 0 then <colcount or else if starts from 1 then colcount<=
			{
				
				loginData[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);//1 row 0 col;1row 1col;colcount is 2 columns(0,1)
				
			}
		
			}
			

return loginData;
}
	
}
*/