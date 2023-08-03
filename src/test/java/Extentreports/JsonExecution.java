package Extentreports;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class JsonExecution extends BaseforScreenshot {

	static WebDriver driver; 
	public static void main(String args[]) throws IOException {
		ExtentReports extentReports=new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report.html");

		//loading with json document from src/test/resources/j.son-config
		sparkReporter.loadJSONConfig(new File("./src/test/resources/J.son.config"));

		extentReports.attachReporter(sparkReporter);


		WebDriverManager.chromedriver().setup();
		driver =new ChromeDriver();
		driver.get("https://www.google.com/");


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
		Desktop.getDesktop().browse(new File("report.html").toURI());//creating file by relativepath
	}
}




