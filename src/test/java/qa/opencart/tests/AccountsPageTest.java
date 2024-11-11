package qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.opencart.base.BaseTest;
import qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accountsPageSetup()
	{
		accPage = lgnpge.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void accountPageTitleTest()
	{
		String actualTitle = accPage.getAccountPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void isLogOutLinkExistTest()
	{
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}
	
	@Test
	public void searchBarExistTest()
	{
		Assert.assertTrue(accPage.searchBarExist());
	}
	 
	@Test
	public void countAccountPageHeadersTest()
	{
		List<String> headersList=accPage.getAccountPageHeaders();
		System.out.println(headersList);
		Assert.assertEquals(headersList.size(), AppConstants.ACCOUNT_PAGE_HEADER_COUNT);
	}
	
	@Test
	public void accountPageHeadersNameTest()
	{
		List<String> headersList=accPage.getAccountPageHeaders();
		Assert.assertEquals(headersList, AppConstants.ACCOUNT_PAGE_HEADERS);
	}
	
	@DataProvider
	public Object [][] getProductData()
			{
			return new Object[][] 
					{
						{"MacBook"},{"iMac"},{"Apple"},{"Samsung"}
					};
			};
	
	@Test(dataProvider="getProductData")
	public void searchProductCountTest(String searchKey)
	{
		searchPage = accPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchProductsCount()>0);
	}
	
	@DataProvider
	public Object [][] getProductTestData()
			{
			return new Object[][] 
					{
						{"MacBook", "MacBook Pro"},{"iMac","iMac"},{"Apple","Apple Cinema 30\""},{"Samsung","Samsung Galaxy Tab 10.1"}
					};
			};
	@Test(dataProvider="getProductTestData")
	public void searchProductTest(String searchKey, String productName)
	{
		searchPage = accPage.performSearch(searchKey);
		if(searchPage.getSearchProductsCount()>0)
		{
			piPage = searchPage.selectProduct(productName);
			String actProductHeader = piPage.getProductHeadervalue();
			Assert.assertEquals(actProductHeader, productName);
		}		
	}
}
