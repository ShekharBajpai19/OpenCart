package qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import qa.opencart.constants.AppConstants;
import qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1: Private By locators
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotPassword = By.linkText("Forgotten Password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By registrationLink = By.linkText("Register");
	
	//2: Page Constant
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	//3: Page actions/Methods
	@Step("...getting the Login page title...")
	public String getLoginPageTitle()
	{
		String title = eleUtil.waitForTitleContainsAndFetch(5, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Login Page Title ::"+title);
		return title;
	}
	
	@Step("...getting the Login page URL...")
	public String getLoginPageURL()
	{
		String url = eleUtil.waitForURLContainsAndFetch(5, AppConstants.LOGIN_PAGE_FRACTION_URL);
		System.out.println("Login Page URL ::"+url);
		return url;
	}
	
	@Step("...getting the forgot pwd link...")
	public boolean isForgotPwdLinkExist()
	{
		return eleUtil.waitForElementVisible(forgotPassword, 5).isDisplayed();
	}
	
	@Step("...Login with username : {0} and password: {1}...")
	public AccountsPage doLogin(String un, String pwd)
	{	
		eleUtil.waitForElementVisible(emailID, 5).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		
		return new AccountsPage(driver);
	}
	
	public RegistrationPage navigateToRegisterPage()
	{
		eleUtil.doClick(registrationLink);
		return new RegistrationPage(driver);
	}
}
