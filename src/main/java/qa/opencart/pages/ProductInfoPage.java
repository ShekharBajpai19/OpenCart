package qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.opencart.constants.AppConstants;
import qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil ele;
	
//*********By Locators*************//	
	private By productHeader = By.tagName("h1");
	private By images = By.cssSelector("ul.thumbnails img");
	private By productMetaData= By.xpath("//div[@class='col-sm-4']//ul[@class='list-unstyled'][1]/li");
	private By productPriceData= By.xpath("//div[@class='col-sm-4']//ul[@class='list-unstyled'][2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By cartSuccessMsg = By.cssSelector(".alert.alert-success");
	
	private Map<String, String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		ele = new ElementUtil(driver);
	}

	public String getProductHeadervalue()
	{
		String productHeaderValue = ele.doElementGetText(productHeader);
		return productHeaderValue;
	}
	
	public int getProductImagesCount()
	{
		int count=ele.getElements(images).size();
		return count;
	}
	
	public Map<String, String> getProductInfo()
	{
		productInfoMap = new HashMap<String, String>();
		
		//header:
		productInfoMap.put("productname", getProductHeadervalue());
		getMetaData();
		getPriceData();
		System.out.println(productInfoMap);
		return productInfoMap;
	}
	
	private void getMetaData()
	{
		//header:
		productInfoMap.put("productname", getProductHeadervalue());
		
		//metadata:
		List<WebElement> metaList =  ele.getElements(productMetaData);
		for(WebElement e:metaList)
		{
			String meta= e.getText();
			String metaInfo[]=meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
		}
	}
//Fetching price data	
	private void getPriceData()
	{
		//pricedata:
				List<WebElement> priceList =  ele.getElements(productPriceData);
				String price = priceList.get(0).getText();
				String exTax = priceList.get(1).getText();
				String exTaxValue = exTax.split(":")[1].trim();
				
				productInfoMap.put("productprice", price);
				productInfoMap.put("exTax", exTaxValue);	
	}
	
	public void enterQuantity(int qty)
	{
		ele.doSendKeys(quantity, String.valueOf(qty));
	}
	
	public String addProductToCart()
	{
		ele.doClick(addToCartBtn);
		String successMsg = ele.waitForElementVisible(cartSuccessMsg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		return successMsg;
	}
	
}
