package Extentreports;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Systemproperties {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		Capabilities capabilities=((RemoteWebDriver)driver).getCapabilities();
		System.out.println(capabilities.getBrowserName());
		System.out.println(capabilities.getBrowserVersion());
		System.getProperties().list(System.out);
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("java.version"));


		driver.close();
	}

}
