package Extentreports;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BaseforScreenshot {
	static WebDriver driver;
	public static String Capture(WebDriver driver1) {
		TakesScreenshot scrShot =((TakesScreenshot)driver1);
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
