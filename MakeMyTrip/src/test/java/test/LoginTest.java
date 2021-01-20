package test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
import page.MakeMyTripLogin;
import utils.ExcelUtils;

public class LoginTest {

	private static WebDriver driver = null;
	String projectPath = System.getProperty("user.dir");
	String excelPath = projectPath+"/excel/data.xlsx";
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	int WAITINGTIME =8;

	@BeforeSuite
	public void setup()
	{
		htmlReporter = new ExtentHtmlReporter("login.html");
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

	@Test(dataProvider = "testdata", enabled=true)
	public void log_inTest(String username, String password) throws InterruptedException {
		ExtentTest test = extent.createTest("log_inTest","To check the functionality of Login");
		test.log(Status.INFO, "Login into MakeMyTrip");
		MakeMyTripLogin.login(driver).click();
		test.log(Status.INFO, "Click on Login Button");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		driver.switchTo().activeElement();
		test.log(Status.INFO, "Switch on Login PopUp");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		MakeMyTripLogin.email_id(driver).sendKeys(username);
		test.log(Status.INFO, "Take username From excel");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		MakeMyTripLogin.submit_btn(driver).click();
		test.log(Status.INFO, "Click on Continue Button");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		MakeMyTripLogin.password(driver).sendKeys(password);
		test.log(Status.INFO, "Take Password from excel");
		driver.manage().timeouts().implicitlyWait(WAITINGTIME, TimeUnit.SECONDS);
		MakeMyTripLogin.login_btn(driver).click();
		test.log(Status.INFO, "Click on Submitt Button");
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
	
	@DataProvider(name = "testdata")
	public Object[][] getDataofUser() {
		Object data[][] = testData(excelPath, "Sheet1");
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
