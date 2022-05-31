package testclass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browser.Base;
import pomclass.ApplicationHeader;
import pomclass.LoginPagePom;
import pomclass.TaskPagePom;
import utils.Utility;

public class VerifyHeader {
	WebDriver driver;
	int id;
	ApplicationHeader aH;
	LoginPagePom login;
	TaskPagePom task;
	@Parameters ("browserName")
	@BeforeTest
	public void launchbrowser(String browser)
	{
		if(browser.equals("Chrome"))
		{
		driver=Base.openChromeBrowser();
		}
		if(browser.equals("Firefox"))
		{
		driver=Base.openFirefoxBrowser();
		}
		if(browser.equals("Opera"))
		{
		driver=Base.openOperaBrowser();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	@BeforeClass
	public void createPomObject()
	{
		aH=new ApplicationHeader(driver);
		login=new LoginPagePom(driver);
		task=new TaskPagePom(driver);
	}
	@BeforeMethod
	public void logintoapp() throws InterruptedException
	{
		driver.get("https://online.actitime.com/xyz17/login.do");
	    login.logintoapp();  
	}
	@Test
	public void checklogin()
	{
		id=100;
		String url=driver.getCurrentUrl();
		Assert.assertEquals(url,"https://online.actitime.com/xyz17/timetrack/enter.do");
	System.out.println("Test is Passed");
	}
	@Test(priority= 1)
	public void verifytasktab()
	{
		id=101;
		aH.tasktab();
		String url=driver.getCurrentUrl();
		Assert.assertEquals(url,"https://online.actitime.com/xyz17/tasks/tasklist.do");
	}
	@Test(priority= 2)
	public void verifyreportstab()
	{
		id=102;
		aH.reportstab();
		String url=driver.getCurrentUrl();
		Assert.assertEquals(url,"https://online.actitime.com/xyz17/reports/dashboard.do");

	}
	@Test(priority= 3)
	public void verifyuserstab()
	{
		id=103;
		aH.userstab();
		String url=driver.getCurrentUrl();
		Assert.assertEquals(url,"https://online.actitime.com/xyz17/administration/userlist.do");

	}

	@AfterMethod
	public void logoutfromapp(ITestResult result) throws InterruptedException, IOException
	{
		if(ITestResult.FAILURE==result.getStatus())
		{
			Utility.CaptureScreenShot(driver, id);
		}
	    aH.logoutapp();
	}
	@AfterClass
	public void clearObject()
	{
		aH=null;
		login=null;
		task=null;
	}
	@AfterTest
	public void closebrowser()
	{
		driver.close();
		driver=null;
		System.gc();//Garbage collector
	}

}
