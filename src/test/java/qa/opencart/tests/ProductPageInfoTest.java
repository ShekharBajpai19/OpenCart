package qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest {

	@BeforeClass
	public void productsInfoPageSetup()
	{
		accPage = lgnpge.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
	}
	
	@DataProvider
	public Object [][] getProductTestData()
			{
			return new Object[][] 
					{
						{"MacBook", "MacBook Pro", 4},{"iMac","iMac", 3},{"Apple","Apple Cinema 30\"",6},{"Samsung","Samsung Galaxy Tab 10.1", 7}
					};
			};
	@Test(dataProvider="getProductTestData")
	public void productImagesCountTest(String searchKey, String productName, int expectedImageCount)
	{
		searchPage=accPage.performSearch(searchKey);
		piPage=searchPage.selectProduct(productName);
		int actualImageCount =piPage.getProductImagesCount();
		Assert.assertEquals(actualImageCount, expectedImageCount);
	}
	
	@Test
	public void productInfoTest()
	{
		searchPage=accPage.performSearch("Macbook");
		piPage=searchPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = piPage.getProductInfo();
		
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("productprice"), "$2,000.00");

		softAssert.assertAll();
	}
	
	@DataProvider
	public Object [][] getAddToCartTestData()
			{
			return new Object[][] 
					{
						{"MacBook", "MacBook Pro", 2},{"iMac","iMac", 3},{"Samsung","Samsung Galaxy Tab 10.1", 5}
					};
			};
	@Test(dataProvider="getAddToCartTestData")
	public void addToCartTest(String searchKey, String productName, int quantity)
	{
		searchPage=accPage.performSearch(searchKey);
		piPage=searchPage.selectProduct(productName);
		piPage.enterQuantity(quantity);
		String actualMessage = piPage.addProductToCart();
		
		softAssert.assertTrue(actualMessage.contains("Success"));
		softAssert.assertTrue(actualMessage.contains(productName));
		
		//Assert.assertEquals(actualMessage, "Success: You have added "+productName+" to your shopping cart!");
	}
	
}
