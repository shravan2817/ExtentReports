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
	import io.github.bonigarcia.wdm.WebDriverManager;

public class Xmlexecution {
		static WebDriver driver; 
		public static void main(String args[]) throws IOException {
			ExtentReports extentReports=new ExtentReports();
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report.html");
			//Loading with xml file from src/test/resources/xmlexecution
			
	sparkReporter.loadXMLConfig(new File("./src/test/resources/xmlfile.config"));
			
			extentReports.attachReporter(sparkReporter);
			

			WebDriverManager.chromedriver().setup();
			driver =new ChromeDriver();
			driver.get("https://www.google.com/");
//			String Base64code=Capture(); 
//			String Screenshot1=Capture("google.jpg");


			extentReports.createTest("Screenshot1","this is desc for screenshot1")
			.info("this is info msg")
			.addScreenCaptureFromBase64String(Capture());
			
			extentReports.createTest("Screenshot2","this is desc for screenshot2")
			.info("this is info msg")
			.addScreenCaptureFromBase64String(Capture(),"Google homapage");
			
			extentReports.createTest("Screenshot2","this is desc for screenshot1")
			.info("this is info msg")
			.addScreenCaptureFromBase64String(Capture());
			
			extentReports.createTest("Screenshot2","this is desc for screenshot2")
			.assignAuthor("Shravan")
			.assignCategory("Test")
			.assignDevice("Self")
			.info("this is info msg")
			.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(Capture(),"Google homapage").build())
			.addScreenCaptureFromBase64String(Capture(),"Google homapage");


			extentReports.flush();
			driver.close();
			Desktop.getDesktop().browse(new File("report.html").toURI());//creating file by relativepath
		}
		public static String Capture() {
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



