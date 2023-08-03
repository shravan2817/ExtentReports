package Extentreports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Configuration {

	static WebDriver driver; 
	public static void main(String args[]) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.google.com");
		ExtentReports extentReports=new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report.html");
		//Configuring the Dash-board extent report with ViewName Enum
		sparkReporter.viewConfigurer().viewOrder().as(new ViewName[]{
			ViewName.TEST,
			ViewName.EXCEPTION,
			ViewName.CATEGORY,
			ViewName.DEVICE,
			ViewName.DASHBOARD
			
			
		}).apply();
		//Attaching the spark reporter to extent reporter
		extentReports.attachReporter(sparkReporter);

		extentReports.createTest("Screenshot1","this is desc for screenshot1")
		.info("this is info msg")
		.addScreenCaptureFromBase64String(Capture(driver));

		extentReports.createTest("Screenshot2","this is desc for screenshot2")
		.info("this is info msg")
		.addScreenCaptureFromBase64String(Capture(driver),"Google homapage");

		extentReports.createTest("Screenshot2","this is desc for screenshot1")
		.info("this is info msg")
		.addScreenCaptureFromBase64String(Capture(driver));

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



	public static String Capture(WebDriver driver) {
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		String Base64code=scrShot.getScreenshotAs(OutputType.BASE64);
		System.out.println("Screemshot captured");
		return Base64code;
	}





	public static String Screenshot1(String fileName) {

		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File pic=new File("./Screenshots/"+fileName);
		try {
			FileUtils.copyFile(SrcFile, pic);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screemshot captured");
		return pic.getAbsolutePath();


	}




}

