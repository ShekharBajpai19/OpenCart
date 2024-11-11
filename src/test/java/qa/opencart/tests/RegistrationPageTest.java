package qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.opencart.base.BaseTest;
import qa.opencart.constants.AppConstants;
import qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void registrationPageSetup()
	{
		registrationPage = lgnpge.navigateToRegisterPage();
	}
	
	public String getRandomEmail()
	{
		String email = "Automation"+System.currentTimeMillis()+"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getRegTestData()
	{
		Object [][] regData =ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider="getRegTestData")
	public void userRegTest(String firstName, String lastName,  String telephone, String password, String subscribe)
	{
		Assert.assertTrue(registrationPage.registerUser(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	}
}
