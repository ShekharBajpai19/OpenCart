package qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class DriverFactory {

	//public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method is initializing the driver on the basis of given browser driver
	 * @param browsername
	 * @return This method returns the driver
	 */
	public WebDriver initDriver(Properties prop) {
		
		optionsManager = new OptionsManager(prop);
		
		highlight=prop.getProperty("highlight").trim();
		String browsername=prop.getProperty("browser").toLowerCase().trim();
		
		System.out.println("Browser name is::" + browsername);

		if(browsername.equalsIgnoreCase("chrome"))
		{
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			// driver = new FirefoxDriver(optionsManager.getFirfoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirfoxOptions()));
		}
		else if(browsername.equalsIgnoreCase("edge"))
		{
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		}
		else
		{
			System.out.println("Enter valid driver name . . . . ");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();		
}
	
	public synchronized static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	/**
	 * This method is reading the properties from the .properties file.
	 * @return
	 */
	public Properties initProp() {
		prop=new Properties();
		try {
			FileInputStream ip =  new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	//Take screenshot
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File (path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}