package Extentreports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class IntegrationClass {
	
		public static ExtentReports extentreports;
		public static ExtentTest extentTest;
		WebDriver driver;

		@BeforeMethod
		public void LaunchAPP(ITestContext context) throws InterruptedException {
			driver = new ChromeDriver();
			driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			driver.manage().window().maximize();
			Thread.sleep(1000);
			extentTest= extentreports.createTest(context.getName());

		}

		@AfterMethod
		public void Close() {
			driver.quit();
		}
		@Test(dataProvider = "TestData") 
		public void login(String Username ,String Password) throws InterruptedException{
			extentTest.info("passing details");
			driver.findElement(By.name("username")).sendKeys(Username);//username
			driver.findElement(By.name("password")).sendKeys(Password);
			driver.findElement(By.cssSelector(".orangehrm-login-button")).click();	
			extentTest.pass("Login succesful");
			Thread.sleep(1000);
		}

		@DataProvider(name="TestData")
		public  Object[][] Logindata () {
			Object[][] data =new Object[2][2];
			data[0][0]="Admin";
			data[0][1]="admin123";

			data[1][0]="admin";
			data[1][1]="admin123";
			return data;
		}

		@BeforeSuite
		public void IntialiseExtentreports()
		
		{ 
			ExtentSparkReporter Sparkreporter_all=new ExtentSparkReporter("alltest.html");
			extentreports = new ExtentReports();
			extentreports.attachReporter(Sparkreporter_all);
			extentreports.setSystemInfo("os",System.getProperty("os.name"));
			extentreports.setSystemInfo("java version",System.getProperty("java.version"));


		}
		@AfterSuite
		public void flush() throws IOException {
			extentreports.flush();
			Desktop.getDesktop().browse(new File("alltest.html").toURI());//creating file by relativepath
		}

	}
