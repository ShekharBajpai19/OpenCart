package qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qa.opencart.constants.AppConstants;
import qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil ele;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value='0']");
	private By policyCheckbox = By.xpath("//input[@name='agree']");
	private By continueButton = By.xpath("//input[@type='submit']");
	
	private By successMsg = By.xpath("//div[@id='content']/h1");
	private By logoutLink = By.linkText("Logout");
	private By registrationLink = By.linkText("Register");
	
	public RegistrationPage(WebDriver driver)
	{
		this.driver = driver;
		ele = new ElementUtil(driver);
	}
	
	public boolean registerUser (String firstName, String lastName, String email, String telephone, String password, String subscribe)
	{
		ele.waitForElementVisible(this.firstName, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(firstName);
		ele.doSendKeys(this.lastName, lastName);
		ele.doSendKeys(this.telephone, telephone);
		ele.doSendKeys(this.email, email);
		ele.doSendKeys(this.password, password);
		ele.doSendKeys(confirmPassword, password);
		if(subscribe.equalsIgnoreCase("yes"))
		{
			ele.doClick(subscribeYes);
		}
		else
		{
			ele.doClick(subscribeNo);
		}
		ele.doClick(policyCheckbox);
		ele.doClick(continueButton);
		
		String scsMsg = ele.waitForElementVisible(successMsg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		System.out.println("SUCCESS MESSAGE :: "+scsMsg);
		
		if(scsMsg.contains(AppConstants.USER_REG_SUCCESS_MSG))
		{
			ele.doClick(logoutLink);
			ele.doClick(registrationLink);
			return true;
		}
		return false;
	}
	
}
