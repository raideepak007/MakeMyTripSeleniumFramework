package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MakeMyTripLogin {
	
	private static WebElement element=null;
	
	public static WebElement login(WebDriver driver)
	{
		element=driver.findElement(By.xpath("//p[text()='Login or Create Account']"));
		return element;
	}
	
	public static WebElement login_popup(WebDriver driver)
	{
		element=driver.findElement(By.xpath("//label[text()='Login with Phone/Email']"));
		return element;
	}
	
	
	
	public static WebElement email_id(WebDriver driver)
	{
		element=driver.findElement(By.xpath("//input[@id='username']"));
		return element;
	}
	
	public static WebElement submit_btn(WebDriver driver)
	{
		element=driver.findElement(By.xpath("//span[text()='Continue']"));
		return element;
	}
	
	public static WebElement password(WebDriver driver)
	{
		element=driver.findElement(By.xpath("//input[@id='password']"));
		return element;
	}
	
	public static WebElement login_btn(WebDriver driver)
	{
		element=driver.findElement(By.xpath("//span[text()='Login']"));
		return element;
	}
	
	

}
