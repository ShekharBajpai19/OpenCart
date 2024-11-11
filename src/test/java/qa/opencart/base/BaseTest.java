package qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import qa.opencart.factory.DriverFactory;
import qa.opencart.pages.AccountsPage;
import qa.opencart.pages.LoginPage;
import qa.opencart.pages.ProductInfoPage;
import qa.opencart.pages.RegistrationPage;
import qa.opencart.pages.SearchPage;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage lgnpge;
	protected AccountsPage accPage;
	protected SearchPage searchPage;
	protected ProductInfoPage piPage;
	protected RegistrationPage registrationPage;
	
	protected SoftAssert softAssert;
	
	@BeforeTest
	public void setup()
	{
		df = new DriverFactory();
		prop = df.initProp(); 
		driver = df.initDriver(prop);	
		lgnpge = new LoginPage(driver); 
		
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
}
