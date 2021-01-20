package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchTrain {

	private static WebElement element = null;

	public static WebElement train(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[text()='Trains']"));
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
		element = driver.findElement(By.xpath("//span[text()='To']"));
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

	public static WebElement traveldate(WebDriver driver) {
		element = driver.findElement(By.xpath("//div//input[@id='travelDate']"));
		return element;
	}

	public static WebElement travelfor(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[text()='Class']"));
		return element;
	}

	public static WebElement travelClass(WebDriver driver) {
		element = driver.findElement(By.xpath("//ul//li[text()='Third AC']"));
		return element;
	}

	public static WebElement searchbtn(WebDriver driver) {
		element = driver.findElement(By.xpath("//a[text()='Search']"));
		return element;
	}
	
	public static WebElement checkpnr(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[text()='CHECK PNR STATUS']"));
		return element;
	}
	
	public static WebElement pnrdigit(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@id='pnr']"));
		return element;
	}
	
	public static WebElement checkpnrstatus(WebDriver driver) {
	element = driver.findElement(By.xpath("//a[text()='CHECK STATUS']"));
		return element;
	}

}
