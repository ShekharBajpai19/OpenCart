package qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.opencart.constants.AppConstants;
import qa.opencart.utils.ElementUtil;

public class SearchPage {

	private WebDriver driver;
	private ElementUtil ele;
	
	private By SearchProductsCount = By.cssSelector(".product-layout.product-grid.col-lg-3.col-md-3.col-sm-6.col-xs-12");
	
	public SearchPage(WebDriver driver) {
	this.driver = driver;
	ele = new ElementUtil(driver);
	}
	
	/*
	 * public void getProductsListed(String productName) { By productsList =
	 * By.xpath("//h4/a[contains(@href,'"+productName+"')]");
	 * List<WebElement>products = ele.waitForElementsVisible(productsList,
	 * AppConstants.DEFAULT_MEDIUM_TIME_OUT); }
	 */
	
	public int getSearchProductsCount()
	{
		int count = ele.waitForElementsVisible(SearchProductsCount, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		return count;
	}
	
	public ProductInfoPage selectProduct(String productName)
	{
		By productLocator = By.linkText(productName);
		ele.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);				
	}
	
	
}
