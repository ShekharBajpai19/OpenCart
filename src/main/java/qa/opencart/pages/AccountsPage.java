package qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.opencart.constants.AppConstants;
import qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logOutLink = By.linkText("Logout");
	private By orderHistoryLink = By.linkText("Order History");
	private By searchBar = By.name("search");
	private By accountHeaders =  By.cssSelector("div#content h2");
	private By searchIcon = By.cssSelector("button.btn.btn-default.btn-lg");
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getAccountPageTitle()
	{
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
		System.out.println("Accounts page title :: "+title);
		return title;
	}
	
	public boolean isLogOutLinkExist()
	{
		return	eleUtil.waitForElementVisible(logOutLink, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	public boolean searchBarExist()
	{
//		return driver.findElement(searchBar).isDisplayed();
		return	eleUtil.waitForElementVisible(searchBar, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	public List<String> getAccountPageHeaders()
	{
		List<WebElement> headers = eleUtil.waitForElementsPresence(accountHeaders, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> accHeaderList = new ArrayList<String>();
		for(WebElement e : headers)
		{
			String text = e.getText();
			accHeaderList.add(text);
		}
		return accHeaderList;
	}

	public SearchPage performSearch(String searchKey)
	{
		if(searchBarExist())
		{
			eleUtil.doSendKeys(searchBar, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
		}
		else
		{
			System.out.println("*****Search field not present on the page*****");
			return null;
		}
			
	}
	
}
