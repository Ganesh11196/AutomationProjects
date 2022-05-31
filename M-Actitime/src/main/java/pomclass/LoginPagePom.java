package pomclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPagePom{
	@FindBy (xpath = "(//input[@type='text'])[1]")
	private WebElement username;
	
	@FindBy (xpath = "(//input[@type='password'])[1]")
	private WebElement password;
	
	@FindBy (xpath = "//input[@type='checkbox']")
	private WebElement checkbox;
	
	@FindBy (xpath = "//a[@id='loginButton']")
	private WebElement login;
	
	public LoginPagePom(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void username(String user) {
		username.sendKeys(user);
	}
	public void password(String pass) {
		password.sendKeys(pass);
	}
	public void clickcheckbox() {
		checkbox.click();
	}
	public void clicklogin() {
		login.click();
	}
	public void logintoapp() throws InterruptedException
	{
		username.sendKeys("patilganesh597@gmail.com");
		password.sendKeys("Patil@2022");
		checkbox.click();
		login.click();
		Thread.sleep(4000);
	}
}
