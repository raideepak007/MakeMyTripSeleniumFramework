package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchBus {
	
	private static WebElement element = null;

	public static WebElement bus(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[text()='Buses']"));
		return element;
	}
	
	public static WebElement from(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[text()='From']"));
		return element;
	}

	public static WebElement fromData(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@placeholder='From']"));
		return element;
	}

	public static WebElement fromDataClick(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[@class='sr_city blackText']"));
		return element;
	}
	
	public static WebElement to(WebDriver driver) {
		element = driver.findElement(By.xpath("//label[@for='toCity']"));
		return element;
	}

	public static WebElement toData(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@placeholder='To']"));
		return element;
	}

	public static WebElement toDataClick(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[@class='sr_city blackText']"));
		return element;
	}
	
	public static WebElement searchbtn(WebDriver driver) {
		element = driver.findElement(By.xpath("//button[@id='search_button']"));
		return element;
	}

}
