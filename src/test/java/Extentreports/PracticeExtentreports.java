package Extentreports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeExtentreports extends BaseforScreenshot {
	static WebDriver driver; 
	public static void main(String args[]) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.google.com");
		Capabilities capabilities=((RemoteWebDriver)driver).getCapabilities();
		ExtentReports extentReports=new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report.html");
		System.out.println(capabilities.getBrowserName());
		System.out.println(capabilities.getBrowserVersion());

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("this is document title");
		sparkReporter.config().setReportName("This is report title");
		sparkReporter.config().setCss(".badge-primary{background-color:#e25d1b;}");
		sparkReporter.config().setTimeStampFormat("dd-MM-yyyy hh-mm-ss");
		sparkReporter.config().setJs("document.getElementsByClassName('logo')[0].style.display='none';");

		extentReports.setSystemInfo("os",System.getProperty("os.name"));
		extentReports.setSystemInfo(" browser name and verison",capabilities.getBrowserName()+ capabilities.getBrowserVersion());
		//extentReports.setSystemInfo("os name",System.getProperty("os.version"));
		extentReports.setSystemInfo("java version",System.getProperty("java.version"));
		extentReports.setSystemInfo("App URL",driver.getCurrentUrl());


		extentReports.attachReporter(sparkReporter);

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
