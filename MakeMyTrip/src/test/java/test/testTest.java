package test;

import static org.testng.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import page.SearchTrain;
import page.SearchBus;
import page.Holidays;
import utils.ExcelUtils;

public class testTest {
	private static WebDriver driver = null;
	String projectPath = System.getProperty("user.dir");
	String excelPath = projectPath+"/excel/data.xlsx";
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	int WAITINGTIME =8;

	@BeforeSuite
	public void setup()
	{
		htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@BeforeTest
	public void setupTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\driver\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.makemytrip.com");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0);
		actions.moveByOffset(10, 10).click().build().perform();
	}

	@Test(dataProvider = "testdata1", enabled=true, priority = 1)
	public void searchTour(String From, String To, String ClassType) throws InterruptedException {
		ExtentTest test = extent.createTest("Search Train","To check the functionality of Train Option");
		test.log(Status.INFO, "Search Train into MakeMyTrip");
		SearchTrain.train(driver).click();
		test.log(Status.INFO, "Click On Train Option");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		assertTrue(driver.getTitle().contains("IRCTC Train Tickets Reservation | Indian Railways Reservation | IRCTC Train Tickets Booking, Trains Enquiry Online : MakeMyTrip"));
		test.log(Status.INFO, "Verify Train Title");
		test.log(Status.INFO, "FromSource and ToSource Details");
		fromAndTo(From,To);
		SearchTrain.travelfor(driver).click();
		test.log(Status.INFO, "Click On Coach Type");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		WebElement wb = driver.findElement(By.xpath("//ul//li[text()='" + ClassType + "']"));
		Actions mouse = new Actions(driver);
		mouse.clickAndHold(SearchTrain.travelfor(driver)).moveToElement(wb).click().build().perform();
		test.log(Status.INFO, "Clicked Coach");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		SearchTrain.searchbtn(driver).click();
		test.log(Status.INFO, "Click on Search Button");
		test.pass("Test passed");
	}

	@Test(dataProvider = "testdata2" , enabled=true, priority = 2)
	public void searchPnr(String pnr) throws InterruptedException {
		ExtentTest test = extent.createTest("Search Pnr Status","To check the functionality of TrainPnr Option");
		test.log(Status.INFO, "Search Pnr Status into MakeMyTrip");
		SearchTrain.train(driver).click();
		test.log(Status.INFO, "Click On Train Option");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		SearchTrain.checkpnr(driver).click();
		test.log(Status.INFO, "Click On CheckPnr Option");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		assertTrue(driver.getTitle().contains("IRCTC Train PNR Status Enquiry, Check PNR Status Online - MakeMyTrip"));
		test.log(Status.INFO, "Verify CheckPnrStatus Title");
		SearchTrain.pnrdigit(driver).click();
		test.log(Status.INFO, "Click On Pnr Digit Text");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		SearchTrain.pnrdigit(driver).sendKeys(pnr);
		test.log(Status.INFO, "Write Pnr Digit From Excel");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		SearchTrain.checkpnrstatus(driver).click();
		test.log(Status.INFO, "Click on CheckPnrStatus Button");
		test.pass("Test passed");

	}

	@Test(dataProvider = "testdata3", enabled=true, priority = 3)
	public void searchbus(String From, String To) throws InterruptedException {
		ExtentTest test = extent.createTest("Search Buses","To check the functionality of Buses Option");
		test.log(Status.INFO, "Search Buses into MakeMyTrip");
		SearchBus.bus(driver).click();
		test.log(Status.INFO, "Click On Buses Option");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		assertTrue(driver.getTitle().contains("Online Bus Ticket Booking, Book Confirmed Reservation Tickets @ MakeMyTrip"));
		test.log(Status.INFO, "Verify Bus Title");
		test.log(Status.INFO, "FromCity And ToCity Details");
		fromAndTo(From,To);
		SearchBus.searchbtn(driver).click();
		test.log(Status.INFO, "Click on Search Button");
		test.pass("Test passed");
	}

	@Test(dataProvider = "testdata4", enabled=true, priority = 4)
	public void holidays(String From, String To) throws InterruptedException
	{
		ExtentTest test = extent.createTest("Search Holidays","To check the functionality of Holidays Option");
		test.log(Status.INFO, "Search Holidays into MakeMyTrip");

		Holidays.holidays(driver).click();
		test.log(Status.INFO, "Click On Holidays Option");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		assertTrue(driver.getTitle().contains("Holiday Packages, Indian Holidays, Honeymoon Packages, India Tourism, Holidays India, Vacation Package : MakeMyTrip"));
		test.log(Status.INFO, "Verify Holidays Title");
		test.log(Status.INFO, "FromCity and ToCity/Country Details");
		Holidays.to(driver).click(); 
		test.log(Status.INFO,"Click On To City Option");
	    driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
	    Holidays.toData(driver).sendKeys(To);
	    test.log(Status.INFO,"Write CityName in City Option From Excel"); Thread.sleep(2000);
		Holidays.toDataClick(driver).click(); 
		test.log(Status.INFO,"Click On Filter City name");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		Holidays.from(driver).click(); test.log(Status.INFO,"Click On From City Option");
		Holidays.fromData(driver).sendKeys(From);
		test.log(Status.INFO, "Write CityName in City Option From Excel");
	    Thread.sleep(2000); Holidays.fromDataClick(driver).click();
	    test.log(Status.INFO, "Click On Filter City name");
	    driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS); 
		Holidays.searchbtn(driver).click();
		test.log(Status.INFO, "Click on Search Button");
		test.pass("Test passed");
	}

	@AfterTest
	public void tearDownTest() {
		driver.close();
	}

	@AfterSuite
	public void tearDown() {
		extent.flush();
	}

	public void waitForElement()
	{
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
	}

	public void fromAndTo(String From, String To) throws InterruptedException
	{

		SearchBus.to(driver).click();
		SearchBus.toData(driver).sendKeys(To);
		Thread.sleep(2000);
		SearchBus.toDataClick(driver).click();
		waitForElement();
		SearchBus.from(driver).click();
		SearchBus.fromData(driver).sendKeys(From);
		Thread.sleep(2000);
		SearchBus.fromDataClick(driver).click();
		waitForElement();
	}

	@DataProvider(name = "testdata1")
	public Object[][] getDataofTour() {
		Object data[][] = testData(excelPath, "Sheet2");
		return data;
	}

	@DataProvider(name = "testdata2")
	public Object[][] getDataofpnr() {
		Object data[][] = testData(excelPath, "Sheet3");
		return data;
	}

	@DataProvider(name = "testdata3")
	public Object[][] getDataofbus() {
		Object data[][] = testData(excelPath, "Sheet4");
		return data;
	}

	@DataProvider(name = "testdata4")
	public Object[][] getDataofholidays() {
		Object data[][] = testData(excelPath, "Sheet5");
		return data;
	}

	public static Object[][] testData(String excelPath, String sheetName) {
		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);
		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();

		Object data[][] = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String cellData = excel.getCellDataString(i, j);
				// System.out.print(cellData+" | ");
				data[i - 1][j] = cellData;
			}
			System.out.println();
		}
		return data;

	}

}
