package Extentreports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ReportsforFailedTests extends BaseforScreenshot{
	static WebDriver driver; 
	public static void main(String args[]) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.google.com");
		ExtentReports extentReports=new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Alltests.html");

		ExtentSparkReporter Passedtests = new ExtentSparkReporter("PAssedtests.html");
		Passedtests.filter().statusFilter().as(new Status[] {Status.PASS}).apply();

		ExtentSparkReporter failedtests = new ExtentSparkReporter("failedtests.html");
		failedtests.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();

		ExtentSparkReporter skippedtests = new ExtentSparkReporter("skippedtests.html");
		skippedtests.filter().statusFilter().as(new Status[] {
				Status.SKIP,
				Status.WARNING
		}).apply();


		//Attaching the spark reporter to extent reporter
		extentReports.attachReporter(sparkReporter,failedtests,skippedtests,Passedtests);

		//passed test
		extentReports.createTest("Screenshot1","this is desc for screenshot1")
		.info("this is info msg")
		.pass("passed test") 
		.addScreenCaptureFromBase64String(Capture(driver));

		//failed test
		extentReports.createTest("Screenshot1","this is desc for screenshot1")
		.info("this is info msg")
		.fail("failedtest");

		//skipped test
		extentReports.createTest("Screenshot2","this is desc for screenshot2")
		.info("this is info msg")
		.skip("this is skipped test")
		.addScreenCaptureFromBase64String(Capture(driver),"Google homapage");

		//warning test
		extentReports.createTest("Screenshot2","this is desc for screenshot1")
		.info("this is info msg")
		.warning("warnung test case")
		.addScreenCaptureFromBase64String(Capture(driver));

		//adding different attributes to the test (Author , Category , Device )
		extentReports.createTest("Screenshot2","this is desc for screenshot2")
		.assignAuthor("Shravan")
		.assignCategory("Test")
		.assignDevice("Self")
		.info("this is info msg")
		.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(Capture(driver),"Google homapage").build())
		.addScreenCaptureFromBase64String(Capture(driver),"Google homapage");


		extentReports.flush();
		driver.close();
		//creating file by relative path opening the reports for all different tests mentioned above
		Desktop.getDesktop().browse(new File("Alltests.html").toURI());
		Desktop.getDesktop().browse(new File("Passedtests.html").toURI());
		Desktop.getDesktop().browse(new File("failedtests.html").toURI());
		Desktop.getDesktop().browse(new File("skippedtests.html").toURI());
	}

}




