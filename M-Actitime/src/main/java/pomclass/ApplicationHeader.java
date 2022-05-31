package pomclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplicationHeader{
	@FindBy (xpath = "//a[@class='content tasks']")
	private WebElement tasktab;
	
	@FindBy (xpath = "//a[@class='content reports']")
	private WebElement reportstab;
	
	@FindBy (xpath = "//a[@class='content users']")
	private WebElement userstab;
	
	@FindBy (xpath = "//a[text()='Logout']")
	private WebElement logoutlink;
public ApplicationHeader(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}
public void tasktab()
{
	tasktab.click();
}
public void reportstab()
{
	reportstab.click();
}
public void userstab()
{
	userstab.click();
}
public void logoutapp() throws InterruptedException
{
	Thread.sleep(4000);
    logoutlink.click();
	Thread.sleep(4000);
}
}
