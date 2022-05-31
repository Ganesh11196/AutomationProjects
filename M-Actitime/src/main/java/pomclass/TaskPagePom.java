package pomclass;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TaskPagePom {
	@FindBy (xpath = "(//div[text()='Spaceship testing'])[1]")
	private WebElement spaceshiptest;
	
	@FindBy (xpath = "//div[@title='Bug fixing']")
	private WebElement bugfixing;
	
	@FindBy (xpath = "(//input[@type='text'])[45]")
	private WebElement entervalue;
	
	@FindBy (xpath = "(//div[@class='hideButton_panelContainer'])[3]")
	private WebElement hidewindow;
	
public TaskPagePom(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}
public void spaceshiptesting()
{
	spaceshiptest.click();
}
public void bugfixingwindow()
{
	bugfixing.click();
}
public void valueenter()
{
	entervalue.sendKeys("2000");
}
public void windowhide()
{
	hidewindow.click();
}
public void BugFixing(WebDriver driver,String value) throws InterruptedException
{
	spaceshiptest.click();
	Thread.sleep(3000);
	bugfixing.click();
	Thread.sleep(3000);
    Actions a =new Actions(driver);
	a.moveToElement(entervalue).doubleClick().click().sendKeys(Keys.BACK_SPACE).build().perform();
	entervalue.sendKeys(value);
}
}
