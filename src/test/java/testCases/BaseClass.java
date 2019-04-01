package testCases;

//import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.ReadConfig;



public class BaseClass {
	
	/*public String baseURL="http://demo.guru99.com/V4/";
	public String username="mngr184859";
	public String password="dUzUdUm";
	public static WebDriver driver;*/
	//moved above to config.properties
	
	ReadConfig readconfigobj=new ReadConfig();
	public String baseURL=readconfigobj.getApplicationURL();
	public String username=readconfigobj.getUserName();
	public String password=readconfigobj.getPassword();
	public static WebDriver driver;
	
	//public static final Logger log = LogManager.getLogger(BaseClass.class);
	//public static Logger logger;
	
	/*@BeforeClass
	public void setUp()
	{
	//moved the system.get property to config.properties
	//	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", readconfigobj.getChromePath());
		driver=new ChromeDriver();
 //logger = Logger.getLogger("Bank");
	PropertyConfigurator.configure("log4j.properties");
	*/
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{			
		
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readconfigobj.getChromePath());
			driver=new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfigobj.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",readconfigobj.getIEPath());
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(baseURL);
	}
	
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");

}
	/*public static String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	*/
	@AfterClass
	public void tearDown()
	{
	//	driver.quit();
	}

	
}

