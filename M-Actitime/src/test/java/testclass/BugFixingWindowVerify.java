package testclass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
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

public class BugFixingWindowVerify {
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
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	@BeforeClass
	public void createPomObject()
	{
		aH=new ApplicationHeader(driver);
		login=new LoginPagePom(driver);
		task=new TaskPagePom(driver);
	}
	@BeforeMethod
	public void logintoapp() throws InterruptedException, EncryptedDocumentException, IOException
	{
		driver.get("https://online.actitime.com/xyz17/login.do");
		Thread.sleep(3000);
		String user=Utility.getDataFromExcel( 1, 0);
	    login.username(user);
		String pass=Utility.getDataFromExcel(1, 1);
	    login.password(pass);
	    login.clickcheckbox();
	    login.clicklogin();
	    Thread.sleep(3000);
	}
	@Test
	public void verifybugfixing() throws InterruptedException, EncryptedDocumentException, IOException
	{
		id=200;
		aH.tasktab();
		Thread.sleep(3000);
		String value=Utility.getDataFromExcel(3, 0);
		task.BugFixing(driver,value);
		String url=driver.getCurrentUrl();
		Assert.assertEquals(url,"https://online.actitime.com/xyz17/tasks/tasklist.do?taskId=92");
		task.windowhide();
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
		System.gc();
	}

}
