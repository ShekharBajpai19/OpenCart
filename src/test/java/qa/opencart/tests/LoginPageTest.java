package qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import qa.opencart.base.BaseTest;
import qa.opencart.constants.AppConstants;


@Epic("EPIC ID :: 101")
@Story("User Login :: Login Page Features")
public class LoginPageTest extends BaseTest{
	
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("<<Getting title of the page>>")
	@Test
	public void loginPageTitleTest()
	{
		String actualLogin= lgnpge.getLoginPageTitle();
		Assert.assertEquals(actualLogin, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	
	@Severity(SeverityLevel.MINOR)
	@Description("<<Getting URL of the page>>")
	@Test
	public void loginPageUrlTest()
	{
		String actualURL = lgnpge.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("<<Checking Forget Password link exist>>")
	@Test
	public void forgotPwdLinkExistTest	()
	{
		Assert.assertTrue(lgnpge.isForgotPwdLinkExist());
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("<<Checking user is able to login with correct credentials>>")
	@Test
	public void loginTest()
	{
		accPage = lgnpge.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}
	
}